package Utils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
    private Logger logger;

    public Log(Class className) {
        PropertyConfigurator.configure("resources/log4j.properties");
        logger = LogManager.getLogger(className);
    }

    public void info(String logMessage) {
        logger.info(logMessage);
    }

    public void error(String logMessage) {
        logger.error(logMessage);
    }
}
