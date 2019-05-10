package java_server;

import java.util.Properties;
import java.util.Set;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class BotHandler {

	private String threadId;
	private Map<String, HashMap<String, String>> outerMap = new HashMap<String, HashMap<String, String>>();
	private HashMap<String, String> innerMap = new HashMap<String, String>();

	public BotHandler() throws IOException, ClassNotFoundException {
		File f = new File("handlerConfig.ser");
		if (f.isFile() && f.canRead()) {
			this.restoreMap();
		}
	}

	/**
	 * Creates a new Bot with the Given Configuration and starts it in its own
	 * Thread. Configuration and associated threadId are saved to Hashmap via
	 * Function addThreadToMap
	 * 
	 * @param botConfig  Bot Configuration consisting of 'link', 'name', 'botId',
	 *                   'interval'
	 * @param mailConfig --- NOT IMPLEMENTED ---
	 * @throws IOException
	 */
	public void createBot(Properties botConfig, Properties mailConfig) throws IOException {
		Thread newBotThread = new Thread(new WillhabenBot(botConfig, mailConfig));
		this.threadId = String.valueOf(newBotThread.getId());
		if (this.threadId == "" | this.threadId == null) {
			throw new IOException(
					"threadId was null or empty after creating Thread for Bot ID: " + botConfig.getProperty("botId"));
		}
		this.addThreadToMap(botConfig, mailConfig, this.threadId);
		newBotThread.start();
		System.out.println("BotHanlder Created Bot with ThreadId " + this.threadId);
	}

	/**
	 * Deletes a Bot by deleting the Reference from outer HashMap
	 * 
	 * @param botId
	 * @throws IOException
	 */
	public void deleteBot(String botId) throws IOException {
		this.stopBot(botId);
		outerMap.remove(botId);
		System.out.println("Deleted Bot with ID: " + botId);
		this.saveMap();
	}

	/**
	 * Stops a running Bot by matching the BotId to the corresponding threadId and
	 * Interrupting the Thread via interruptThread() Function
	 * 
	 * @param botId generated by the GUI Client
	 * @throws IOException
	 */
	public void stopBot(String botId) throws IOException {
		this.innerMap = this.outerMap.get(botId);
		if (this.innerMap == null) {
			throw new IOException("Tried to stop Bot with ID: " + botId + " does not exist!");
		}
		interruptThread(this.innerMap.get("threadId"));
	}

	/**
	 * Start an existing Bot by referencing its id and loading the configurations
	 * from the HashMap
	 * 
	 * @param botId generated by the GUI Client
	 * @throws IOException
	 */
	public void startBot(String botId) throws IOException {
		this.innerMap = this.outerMap.get(botId);
		if (this.innerMap == null) {
			throw new IOException("Tried to start Bot with ID: " + botId + " does not exist!");
		}
		Properties botConfig = convertToBotConfig(this.innerMap);
		Properties mailConfig = convertToMailConfig(this.innerMap);
		System.out.println("Starting Existing Bot with ID " + botId);
		this.createBot(botConfig, mailConfig);
	}
	
	/**
	 * Saves the Bot Configuration in HashMap outerMap to File
	 * @throws IOException
	 */
	private void saveMap() throws IOException {
		ObjectOutputStream outputStreamMap = new ObjectOutputStream(new FileOutputStream("handlerConfig.ser"));
		outputStreamMap.writeObject(this.outerMap);
		outputStreamMap.close();
	}
	
	/**
	 * Creates Bot Configuration HashMap outerMap by reading from File
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void restoreMap() throws IOException, ClassNotFoundException {
		ObjectInputStream inputStreamMap = new ObjectInputStream(new FileInputStream("handlerConfig.ser"));
		this.outerMap = (Map<String, HashMap<String, String>>) inputStreamMap.readObject();
		inputStreamMap.close();
	}

	/**
	 * Creates a set of all running Threads, iterates through the set until the
	 * threadId matches the passed argument threadId and then sends interrupt to the
	 * Thread
	 * 
	 * @param threadId ThreadId of Thread to kill
	 */
	private void interruptThread(String threadId) {
		Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
		for (Thread thread : setOfThread) {
			if (thread.getId() == Long.parseLong(threadId)) {
				thread.interrupt();
				System.out.println("Interrupted Thread " + threadId + " via BotHandler");
			}
		}

	}

	/**
	 * Converts a given Property to HashMap
	 * 
	 * @param props
	 * @return Converted HashMap
	 */
	private Map<String, String> convertToMap(Properties props) {
		Map<String, String> propMap = new HashMap(props);
		return propMap;
	}

	/**
	 * Converts a map in the correct Format to the botConfig Property for use with
	 * WillhabenBot Class
	 * 
	 * @param map
	 * @return Converted botConfig Property
	 */
	private Properties convertToBotConfig(Map<?, ?> map) {
		String[] botConfigItems = { "link", "name", "botId", "interval" };
		Properties botConfig = new Properties();
		for (int i = 0; i < botConfigItems.length; i++) {
			botConfig.put(botConfigItems[i], map.get(botConfigItems[i]));
		}
		return botConfig;
	}

	/**
	 * Converts a map in the correct Format to the mailConfig Property for use with
	 * WillhabenBot Class
	 * 
	 * @param map
	 * @return Converted mailConfig Property --- NOT IMPLEMENTED ---
	 */
	private Properties convertToMailConfig(Map<?, ?> map) {
		Properties mailConfig = new Properties();
		return mailConfig;
	}

	/**
	 * Adds the Bot Configurations and threadId to map, then adds this generated map
	 * to outerMap which is access by the key *botId*
	 * 
	 * @param botConfig  botConfig Property
	 * @param mailConfig botConfig Property
	 * @param threadId   ID of Thread this Bot is running in
	 * @throws IOException
	 */
	private void addThreadToMap(Properties botConfig, Properties mailConfig, String threadId) throws IOException {
		this.innerMap = new HashMap<String, String>();
		this.innerMap.putAll(convertToMap(botConfig));
		this.innerMap.putAll(convertToMap(mailConfig));
		this.innerMap.put("threadId", threadId);
		this.outerMap.put(botConfig.getProperty("botId"), this.innerMap);
		this.saveMap();
	}

}
