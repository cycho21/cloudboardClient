package test.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;


public class ViewManager extends JPanel{

	/**
	 * 
	 */
	private static final long 					serialVersionUID = 1L;
	private FrameManager						frame;
	private TextAreaPanel						textAreaPanel;
	private AccountPanel						accountPanel;
	private CommandPanel						commandPanel;
	
	public ViewManager(ActionListener al, KeyListener kl) {
		frame = new FrameManager("Cloudboard Messaging Test");
		textAreaPanel = new TextAreaPanel();
		accountPanel = new AccountPanel(al);
		commandPanel = new CommandPanel(kl);
		
		frame.getFrame().add(BorderLayout.NORTH, accountPanel);
		frame.getFrame().add(BorderLayout.CENTER, textAreaPanel);
		frame.getFrame().add(BorderLayout.SOUTH, commandPanel);
		
		frame.openFrame();
	}
	
	public void setConnectStateView(){
		accountPanel.getConnectButton().setEnabled(false);
		accountPanel.getDisconnectButton().setEnabled(true);

		accountPanel.getIdInputField().setEditable(false);
		accountPanel.getIdInputField().setForeground(Color.GRAY);
		
		commandPanel.getCommandField().setEditable(true);
		commandPanel.getCommandField().setForeground(Color.BLACK);
		commandPanel.getCommandField().setBackground(Color.WHITE);
	}
	
	public void setDisconnectStateView(){
		accountPanel.getConnectButton().setEnabled(true);
		accountPanel.getDisconnectButton().setEnabled(false);

		accountPanel.getIdInputField().setEditable(true);
		accountPanel.getIdInputField().setForeground(Color.BLACK);
		accountPanel.getIdInputField().setBackground(Color.WHITE);
		
		commandPanel.getCommandField().setEditable(false);
		commandPanel.getCommandField().setText(null);
		commandPanel.getCommandField().setForeground(Color.GRAY);
	}

	public String getId(){
		return accountPanel.getIdInputField().getText();
	}
	
	public String getCommand(){
		return commandPanel.getCommandField().getText();
	}
	
	public void setCommandField(String value){
		commandPanel.getCommandField().setText(value);
	}
	
	public void setCommandFieldEmpty(){
		setCommandField(null);
	}
	
	public void printLogMessage(String log){
		textAreaPanel.printText(log);
	}
	
}
