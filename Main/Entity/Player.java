package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.KeyHandle;

public class Player extends Entity {
	GamePanel gp;
	KeyHandle keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandle keyH) {
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.ScreenWidth/2 - (gp.titleSize/2);
		screenY = gp.ScreenHeight/2 - (gp.titleSize/2);
		setDefaultValue();
		getPlayerImage();
	}
	
	
	public void setDefaultValue() {
		// CHINH VI TRI SPAWN
		worldX = gp.titleSize * 16;
		worldY = gp.titleSize * 16;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/Player/up.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/Player/down.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(keyH.upPressed == true) {
			direction  = "up";
			worldY -= speed;
		}
		else if(keyH.downPressed == true) {
			direction = "down";
			worldY += speed;
		}
		else if(keyH.rightPressed == true) {
			worldX += speed;
		}
		else if(keyH.leftPressed == true) {
			worldX -= speed;
		}
	}
	public void draw(Graphics2D g2) {
//		g2.setColor(Color.white);
//		
//		g2.fillRect(x,y,gp.titleSize,gp.titleSize);
		
		BufferedImage image = null;
		switch(direction) {
		case "up":
			image = up;
			break;
		case "down":
			image = down;
			break;	
		}
		
		g2.drawImage(image, screenY, screenY, gp.titleSize,gp.titleSize,null);
	}
}
















