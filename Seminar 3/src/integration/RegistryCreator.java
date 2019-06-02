package integration;



/**
 * The registry creator start up the accounting system and the item registry
 */
public class RegistryCreator {

	private ItemRegistry itemRegistry = new ItemRegistry();
	private AccountingSystem accountingSystem = new AccountingSystem();

	/**
	 * Gives back the item registry
	 *
	 * @return returns the instance of the ItemRegistry class
	 */
	public ItemRegistry getItemRegistry()
	{
		return itemRegistry;
	}

	/**
	 * Gives back the item registry
	 *
	 * @return returns the instance of the AccountingSystem class
	 */
	public AccountingSystem getAccountingSystem()
	{
		return accountingSystem;
	}
}
