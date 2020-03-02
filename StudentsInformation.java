package java_project;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java_project.*;


public class StudentsInformation extends JFrame{
	private static ArrayList<Student> students=new ArrayList<Student>(); // �������� ������ �����迭�� ����
	private JLabel[] info_la=new JLabel[5]; // �л����� �κ��� ���� �迭
	private JTextField[] info_tf=new JTextField[4]; // �̸� text field
	private JLabel la=new JLabel(" "); // ���� ���� ���θ� Ȯ��
	private static String name, major, stdid, tel; // �̸�, �й� , �а� ��ȭ��ȣ
	private int totalscore; // ��ü ����
	private static String sub1, sub2, sub3; // ���� �̸�
	private static int sub1_mid, sub1_fin, sub2_mid, sub2_fin, sub3_mid, sub3_fin, rank; // 3������ �߰� �⸻ ����
	private String [] department= {"��ǻ���а�", "���а�", "ȭ�а�"};
	private boolean output=false; // �л� ������ �ҷ����� true�� ����
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


				// ������ �о ������ ��Ʈ�� Arraylist�� ����
				students=addStudents(name,totalscore, stdid, major, tel, sub1, sub1_mid, sub1_fin, sub2, sub2_mid, sub2_fin, sub3, sub1_mid, sub3_fin, rank);
				System.out.print(name+"\t"+totalscore+"\t"+stdid+"\t"+ major+ "\t" +tel+"\t"+sub1+"\t"+sub1_mid+"   "+sub1_fin+"  \t|\t"+sub2+"\t"+sub2_mid+"   "+sub2_fin+"  \t|\t"+sub3+"\t"+sub3_mid+"   "+sub3_fin+"\n");
			}
			s.close();
		} catch(IOException e){
			System.out.println("File not found.");
		}

		// ��� �������� ����
		Collections.sort(students);
		for(int i=0;i<students.size();i++) {
			students.get(i).setRank(i+1);
		}


		info_la[0]=new JLabel("[������ ����]") ; // [������ ����] �� ���� �� ���̱�
		info_la[0].setLocation(40, 20);
		info_la[0].setSize(120,20);
		p1.add(info_la[0]);

		info_la[1]=new JLabel("�̸�"); // textFiel���� ��
		info_la[2]=new JLabel("�й�");
		info_la[3]=new JLabel("�а�");
		info_la[4]=new JLabel("��ȭ��ȣ");


		for(int i=1;i<4;i++) { // �̸� �й� �а� label ����
			info_la[i].setLocation(70, 20+40*i);
			info_la[i].setSize(60,20);
			p1.add(info_la[i]);
		}

		for(int i=0;i<2;i++) { 				// �̸� �й� textfield ����
			info_tf[i]=new JTextField(10);  // tf�� �� TextField ��ü ���� 
			info_tf[i].setLocation(150, 60+40*i);
			info_tf[i].setSize(90,20);
			p1.add(info_tf[i]);
		}

		JComboBox<String> info_com=new JComboBox<String>(); // �а� ���� �޺��ڽ� ����
		for(int i=0;i<department.length;i++) {
			info_com.addItem(department[i]);
		}
		info_com.setLocation(150, 140);
		info_com.setSize(90,20);
		p1.add(info_com);


		// �޺��ڽ��� �̺�Ʈ ������ �ޱ�
		// �޺��ڽ����� ���õ� �а��� String�� depart�� ����
		info_com.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb=(JComboBox<String>)e.getSource();
				int index=cb.getSelectedIndex();
				major=department[index];
			}
		});


		// ����� �л� ���������� Ȯ���ϴ� label
		la.setLocation(280, 180);
		la.setFont(new Font("���ü", Font.BOLD, 22));
		la.setSize(280,40);
		p1.add(la);


		JButton search_btn=new JButton("Ȯ��");
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=info_tf[0].getText();

				for(int i=0;i<students.size();i++) {
					// text ���Ͽ� �л��� �̸� �й� �а��� ���� �л��� ������ ����� ��ȭ��ȣ�� ���
					if((info_tf[0].getText()).equals(students.get(i).getName()) && (info_tf[1].getText()).equals(students.get(i).getStdid()) && department[(info_com.getSelectedIndex())].equals(students.get(i).getMajor())) {
						info_tf[3].setText(students.get(i).getTel());
						output=true;
					}
				}
				// ����� �л� �����͸� ���� ���� Ȯ�� �г� ����
				if(output) {
					la.setText("����� �л� ������");
					p3=new Panel3(name, clr);
				} else { // ����� �л� �����Ͱ� �ƴϸ� �������� �� ���� �����ϴ� �г��� ����
					la.setText("������� ���� �л� ������");
					p2=new DataInput(clr);
					//�̰� ������ Panel2 ������ ������ Panel1 frameâ�� ����.
					//dispose();
				}
			}
		});
		search_btn.setLocation(170, 250);
		search_btn.setSize(90,20);
		p1.add(search_btn);


		info_la[4].setLocation(300, 30); // ��ȭ��ȣ label ����
		info_la[4].setSize(60,20);
		p1.add(info_la[4]);

		info_tf[3]=new JTextField(10); // ��ȭ��ȣ�� TextField ��ü ���� 
		info_tf[3].setLocation(360, 30);
		info_tf[3].setSize(120,20);
		p1.add(info_tf[3]);
		add(p1);

		setSize(600, 360); // ��ü �������� ũ��
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
	//����
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

	//�߰����
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

	//�⸻
	public void setSub1_fin(int sub1_fin) {
		this.sub1_fin=sub1_fin;
	}
	// �гο��� �̰ž�
	public int getSub1_fin(int i) {
		return students.get(i).getSub1_fin();
	}
	public void setSub2_fin(int sub2_fin) {
		this.sub2_fin=sub2_fin;
	}
	// �гο��� �̰ž�
	public int getSub2_fin(int i) {
		return students.get(i).getSub2_fin();
	}
	public void setSub3_fin(int sub3_fin) {
		this.sub3_fin=sub3_fin;
	}
	// �гο��� �̰ž�
	public int getSub3_fin(int i) {
		return students.get(i).getSub3_fin();
	}
	//��� ���ϱ�
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

