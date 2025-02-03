package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandle implements KeyListener{
	GamePanel gp;
	public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed;

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public KeyHandle(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); // get key
		if(gp.GameState == gp.titleState) {
			
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0 ) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.GameState = gp.playState;
				}
				if(gp.ui.commandNum == 1) {
					// LATER
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		
		//PLAY STATE
		if(gp.GameState == gp.playState) {
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_P) {
				gp.GameState = gp.pauseState;
			}
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}
		//PAUSE STATE
		else if(gp.GameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
				gp.GameState = gp.playState;
			}
		}
		else if(gp.GameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.GameState = gp.playState;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode(); // get key
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
