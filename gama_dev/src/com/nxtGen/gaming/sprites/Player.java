package com.nxtGen.gaming.sprites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Player extends Sprite{
	
	public Player() {
		w=180;
		h=250;
		x=25;
		y=500;
		image= new ImageIcon(Player.class.getResource("player.gif"));
	}
	public void move() {
		x = x + speed;
		
	}
	public boolean outOfScreen() {
		return x>1500;
	}
}
