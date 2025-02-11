package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import Game.GamePanel;

public class OBJ_Door extends Entity {
	
	
	public OBJ_Door(GamePanel gp) {
		super(gp);
		name = "Door";
		down1 = setup("/object/door");
	}
}