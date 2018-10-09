package googletests.apps;

public class Constants {
	public static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    public static final String DS = System.getProperty("file.separator");
    public static final String LOGS_DIRECTORY = CURRENT_DIRECTORY+DS+"logs"+DS;
    public static final String DRIVERS_DIRECTORY = CURRENT_DIRECTORY+DS+"src"+DS+"test"+DS+"drivers"+DS;
    public static final String SCREENSHOT_DIRECTORY = CURRENT_DIRECTORY+DS+"target"+DS+"screenshots"+DS;
    public static final String TESTDATA_DIRECTORY = CURRENT_DIRECTORY+DS+"src"+DS+"test"+DS+"testdata"+DS;
    public static final String PROPERTIES_DIRECTORY = CURRENT_DIRECTORY+DS+"src"+DS+"test"+DS+"resources"+DS;
}
