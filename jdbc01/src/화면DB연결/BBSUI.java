package 화면DB연결;

import 자바DB연결.BbsDAO;
import 자바DB연결.MemberDAO3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BBSUI {

	public static void open() {
		JFrame f = new JFrame();
		f.setTitle("나의 게시판");
		f.setSize(400, 800);
		JLabel l0 = new JLabel("나의 게시판");
		JLabel l01 = new JLabel("---------------");
		JLabel l1 = new JLabel("게시판 id");
		JLabel l2 = new JLabel("게시판 제목");
		JLabel l3 = new JLabel("게시판 내용");
		JLabel l4 = new JLabel("게시판 작성자");
		
		JTextField t1 = new JTextField(10);
		JTextField t2 = new JTextField(10);
		JTextField t3 = new JTextField(10);
		JTextField t4 = new JTextField(10);
		
		JButton b1 = new JButton("게시판 작성 완료");
		JButton b2 = new JButton("게시판 삭제 완료");
		JButton b3 = new JButton("게시물 검색 완료");
		
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("게시판 검색 처리");
				String no = t1.getText();
				int no2 = Integer.parseInt(no);
				BbsDAO dao = new BbsDAO();
				
				BbsVO bag = dao.one(no2); // MemberVO return
				if(bag != null) {
					//t1.setText(Integer.toString(bag.getNo()));
					t2.setText(bag.getTitle());
					t3.setText(bag.getContent());
					t4.setText(bag.getWriter());
					t1.setBackground(Color.pink);
					t2.setBackground(Color.pink);
					t3.setBackground(Color.pink);
					t4.setBackground(Color.pink);
				} else {					
					//t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t1.setBackground(Color.white);
					t2.setBackground(Color.white);
					t3.setBackground(Color.white);
					t4.setBackground(Color.white);
					JOptionPane.showMessageDialog(f, "검색 결과 없음");
				}
			} // action
		}); // b3
		// f의 위에 있는 요소들을 add로 붙여주어야 하는데,
		// 붙이는 순서대로 물 흐르듯이 붙여주고 싶음.
		// 물 흐르듯이 붙여주는 부품이 필요
		FlowLayout flow = new FlowLayout();
		
		// 폰트를 설정하기 위해 font 부품 필요
		Font font = new Font("굴림", Font.BOLD, 40);
		
		// 필요한 부품(객체)를 ram에 가져다 놓고, 준비 끝!
		// 조립을 시작해보자.
		f.setLayout(flow);
		f.add(l0);
		f.add(l01);
		f.add(l1); f.add(t1);
		f.add(l2); f.add(t2);
		f.add(l3); f.add(t3);
		f.add(l4); f.add(t4);
		
		f.add(b1); f.add(b2);
		f.add(b3); 
		l0.setFont(font); l01.setFont(font);
		l1.setFont(font); l2.setFont(font); l3.setFont(font);
		l4.setFont(font);
		t1.setFont(font); t2.setFont(font); t3.setFont(font); t4.setFont(font);
		b1.setFont(font); b2.setFont(font); b3.setFont(font);
		
		t1.setBackground(Color.white); t1.setForeground(Color.red);
		t2.setBackground(Color.white); t2.setForeground(Color.red);
		t3.setBackground(Color.white); t3.setForeground(Color.red);
		t4.setBackground(Color.white); t4.setForeground(Color.red);
		
		b1.setBackground(Color.yellow); // 배경색
		b1.setForeground(Color.black); // 글자색
		b2.setBackground(Color.red); b2.setForeground(Color.black);
		b3.setBackground(Color.blue); b3.setForeground(Color.black);
		
		
		f.getContentPane().setBackground(Color.green);
		// 맨 끝으로~
		f.setVisible(true);

	}

}
