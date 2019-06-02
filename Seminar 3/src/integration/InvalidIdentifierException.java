package integration;

/**
 * An exception that is thrown when an item pertaining to a specific identifier cold not be found in the item registry
 */
public class InvalidIdentifierException extends Exception
{
    private int nonExistantIdentfifier;

    /**
     * Creates an instance of the exception invalid identifier with a message regarding the cause of the error
     *
     * @param invalidIdentifier the identifier that could not be found
     */
    public InvalidIdentifierException(int invalidIdentifier)
    {
        super("Item with the identifier " + invalidIdentifier + " could not be found");
        this.nonExistantIdentfifier = invalidIdentifier;
    }

    /**
     *
     * @return Identifier that could not be found
     */
    public int getNonExistantIdentfifier()
    {
        return nonExistantIdentfifier;
    }
}
