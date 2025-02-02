package Entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Game.GamePanel;
import Game.UtilityTool;

public class NPC_MagicFrog extends Entity {
	public NPC_MagicFrog(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
	}
	public void getImage() {
		up1 = setup("/npc/frogup1");
		up2 = setup("/npc/frogup2");
		down1 = setup("/npc/frogdown1");
		down2 = setup("/npc/frogdown2");
		left1 = setup("/npc/frogleft1");
		left2 = setup("/npc/frogleft2");
		right1 = setup("/npc/frogright1");
		right2 = setup("/npc/frogright2");
	}
	// AI CHO NPC VÀ NHỨNG RANDOM MOVE OR SMT LIKE THAT IDK
	public void setAction() {
		
		actionLockCounter++;
		if(actionLockCounter == 120) {
			Random random = new Random();
		int i = random.nextInt(100) + 1; // pick up 1-100
		
		if(i <= 25) {
			direction = "up";
		}
		if(i > 25 && i <= 50) {
			direction = "down";
		}
		if(i > 50 && i <= 75) {
			direction = "left";
		}
		if(i > 75 && i <= 100) {
			direction = "right";
			}
		
		actionLockCounter =0;
		}
		
		
	}
}







