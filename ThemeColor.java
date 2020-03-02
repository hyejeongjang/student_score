package java_project;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java_project.Student;
import java.awt.event.*;

public class ThemeColor extends JFrame{
	private String[] colorArray= {"Red", "Orange", "Yellow", "Green", "Blue", "Cyan", "Magenta", "Pink", "Light Gray" };
	private Color[] c={ Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.PINK, Color.LIGHT_GRAY }; // 변경할 색상의 저장하는 배열
	private int color_index=0; // 선택된 라디오 버튼의 색의 index
	private JLabel color_ra=new JLabel("Color");
	private JRadioButton[] colors=new JRadioButton[9]; // 여러 색상의 테마로 변경하는 라디오버튼
	private JLabel la=new JLabel("[ 테마 컬러 변경 ]");
	private JButton change=new JButton("Change Theme"); // 색상 변경 버튼
	private String clr=""; // 선택된 color를 보여주는 라벨

	public ThemeColor() {
		setTitle("Data Input");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p7=new JPanel();
		p7.setLayout(null);

		la.setLocation(30, 20);
		la.setSize(120, 30);
		p7.add(la);


		ButtonGroup group=new ButtonGroup();

		for(int i=0;i<colorArray.length;i++) {
			colors[i]=new JRadioButton(colorArray[i]);
			group.add(colors[i]);
			p7.add(colors[i]);
			if(i<3) {
				colors[i].setLocation(40+(i*145), 60);
			} else if(i<6) {
				colors[i].setLocation(40+((i-3)*145), 90);
			} else {
				colors[i].setLocation(40+((i-6)*145), 120);
			}
			colors[i].addItemListener(new MyItemListener());
			colors[i].setSize(120, 20);
		}

		color_ra.setOpaque(true);
		color_ra.setLocation(130, 180);
		color_ra.setSize(200, 80);
		color_ra.setHorizontalAlignment(SwingConstants.CENTER);
		color_ra.setFont(new Font("Arial", Font.PLAIN, 22));

		change.setSize(120, 20);
		change.setLocation(330, 270);
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clr=="RED") {
					color_index=0;
					StudentsInformation p1;
					p1=new StudentsInformation(c[color_index]);
					p7.setBackground(Color.RED);

				} else if (clr=="ORANGE") {
					color_index=1;
					StudentsInformation p1;
					p1=new StudentsInformation(c[color_index]);
					p7.setBackground(Color.ORANGE);

				} else if (clr=="YELLOW") {
					color_index=2;
					StudentsInformation p1;
					p1=new StudentsInformation(c[color_index]);
					p7.setBackground(Color.YELLOW);

				} else if (clr=="GREEN") {
					color_index=3;
					StudentsInformation p1;
					p1=new StudentsInformation(c[color_index]);
					p7.setBackground(Color.GREEN);

				} else if (clr=="BLUE") {
					color_index=4;
					StudentsInformation p1;
					p1=new StudentsInformation(c[color_index]);
					p7.setBackground(Color.BLUE);

				} else if (clr=="CYAN") {
					color_index=5;
					StudentsInformation p1;
					p1=new StudentsInformation(c[color_index]);
					p7.setBackground(Color.CYAN);

				} else if (clr=="MAGENTA") {
					color_index=6;
					StudentsInformation p1;
					p1=new StudentsInformation(c[color_index]);
					p7.setBackground(Color.MAGENTA);

				} else if (clr=="PINK") {
					color_index=7;
					StudentsInformation p1;
					p1=new StudentsInformation(c[color_index]);
					p7.setBackground(Color.PINK);

				} else if (clr=="LIGHT_GRAY") {
					color_index=8;
					StudentsInformation p1;
					p1=new StudentsInformation(c[color_index]);
					p7.setBackground(Color.LIGHT_GRAY);

				}
			}
		});
		p7.add(change);

		p7.add(color_ra);
		add(p7);
		setSize(500, 360);
		setVisible(true);
	}

	// 라벨의 text와 배경색을 변경하는 ItemListener 정의
	class MyItemListener implements ItemListener{
		public void  itemStateChanged(ItemEvent e) {
			if(e.getSource()==colors[0]) {
				color_ra.setBackground(Color.RED);
				color_ra.setText("Red Theme");
				color_ra.setForeground(Color.WHITE);
				clr="RED";

			} else if(e.getSource()==colors[1]) {
				color_ra.setBackground(Color.ORANGE);
				color_ra.setText("Orange Theme");
				color_ra.setForeground(Color.WHITE);
				clr="ORANGE";

			} else if(e.getSource()==colors[2]) {
				color_ra.setBackground(Color.YELLOW);
				color_ra.setText("Yellow Theme");
				color_ra.setForeground(Color.BLACK);
				clr="YELLOW";

			} else if(e.getSource()==colors[3]) {
				color_ra.setBackground(Color.GREEN);
				color_ra.setText("Green Theme");
				color_ra.setForeground(Color.WHITE);
				clr="GREEN";

			} else if(e.getSource()==colors[4]) {
				color_ra.setBackground(Color.BLUE);
				color_ra.setText("Blue Theme");
				color_ra.setForeground(Color.WHITE);
				clr="BLUE";

			} else if(e.getSource()==colors[5]) {
				color_ra.setBackground(Color.CYAN);
				color_ra.setText("Cyan Theme");
				color_ra.setForeground(Color.WHITE);
				clr="CYAN";

			} else if(e.getSource()==colors[6]) {
				color_ra.setBackground(Color.MAGENTA);
				color_ra.setText("Magenta Theme");
				color_ra.setForeground(Color.WHITE);
				clr="MAGENTA";

			} else if(e.getSource()==colors[7]) {
				color_ra.setBackground(Color.PINK);
				color_ra.setText("Pink Theme");
				color_ra.setForeground(Color.WHITE);
				clr="PINK";

			} else if(e.getSource()==colors[8]) {
				color_ra.setBackground(Color.LIGHT_GRAY);
				color_ra.setText("Light Gray Theme");
				color_ra.setForeground(Color.WHITE);
				clr="LIGHT_GRAY";
			}
		}
	}

	// color의 getter
	public Color getColors() {
		return c[color_index];
	}

	public int getColorIndex() {
		return color_index;
	}
}
// 그림 소리 등 문자가 아닌 파일들은 binary로 처리해야함