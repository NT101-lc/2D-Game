package Game;

import Objects.OBJ_Apple;
import Objects.OBJ_Chest;
import Objects.OBJ_Door;
import Objects.OBJ_GKey;
import Objects.OBJ_SKey;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	// HÀM ĐỂ LOAD VẬT PHẨM, CHÚNG Ý 10 VẬT PHẨM LÀ LIMITED, CẦN CHỈNH Ở GAME PANEL
	public void setObject() {
		
		gp.obj[0] = new OBJ_GKey();
		gp.obj[0].worldX = gp.tileSize * 14;
		gp.obj[0].worldY = gp.tileSize * 5;
		
		gp.obj[1] = new OBJ_Chest();
		gp.obj[1].worldX = gp.tileSize * 6;
		gp.obj[1].worldY = gp.tileSize * 16;
		
		gp.obj[2] = new OBJ_Door();
		gp.obj[2].worldX = gp.tileSize * 9;
		gp.obj[2].worldY = gp.tileSize * 16;
		
		gp.obj[3] = new OBJ_SKey();
		gp.obj[3].worldX = gp.tileSize * 19;
		gp.obj[3].worldY = gp.tileSize * 16;
		
		gp.obj[4] = new OBJ_Apple();
		gp.obj[4].worldX = gp.tileSize * 14;
		gp.obj[4].worldY = gp.tileSize * 21;
	}
	
	
}
