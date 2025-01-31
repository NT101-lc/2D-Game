package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_GKey extends SuperObject {
	public OBJ_GKey() {
		name = "GKey";
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/object/GKey.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
