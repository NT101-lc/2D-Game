package Game;

import Entity.NPC_MagicFrog;
import Objects.OBJ_Apple;
import Objects.OBJ_Chest;
import Objects.OBJ_Door;
import Objects.OBJ_GKey;
import Objects.OBJ_SKey;
import monster.MON_GreenSlime;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	// HÀM ĐỂ LOAD VẬT PHẨM, CHÚNG Ý 10 VẬT PHẨM LÀ LIMITED, CẦN CHỈNH Ở GAME PANEL
	public void setObject() {
		
	}
	/**
	 * @effect
	 * sử dụng để cho npc lên map, dòng dưới dùng đêt set vị trí spawn của npc
	 */
	public void setNpc() {
		gp.npc[0] = new NPC_MagicFrog(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
	}
	/**
	 * @effect
	 * như method trên nhưng thay vì npc thì là của quái/mob
	 */
	public void setMonster() {
		gp.monster[0] = new MON_GreenSlime(gp);
		gp.monster[0].worldX = gp.tileSize * 23;
		gp.monster[0].worldY = gp.tileSize * 23;
	}
}








