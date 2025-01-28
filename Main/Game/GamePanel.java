package Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
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
	public final int maxWorldCol = 32;
	public final int maxWorldRow = 32;
	public final int worldWidth = tileSize * maxScreenCol;
	public final int worldHeight = tileSize * maxScreenRow;
	
	// FPS 
	int FPS = 60;
	
	
	// LIÊN KẾT VỚI CLASS KHÁC
	KeyHandle keyH = new KeyHandle();
	Thread gameThread; // start and stop, keep your program running
	public Player player = new Player(this,keyH);
	TileManager tileM = new TileManager(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	public GamePanel() {
		this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
		this.setBackground(Color.black); // MÀU BACKGROUND
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
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
	
		player.update();
	}
	
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 
		Graphics2D g2 =  (Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
		
	}
}






