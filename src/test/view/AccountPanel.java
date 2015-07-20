package test.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton							connectButton;
	private JButton							disconnectButton;
	private JLabel							idLabel;
	private JTextField						idInputField;
	
	public AccountPanel() {
		
	}
	
	public AccountPanel(ActionListener al){
		connectButton = new JButton("CONNECT");
		disconnectButton = new JButton("DISCONNECT");
		idLabel = new JLabel("ID : ");
		idInputField = new JTextField(10);
		idInputField.setActionCommand("CONNECT");
		
		connectButton.addActionListener(al);
		disconnectButton.addActionListener(al);
		idInputField.addActionListener(al);
		
		this.add(idLabel, BorderLayout.LINE_START);
		this.add(idInputField);
		this.add(connectButton);
		this.add(disconnectButton);
	}
	

	public JButton getConnectButton() {
		return connectButton;
	}

	public void setConnectButton(JButton connectButton) {
		this.connectButton = connectButton;
	}

	public JButton getDisconnectButton() {
		return disconnectButton;
	}

	public void setDisconnectButton(JButton disconnectButton) {
		this.disconnectButton = disconnectButton;
	}

	public JLabel getIdLabel() {
		return idLabel;
	}

	public void setIdLabel(JLabel idLabel) {
		this.idLabel = idLabel;
	}

	public JTextField getIdInputField() {
		return idInputField;
	}

	public void setIdInputField(JTextField idInputField) {
		this.idInputField = idInputField;
	}

}
