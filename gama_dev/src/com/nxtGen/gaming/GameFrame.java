package com.nxtGen.gaming;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		Board board = new Board();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Dodge and Go");
		setSize(1500,900);
		setResizable(false);
		setLocationRelativeTo(null);
		add(board);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameFrame();
		//new Board();
		
	}

}
