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
	public int GKey = 0;
	int SKey = 0;
	
	public Player(GamePanel gp, KeyHandle keyH) {
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.ScreenWidth/2 - (gp.tileSize/2);
		screenY = gp.ScreenHeight/2 - (gp.tileSize/2);
		// SỬA AREA CỦA COLLISION HITBOX
		
		solidArea = new Rectangle();
		//HỘP COLLISION CHECK
		solidArea.x = 16;
		solidArea.y = 32;
		solidArea.width = 16;
		solidArea.height = 12;
		//set gia tri co ban de chinh trong phan check object collision
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValue();
		getPlayerImage();
	}
	
	
	public void setDefaultValue() {
		// CHINH VI TRI SPAWN
		worldX = (gp.tileSize * 14);
		worldY = gp.tileSize * 16;
		speed = 4;
		direction = "down";
	}
	// UP HOẠT ẢNH NHÂN VẬT
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
	// CHỈNH CÁC KEY MOVEMENT
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
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.CheckTile(this);
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			
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
	
	// HÀM CHECK ĐỂ NHẶT VÀ TÁC ĐỘNG VỚI VẬT PHẨM
	public void pickUpObject(int i) {
		if(i != 999) {
			String objectName = gp.obj[i].name;
			switch(objectName) {
			case "GKey":
				GKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You have received a key!");
				break;
			case "Door":
				if(SKey > 0) {
					gp.obj[i] = null;
					SKey--;
				}
				else {
					gp.ui.showMessage("You need key");
				}
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				if(GKey > 0) {
					gp.obj[i] = null;
					GKey--;
				}
				gp.ui.showMessage("You have found the treasure!!");
				break;
			case "SKey":
				SKey++;
				gp.obj[i] = null;
				break;
			case "Apple":
				gp.playSE(0);
				speed += 10;
				gp.ui.showMessage("faster!");
				gp.obj[i] = null; break;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
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
		
		g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize,null);
		
		// PHẦN NÀY DÙNG ĐỂ BẬT HITBOX CHO COLLISION, BẬT LÊN KHI CẦN CHECK COLLISION BOX ONLY
		g2.setColor(Color.red);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
}
















