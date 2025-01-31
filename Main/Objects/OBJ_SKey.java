package Objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_SKey extends SuperObject {
	public OBJ_SKey() {
		name = "SKey";
		try{
			image = ImageIO.read(getClass().getResourceAsStream("/object/SKey.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
