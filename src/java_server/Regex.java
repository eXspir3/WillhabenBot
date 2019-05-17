package java_server;

public class Regex {

	public static final String mailSenderRecepientRegex = 	"(((?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]"
															+ "+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e"
															+ "-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]"
															+ "|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:"
															+ "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
															+ "(,\\s|$))+)";
	
	public static final String mailHostRegex = 				"^((\\*)|((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]"
															+ "|[01]?[0-9][0-9]?)|((\\*\\.)?([a-zA-Z0-9-]+\\.){0,5}[a-zA-Z0-9-][a-zA-Z0-9-]+\\.[a-zA-Z]{2,63}?))$";
				
	public static final String mailPortRegex = 				"^[0-9]{1,5}$";
	
	public static final String botIntervalRegex = 			"([4-8][0-9]|9[0-9]|[1-8][0-9]{2}|9[0-8][0-9]|99[0-9]"
															+ "|[12][0-9]{3}|3[0-5][0-9]{2}|3600)";
	
	public static final String botLinkRegex = 				"(https?:\\/\\/(.+?\\.)?willhaben\\.at(\\/[A-Za-z0-9\\"
															+ "-\\._~:\\/\\?#\\[\\]@!$&'\\(\\)\\*\\+,;\\=]*)?)";
	
	public static final String botBotIdRegex = 				"^[0-9]{1,3}$";
	
	public static final String botNameRegex = 				"^[a-zA-Z0-9]{1,32}$";

}
