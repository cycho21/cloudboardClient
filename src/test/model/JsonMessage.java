package test.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonMessage {

	private ObjectMapper mapper;
	private JsonNode rootNode;
	private String command;
	private String commandList[];
	private String clientName;

	public JsonMessage() {
		setMapper(new ObjectMapper());
	}

	public void init_command(String clientName, String message) {
		this.command = message;
		this.clientName = clientName;
		rootNode = mapper.createObjectNode();
	}

	public String makeJsonMessage() {
		splitMessage();
		makeJSONMessage();
		makeArguments();
		return rootNode.toString();
	}

	private void makeArguments() {
		for (int i = 2; i < commandList.length; i++) {
			JsonNode temporaryNode = mapper.createObjectNode();
			((ObjectNode) temporaryNode).put(commandList[i], commandList[i + 1]);
			((ObjectNode) rootNode).with("data").withArray("arguments").add(temporaryNode);
			i++;
		}
	}

	public String subscribe() { // subscribe AUTHOR OPERATION TREE A B
								// TREE A B
								// example ; subscribe user2 roboSensor update $gt left 15
								// subscribe server nearBy assert $equal me #robot
		splitMessage();
		((ObjectNode) rootNode).put("sender", clientName);
		((ObjectNode) rootNode).put("messageType", "subscribe");
		((ObjectNode) rootNode).with("data").put("author", commandList[1]);
		((ObjectNode) rootNode).with("data").put("name", commandList[2]);
		((ObjectNode) rootNode).with("data").put("operation", commandList[3]);
		((ObjectNode) rootNode).with("data").put("operand", commandList[4]);
		((ObjectNode) rootNode).with("data").put("pTime", System.currentTimeMillis());
		((ObjectNode) rootNode).with("data").put("eTime", System.currentTimeMillis());
		for (int i = 5; i < commandList.length; i++) {
			JsonNode temporaryNode = mapper.createObjectNode();
			((ObjectNode) temporaryNode).put(commandList[i], commandList[i + 1]);
			((ObjectNode) rootNode).with("data").withArray("arguments").add(temporaryNode);
			i++;
		}
		System.out.println(rootNode.toString());
		return rootNode.toString();
	}

	public String unsubscribe() { 
		
		// subscribe AUTHOR OPERATION TREE A B
		// TREE A B
		// example ; unsubscribe dataName user11 assert x 1 y 1
		
		// example ; update dataName x 1 y 5 
		
		splitMessage();
		((ObjectNode) rootNode).put("sender", clientName);
		((ObjectNode) rootNode).put("messageType", "unsubscribe");
		((ObjectNode) rootNode).with("data").put("name", commandList[1]);
		((ObjectNode) rootNode).with("data").put("operation", commandList[2]);
		((ObjectNode) rootNode).with("data").put("operand", commandList[3]);
		((ObjectNode) rootNode).with("data").put("pTime",
				System.currentTimeMillis());
		((ObjectNode) rootNode).with("data").put("eTime",
				System.currentTimeMillis());
		for (int i = 5; i < commandList.length; i++) {
			JsonNode temporaryNode = mapper.createObjectNode();
			((ObjectNode) temporaryNode)
					.put(commandList[i], commandList[i + 1]);
			((ObjectNode) rootNode).with("data").withArray("arguments")
					.add(temporaryNode);
			i++;
		}
		return rootNode.toString();
	}

	private void makeJSONMessage() {
		((ObjectNode) rootNode).put("sender", clientName);
		((ObjectNode) rootNode).put("messageType", commandList[0]);
		((ObjectNode) rootNode).with("data").put("name", commandList[1]);
		((ObjectNode) rootNode).with("data").put("author", clientName);
		((ObjectNode) rootNode).with("data").put("pTime", System.currentTimeMillis());
		((ObjectNode) rootNode).with("data").put("eTime", System.currentTimeMillis());
	}

	public String post() {
		return rootNode.toString();
	}

	private void splitMessage() {
		String commandList[] = command.split("\\s+");
		this.commandList = commandList;
	}

	public String connectMessage() {
		((ObjectNode) rootNode).put("connect", command.substring(8));
		return rootNode.toString();
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public String disconnectMessage() {
		((ObjectNode) rootNode).put("disconnect", command.substring(11));
		return rootNode.toString();
	}

}
