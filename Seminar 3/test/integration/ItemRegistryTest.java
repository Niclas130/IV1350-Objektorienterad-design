package integration;

import model.ItemDTO;
import model.Sale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ItemRegistryTest {

    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;

    @Before
    public void setUpStream()
    {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream(0);
        System.setOut(new PrintStream(outContent));
    }
    @After
    public void tearDownStream()
    {
        outContent= null;
        System.setOut(originalSysOut);
    }

    /**
     * A test for the method findItem. a ItemDTO that is created item description is compared to
     * the item description of the item returned after a call to the method findItem. The item identifier used as argument
     * in the findItem method is the same as the item created.
     *
     * @throws InvalidIdentifierException thrown when a item could not be found
     */
    @Test
    public void testFindItem() throws InvalidIdentifierException{

        ItemDTO expectedItem = new ItemDTO("apple",25,0.5,1,1);
        ItemRegistry testItemRegistry = new ItemRegistry();
        ItemDTO actualItem = testItemRegistry.findItem(1,1);
        assertEquals("findItem doesnt work", expectedItem.getItemDescription(), actualItem.getItemDescription());

    }

    /**
     * Tests if the item returned from findItem is correct
     *
     * @throws InvalidIdentifierException thrown when as item could not be found
     */
    @Test
    public void testFindItemQuantity() throws InvalidIdentifierException{

        ItemDTO expectedItem = new ItemDTO("apple",25,0.5,1,1);
        ItemRegistry testItemRegistry = new ItemRegistry();
        ItemDTO actualItem = testItemRegistry.findItem(1,1);
        assertEquals("FindItem does not give correct quantity", expectedItem.getQuantity(), actualItem.getQuantity());

    }

    /**
     * A test to see if the exception InvalidIdentifier returns the correct message
     */
    @Test
    public void testFindItemDoesNotExsistMessage()
    {
        int expectedIdentifier = 5;
        ItemRegistry testItemRegistry = new ItemRegistry();
        try
        {
            ItemDTO item = testItemRegistry.findItem(5,1);
        }
        catch(InvalidIdentifierException excp)
        {
            assertTrue("The exception InvalidIdentifier does not give correct message", excp.getMessage().contains("Item with the identifier " + expectedIdentifier + " could not be found"));
        }
    }

    /**
     * A test to see if the exception InvalidIdentifier returns the identifier that could not be located
     */
    @Test
    public void testFindItemDoesNotExsistIdentifier()
    {
        int expectedIdentifier = 5;
        ItemRegistry testItemRegistry = new ItemRegistry();
        try
        {
            ItemDTO actualItem = testItemRegistry.findItem(expectedIdentifier,1);
        }
        catch(InvalidIdentifierException exc)
        {
            assertEquals("Wrong identifier", expectedIdentifier, exc.getNonExistantIdentfifier());
        }
    }

    /**
     * A test to see if the database error exception works correctly
     *
     * @throws DatabaseErrorException thrown when there is a error in the database
     * @throws InvalidIdentifierException thrown when the item with the identifier could not be found
     */
    @Test
    public void findItemDatabaseError() throws DatabaseErrorException, InvalidIdentifierException
    {
        int expectedIdentifier = 6;
        ItemRegistry testItemRegistry = new ItemRegistry();

        try {
            ItemDTO item = testItemRegistry.findItem(expectedIdentifier,1);
        }
        catch(DatabaseErrorException exc)
        {
            assertTrue("Incorrect exception message", exc.getMessage().contains("An error occurred in the database"));
        }
    }

    /**
     * A test of the method sendSaleInformation. I checks if the message is correct
     */
    @Test
    public void testSendSaleInformation() {
        Sale sale = null;
        ItemRegistry testItemRegistry = new ItemRegistry();
        testItemRegistry.sendSaleInformation(sale);
        String expectedString = "Itemregistry updated";
        String actualString = outContent.toString();
        assertTrue(actualString.contains(expectedString));


    }
}