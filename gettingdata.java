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
	private JLabel[] info_la=new JLabel[5]; // �л����� �κ��� ���� �迭
	private JTextField[] info_tf=new JTextField[4]; // �̸� text field
	private static String name, major, stdid, tel; // �̸�, �й� , �а� ��ȭ��ȣ ���� ����
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
	private String [] department= {"��ǻ���а�", "���а�", "ȭ�а�"};
	private boolean output=false; // �л� ������ �ҷ����� true�� ����
	private static ArrayList<Student> students=new ArrayList<Student>(); // �������� ������ �����迭�� ����
	private JLabel la=new JLabel(" ");

	public gettingdata() {
		// ������ ������ ���� try-catch��
		try {
			File file=new File("std_data.txt");
			Scanner s=new Scanner(file);

			while(s.hasNext()) {
				name=s.next(); // �̸�
				stdid=s.next(); // �й�
				major=s.next(); // �а�
				tel=s.next(); // ����ó
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

		//����
		Collections.sort(students);
		for(int i=students.size();i>0;i--) {
			students.get(students.size()-i).setRank(i);
		}
	}
	// �л� ������ �������ִ� arraylist
	public static ArrayList<Student> addStudents(String name, int totalscore, String stdid, String major, String tel, String sub1, int sub1_mid, int sub1_fin, String sub2, int sub2_mid, int sub2_fin, String sub3, int sub3_mid, int sub3_fin, int rank) {
		Student student=new Student(name, totalscore, stdid, major, tel, sub1, sub1_mid, sub1_fin, sub2, sub2_mid, sub2_fin, sub3, sub3_mid, sub3_fin, rank);
		students.add(student);
		return students;
	}
	// set���� �����ϴ� �޼ҵ���� �ٸ� Ŭ�������� �� �ε����� �ش��ϴ� ������ �����ϰ��� �� �� ����ϴ� �޼ҵ��̴�.
	public void setName(String name) {
		this.name=name;
	}
	//get���� �����ϴ� �޼ҵ���� arraylist�� �ε��� ��ȣ�� �޾ƿͼ� �ش��ϴ� �ε����� ����Ǿ� �ִ� ������ �о �� �ְ� ���ش�.
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
	//��� ���ϱ�
	public int getRank(int i) {
		return students.get(i).getRank();
	}

	public int setRank(int rank) {
		this.rank=rank;
		return rank;
	}
	// ��ü �л����� ���Ѵ�.
	public int getCount() {
		int number=students.size();
		return number;
	}
}