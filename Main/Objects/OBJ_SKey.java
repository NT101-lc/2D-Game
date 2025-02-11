package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import Game.GamePanel;

public class OBJ_SKey extends Entity {
	
	
	public OBJ_SKey(GamePanel gp) {
		super(gp);
		name = "SKey";
		down1 = setup("/object/SKey");
	}
}