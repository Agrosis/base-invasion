package com.jantox.baseinvasion;

import java.awt.BorderLayout;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class BaseInvasionFrame extends JFrame {

	private BaseInvasion bi;
	
	public BaseInvasionFrame() {
		this.setTitle("Base Invasion");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,600);
		bi = new BaseInvasion(800,600);
		this.setLayout(new BorderLayout());
		this.add(bi, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {		
		new BaseInvasionFrame().setVisible(true);
	}
	
}
