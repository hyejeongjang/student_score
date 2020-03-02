package java_project;


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

// Panel_2
public class DataInput extends JFrame{
	private JLabel[] input_la=new JLabel[15]; // 학생정보 부분의 라벨의 배열
	private JLabel ps_la1=new JLabel("'-' 없이 입력하세요");// 주의사항 라벨  추가 부착
	private JTextField[] info_tf=new JTextField[3]; // 이름 text field
	private JTextField sub1_tf=new JTextField(); // 과목명 입력 TextField
	private JTextField sub2_tf=new JTextField();
	private JTextField sub3_tf=new JTextField();
	private JTextField[] score_tf=new JTextField[6]; // 점수 저장 textField
	private String [] department= {"컴퓨터학과", "수학과", "화학과"};
	private JComboBox<String> info_com=new JComboBox<String>(); // 학과 선택 콤보박스 생성
	private String[] rdo= {"중간", "기말"};
	private JButton save_btn=new JButton("데이터 저장");
	private static ArrayList<Student> students=new ArrayList<Student>(); // Students 생성자의 동적 배열 생성
	private int total_scr;
	private StudentsInformation std_info;
	private String name, stdid, major, tel, sub1, sub2, sub3;
	private int sub1_mid, sub1_fin, sub2_mid, sub2_fin, sub3_mid, sub3_fin;

	public DataInput(Color clr) {
		setTitle("Data Input");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();

		JPanel p2=new JPanel(); // 데이터 저장 패널 생성
		p2.setLayout(null);
		p2.setBackground(clr); // 


		// 이름 라벨 생성
		input_la[0]=new JLabel("[ 학생 정보 입력 ]");
		input_la[1]=new JLabel("이름");
		input_la[2]=new JLabel("학번");
		input_la[3]=new JLabel("학과");
		input_la[4]=new JLabel("전화번호");
		input_la[5]=new JLabel("[ 수강과목 입력 ]");
		input_la[6]=new JLabel("과목1");
		input_la[7]=new JLabel("과목2");
		input_la[8]=new JLabel("과목3");


		// 학생 정보 입력 라벨
		input_la[0].setLocation(40, 20);
		input_la[0].setSize(120,20);
		p2.add(input_la[0]);

		// 이름 학번 학과 label 부착
		for(int i=1;i<4;i++) { 
			input_la[i].setLocation(70, 20+40*i);
			input_la[i].setSize(60,20);
			p2.add(input_la[i]);
		}

		// 이름 학번 textField 부착
		for(int i=0;i<2;i++) { 
			info_tf[i]=new JTextField(10);
			info_tf[i].setLocation(140, 60+40*i);
			info_tf[i].setSize(80,20);
			p2.add(info_tf[i]);
		}

		// 학과 textfield 부착
		for(int i=0;i<department.length;i++) {
			info_com.addItem(department[i]);
		}
		info_com.setLocation(140, 140);
		info_com.setSize(90,20);
		p2.add(info_com);

		// 전화 번호 라벨 & 텍스트필드
		input_la[4].setLocation(46, 220);
		input_la[4].setSize(120,20);
		p2.add(input_la[4]);
		info_tf[2]=new JTextField(10);
		info_tf[2].setLocation(110, 220);
		info_tf[2].setSize(120,20);
		p2.add(info_tf[2]);

		ps_la1.setLocation(115, 240); // 전화번호 입력 시 주의사항 라벨
		ps_la1.setSize(150,20);
		p2.add(ps_la1);


		// 수강 과목 입력 라벨
		input_la[5].setLocation(250, 20);
		input_la[5].setSize(120,20);
		p2.add(input_la[5]);

		// 과목1, 2, 3 라벨
		for(int i=6;i<9;i++) { 
			input_la[i].setLocation(300+130*(i-6), 60);
			input_la[i].setSize(60,20);
			p2.add(input_la[i]);
		}

		// 과목 입력 textfield
		sub1_tf=new JTextField(10);
		sub1_tf.setLocation(300, 90);
		sub1_tf.setSize(100,20);
		p2.add(sub1_tf);
		sub2_tf=new JTextField(10);
		sub2_tf.setLocation(430, 90);
		sub2_tf.setSize(100,20);
		p2.add(sub2_tf);
		sub3_tf=new JTextField(10);
		sub3_tf.setLocation(560, 90);
		sub3_tf.setSize(100,20);
		p2.add(sub3_tf);

		// 중긴 기말 라벨
		for(int i=9;i<15;i++) {
			if(i%2==1) {
				input_la[i]=new JLabel("중간고사");
				input_la[i].setLocation(290+130*(i-9)/2, 120);
				input_la[i].setSize(70,20);
				p2.add(input_la[i]);
			}
			else {
				input_la[i]=new JLabel("기말고사");
				input_la[i].setLocation(290+130*(i-10)/2, 150);
				input_la[i].setSize(70,20);
				p2.add(input_la[i]);
			}
		}

		// 점수 입력 textfield
		for(int i=0;i<3;i++) {      // 1  2  3
			// 4  5  6
			score_tf[i]=new JTextField(10);
			score_tf[i].setLocation(350+130*i, 120);
			score_tf[i].setSize(51,20);
			p2.add(score_tf[i]);
		}
		for(int i=3;i<6;i++) {
			score_tf[i]=new JTextField(10);
			score_tf[i].setLocation(350+130*(i-3), 150);
			score_tf[i].setSize(51,20);
			p2.add(score_tf[i]);
		}

		save_btn.setLocation(540, 260);
		save_btn.setSize(110,20);
		// 데이터 저장 버튼을 누르면
		save_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 데이터 확인 패널 재생성
				StudentsInformation std_info=new StudentsInformation(clr);

				// 데이터 저장 버튼을 누르면 입력된 데이터를 포함하여 파일 수정
				try {
					File file=new File("std_data.txt");
					FileWriter fw=new FileWriter("std_data.txt", true);
					Scanner s=new Scanner(file);

					// 데이터 입력 폼에 입력된 데이터 저장 및  학생 데이터 배열에 추가
					name=info_tf[0].getText();
					stdid=info_tf[1].getText();
					major= department[(info_com.getSelectedIndex())];
					tel=info_tf[2].getText();
					sub1=sub1_tf.getText();
					sub2=sub2_tf.getText();
					sub3=sub3_tf.getText();
					sub1_mid=Integer.parseInt(score_tf[0].getText());
					sub2_mid=Integer.parseInt(score_tf[1].getText());
					sub3_mid=Integer.parseInt(score_tf[2].getText());
					sub1_fin=Integer.parseInt(score_tf[3].getText());
					sub2_fin=Integer.parseInt(score_tf[4].getText());
					sub3_fin=Integer.parseInt(score_tf[5].getText());
					total_scr=sub1_mid+sub2_mid+sub3_mid+sub1_fin+sub2_fin+sub3_fin;


					students=std_info.addStudents(name, total_scr , stdid, major, tel, sub1, sub1_mid, sub1_fin, sub2, sub2_mid, sub2_fin, sub3, sub3_mid, sub3_fin, 0);


					System.out.print(name+"\t"+stdid+"\t"+ major+ "\t" +tel+"\t"+sub1+"\t"+sub1_mid+"   "+sub1_fin+"  \t|\t"+sub2+"\t"+sub2_mid+"   "+sub2_fin+"  \t|\t"+sub3+"\t"+sub3_mid+"   "+sub3_fin+"\n");
					fw.write(name+"\t"+stdid+"\t"+ major+ "\t" +tel+"\t"+sub1+"\t"+sub1_mid+"   "+sub1_fin+"  \t\t"+sub2+"\t"+sub2_mid+"   "+sub2_fin+"  \t\t"+sub3+"  "+sub3_mid+"  "+sub3_fin);
					fw.write("\n");
					s.close();
					fw.close();
				} catch(IOException e1){
					System.out.println("Can not save the New Data.");
				}
			}
		});
		p2.add(save_btn);

		c.add(p2);
		setSize(800, 360);
		setVisible(true);

	}

	public static ArrayList<Student> getStudent(){
		return students;
	}
}
