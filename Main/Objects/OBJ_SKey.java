package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GamePanel;

public class OBJ_SKey extends SuperObject {
	
	GamePanel gp;
	
	public OBJ_SKey(GamePanel gp) {
		this.gp = gp;
		
		name = "SKey";
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/object/SKey.png"));			
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
