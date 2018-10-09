package googletests.helpers;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log {
	public static Logger logger;
	
	public static Logger getLogData(String className){	
		logger = LogManager.getLogger(className);
		return logger;
	}
	
	public static void startTestCase(String sTestCaseName){
		logger.info("****************************************************************************************");
		logger.info("****************************************************************************************");
		logger.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");
		logger.info("****************************************************************************************");
		logger.info("****************************************************************************************");
	}
	
	public static void endTestCase(){
		logger.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX"); 
		logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		logger.info("                                                                                        ");
		logger.info("                                                                                        ");
	}
}