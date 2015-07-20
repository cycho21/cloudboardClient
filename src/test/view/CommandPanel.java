package test.view;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommandPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField										commandField;
	
	public CommandPanel() {
		commandField = new JTextField(50);	
		this.add(commandField, BorderLayout.CENTER);
	}

	public CommandPanel(KeyListener kl) {
		commandField = new JTextField(50);
		commandField.addKeyListener(kl);
		this.add(commandField, BorderLayout.CENTER);
	}

	public JTextField getCommandField() {
		return commandField;
	}

	public void setCommandField(JTextField commandField) {
		this.commandField = commandField;
	}

}
