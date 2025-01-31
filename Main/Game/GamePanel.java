package Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import Entity.Player;
import Objects.SuperObject;
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
	
	// FPS 
	int FPS = 60;
	
	
	// LIÊN KẾT VỚI CLASS KHÁC
	// SYSTEM
	TileManager tileM = new TileManager(this);
	KeyHandle keyH = new KeyHandle();
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	
	// ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10]; // 10 slots for the objects, display 10 objects at the same time
	
	
	// TẠO CỬA SỔ 
	public GamePanel() {
		this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
		this.setBackground(Color.black); // MÀU BACKGROUND
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	// QUẢN LÍ OBJECT
	public void setupGame() {
		aSetter.setObject();	
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
		player.update();}
	
	//VẼ 
	// THƯỜNG CẦN SẮP XẾP CÁC METHOD VÌ NÓ ẢNH HƯỞNG ĐẾN LAYER DẪN ĐẾN CÁI GÌ CẦN VẼ TRƯỚC VÀ VẼ SAU
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); 
		Graphics2D g2 =  (Graphics2D)g;
		// TILE
		tileM.draw(g2);
		// VE OBJ
		for(int i = 0 ; i < obj.length ; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// VE PLAYER
		player.draw(g2);
		//UI
		ui.draw(g2);
		
		//END
		g2.dispose();
	}

	// DÙNG ĐỂ BẮT ĐẦU NHẠC VÀ LOOP TRONG SUỐT QUÁ TRÌNH CHƠI GAME
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	// DỪNG NHẠC, THƯỜNG DÙNG VỚI METHOD TRÊN
	public void stopMusic(int i) {
		music.stop();
	}
	// DÙNG ĐỂ SỬ DỤNG 1 ĐOẠN NHẠC NGẮN - SOUND EFFECT
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}
}


















