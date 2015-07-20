package test.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class CommandActionListner implements KeyListener{

	private MainController								mc;
	
	public CommandActionListner(MainController mc) {
		this.mc = mc;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			try {
				mc.sendMessage();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}

// subscribe dataName user10 assert x 1 y 1
// assert dataName x 1 y 1
