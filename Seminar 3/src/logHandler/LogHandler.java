package logHandler;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * The log handler creates a log that saves information regarding an error in the system
 */
public class LogHandler {

    private static final String LOG_FILE_NAME = "sale-error-log.txt";
    private PrintWriter logFile;
    private static LogHandler instance = null;

    /**
     * Creates an instance of the LogHandler
     *
     * @throws IOException thrown when there is a IO problem
     */
    public LogHandler() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
    }

    /**
     * Log the exception that occurred in the system
     *
     * @param exception the exception that is to be logged
     */
    public void logException(Exception exception)
    {
        StringBuilder logMessage = new StringBuilder();
        logMessage.append(getCurrentTime());
        logMessage.append(", Exception was thrown: ");
        logMessage.append(exception.getMessage());
        logFile.println(logMessage);
        exception.printStackTrace(logFile);
    }

    /**
     * Returns the only instance of the class LogHandler
     *
     * @return the instance of the class LogHandler
     * @throws IOException When there is a IO problem
     */
    public static LogHandler getLogHandler() throws IOException
    {
        if(instance == null)
        {
            instance = new LogHandler();
        }

        return instance;
    }

    private String getCurrentTime()
    {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
