package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

public class OBJ_GKey extends SuperObject {
	GamePanel gp;
	
	public OBJ_GKey(GamePanel gp) {

		this.gp = gp;
		
		name = "GKey";
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/object/GKey.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
