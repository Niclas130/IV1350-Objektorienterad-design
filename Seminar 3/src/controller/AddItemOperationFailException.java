package controller;

/**
 * An Exception that is thrown when an error occurs in the addItem operation
 */
public class AddItemOperationFailException extends Exception {

    /**
     *Creates an instance of the exception addItemOperationFail
     *
     * @param explanation a message that contain information regarding exception
     * @param cause the error that cause the exception
     */
    public AddItemOperationFailException(String explanation, Exception cause)
    {
        super(explanation, cause);
    }
}
