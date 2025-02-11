package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import Game.GamePanel;

public class OBJ_Heart extends Entity {
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		
		name = "Heart";
		image = setup("/object/heart-full");
		image2 = setup("/object/heart-half");
		image3 = setup("/object/heart-empty");
		
	}
}
