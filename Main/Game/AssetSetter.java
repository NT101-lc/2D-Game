package Game;

import Entity.NPC_MagicFrog;
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
		
		
	}
	
	public void setNpc() {
		gp.npc[0] = new NPC_MagicFrog(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
	}
	
}
