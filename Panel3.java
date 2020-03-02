package java_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Panel3 extends JFrame{
	public JTextArea rest;//��� ������ ������ �˷��ִ� �ؽ�Ʈ�����
	int index=4;
	protected int av1, av2, av3, cnt_A, cnt_B, cnt_C, cnt_D,cnt_F; // �׷����� �׸��� ���� �� ������ ��� ������ ���� ������ ��Ÿ���� ����
	String name, subject;
	public Grading grading=new Grading(); // ���� ����� ���� �޼ҵ�
	public JSlider s1; // ���� ũ�� ������ ���� �����̴�
	public JLabel upper; // �ؽ�Ʈ ����� ���� ��ġ�� ��
	gettingdata std_info=new gettingdata(); // arraylist�� �ִ� ������ ��� ���� Ŭ����
	public JTextField result_tf, avg_tf, grade_tf, rank_tf, name_tf;
	int totalScore[]=new int [std_info.getCount()];//����
	private JButton btn=new JButton("��ü �л� ���� Ȯ��"); // Panel4�� ȣ���ϴ� ��ư ����
	public Panel3 p3;
	public Panel4 p4;
	public Panel5 p5;
	public Panel6 p6;
	public JCheckBox cb1, cb2; // cb1�� ����׷���, cb2�� ���׷����� �ҷ����� üũ�ڽ�
	public Color clr; //���� ������ ���� �÷� ����

	public Panel3(String name, Color clr) {//���߿� ��ȣ �ȿ� �̸�, �������� �޾ƿ��� public Panel3(String name, String subject)
		super("���� ���� Ȯ��");
		this.name=name;
		this.clr=clr;
		JPanel p3=new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(p3);
		p3.setLayout(null);
		p3.setBackground(clr);

		JLabel headline=new JLabel("����Ȯ��                                                      ���� ũ�� ���/Ȯ��");//����Ȯ�� ���� ����
		JLabel subject_la=new JLabel("�������� : ");//�������� 
		JLabel result_la=new JLabel("����");//����
		JLabel avg_la=new JLabel("���");//���
		JLabel grade_la=new JLabel("����");//����
		JLabel rank_la=new JLabel("���");//���

		//subject_tf=new JTextField(20);//�������� 
		result_tf=new JTextField(15);//����
		avg_tf=new JTextField(5);//���
		grade_tf=new JTextField(5);//����
		rank_tf=new JTextField(5);//���
		//JComboBox<String> subCombo = null;

		s1=new JSlider(JSlider.HORIZONTAL, 0, 30, 10);
		s1.setPaintLabels(true);
		s1.setPaintTicks(true);
		s1.setPaintTrack(true);
		s1.setMajorTickSpacing(5);
		s1.setMinorTickSpacing(1);

		//�����̴��� �����̸� �� ���ڸ�ŭ ����ũ�Ⱑ ��ȭ�Ѵ�.
		s1.addChangeListener((ChangeListener) new ChangeListener() {
			//@Override
			public void stateChanged(ChangeEvent e) {
				JSlider js=(JSlider)e.getSource();
				upper.setFont(new Font("����", Font.BOLD, js.getValue()));
				rest.setFont(new Font("����", Font.ITALIC, js.getValue()));
			}
		});
		s1.setLocation(175, 60);
		s1.setSize(200,50);
		this.add(s1);


		headline.setLocation(0,0);
		headline.setSize(350,50);
		this.add(headline); // ���� Ȯ�� ���� ����.

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

		JLabel name_la=new JLabel("�� ������ ���� ����");
		name_la.setLocation(75,160);
		name_la.setSize(130, 30);
		this.add(name_la);

		upper=new JLabel(" |           ����                    |   �߰�      |   �⸻       |     ����      |     ���      |     ����      |     ");
		upper.setLocation(5,185);
		upper.setSize(800,30);
		this.add(upper);

		rest=new JTextArea(5, 20);
		rest.setLocation(5, 220);
		rest.setSize(700, 100);
		this.add(new JScrollPane(rest));
		this.add(rest); // �� �л��� ���� ������ �����ִ� �ؽ�Ʈ �����
		//��ü���� Ʋ �����

		//�̸����� ��ü ���� ���
		//���� ������ ������ �޺��ڽ� ����
		cb1=new JCheckBox("���� ���� ��� ����", false);
		cb2=new JCheckBox("��ü ���� ���", false);
		cb1.setBorderPainted(true);
		cb2.setBorderPainted(true);

		cb1.setSize(150, 20);
		cb2.setSize(130, 20);
		cb1.setLocation(470, 150);
		cb2.setLocation(630, 150);
		this.add(cb1);
		this.add(cb2);

		JComboBox<String> subCombo=new JComboBox<String>(); // ������ �����ϱ� ���� �޺��ڽ� ����

		for(int i=0;i<std_info.getCount();i++) {
			if(name.equals(std_info.getName(i))) {
				MyActionListener al=new MyActionListener();
				MyItemListener myil=new MyItemListener(); // üũ �ڽ��� �߰���ų ������ ������ ��ü�� �����Ѵ�.
				myil.give(i); // arraylist�� ���° ������ �ҷ��� ������ �ε����� �Ѱ��ִ� �޼ҵ�
				cb1.addItemListener(myil);
				cb2.addItemListener(myil); // ���� üũ�ڽ��� ������ �����ʸ� �޾��ش�.
				al.give(i); // arraylist�� ���° ������ �ҷ��ð����� �Ѱ��ִ� �׼Ǹ�����
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
	// üũ �ڽ��� �޾��� ������ ������
	class MyItemListener implements ItemListener{
		int i;
		public void give(int i) {
			this.i=i;
		} // arraylist�� ���° ������ ������ ������ �˷��ִ� �޼ҵ�
		public void itemStateChanged(ItemEvent e) {
			if(e.getStateChange()==ItemEvent.SELECTED) { // ���� üũ�ڽ��� üũ ǥ�ð� �Ǿ��ٸ�
				if(e.getItem()==cb1) { // ���� üũ ǥ�õ� ���� ������ ���� �׷����� �׸��µ� �ʿ��� ����(�� ������ ���)�� ���ϰ� �� �����͸� �׷����� ����µ� ����  Panel5�� �������ش�.
					av1=(int)((std_info.getSub1_mid(i)+std_info.getSub1_fin(i))/2);
					av2=(int)((std_info.getSub2_mid(i)+std_info.getSub2_fin(i))/2);
					av3=(int)((std_info.getSub3_mid(i)+std_info.getSub3_fin(i))/2);
					new Panel5(std_info.getSub1(i), std_info.getSub2(i), std_info.getSub3(i), av1, av2, av3, clr);
				}
				else if(e.getItem()==cb2) { // ���� üũ ǥ�õȰ��� ���׷������
					int cnt_A=0, cnt_B=0, cnt_C=0, cnt_D=0, cnt_F=0; // ���� ������ ��Ÿ����. ��, a�� � �޾Ҵ��� �� ���� count�ؼ� Panel6�� �Ѱ��ش�.
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
	// �޺� �ڽ��� ����� ���� �׼� ������
	class MyActionListener implements ActionListener{
		int i;
		public void give(int i) { //arraylist�� ���° �ε����� �޾Ƽ� ������ �޾ƿ� ������ �˷��ִ� �޼ҵ�
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

			// ��ü ���� Ȯ���̶�� ��ư�� ������
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(index==0) // �ش� ������ index�� ��ġ�Ǵ� ���� ������ �����ؼ� Panel4�� �������ش�. �׸��� �׸� �÷��� �������༭ ���� ������ ��׶��尡 �����ǰ� ���� ������ �������ش�.
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


			name_tf.setText(std_info.getName(i)); // ������ ���� �������� �л� �̸��� �ؽ�Ʈ �ʵ忡 ǥ���ؼ� �˷��ش�.
			rank_tf.setText(""+std_info.getRank(i)); // �� �л��� ������� �˷��ش�.

			//ù��° ���� ���� ���
			int printnumber=std_info.getSub1_mid(i)+std_info.getSub1_fin(i);
			rest.setText("        ���� : "+std_info.getSub1(i)+"   �߰����� : "+std_info.getSub1_mid(i)+"    �⸻���� : "+std_info.getSub1_fin(i)+"   ����: "+printnumber+"   ��� : "+printnumber/2+"   ���� : "+
					grading.Grading(std_info.getSub1_mid(i), std_info.getSub1_fin(i))+"\n"+
					"        ���� : "+std_info.getSub2(i)+"   �߰����� : "+std_info.getSub2_mid(i)+"   �⸻���� : "+std_info.getSub2_fin(i)+"   ���� : "+printnumber+"   ��� : "+printnumber/2+"   ���� : "+
					grading.Grading(std_info.getSub2_mid(i), std_info.getSub2_fin(i))+"\n"+
					"        ���� : "+std_info.getSub3(i)+"   �߰����� : "+std_info.getSub3_mid(i)+"   �⸻���� : "+std_info.getSub3_fin(i)+"   ���� : "+printnumber+"   ��� : "+printnumber/2+"   ���� : "+
					grading.Grading(std_info.getSub3_mid(i), std_info.getSub3_fin(i))+"\n");
		}
	}
	// ������ ���� ���� ������ִ� �޵�
	class Grading {
		public char Grading(int mid, int fin) {
			int avg=(mid+fin)/2; // ��� �������� ������ ����ϰ� �ȴ�.
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

	// ������ ���� �ڼ��� ������ �������ִ� �г�
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
			this.subject=subject;// �����̸��� �޾ƿͼ� �̿� ���� ������ ������ش�.
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setContentPane(p4);
			p4.setLayout(null);
			p4.setBackground(clr);

			JLabel headline=new JLabel("��ü ���� Ȯ��");
			JTextField headline2=new JTextField(subject);

			JLabel sort=new JLabel("��(��) ���� ������� ����");
			JLabel rank=new JLabel("���� 14�� �л��� ���� ���");
			// ���� ������ ���� ������� ������ ��Ÿ���ִ� �ؽ�Ʈ��������
			sorting=new JTextArea(6, 22);
			sorting.setSize(350, 250);
			sorting.setLocation(10, 70);
			p4.add(sorting);

			// ���� �л����� ������ ��Ÿ���ֱ� ���� �ؽ�Ʈ �����
			ranking=new JTextArea(6,20);
			ranking.setSize(350, 250);
			ranking.setLocation(420, 70);
			p4.add(ranking);

			headline.setLocation(0,0); // ��ü ���� Ȯ���̶�� ���� ����.
			headline.setSize(350, 25);

			headline2.setLocation(25, 30); // ���� ���� ������ ��Ÿ���ش�.
			headline2.setSize(90, 25);

			sort.setLocation(125, 30);
			sort.setSize(280, 25);

			rank.setLocation(410, 30);
			rank.setSize(350, 25);


			// ��ü �л� ����
			int number=std_info.getCount();

			for(int i=0;i<number;i++) {
				if(subject.equals(std_info.getSub1(i))) {
					sorting.append(std_info.getName(i)+" �߰���� : "+std_info.getSub1_mid(i)+"  �⸻��� : "+std_info.getSub1_fin(i)+" ��� : "+(std_info.getSub1_mid(i)+std_info.getSub1_fin(i))/2+"\n");
				}
				if(subject.equals(std_info.getSub2(i))) {
					sorting.append(std_info.getName(i)+" �߰���� : "+std_info.getSub2_mid(i)+"  �⸻��� : "+std_info.getSub2_fin(i)+" ��� : "+(std_info.getSub2_fin(i)+std_info.getSub2_mid(i))/2+"\n");
				}
				if(subject.equals(std_info.getSub3(i))) {
					sorting.append(std_info.getName(i)+" �߰���� : "+std_info.getSub3_mid(i)+"  �⸻��� : "+std_info.getSub3_fin(i)+" ��� : "+(std_info.getSub3_fin(i)+std_info.getSub3_mid(i))/2+"\n");
				}
			}


			// ���� ��ü ������ ���������� �л� ���
			for(int i=1;i<15;i++) {
				ranking.append(i+"�� : "+std_info.getName(number-i)+" ������ ���� : "+std_info.getSub1(number-i)+", "+std_info.getSub2(number-i)+", "+std_info.getSub3(number-i)+"\n");
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


