package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import Game.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];
    
    
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
   
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
        	// GRASS TILE
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/title/grass.png"));
            //BRICK TILE
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/title/brick.png"));
            tile[1].collision = true;
            //WATER TILE
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/title/water.png"));
            tile[2].collision = true;
            //TREE TILE
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/title/tree.png"));
            tile[3].collision = true;            
            //Rock TILE
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/title/rock.png"));
            tile[4].collision = true;   
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap() {
    	
    	try {
    		InputStream is = getClass().getResourceAsStream("/map/map01.txt");
    		BufferedReader br = new BufferedReader(new InputStreamReader(is));
    		
    		int col = 0, row = 0;
    		
    		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
    			
    			String line = br.readLine(); // READ LINE FROM THE TEXT FILE
    			
    			while(col < gp.maxWorldCol) {
    				
    				String numbers[] = line.split(" "); // chia cách các space trong 0 1 0 0 1
    				
    				int num = Integer.parseInt(numbers[col]); // chuyển từ string qua int
    				
    				mapTileNum[col][row] = num;
    				col++;
    			}	
    			if(col == gp.maxWorldCol) {
    				col = 0; 
    				row++;				
    			}
    		}
    			br.close(); // close 
    	}catch(Exception e) {
    		
    	}
    }
    
    
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
        	
            int tileNum = mapTileNum[worldCol][worldRow];
            //MAP DRAW
            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            if(worldX + gp.titleSize> gp.player.worldX - gp.player.screenX &&
            		worldX - gp.titleSize< gp.player.worldX + gp.player.screenX &&
            		worldY + gp.titleSize> gp.player.worldY - gp.player.screenY &&
            		worldY - gp.titleSize< gp.player.worldY + gp.player.screenY) {
            
            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
            	worldCol = 0;
            	worldRow++;
            }
        }
    }

    }
    
    
  
