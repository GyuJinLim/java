package javabasic;

import javax.swing.JOptionPane;

public class Hi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int temp = 30;
		String data = JOptionPane.showInputDialog("이름을 입력해주세요.");
		System.out.println("이름 : " + data);
		JOptionPane.showMessageDialog(null, "온도는" + temp);
	}

}
