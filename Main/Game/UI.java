package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Objects.OBJ_GKey;

// THIS CLASS HANDLE ALL THE ON-SCREEN UI
// LIKE TEXT, ITEM ICON, HEART,...
public class UI {
	GamePanel gp;
	Font arial_40,arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	double playTime = 0;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial",Font.PLAIN,32);
		arial_80B = new Font("Arial",Font.BOLD,80);
		OBJ_GKey key = new OBJ_GKey(gp);
		keyImage = key.image;
	}
	public void showMessage(String txt) {
		message = txt;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		if(gameFinished == true ) {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			String text;
			int textLength;
			int x ;
			int y;
			// DISPLAY TEXT WHEN FOUND TREASURE
			text = "You've found the treasure";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.ScreenWidth/2 - textLength/2;
			y = gp.ScreenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			//CHINH FONT LEN 80 VA DISPLAY CONGRATULATION
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			text = "Congratulation!!!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.ScreenWidth/2 - textLength/2;
			y = gp.ScreenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			// CHINH FONT VE 40 VA DISPLAY TGIAN CHOI
			g2.setFont(arial_40);
			text = "Your time is :" + dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.ScreenWidth/2 - textLength/2;
			y = gp.ScreenHeight/2 + (gp.tileSize*4);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;	
		}
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2,gp.tileSize/2,gp.tileSize,	gp.tileSize,null);
			g2.drawString("x " + gp.player.GKey, 70, 61);
			
			//TIME
			playTime += (double)1/60;
			g2.drawString("Time =" + dFormat.format(playTime), gp.tileSize*11, 65);
			
			//MESSAGE
			if(messageOn) {
				g2.setFont(g2.getFont().deriveFont(24F));
				g2.drawString(message, 35, 200);
				
				messageCounter++;
				
				if(messageCounter > 120) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
	}
}
