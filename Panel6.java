package java_project;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import java_project.DataInput;
import java_project.Panel5.MyPanel5;;


public class Panel6 extends JFrame{
	public MyPanel6 p6=new MyPanel6();
	private float cnt_A, cnt_B, cnt_C, cnt_D, cnt_F;
	private JLabel la[]=new JLabel[6];

	public Panel6(int cnt_A, int cnt_B,int cnt_C, int cnt_D, int cnt_F, Color clr){
		setTitle("Average PieChart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(p6);
		p6.setBackground(clr);

		// 패널 3에서 특정 학생의 학점 정보 가져오기
		this.cnt_A=cnt_A;
		this.cnt_B=cnt_B;
		this.cnt_C=cnt_C;
		this.cnt_D=cnt_D;
		this.cnt_F=cnt_F;

		la[0]=new JLabel(" A Grade ");
		la[1]=new JLabel(" B Grade ");
		la[2]=new JLabel(" C Grade ");
		la[3]=new JLabel(" D Grade ");
		la[4]=new JLabel(" F Grade ");

		// 평균학점 라벨 생성
		float totalgrd=((float)(cnt_A*4+cnt_B*3+cnt_C*2+cnt_D*1+cnt_F*0)/(float)(cnt_A+cnt_B+cnt_C+cnt_D+cnt_F));
		la[5]=new JLabel(" 평균 학점은 "+(Math.round(totalgrd*100)/100.0));

		// 라벨들의 색 설정
		la[0].setBackground(new Color(255, 102, 102));
		la[1].setBackground(new Color(255, 159, 64));
		la[2].setBackground(new Color(255, 242, 4));
		la[3].setBackground(new Color(165, 255, 64));
		la[4].setBackground(new Color(134, 170, 255));

		// 원 그래프 설명 라벨의 속성 설정
		for(int i=0;i<la.length-1;i++) {
			la[i].setOpaque(true);
			la[i].setForeground(Color.white);
			la[i].setSize(50, 10);
			la[i].setFont(new Font("Arial", Font.BOLD, 20));
			la[i].setLocation(350, 80+20*i);
			add(la[i]);
		}

		// 평균 성적을 알려주는 라벨
		la[5].setSize(50, 10);
		la[5].setFont(new Font("고딕체", Font.BOLD, 30));
		la[5].setLocation(50, 400);
		add(la[5]);

		setSize(500, 500);
		setVisible(true);
	}

	// 성적을 받아 원 그래프를 그리는 패널 정의
	class MyPanel6 extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			float total=cnt_A+cnt_B+cnt_C+cnt_D+cnt_F;
			float st_p=0, tmp=0;

			// A
			g.setColor(new Color(255, 102, 102));
			if(cnt_A!=0) {
				st_p=360*(cnt_A/total);
				g.fillArc(80, 80, 300, 300, 0, (int)st_p);
				tmp=tmp+st_p;
			}

			//B
			g.setColor(new Color(255, 159, 64));
			if(cnt_B!=0) {
				st_p=360*(cnt_B/total);
				g.fillArc(80, 80, 300, 300, (int)tmp, (int)st_p);
				tmp+=st_p;
			}


			//C
			g.setColor(new Color(255, 242, 4));
			if(cnt_C!=0) {
				st_p=360*(cnt_C/total);
				g.fillArc(80, 80, 300, 300, (int)tmp, (int)st_p);
				tmp+=st_p;
			}


			//D
			g.setColor(new Color(165, 255, 64));
			if(cnt_D!=0) {
				st_p=360*(cnt_D/total);
				g.fillArc(80, 80, 300, 300, (int)tmp, (int)st_p);
				tmp+=st_p;
			}


			//F
			g.setColor(new Color(134, 170, 255));
			if(cnt_F!=0) {
				st_p=360*(cnt_F/total);
				g.fillArc(80, 80, 300, 300, (int)tmp, (int)st_p);
				tmp+=st_p;
			}

			repaint();
		}
	}
}
