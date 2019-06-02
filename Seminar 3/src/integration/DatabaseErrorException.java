package integration;

/**
 * A exception that is supposed to represent a error in the database
 */
public class DatabaseErrorException extends RuntimeException {

    /**
     * Creates a instance of the database error
     */
    public DatabaseErrorException()
    {
        super("An error occurred in the database");
    }
}
