package controller;
import integration.*;
import model.*;

/**
 * The Controller class is what connects teh view with the rest of the program
 * and instructs it what to do
 */
public class Controller {
	private Sale sale;
	private CashRegister cashRegister;
	private ItemRegistry itemRegistry;
	private AccountingSystem accountingSystem;
	private double runningTotal;
	private SaleObserver[] saleObservers;

	/**
	 * Creates an instance of the class Controller
	 *
	 * @param accountingSystem represents the accounting system in the store
	 * @param itemRegistry represents the item registry in the store
	 * @param cashRegister represents the cash register at the counting in the store
	 */
	public Controller (AccountingSystem accountingSystem, ItemRegistry itemRegistry, CashRegister cashRegister)
	{
		this.itemRegistry = itemRegistry;
		this.cashRegister = cashRegister;
		this.accountingSystem = accountingSystem;
	}

	/**
	 * Creates a instance of the of the class sale
	 */
	public void startNewSale(RegistryCreator creator)
	{
		this.sale = new Sale(creator);
		sale.startRegister();
		sale.addSaleObservers(this.saleObservers);
	}

	/**
	 * Searches for the required item in item registry then add it ro the sale and receives the running total
	 *
	 * @param itemID specifies which item to add
	 * @param quantity indicates how many examples of the item that shall be added
	 * @return the item that is to be added with the current running total
	 * @throws InvalidIdentifierException thrown when a item with a specific identifier could not be found
	 * @throws AddItemOperationFailException thrown when a database error occur in the integration layer
	 */
	public ItemDTO addItem(int itemID, int quantity) throws InvalidIdentifierException, AddItemOperationFailException
	{
		try {
			ItemDTO foundItem = itemRegistry.findItem(itemID, quantity);
			sale.addItemToSale(foundItem);
			return foundItem;
		}
		catch(DatabaseErrorException exception)
		{
			throw new AddItemOperationFailException("The item could not be added", exception);
		}
	}

	/**
	 * Indicates that all item have been registered
	 *
	 * @return the total price of the entire sale
	 */
	public double signalAllRegistered()
	{
		return sale.getRunningTotal();
	}

	/**
	 * Adds payment to sale and gets the amount of change
	 *
	 * @param paidAmount the amount the customer paid
	 * @return the amount of change that the customer is to be returned
	 */
	public double pay (double paidAmount)
	{
		sale.addPaymentToSale(paidAmount);
		cashRegister.addPaymentToRegister(paidAmount);
		accountingSystem .sendSaleInformation(sale);
		itemRegistry.sendSaleInformation(sale);
		System.out.println();
		double change = cashRegister.calculateChange(paidAmount, sale.getRunningTotal());
		sale.setChangeAmount(change);
		return change;
	}

	/**
	 * Creates an instance of the class Receipt that contain all relevant information about the sale.
	 *
	 * @return the receipt with information concerning the purchase.
	 */
	public Receipt getReceipt()
	{
		Receipt receipt = new Receipt(sale);
		return receipt;		
	}

	/**
	 * Retrieves the current total price of the sale
	 *
	 * @return the price of the sale at the moment the method is called
	 */
	public double getRunningTotal()
	{
		runningTotal = sale.getRunningTotal();
		return runningTotal;
	}

	/**
	 * The observer sent as argument will be notified when a sale has been paid for
	 *
	 * @param observer the observer to be notified
	 */
	public void addSaleObserver(SaleObserver observer)
	{
		if(this.saleObservers != null) {
			SaleObserver[] newSaleObservers = new SaleObserver[this.saleObservers.length + 1];
			for (int a = 0; a < this.saleObservers.length; a++) {
				newSaleObservers[a] = this.saleObservers[a];
			}
			newSaleObservers[this.saleObservers.length] = observer;
			this.saleObservers = newSaleObservers;
		}
		else{
			saleObservers = new SaleObserver[1];
			saleObservers[0] = observer;
		}
	}
}
