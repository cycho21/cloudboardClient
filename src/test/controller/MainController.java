package test.controller;

import java.io.IOException;

import test.view.ViewManager;

public class MainController {
	private ViewManager						view;
	private AccountActionListener			aal;
	private CommandActionListner			cal;
	private Sender							sender;
	private Receiver						receiver;
	
	public MainController() throws IOException, InterruptedException {
		aal = new AccountActionListener(this);
		cal = new CommandActionListner(this);
		view =  new ViewManager(aal, cal);
		sender = new Sender();
		receiver = new Receiver(this);
		view.setDisconnectStateView();
	}

	public void sendMessage() throws IOException {
		
		String command = view.getCommand();
		
		view.setCommandFieldEmpty();
		
		sender.sendMessage(receiver.getQueueName(), command);

	}
	
	public void sendMessage(String command) throws IOException {
		
		view.setCommandFieldEmpty();
		
		sender.sendMessage(receiver.getQueueName(), command);
		
	}
	
	public void receiveMessage(){
		view.printLogMessage(receiver.getMessage());
		
		receiver.setMessage("");
	}

	public void connect() {
		view.setConnectStateView();
		view.printLogMessage("Connecting to server from \"" + view.getId() + "\"...");

		try {
			receiver.createQueue(view.getId());
			this.sendMessage("connect " + view.getId());
			receiver.start();
			view.printLogMessage("Connect success.");
			view.printLogMessage("============================================");
			view.printLogMessage("Ready to send message.");
		} catch (IOException e) {
			System.err.println("Connect failed.");
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		view.printLogMessage("Disonnecting to server from \"" + view.getId() + "\"...");
		try {
			receiver.createQueue(view.getId());
			this.sendMessage("disconnect " + view.getId());
			receiver.start();
			view.printLogMessage("Disconnect success.");
			view.printLogMessage("=============================================");
			view.setDisconnectStateView();
			receiver.closeReceiver();
		} catch (IOException e) {
			System.err.println("Disconnect failed.");
			e.printStackTrace();
		}
	}

}
