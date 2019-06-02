package integration;

import model.Sale;

/**
 * Represents the Accounting system int the store
 */
public class AccountingSystem {

	/**
	 * Creates a instance of the class without parameters
	 */
	public AccountingSystem()
	{
		
	}

	/**
	 * The method doesn't do anything except print to screen that the method has been called.
	 *
	 * @param sale The parameter is never used it only exists to create teh illusion of the accounting system being updated
	 */
	public void sendSaleInformation(Sale sale)
	{
		System.out.println("Accounting system updated");
	}
}
