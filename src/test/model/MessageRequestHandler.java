package test.model;

public class MessageRequestHandler {

	private String 		messageType;
	private String 		message;
	private JsonMessage jsonMessage;

	public MessageRequestHandler(JsonMessage json) {
		this.jsonMessage = json;
	}

	public void classify(String command) {
		String commandList[] = command.split("\\s+");
		
		switch (commandList[0]) {
		
		case "connect":
			setMessage(jsonMessage.connectMessage());
			break;
		
		case "disconnect":
			setMessage(jsonMessage.disconnectMessage());
			break;

		case "assert":
			setMessage(jsonMessage.makeJsonMessage());
			break;
		
		case "update":
			setMessage(jsonMessage.makeJsonMessage());
			break;
		
		case "subscribe":
			setMessage(jsonMessage.subscribe());
			break;
			
		case "post":
			setMessage(jsonMessage.makeJsonMessage());
			break;
		
		case "unsubscribe":
			setMessage(jsonMessage.unsubscribe());
			
		case "gets":
			setMessage(jsonMessage.makeJsonMessage());
		default:
			break;
		}
	}

	private void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
}
