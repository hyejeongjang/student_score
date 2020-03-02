package java_project;

//import java.io.Serializable; // ��ü Ÿ�� ���� �����


public class Student implements Comparable<Student>{
	private String name, stdid = null, major, tel; // �л� ����
	private String sub1,sub2,sub3; // ���� ����
	private int sub1_mid, sub1_fin,sub2_mid, sub2_fin,sub3_mid, sub3_fin, rank; // ���� ���� �� ���
	private Integer totalscore; // ���� �� ��

	// �л� ������ ������
	public Student(String name, int totalscore, String stdid, String major, String tel, String sub1, int sub1_mid, int sub1_fin, String sub2, int sub2_mid, int sub2_fin, String sub3, int sub3_mid, int sub3_fin, int rank) {
		this.name=name;
		this.totalscore=totalscore;
		this.stdid=stdid;
		this.major=major;
		this.tel=tel;
		this.sub1=sub1;
		this.sub2=sub2;
		this.sub3=sub3;
		this.sub1_mid=sub1_mid;
		this.sub1_fin=sub1_fin;
		this.sub2_mid=sub2_mid;
		this.sub2_fin=sub2_fin;
		this.sub3_mid=sub3_mid;
		this.sub3_fin=sub3_fin;
		this.rank=rank;
		
		rank=0; // ��� �ʱ�ȭ
		totalscore=0;
	}
	
	
	// �л� �̸� getter, setter �޼ҵ�
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(int totalscore) {
		this.totalscore=totalscore;
	}
	
	// �й� getter, setter �޼ҵ�
	public String getStdid() {
		return stdid;
	}
	public void settdid(String stdid) {
		this.stdid=stdid;
	}
	
	
	// �а� getter, setter �޼ҵ�
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major=major;
	}
	
	
	// ��ȭ��ȣ getter, setter �޼ҵ�
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel=tel;
	}
	
	
	// ���� getter, setter �޼ҵ�
	public String getSub1() {
		return sub1;
	}
	public void setSub1(String sub1) {
		this.sub1=sub1;
	}
	public String getSub2() {
		return sub2;
	}
	public void setSub2(String sub2) {
		this.sub2=sub2;
	}
	public String getSub3() {
		return sub3;
	}
	public void setSub3(String sub3) {
		this.sub3=sub3;
	}
	
	
	// �߰���� getter, setter �޼ҵ�
	public int getSub1_mid() {
		return sub1_mid;
	}
	public void setSub1_mid(int sub1_mid) {
		this.sub1_mid=sub1_mid;
	}
	public int getSub2_mid() {
		return sub2_mid;
	}
	public void setSub2_mid(int sub2_mid) {
		this.sub2_mid=sub2_mid;
	}	
	public int getSub3_mid() {
		return sub3_mid;
	}
	public void setSub3_mid(int sub3_mid) {
		this.sub3_mid=sub3_mid;
	}
	
	
	//�⸻��� getter, setter �޼ҵ�
	public int getSub1_fin() {
		return sub1_fin;
	}
	public void setSub1_fin(int sub1_fin) {
		this.sub1_fin=sub1_fin;
	}
	public int getSub2_fin() {
		return sub2_fin;
	}
	public void setSub2_fin(int sub2_fin) {
		this.sub2_fin=sub2_fin;
	}
	public int getSub3_fin() {
		return sub3_fin;
	}
	public void setSub3_fin(int sub3_fin) {
		this.sub3_fin=sub3_fin;
	}
	
	// ���getter, setter  �޼ҵ�
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank=rank;
	}
	@Override
	public int compareTo(Student s) {
		return totalscore.compareTo(s.getTotalscore());
	}
}