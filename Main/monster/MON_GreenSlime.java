package monster;

import java.util.Random;

import Entity.Entity;
import Game.GamePanel;

public class MON_GreenSlime extends Entity {
	public MON_GreenSlime(GamePanel gp) {
		super(gp);
		
		name = "green_slime";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		type = 2;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/monster/slime1");
		up2 = setup("/monster/slime2");
		down1 = setup("/monster/slime1");
		down2 = setup("/monster/slime2");
		left1 = setup("/monster/slime1");
		left2 = setup("/monster/slime2");
		right1 = setup("/monster/slime1");
		right2 = setup("/monster/slime2");
	}
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








