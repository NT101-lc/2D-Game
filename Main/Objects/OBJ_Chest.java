package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import Game.GamePanel;

public class OBJ_Chest extends Entity {
	
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		name = "Chest";
		down1 = setup("/object/chest");
	}
}