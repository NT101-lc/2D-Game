package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.KeyHandle;
import Game.UtilityTool;

public class Player extends Entity {
	KeyHandle keyH;
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandle keyH) {
		super(gp);
		this.keyH = keyH;
		screenX = gp.ScreenWidth/2 - (gp.tileSize/2);
		screenY = gp.ScreenHeight/2 - (gp.tileSize/2);
		// SỬA AREA CỦA COLLISION HITBOX
		
		solidArea = new Rectangle();
		//HỘP COLLISION CHECK
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 48;
		//set gia tri co ban de chinh trong phan check object collision
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValue();
		getPlayerImage();
	}
	
	
	public void setDefaultValue() {
		// CHINH VI TRI SPAWN
		worldX = (gp.tileSize * 17);
		worldY = gp.tileSize * 20;
		speed = 4;
		direction = "down";
		//PLAYER STATUS
		maxLife = 6;
		life = maxLife;
	}
	// UP HOẠT ẢNH NHÂN VẬT
	public void getPlayerImage() {
			up1 = setup("/Player/up1");
			up2 = setup("/Player/up2");
			down1 = setup("/Player/down1");
			down2 = setup("/Player/down2");
			left1 = setup("/Player/left1");
			left2 = setup("/Player/left2");
			right1 = setup("/Player/right1");
			right2 = setup("/Player/right2");
	}
	// CHỈNH CÁC KEY MOVEMENT
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed) {
			if(keyH.upPressed == true) {direction  = "up";}
			else if(keyH.downPressed == true) {direction = "down";}
			else if(keyH.rightPressed == true) {direction = "right";}
			else if(keyH.leftPressed == true) {direction = "left";}
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.CheckTile(this);
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			//CHECK NPC'S COLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			//CHECK EVENT	
			gp.eHandle.checkEvent();
			
			gp.keyH.enterPressed = false;
			
			// IF COLLISION FALSE, PLAYER CAN MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up":worldY -= speed;break;
				case "down":worldY += speed;break;
				case "left":worldX -= speed;break;
				case "right":worldX += speed;break;
				}
			}spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		}
		
	}
	
	// HÀM CHECK ĐỂ NHẶT VÀ TÁC ĐỘNG VỚI VẬT PHẨM
	public void pickUpObject(int i) {
		if(i != 999) {
			//CODE
		}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			if(gp.keyH.enterPressed) {
				gp.GameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;	
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize,null);
		
		// PHẦN NÀY DÙNG ĐỂ BẬT HITBOX CHO COLLISION, BẬT LÊN KHI CẦN CHECK COLLISION BOX ONLY
		g2.setColor(Color.red);
		g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
	}
}
















