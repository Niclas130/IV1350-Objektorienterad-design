package integration;
import model.ItemDTO;
import model.Sale;

/**
 * The class represents the item registry in the store that contains all the items
 */
public class ItemRegistry {

	private ItemDTO[] itemsInStore = new ItemDTO[3];

	/**
	 * Creates an instance of the class with a preselected collection of item available for purchase
	 */
	public ItemRegistry ()
	{
		itemsInStore[0] = new ItemDTO("apple",10, 0.5 , 1,2 );
		itemsInStore[1] = new ItemDTO("banana",10, 0.5 , 2,2  );
		itemsInStore[2] = new ItemDTO("pear",10, 0.5 , 3,2  );
	}

	/**
	 * The method findItem searches for the item to add in the item registry and returns that item
	 *
	 * @param ID the identifier that tells the method which item to return
	 * @return the item that is found
	 * @throws InvalidIdentifierException Thrown when the item identifier cant not be found
	 * @throws DatabaseErrorException Thrown when the identifier 6 is sent as argument.
	 * This is to represent a error in the database
	 */
	public ItemDTO findItem(int ID, int quantity) throws InvalidIdentifierException, DatabaseErrorException
	{
		if(ID == 6)
		{
			throw new DatabaseErrorException();
		}
		else {
			ItemDTO foundItem;
			for (int i = 0; i < this.itemsInStore.length; i++) {
				if (this.itemsInStore[i].getIdentifier() == ID) {
					decreaseQuantity(this.itemsInStore[i], quantity);
					foundItem = this.itemsInStore[i];
					foundItem.setQuantity(quantity);
					return foundItem;
				}
			}
			throw new InvalidIdentifierException(ID);
		}
	}

	/**
	 * The method doesnt do anything except print out a message that tells that the method has been called
	 *
	 * @param sale The parameter has no real purpose except to create the illusion of the ItemRegistry saving
	 *             the sale information
	 */
	public void sendSaleInformation(Sale sale)
	{
		System.out.println("Itemregistry updated");
	}

	private void decreaseQuantity(ItemDTO itemToDecrease , int amountToDecrease)
	{
		itemToDecrease.setQuantity(itemToDecrease.getQuantity() - amountToDecrease);
	}
}
