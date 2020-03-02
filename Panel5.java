package java_project;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Panel5 extends JFrame{
	// 막대 그래프를 그릴 패널 생성
	public MyPanel5 p5=new MyPanel5();
	private String sub1, sub2, sub3;
	private int a,b,c;
	public Panel5(String sub1, String sub2, String sub3, int a, int b, int c, Color clr) {
		super("All Subjects Score");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(p5);

		this.sub1=sub1;
		this.sub2=sub2;
		this.sub3=sub3;
		this.a=a;
		this.b=b;
		this.c=c;

		// 새 패널 생성 후 위쪽에 붙이기
		JPanel minip=new JPanel();
		minip.setLayout(new FlowLayout());
		minip.setBackground(clr);

		// GUI 구성
		JTextField score1 = new JTextField(3);
		JTextField score2 = new JTextField(3);
		JTextField score3 = new JTextField(3);
		JButton button = new JButton("그래프 그리기");
		minip.add(new JLabel(sub1));
		score1.setText(Integer.toString(a));
		minip.add(score1);
		minip.add(new JLabel(sub2));
		score2.setText(Integer.toString(b));
		minip.add(score2);
		minip.add(new JLabel(sub3));
		score3.setText(Integer.toString(c));
		minip.add(score3);

		this.add(minip, BorderLayout.SOUTH);

		setSize(400, 350);
		setVisible(true);
	}

	// 패널 정의
	class MyPanel5 extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// g 그래픽 화면 초기화
			g.clearRect(0,0,getWidth(),getHeight());
			g.drawLine(50,250,350,250);

			// 그래프 점수 줄 
			for(int cnt = 1 ;cnt<11;cnt++)
			{
				g.drawString(cnt *10 +"",25,255-20*cnt);
				g.drawLine(50, 250-20*cnt, 350,250-20*cnt);
			}

			// 성적 이름 drawing
			g.drawLine(50,20,50,250);
			g.drawString(sub1,70,270);
			g.drawString(sub2,177,270);
			g.drawString(sub3,275,270);
			g.setColor(Color.ORANGE);

			// 막대그래프 그리기
			if (a>0)
				g.fillRect(75,250-a*2,40,a*2);
			if(b>0)
				g.fillRect(175,250-b*2,40,b*2);
			if(c>0)
				g.fillRect(275,250-c*2,40,c*2);
		}
	}
}
