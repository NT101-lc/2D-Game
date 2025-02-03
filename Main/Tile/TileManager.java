package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import Game.GamePanel;
import Game.UtilityTool;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    
    
    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[60];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
   
        getTileImage();
        loadMap();
    }
    //setup load anh
    public void getTileImage() {   
    	//PLACEHOLDER
    		setup(0,"000",true); 
    		setup(1,"001",true); 
        	setup(2,"002",true); 
        	setup(3,"003",true); 
        	setup(4,"004",true); 
        	setup(5,"005",true); 
        	setup(6,"006",true); 
        	setup(7,"007",true); 
        	setup(8,"008",true); 
        	setup(9,"009",true);
        	setup(10,"010",true);
        	setup(11,"011",true);
        	setup(12,"012",true);
        	setup(13,"013",true);
        	setup(14,"014",true);
        	setup(15,"015",true);
        	setup(16,"016",true);
        	setup(17,"017",true);
        	setup(18,"018",true);
        	setup(19,"019",true);
        	setup(20,"020",true);
        	setup(21,"021",true);
        	setup(22,"022",true);
        	setup(23,"023",false);
        	setup(24,"024",false);
        	setup(25,"025",false);
        	setup(26,"026",false);
        	setup(27,"027",false);
        	setup(28,"028",false);
        	setup(29,"029",false);
        	setup(30,"030",false);
        	setup(31,"031",false);
        	setup(32,"032",false);
        	setup(33,"033",false);
        	setup(34,"034",false);
        	setup(35,"035",false);
        	setup(36,"036",false);
        	setup(37,"037",false);
        	setup(38,"038",false);
        	setup(39,"039",false);
           	setup(40,"040",false);
        	setup(41,"041",false);
        	setup(42,"042",false);
        	setup(43,"043",false);
        	setup(44,"044",false);
        	setup(45,"045",false);
        	setup(46,"046",false);
        	setup(47,"047",false);
        	setup(48,"048",false);
        	setup(49,"049",false);
        	setup(50,"050",false);
        	setup(51,"051",false);
        	setup(52,"052",false);
        	setup(53,"053",false);
        //

    }
    public void setup(int i, String imageName,boolean collision) {
    	UtilityTool uTool = new UtilityTool();
    	
    	try {
    		tile[i] = new Tile();
    		tile[i].image = ImageIO.read(getClass().getResourceAsStream("/title/" + imageName + ".png"));
    		tile[i].image = uTool.scaleImage(tile[i].image, gp.tileSize, gp.tileSize);
    		tile[i].collision = collision;
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    // VẼ MAP, KHÔNG CẦN ĐỘNG VÀO MẤY
    public void loadMap() {
    	
    	try {
    		InputStream is = getClass().getResourceAsStream("/map/mapV3.txt");
    		BufferedReader br = new BufferedReader(new InputStreamReader(is));
    		int col = 0,row = 0;
    		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
    			
    			String line = br.readLine(); // READ LINE FROM THE TEXT FILE
    			while(col < gp.maxWorldCol) {
    				String numbers[] = line.split(" "); // chia cách các space trong 0 1 0 0 1
    				int num = Integer.parseInt(numbers[col]); // chuyển từ string qua int
    				mapTileNum[col][row] = num; col++;
    			}	
    			if(col == gp.maxWorldCol) {
    				col = 0; row++;}
    			}
    			br.close(); // close 
    	}catch(Exception e) {}
    }
    // HÃM VẼ SCREEN VÀ WORLD KHI DI CHUYỂN
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
        	
            int tileNum = mapTileNum[worldCol][worldRow];
            //MAP DRAW
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            
            g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
            	worldCol = 0;
            	worldRow++;
            }
        }
    }

    }
    
    
  
