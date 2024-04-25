package com.nxtGen.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.nxtGen.gaming.sprites.Enemy;
import com.nxtGen.gaming.sprites.Player;

public class Board extends JPanel {
	Timer timer;
    BufferedImage backgroundImage;
    Player player;
    Enemy enemies[] = new Enemy[4]; 
    public Board() {
        setSize(1500, 900);
        loadBackGroundImage();
        player = new Player();
        loadEnemies();
        gameLoop();
        bindEvents();
        setFocusable(true);
        
    }
    private void gameOver(Graphics pen) {
    	if(player.outOfScreen()) {
    		pen.setFont(new Font("times", Font.BOLD, 40));
			pen.setColor(Color.BLACK);
			pen.drawString("Game Win", 1500/2, 900/2);
			timer.stop();
			return;
    	}
    	for(Enemy enemy : enemies) {
    		if(isCollide(enemy)) {
    			pen.setFont(new Font("times", Font.BOLD, 40));
    			pen.setColor(Color.BLACK);
    			pen.drawString("Game Over", 1500/2, 900/2);
    			timer.stop();
    		}
    		
    	}
    }
    private boolean isCollide(Enemy enemy) {
    	int xDistance = Math.abs(player.x - enemy.x);
    	int yDistance = Math.abs(player.y - enemy.y);
    	int maxH = Math.max(player.h, enemy.h);
    	int maxW = Math.max(player.w, enemy.w);
    	return xDistance <= maxW-130 && yDistance <= maxH-130;
    }
    private void bindEvents() {
	    addKeyListener(new KeyListener() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	        	if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
	            player.speed = 8;
	        	}
	        	else if(e.getKeyCode() ==KeyEvent.VK_LEFT) {
	        		player.speed=-8;
	        	}
	        	
	        	else if(e.getKeyCode() == KeyEvent.VK_UP) {
	        		player.speed=5;
	        	}
	        	else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
	        		player.speed=-5;
	        	}
	        	
	        }

	        @Override
	        public void keyReleased(KeyEvent e) {
	            // Handle key release events here
	            player.speed = 0;
	        }

	        @Override
	        public void keyTyped(KeyEvent e) {
	            // Handle key typed events here
	        }
	    });
	}

    private void loadEnemies() {
    	int x = 350;
    	int gap = 300;
    	int speed = 8;
    	for(int i=0;i<enemies.length;i++) {
    		enemies[i] = new Enemy(x, speed);
    		x = x+ gap;
    		speed =speed + 5;
    	}
    }
    private void gameLoop() {
    	timer = new Timer(30, (e)->repaint());
    	timer.start();
        
    }

    private void loadBackGroundImage() {
        try {
            backgroundImage = ImageIO.read(Board.class.getResource("Board_BG.jpg"));
        } catch (IOException e) {
            System.out.println("Background Image Not Found......");
            System.exit(1);
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void printEnemies(Graphics pen) {
    	for(Enemy enemy:enemies) {
    		enemy.draw(pen);
    		enemy.move();
    	}
    }

    @Override
    protected void paintComponent(Graphics pen) {
        // all Printing logic
        super.paintComponent(pen);
        pen.drawImage(backgroundImage, 0, 0, 1500, 900, null);
        player.draw(pen);
        player.move();
        printEnemies(pen);
        gameOver(pen);
    }
}
