package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		screenX = gp.ScreenWidth/2 - (gp.tileSize/2);
		screenY = gp.ScreenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 32;
		solidArea.width = 16;
		solidArea.height = 16;

		setDefaultValue();
		getPlayerImage();
	}
	
	
	public void setDefaultValue() {
		// CHINH VI TRI SPAWN
		worldX = gp.tileSize * 16;
		worldY = gp.tileSize * 16;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up = ImageIO.read(getClass().getResourceAsStream("/Player/up.png"));
			down = ImageIO.read(getClass().getResourceAsStream("/Player/down.png"));
			left = ImageIO.read(getClass().getResourceAsStream("/Player/down.png"));
			right = ImageIO.read(getClass().getResourceAsStream("/Player/down.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed) {
			
			if(keyH.upPressed == true) {
				direction  = "up";}
			else if(keyH.downPressed == true) {
				direction = "down";}
			else if(keyH.rightPressed == true) {
				direction = "right";}
			else if(keyH.leftPressed == true) {
				direction = "left";}
		
			collisionOn = false;
			gp.cChecker.CheckTile(this);
		
		
			// IF COLLISION FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up":worldY -= speed;break;
				case "down":worldY += speed;break;
				case "left":worldX -= speed;break;
				case "right":worldX += speed;break;
				}
			}
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
		case "left":
			image = left;
			break;
		case "right":
			image = right;
			break;
		}
		
		g2.drawImage(image, screenY, screenY, gp.tileSize,gp.tileSize,null);
	}
}
















