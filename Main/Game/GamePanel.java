package Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.Player;
import Tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
	//SCREEN SETTING
	final int originalTileSize = 16;
	final int scale = 3;
	// CHỈNH GIÁ TRỊ CỦA Ô VÀ BẢNG TRONG PANEL
	public final int tileSize = originalTileSize*scale; // 48x48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int ScreenWidth = tileSize * maxScreenCol;	
	public final int ScreenHeight = tileSize * maxScreenRow;
	//WORLD SeTTIngs
	public int maxWorldCol = 50;
	public int maxWorldRow = 50;
	// FPS 
	int FPS = 60;
	// LIÊN KẾT VỚI CLASS KHÁC
	// SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyHandle keyH = new KeyHandle(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public EventHandle eHandle = new EventHandle(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	
	// ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public Entity obj[] = new Entity[10]; // 10 slots for the objects, display 10 objects at the same time
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	//GAME STATE
	public final int titleState = 0;
	public int GameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	

	/**
	 * @effect
	 * tạo cửa sổ vào set màu background
	 */
	public GamePanel() {
		this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
		this.setBackground(Color.black); // MÀU BACKGROUND
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	/**
	 * @effect
	 * liên kết đến hình ảnh và đưa vào cửa sổ game
	 */
	// QUẢN LÍ OBJECT
	public void setupGame() {
		aSetter.setObject();	
		aSetter.setNpc();
		aSetter.setMonster();
		GameState = titleState;
	}
	
	public void startGameThread() {
		// passing gamepanel in this constructor
		gameThread = new Thread(this);
		gameThread.start(); // call the run method
	}

	
	// LOOP GAME
	@Override
	public void run() {
		
		double drawInterval = 1000000000 / FPS; // 0.0166667
		double nextDrawTime = System.nanoTime() + drawInterval; // current time + interval
		long timer = 0;
		int drawCount = 0;
		
		
		while(gameThread != null) {
			
			// UPDATE 1 : Update information such as characters position
			update();
			// UPDATE 2 : draw screen with update information
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if(GameState == 1) {
			player.update();
			//UPDATE NPC MOVEMENT
			for(int i = 0 ; i < npc.length ; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			for(int i = 0 ; i < monster.length ; i++) {
				if(monster[i] != null) {
					monster[i].update();
				}
			}
		}
		if(GameState == pauseState) {
			
		}
		}
	
	/**
	 * @effect
	 * method dùng để vẽ, như là các ô, npc hay là nhân vật, method chính để load hình ảnh
	 * phải sắp xếp thứ tự vẽ nếu không sẽ ảnh hưởng đến thứ tự của cảnh, npc trên hình
	 * @attributes
	 * GameState Integer
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 
		Graphics2D g2 =  (Graphics2D)g;
		
		
		if(GameState == titleState) {
			ui.draw(g2);
		}
		else {
		// TILE
		tileM.draw(g2);
		
		entityList.add(player);
		for(int i = 0 ; i < npc.length ; i++) {
			if(npc[i] != null) {
				entityList.add(npc[i]);
			}
		}
		
		for(int i = 0 ; i < obj.length ; i++) {
			if(obj[i] != null) {
				entityList.add(obj[i]);
			}
		}
		for(int i = 0 ; i < monster.length ; i++) {
			if(monster[i] != null) {
				entityList.add(monster[i]);
			}
		}
		
		//SORT
		Collections.sort(entityList, new Comparator<Entity>() {

			@Override
			public int compare(Entity e1, Entity e2) {
				
				int result = Integer.compare(e1.worldY, e2.worldY);
				
				return result;
			}
		});
		
		//DRAW ENTITIES
		for(int i = 0 ; i < entityList.size() ; i++) {
			entityList.get(i).draw(g2);
		}
		//EMPTY THE LIST 
		entityList.clear();
		
		//UI
		ui.draw(g2);
		}
		
	}

	/**
	 * @effect
	 * dùng để bắt đầu 1 file nhạc và loop nó trong suốt quá trình game
	 * @param i
	 */
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	/**
	 * @effect
	 * dừng nhạc, thường dùng với method playMusic
	 * @param i
	 */
	public void stopMusic(int i) {
		music.stop();
	}
	/**
	 * @effect
	 * sound effect
	 * @param i
	 */
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}


















