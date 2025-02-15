package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.UtilityTool;

public class Entity {
	
	GamePanel gp;
	
	public int worldX,worldY;
	public int speed;
	
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public String direction = "down";
	public int spriteNum = 1;
	public int spriteCounter = 0; 
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int actionLockCounter = 0;
	public boolean collisionOn = false;
	String dialogues[] = new String[20];
	public int dialogueIndex = 0;
	//CHARACTERS LIFE
	public int maxLife;
	public int life;
	public boolean invincible = false;
	public int invincibleCounter = 0;
	public BufferedImage image,image2,image3;
	public String name;
	public boolean collision = false;
	public int type; // 0 = player, 1 = npc, 2 = monster
	
	
	
	public Entity(GamePanel gp) {this.gp = gp;}
	
	public void setAction() {}
	public void speak() {
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex = 0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		
		switch(gp.player.direction) {
		case "up":
			direction = "down"; break;
		case "down":
			direction = "up"; break;
		case "left":
			direction = "right"; break;
		case "right":
			direction = "left"; break;
		}
	}
	/**
	 *@effect
	 *hàm để check collision giữa các entity, đồng thời làm việc với player contect, như collision
	 */
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cChecker.CheckTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this,gp.npc);
		gp.cChecker.checkEntity(this,gp.monster);
		boolean contactPlayer  = gp.cChecker.checkPlayer(this);
		
		
		if(this.type == 2 && contactPlayer == true) {
			if(gp.player.invincible == false) {
				// we can give damage
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
		
		// IF COLLISION FALSE, PLAYER CAN MOVE
		if(collisionOn == false) {
			switch(direction) {
			case "up":worldY -= speed;break;
			case "down":worldY += speed;break;
			case "left":worldX -= speed;break;
			case "right":worldX += speed;break;
			}
		}
		spriteCounter++;
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
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
    		
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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }	
	}
	
	/**
	 * @effect
	 * đây là hàm dùng để load ảnh, chỉ cần gắn tên file là nó sẽ tự động load ảnh vào program
	 * @param imagePath
	 * @return
	 * trả lại image, cũng đồng nghĩa là sẽ load ảnh lên màn để sử dụng
	 */
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		} return image;}
		
		
		
	
}