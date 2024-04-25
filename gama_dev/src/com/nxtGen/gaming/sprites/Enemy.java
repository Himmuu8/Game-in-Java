package com.nxtGen.gaming.sprites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy extends Sprite{

	public Enemy(int x, int speed) {
		y = 5;
		this.x = x;
		this.speed = speed;
		w = 100;
		h = 100;
		image = new ImageIcon(Enemy.class.getResource("enemy.gif"));
	}
	
	public void move() {
		if(y>900) {
			y = 0;
		}
		y = y + speed;
	}
}
