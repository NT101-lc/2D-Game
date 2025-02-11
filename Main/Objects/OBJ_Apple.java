package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import Game.GamePanel;

public class OBJ_Apple extends Entity {
	
	
	public OBJ_Apple(GamePanel gp) {
		super(gp);
		name = "Apple";
		down1 = setup("/object/apple.png");
	}
}