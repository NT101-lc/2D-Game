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
    Tile[] tile;
    int mapTileNum[][];
    
    
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
            //WATER TILE
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/title/water.png"));
            //TREE TILE
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/title/tree.png"));
            //Rock TILE
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/title/rock.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap() {
    	
    	try {
    		InputStream is = getClass().getResourceAsStream("/map/map01.txt");
    		BufferedReader br = new BufferedReader(new InputStreamReader(is));
    		
    		int col = 0, row = 0;
    		
    		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
    			
    			String line = br.readLine(); // READ LINE FROM THE TEXT FILE
    			
    			while(col < gp.maxScreenCol) {
    				
    				String numbers[] = line.split(" "); // chia cách các space trong 0 1 0 0 1
    				
    				int num = Integer.parseInt(numbers[col]); // chuyển từ string qua int
    				
    				mapTileNum[col][row] = num;
    				col++;
    			}	
    			if(col == gp.maxScreenCol) {
    				col = 0; 
    				row++;				
    			}
    		}
    			br.close(); // close 
    	}catch(Exception e) {
    		
    	}
    }
    
    
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
        	
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.titleSize, gp.titleSize, null);
            col++;
            x += gp.titleSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.titleSize;
            }
        }
    }

    }
    
    
  
