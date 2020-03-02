package java_project;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import javax.swing.*;
import java.util.*;
public class gettingdata {
	private JLabel[] info_la=new JLabel[5]; // 학생정보 부분의 라벨의 배열
	private JTextField[] info_tf=new JTextField[4]; // 이름 text field
	private static String name, major, stdid, tel; // 이름, 학번 , 학과 전화번호 변수 생성
	public int totalscore;
	private static String sub1;
	private static String sub2;
	private static String sub3;
	private static int sub1_mid;
	private static int sub1_fin;
	private static int sub2_mid;
	private static int sub2_fin;
	private static int sub3_mid;
	private static int sub3_fin;
	private static int rank;
	private String [] department= {"컴퓨터학과", "수학과", "화학과"};
	private boolean output=false; // 학생 정보를 불러내면 true를 저장
	private static ArrayList<Student> students=new ArrayList<Student>(); // 수강생의 정보를 동적배열로 생성
	private JLabel la=new JLabel(" ");

	public gettingdata() {
		// 데이터 저장을 위한 try-catch문
		try {
			File file=new File("std_data.txt");
			Scanner s=new Scanner(file);

			while(s.hasNext()) {
				name=s.next(); // 이름
				stdid=s.next(); // 학번
				major=s.next(); // 학과
				tel=s.next(); // 연락처
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

		//정렬
		Collections.sort(students);
		for(int i=students.size();i>0;i--) {
			students.get(students.size()-i).setRank(i);
		}
	}
	// 학생 성적을 저장해주는 arraylist
	public static ArrayList<Student> addStudents(String name, int totalscore, String stdid, String major, String tel, String sub1, int sub1_mid, int sub1_fin, String sub2, int sub2_mid, int sub2_fin, String sub3, int sub3_mid, int sub3_fin, int rank) {
		Student student=new Student(name, totalscore, stdid, major, tel, sub1, sub1_mid, sub1_fin, sub2, sub2_mid, sub2_fin, sub3, sub3_mid, sub3_fin, rank);
		students.add(student);
		return students;
	}
	// set으로 시작하는 메소드들은 다른 클래스에서 그 인덱스에 해당하는 정보를 설정하고자 할 때 사용하는 메소드이다.
	public void setName(String name) {
		this.name=name;
	}
	//get으로 시작하는 메소드들은 arraylist의 인덱스 번호를 받아와서 해당하는 인덱스에 저장되어 있는 성적을 읽어볼 수 있게 해준다.
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
	public int getSub1_fin(int i) {
		return students.get(i).getSub1_fin();
	}
	public void setSub2_fin(int sub2_fin) {
		this.sub2_fin=sub2_fin;
	}
	public int getSub2_fin(int i) {
		return students.get(i).getSub2_fin();
	}
	public void setSub3_fin(int sub3_fin) {
		this.sub3_fin=sub3_fin;
	}
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
	// 전체 학생수를 구한다.
	public int getCount() {
		int number=students.size();
		return number;
	}
}