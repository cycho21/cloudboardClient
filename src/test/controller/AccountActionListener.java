package test.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountActionListener implements ActionListener{
	private MainController						control;
	
	public AccountActionListener(MainController mc) {
		this.control = mc;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("CONNECT")){
			control.connect();
		}
		if(e.getActionCommand().equals("DISCONNECT")){
			control.disconnect();
		}
		
	}

}
