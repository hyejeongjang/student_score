package java_project;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java_project.*;


public class StudentsInformation extends JFrame{
	private static ArrayList<Student> students=new ArrayList<Student>(); // 수강생의 정보를 동적배열로 생성
	private JLabel[] info_la=new JLabel[5]; // 학생정보 부분의 라벨의 배열
	private JTextField[] info_tf=new JTextField[4]; // 이름 text field
	private JLabel la=new JLabel(" "); // 정보 저장 여부를 확인
	private static String name, major, stdid, tel; // 이름, 학번 , 학과 전화번호
	private int totalscore; // 전체 성적
	private static String sub1, sub2, sub3; // 과목 이름
	private static int sub1_mid, sub1_fin, sub2_mid, sub2_fin, sub3_mid, sub3_fin, rank; // 3과목의 중간 기말 점수
	private String [] department= {"컴퓨터학과", "수학과", "화학과"};
	private boolean output=false; // 학생 정보를 불러내면 true를 저장
	private DataInput p2;
	private Panel3 p3;

	public StudentsInformation(Color clr) {
		setTitle("Students Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p1=new JPanel();
		p1.setLayout(null);
		p1.setBackground(clr);


		try {
			File file=new File("std_data.txt");
			Scanner s=new Scanner(file);


			while(s.hasNext()) {
				name=s.next(); 
				stdid=s.next(); 
				major=s.next(); 
				tel=s.next();
				sub1=s.next();
				sub1_mid=s.nextInt();
				sub1_fin=s.nextInt();
				sub2=s.next();
				sub2_mid=s.nextInt();
				sub2_fin=s.nextInt();
				sub3=s.next();
				sub3_mid=s.nextInt();
				sub3_fin=s.nextInt();
				totalscore=sub1_mid+sub1_fin+sub2_mid+sub2_fin+sub3_mid+sub3_fin;


				// 파일을 읽어서 각각의 세트를 Arraylist에 저장
				students=addStudents(name,totalscore, stdid, major, tel, sub1, sub1_mid, sub1_fin, sub2, sub2_mid, sub2_fin, sub3, sub1_mid, sub3_fin, rank);
				System.out.print(name+"\t"+totalscore+"\t"+stdid+"\t"+ major+ "\t" +tel+"\t"+sub1+"\t"+sub1_mid+"   "+sub1_fin+"  \t|\t"+sub2+"\t"+sub2_mid+"   "+sub2_fin+"  \t|\t"+sub3+"\t"+sub3_mid+"   "+sub3_fin+"\n");
			}
			s.close();
		} catch(IOException e){
			System.out.println("File not found.");
		}

		// 등수 내림차순 정렬
		Collections.sort(students);
		for(int i=0;i<students.size();i++) {
			students.get(i).setRank(i+1);
		}


		info_la[0]=new JLabel("[수강생 정보]") ; // [수강생 선택] 라벨 생성 및 붙이기
		info_la[0].setLocation(40, 20);
		info_la[0].setSize(120,20);
		p1.add(info_la[0]);

		info_la[1]=new JLabel("이름"); // textFiel옆의 라벨
		info_la[2]=new JLabel("학번");
		info_la[3]=new JLabel("학과");
		info_la[4]=new JLabel("전화번호");


		for(int i=1;i<4;i++) { // 이름 학번 학과 label 부착
			info_la[i].setLocation(70, 20+40*i);
			info_la[i].setSize(60,20);
			p1.add(info_la[i]);
		}

		for(int i=0;i<2;i++) { 				// 이름 학번 textfield 부착
			info_tf[i]=new JTextField(10);  // tf의 각 TextField 객체 생성 
			info_tf[i].setLocation(150, 60+40*i);
			info_tf[i].setSize(90,20);
			p1.add(info_tf[i]);
		}

		JComboBox<String> info_com=new JComboBox<String>(); // 학과 선택 콤보박스 생성
		for(int i=0;i<department.length;i++) {
			info_com.addItem(department[i]);
		}
		info_com.setLocation(150, 140);
		info_com.setSize(90,20);
		p1.add(info_com);


		// 콤보박스에 이벤트 리스너 달기
		// 콤보박스에서 선택된 학과의 String이 depart에 저장
		info_com.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb=(JComboBox<String>)e.getSource();
				int index=cb.getSelectedIndex();
				major=department[index];
			}
		});


		// 저장된 학생 정보인지를 확인하는 label
		la.setLocation(280, 180);
		la.setFont(new Font("고딕체", Font.BOLD, 22));
		la.setSize(280,40);
		p1.add(la);


		JButton search_btn=new JButton("확인");
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=info_tf[0].getText();

				for(int i=0;i<students.size();i++) {
					// text 파일에 학생의 이름 학번 학과가 같은 학생이 있으면 저장된 전화번호를 띄움
					if((info_tf[0].getText()).equals(students.get(i).getName()) && (info_tf[1].getText()).equals(students.get(i).getStdid()) && department[(info_com.getSelectedIndex())].equals(students.get(i).getMajor())) {
						info_tf[3].setText(students.get(i).getTel());
						output=true;
					}
				}
				// 저장된 학생 데이터면 개인 성적 확인 패널 생성
				if(output) {
					la.setText("저장된 학생 데이터");
					p3=new Panel3(name, clr);
				} else { // 저장된 학생 데이터가 아니면 개인정보 및 성적 저장하는 패널을 생성
					la.setText("저장되지 않은 학생 데이터");
					p2=new DataInput(clr);
					//이걸 넣으면 Panel2 생성시 기존의 Panel1 frame창이 꺼짐.
					//dispose();
				}
			}
		});
		search_btn.setLocation(170, 250);
		search_btn.setSize(90,20);
		p1.add(search_btn);


		info_la[4].setLocation(300, 30); // 전화번호 label 부착
		info_la[4].setSize(60,20);
		p1.add(info_la[4]);

		info_tf[3]=new JTextField(10); // 전화번호의 TextField 객체 생성 
		info_tf[3].setLocation(360, 30);
		info_tf[3].setSize(120,20);
		p1.add(info_tf[3]);
		add(p1);

		setSize(600, 360); // 전체 프레임의 크기
		setVisible(true);
	}


	public static ArrayList<Student> addStudents(String name, int totalscore, String stdid, String major, String tel, String sub1, int sub1_mid, int sub1_fin, String sub2, int sub2_mid, int sub2_fin, String sub3, int sub3_mid, int sub3_fin, int rank) {
		Student student=new Student(name, totalscore, stdid, major, tel, sub1, sub1_mid, sub1_fin, sub2, sub2_mid, sub2_fin, sub3, sub3_mid, sub3_fin, rank);
		students.add(student);

		return students;
	}

	public void setName(String name) {
		this.name=name;
	}
	public String getName(int i) {
		return students.get(i).getName();
	}
	public void setTotalscore(int totalscore) {
		this.totalscore=totalscore;
	}
	public int getTotalscore(int i) {
		return students.get(i).getTotalscore();
	}
	public void setStdid(String stdid) {
		this.stdid=stdid;
	}
	public String getStdid(int i) {
		return students.get(i).getStdid();
	}
	public void setMajor(String major) {
		this.major=major;
	}
	public String getMajor(int i) {
		return students.get(i).getMajor();
	}
	public void setTel(String tel) {
		this.tel=tel;
	}
	public String getTel(int i) {
		return students.get(i).getTel();
	}
	//과목
	public String getSub1(int i) {
		return students.get(i).getSub1();
	}
	public void setSub1(String sub1) {
		this.sub1=sub1;
	}
	public String getSub2(int i) {
		return students.get(i).getSub2();
	}
	public void setSub2(String sub2) {
		this.sub2=sub2;
	}
	public String getSub3(int i) {
		return students.get(i).getSub3();
	}
	public void setSub3(String sub1) {
		this.sub3=sub3;
	}

	//중간고사
	public void setSub1_mid(int sub1_mid) {
		this.sub1_mid=sub1_mid;
	}
	public int getSub1_mid(int i) {
		return students.get(i).getSub1_mid();
	}
	public void setSub2_mid(int sub2_mid) {
		this.sub2_mid=sub2_mid;
	}
	public int getSub2_mid(int i) {
		return students.get(i).getSub2_mid();
	}
	public void setSub3_mid(int sub3_mid) {
		this.sub3_mid=sub3_mid;
	}
	public int getSub3_mid(int i) {
		return students.get(i).getSub3_mid();
	}

	//기말
	public void setSub1_fin(int sub1_fin) {
		this.sub1_fin=sub1_fin;
	}
	// 패널에서 이거씀
	public int getSub1_fin(int i) {
		return students.get(i).getSub1_fin();
	}
	public void setSub2_fin(int sub2_fin) {
		this.sub2_fin=sub2_fin;
	}
	// 패널에서 이거씀
	public int getSub2_fin(int i) {
		return students.get(i).getSub2_fin();
	}
	public void setSub3_fin(int sub3_fin) {
		this.sub3_fin=sub3_fin;
	}
	// 패널에서 이거씀
	public int getSub3_fin(int i) {
		return students.get(i).getSub3_fin();
	}
	//등수 구하기
	public int getRank(int i) {
		return students.get(i).getRank();
	}
	public int setRank(int rank) {
		this.rank=rank;
		return rank;

	}

	public int getCount() {
		int number=students.size();
		return number;
	}

}

