package java_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Panel3 extends JFrame{
	public JTextArea rest;//모든 과목의 성적을 알려주는 텍스트에어리어
	int index=4;
	protected int av1, av2, av3, cnt_A, cnt_B, cnt_C, cnt_D,cnt_F; // 그래프를 그리기 위해 각 과목의 평균 성적과 학점 분포를 나타내는 변수
	String name, subject;
	public Grading grading=new Grading(); // 성적 계산을 위한 메소드
	public JSlider s1; // 글자 크기 조절을 위한 슬라이더
	public JLabel upper; // 텍스트 에어리아 위에 위치한 라벨
	gettingdata std_info=new gettingdata(); // arraylist에 있는 정보를 얻기 위한 클래스
	public JTextField result_tf, avg_tf, grade_tf, rank_tf, name_tf;
	int totalScore[]=new int [std_info.getCount()];//총점
	private JButton btn=new JButton("전체 학생 성적 확인"); // Panel4를 호출하는 버튼 생성
	public Panel3 p3;
	public Panel4 p4;
	public Panel5 p5;
	public Panel6 p6;
	public JCheckBox cb1, cb2; // cb1은 막대그래프, cb2는 원그래프를 불러오는 체크박스
	public Color clr; //배경색 적용을 위한 컬러 변수

	public Panel3(String name, Color clr) {//나중에 괄호 안에 이름, 수강과목 받아오기 public Panel3(String name, String subject)
		super("개인 성적 확인");
		this.name=name;
		this.clr=clr;
		JPanel p3=new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(p3);
		p3.setLayout(null);
		p3.setBackground(clr);

		JLabel headline=new JLabel("성적확인                                                      글자 크기 축소/확대");//성적확인 제목 부착
		JLabel subject_la=new JLabel("수강과목 : ");//수강과목 
		JLabel result_la=new JLabel("성적");//성적
		JLabel avg_la=new JLabel("평균");//평균
		JLabel grade_la=new JLabel("학점");//학점
		JLabel rank_la=new JLabel("등수");//등수

		//subject_tf=new JTextField(20);//수강과목 
		result_tf=new JTextField(15);//성적
		avg_tf=new JTextField(5);//평균
		grade_tf=new JTextField(5);//학점
		rank_tf=new JTextField(5);//등수
		//JComboBox<String> subCombo = null;

		s1=new JSlider(JSlider.HORIZONTAL, 0, 30, 10);
		s1.setPaintLabels(true);
		s1.setPaintTicks(true);
		s1.setPaintTrack(true);
		s1.setMajorTickSpacing(5);
		s1.setMinorTickSpacing(1);

		//슬라이더가 움직이면 그 숫자만큼 글자크기가 변화한다.
		s1.addChangeListener((ChangeListener) new ChangeListener() {
			//@Override
			public void stateChanged(ChangeEvent e) {
				JSlider js=(JSlider)e.getSource();
				upper.setFont(new Font("굴림", Font.BOLD, js.getValue()));
				rest.setFont(new Font("굴림", Font.ITALIC, js.getValue()));
			}
		});
		s1.setLocation(175, 60);
		s1.setSize(200,50);
		this.add(s1);


		headline.setLocation(0,0);
		headline.setSize(350,50);
		this.add(headline); // 성적 확인 라벨을 띄운다.

		subject_la.setLocation(5,60);
		subject_la.setSize(60,50);
		this.add(subject_la);

		result_la.setLocation(5,95);
		result_la.setSize(50,65);
		this.add(result_la);

		result_tf.setLocation(70,110);
		result_tf.setSize(50,35);
		this.add(result_tf);

		avg_la.setLocation(135,95);
		avg_la.setSize(50,65);
		this.add(avg_la);

		avg_tf.setLocation(200, 110);
		avg_tf.setSize(50,35);
		this.add(avg_tf);

		grade_la.setLocation(275,95);
		grade_la.setSize(50,65);
		this.add(grade_la);

		grade_tf.setLocation(350, 110);
		grade_tf.setSize(50, 35);
		this.add(grade_tf);

		rank_la.setLocation(425, 95);
		rank_la.setSize(50, 65);
		this.add(rank_la);

		rank_tf.setLocation(500, 110);
		rank_tf.setSize(50, 35);
		this.add(rank_tf);


		name_tf=new JTextField(20);
		name_tf.setLocation(5,160);
		name_tf.setSize(65, 30);
		this.add(name_tf);

		JLabel name_la=new JLabel("의 나머지 과목 성적");
		name_la.setLocation(75,160);
		name_la.setSize(130, 30);
		this.add(name_la);

		upper=new JLabel(" |           과목                    |   중간      |   기말       |     총점      |     평균      |     학점      |     ");
		upper.setLocation(5,185);
		upper.setSize(800,30);
		this.add(upper);

		rest=new JTextArea(5, 20);
		rest.setLocation(5, 220);
		rest.setSize(700, 100);
		this.add(new JScrollPane(rest));
		this.add(rest); // 한 학생의 성적 정보를 보여주는 텍스트 에어리어
		//전체적인 틀 만들기

		//이름으로 전체 정보 얻기
		//과목 선택이 가능한 콤보박스 생성
		cb1=new JCheckBox("수강 과목 평균 점수", false);
		cb2=new JCheckBox("전체 학점 평균", false);
		cb1.setBorderPainted(true);
		cb2.setBorderPainted(true);

		cb1.setSize(150, 20);
		cb2.setSize(130, 20);
		cb1.setLocation(470, 150);
		cb2.setLocation(630, 150);
		this.add(cb1);
		this.add(cb2);

		JComboBox<String> subCombo=new JComboBox<String>(); // 과목을 선택하기 위한 콤보박스 생성

		for(int i=0;i<std_info.getCount();i++) {
			if(name.equals(std_info.getName(i))) {
				MyActionListener al=new MyActionListener();
				MyItemListener myil=new MyItemListener(); // 체크 박스에 추가시킬 아이템 리스너 객체를 생성한다.
				myil.give(i); // arraylist의 몇번째 정보를 불러올 것인지 인덱스를 넘겨주는 메소드
				cb1.addItemListener(myil);
				cb2.addItemListener(myil); // 각각 체크박스에 아이템 리스너를 달아준다.
				al.give(i); // arraylist의 몇번째 정보를 불러올것인지 넘겨주는 액션리스너
				String subs[]=new String[3];
				subs[0]=std_info.getSub1(i);
				subs[1]=std_info.getSub2(i);
				subs[2]=std_info.getSub3(i);
				for(int t=0;t<subs.length;t++) {
					subCombo.addItem(subs[t]);   
				}
				subCombo.addActionListener(al);
			}
		}
		subCombo.setLocation(75, 60);
		subCombo.setSize(100, 50);
		this.add(subCombo);

		//setContentPane(p3); 
		setSize(800,360);
		setVisible(true);
	}
	// 체크 박스에 달아줄 아이템 리스너
	class MyItemListener implements ItemListener{
		int i;
		public void give(int i) {
			this.i=i;
		} // arraylist에 몇번째 정보를 전달할 것인지 알려주는 메소드
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED) { // 만약 체크박스에 체크 표시가 되었다면
				if(e.getItem()==cb1) { // 만약 체크 표시된 것이 막대라면 막대 그래프를 그리는데 필요한 정보(각 성적의 평균)을 구하고 그 데이터를 그래프를 만드는데 쓰일  Panel5에 전달해준다.
					av1=(int)((std_info.getSub1_mid(i)+std_info.getSub1_fin(i))/2);
					av2=(int)((std_info.getSub2_mid(i)+std_info.getSub2_fin(i))/2);
					av3=(int)((std_info.getSub3_mid(i)+std_info.getSub3_fin(i))/2);
					new Panel5(std_info.getSub1(i), std_info.getSub2(i), std_info.getSub3(i), av1, av2, av3, clr);
				}
				else if(e.getItem()==cb2) { // 만약 체크 표시된것이 원그래프라면
					int cnt_A=0, cnt_B=0, cnt_C=0, cnt_D=0, cnt_F=0; // 학점 분포를 나타낸다. 즉, a를 몇개 받았는지 그 수를 count해서 Panel6에 넘겨준다.
					char grd[]= {grading.Grading(std_info.getSub1_mid(i), std_info.getSub1_fin(i)), grading.Grading(std_info.getSub2_mid(i), std_info.getSub2_fin(i)), grading.Grading(std_info.getSub3_mid(i), std_info.getSub3_fin(i))};
					for(int i=0;i<3;i++) {
						if(grd[i]=='A')
							cnt_A++;
						else if(grd[i]=='B')
							cnt_B++;
						else if(grd[i]=='C')
							cnt_C++;
						else if(grd[i]=='D')
							cnt_D++;
						else
							cnt_F++;
					}
					new Panel6(cnt_A, cnt_B, cnt_C, cnt_D, cnt_F, clr);
				}
			}
		}
	}
	// 콤보 박스를 만들기 위한 액션 리스너
	class MyActionListener implements ActionListener{
		int i;
		public void give(int i) { //arraylist의 몇번째 인덱스를 받아서 정보를 받아올 것인지 알려주는 메소드
			this.i=i;
		}
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> cb=(JComboBox<String>)e.getSource();
			index=cb.getSelectedIndex();
			if(index>=0&&index<=2) {
				grade_tf.setText(""+grading.Grading(std_info.getSub1_mid(i), std_info.getSub1_fin(i)));
				result_tf.setText(std_info.getSub1_mid(i)+","+std_info.getSub1_fin(i));
				av1=(int)((std_info.getSub1_mid(i)+std_info.getSub1_fin(i))/2);
				av2=(int)((std_info.getSub2_mid(i)+std_info.getSub2_fin(i))/2);
				av3=(int)((std_info.getSub3_mid(i)+std_info.getSub3_fin(i))/2);
				avg_tf.setText(""+(std_info.getSub1_mid(i)+std_info.getSub1_fin(i))/2);
			}

			// 전체 성적 확인이라는 버튼이 눌리면
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(index==0) // 해당 과목의 index에 매치되는 과목 정보를 전달해서 Panel4를 생성해준다. 그리고 테마 컬러도 전달해줘서 같은 색상의 백그라운드가 설정되게 색상 변수도 전달해준다.
						p4=new Panel4(std_info.getSub1(i), clr);
					else if(index==1)
						p4=new Panel4(std_info.getSub2(i), clr);
					else if(index==2)
						p4=new Panel4(std_info.getSub3(i), clr);
				}
			});
			btn.setOpaque(true);
			btn.setBackground(Color.DARK_GRAY);
			btn.setForeground(Color.WHITE);
			btn.setLocation(500, 60);
			btn.setSize(200, 40);
			add(btn);


			name_tf.setText(std_info.getName(i)); // 누구의 성적 분포인지 학생 이름을 텍스트 필드에 표시해서 알려준다.
			rank_tf.setText(""+std_info.getRank(i)); // 그 학생이 몇등인지 알려준다.

			//첫번째 과목 정보 출력
			int printnumber=std_info.getSub1_mid(i)+std_info.getSub1_fin(i);
			rest.setText("        과목 : "+std_info.getSub1(i)+"   중간성적 : "+std_info.getSub1_mid(i)+"    기말성적 : "+std_info.getSub1_fin(i)+"   총점: "+printnumber+"   평균 : "+printnumber/2+"   학점 : "+
					grading.Grading(std_info.getSub1_mid(i), std_info.getSub1_fin(i))+"\n"+
					"        과목 : "+std_info.getSub2(i)+"   중간성적 : "+std_info.getSub2_mid(i)+"   기말성적 : "+std_info.getSub2_fin(i)+"   총점 : "+printnumber+"   평균 : "+printnumber/2+"   학점 : "+
					grading.Grading(std_info.getSub2_mid(i), std_info.getSub2_fin(i))+"\n"+
					"        과목 : "+std_info.getSub3(i)+"   중간성적 : "+std_info.getSub3_mid(i)+"   기말성적 : "+std_info.getSub3_fin(i)+"   총점 : "+printnumber+"   평균 : "+printnumber/2+"   학점 : "+
					grading.Grading(std_info.getSub3_mid(i), std_info.getSub3_fin(i))+"\n");
		}
	}
	// 성적에 따른 학점 계산해주는 메드
	class Grading {
		public char Grading(int mid, int fin) {
			int avg=(mid+fin)/2; // 평균 성적으로 학점을 계산하게 된다.
			char grade = 0;
			if(avg>=90&&avg<=100) {
				grade='A';
			}
			if(avg>=80&&avg<90) {
				grade='B';
			}
			if(avg>=70&&avg<80) {
				grade='C';
			}
			if(avg>=60&&avg<70) {
				grade='D';
			}
			if(avg<60) {
				grade='F';
			}
			return grade;
		}
	}

	// 성적애 대한 자세한 정보를 제공해주는 패널
	public class Panel4 extends JFrame{
		String subject;
		private MyPanel4 p4=new MyPanel4();
		private Panel5 p5;
		private Panel6 p6;
		public JTextArea sorting;
		public JTextArea ranking;
		private JCheckBox [] cbox=new JCheckBox[2];

		public Panel4(String subject, Color clr) {
			super("All Students' Scores");
			this.subject=subject;// 과목이름을 받아와서 이에 대한 정보를 출력해준다.
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setContentPane(p4);
			p4.setLayout(null);
			p4.setBackground(clr);

			JLabel headline=new JLabel("전체 성적 확인");
			JTextField headline2=new JTextField(subject);

			JLabel sort=new JLabel("을(를) 들은 사람들의 점수");
			JLabel rank=new JLabel("상위 14명 학생의 학점 등수");
			// 같은 과목을 들은 사람들의 성적을 나타내주는 텍스트에여리어
			sorting=new JTextArea(6, 22);
			sorting.setSize(350, 250);
			sorting.setLocation(10, 70);
			p4.add(sorting);

			// 상의 학생들의 성적을 나타내주기 위한 텍스트 에어리어
			ranking=new JTextArea(6,20);
			ranking.setSize(350, 250);
			ranking.setLocation(420, 70);
			p4.add(ranking);

			headline.setLocation(0,0); // 전체 성적 확인이라는 라벨을 띄운다.
			headline.setSize(350, 25);

			headline2.setLocation(25, 30); // 과목에 대한 정보를 나타내준다.
			headline2.setSize(90, 25);

			sort.setLocation(125, 30);
			sort.setSize(280, 25);

			rank.setLocation(410, 30);
			rank.setSize(350, 25);


			// 전체 학생 숫자
			int number=std_info.getCount();

			for(int i=0;i<number;i++) {
				if(subject.equals(std_info.getSub1(i))) {
					sorting.append(std_info.getName(i)+" 중간고사 : "+std_info.getSub1_mid(i)+"  기말고사 : "+std_info.getSub1_fin(i)+" 평균 : "+(std_info.getSub1_mid(i)+std_info.getSub1_fin(i))/2+"\n");
				}
				if(subject.equals(std_info.getSub2(i))) {
					sorting.append(std_info.getName(i)+" 중간고사 : "+std_info.getSub2_mid(i)+"  기말고사 : "+std_info.getSub2_fin(i)+" 평균 : "+(std_info.getSub2_fin(i)+std_info.getSub2_mid(i))/2+"\n");
				}
				if(subject.equals(std_info.getSub3(i))) {
					sorting.append(std_info.getName(i)+" 중간고사 : "+std_info.getSub3_mid(i)+"  기말고사 : "+std_info.getSub3_fin(i)+" 평균 : "+(std_info.getSub3_fin(i)+std_info.getSub3_mid(i))/2+"\n");
				}
			}


			// 과목 전체 학점을 기준으로한 학생 등수
			for(int i=1;i<15;i++) {
				ranking.append(i+"등 : "+std_info.getName(number-i)+" 수강한 과목 : "+std_info.getSub1(number-i)+", "+std_info.getSub2(number-i)+", "+std_info.getSub3(number-i)+"\n");
			}

			p4.add(headline);
			p4.add(headline2);
			p4.add(sort);
			p4.add(rank);

			setContentPane(p4);
			setSize(800,360);
			setVisible(true);
		}
		class MyPanel4 extends JPanel{
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
			}
		}
	}
}


