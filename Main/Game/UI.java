package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import Objects.OBJ_GKey;
import Objects.OBJ_Heart;
import Objects.SuperObject;

// THIS CLASS HANDLE ALL THE ON-SCREEN UI
// LIKE TEXT, ITEM ICON, HEART,...
public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font Monica;
	BufferedImage heart_full,heart_half,heart_empty;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	double playTime = 0;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	public String currentDialogue = "";
	public int commandNum = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			Monica = Font.createFont(Font.TRUETYPE_FONT, is);
		}catch(IOException e) {
			e.printStackTrace();
		}catch(FontFormatException e){
			e.printStackTrace();
		}
		//CREATE HUG OBJECT
		SuperObject heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_empty = heart.image3;
	}
	public void showMessage(String txt) {
		message = txt;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(Monica);
		g2.setColor(Color.white);
		// title state
		if(gp.GameState == gp.titleState) {
			drawTitleScreen();
		}
		// PLAY STATE
		if(gp.GameState == gp.playState) {
			drawPlayerLife();
		}
		//PAUSED STATE
		if(gp.GameState == gp.pauseState) {
			drawPauseScreen();
		}
		//DIALOGUE STATE
		if(gp.GameState == gp.dialogueState) {
			drawPlayerLife();
			drawDialogueScreen();
		}
	}
	
	public void drawPlayerLife() {
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_empty, x, y, null);
			i++;
			x+= gp.tileSize;
		}
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);			
			}
			i++; x+=gp.tileSize;
		}
	}
	public void drawTitleScreen() {
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String txt = "No name yet";
		int x = getXforCenteredText(txt);
		int y = gp.tileSize*3;
		//SHADOW
		g2.setColor(Color.DARK_GRAY);
		g2.drawString(txt, x+5, y+5);
		//MAIN COLOR
		g2.setColor(Color.white);
		g2.drawString(txt, x, y);
		
		
		//NEW GAME MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		txt = "NEW GAME";
		x = getXforCenteredText(txt);
		y += gp.tileSize * 5;
		g2.drawString(txt, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x- gp.tileSize,y);
		}
		
		//LOAD GAME MENU
		txt = "LOAD GAME";
		x = getXforCenteredText(txt);
		y += gp.tileSize;
		g2.drawString(txt, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x- gp.tileSize,y);
		}
		// QUIT MENU
		txt = "QUIT";
		x = getXforCenteredText(txt);
		y += gp.tileSize;
		g2.drawString(txt, x, y);
		if(commandNum == 2) {
			g2.drawString(">", x- gp.tileSize,y);
		}
	}
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.ScreenWidth/2;
		g2.drawString(text, x, y);
	}
	public void drawDialogueScreen() {
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.ScreenWidth - (gp.tileSize * 4);
		int height = gp.tileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")){
			g2.drawString(line, x, y);
			y += 40;
		}
		
	}
	
	public void drawSubWindow(int x,int y,int width, int height) {
		
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.ScreenWidth/2 - length/2;
		return x;
	}
}












