package model;


import integration.RegistryCreator;

/**
 * Represents a sale taking place in a store
 */
public class Sale {

	private RegistryCreator creator;
	private double runningTotal;
	private ItemDTO[] boughtItems;
	private double changeAmount;
	private double amountPaid;
	private double vATRate;
	private CashRegister cashRegister;
	private SaleObserver[] saleObservers;

	/**
	 * The constructor that creates a instance of the class Sale using a registryCreator.
	 * The sale is started with no items in the bought items list
	 * @param creator The RegistryCreator used to create the sale.
	 */
	public Sale(RegistryCreator creator)
	{
		this.creator = creator;
		this.boughtItems = new ItemDTO[0];
	}

	/**
	 *
	 * @return The current running total of the sale
	 */
	public double getRunningTotal()
	{
		return runningTotal;
	}

	/**
	 *
	 * @return The total VATRate of the sale
	 */
	public double getVATRate()
	{
		return this.vATRate;
	}

	/**
	 *
	 * @return The amount of cash the customer used to pay
	 */
	public double getAmountPaid()
	{
		return this.amountPaid;
	}

	/**
	 *
	 * @return Returns a list of the item purchased in the sale
	 */
	public ItemDTO[] getBoughtItems()
	{
		return boughtItems;
	}

	/**
	 *
	 * @return The change to be returned to the customer
	 */
	public double getChangeAmount()
	{
		return this.changeAmount;
	}

	/**
	 *
	 * @param changeToAdd The amount of change returned from the sale
	 */
	public void setChangeAmount(double changeToAdd)
	{
		this.changeAmount = changeToAdd;
	}

	/**
	 * Adds a item to the item list in the sale.
	 * Depending on which item is added either a new item will be added or if the item is already in sale then
	 * the quantity of the item will be increased
	 *
	 * @param itemToAdd The item that is to be added to the sale
	 */
	public void addItemToSale(ItemDTO itemToAdd)
	{
		calculateRunningTotal(itemToAdd);
		if(searchItemInSale(itemToAdd))
		{
			increaseAmount(itemToAdd);
		}
		
		else {
			ItemDTO[] oldItems = new ItemDTO[boughtItems.length + 1];
			int b = 0 ;
			for (int a = 0; a < boughtItems.length; a++) {
				oldItems[a] = boughtItems[a];
				b = b+1;
			}

			oldItems[b] = itemToAdd;
			boughtItems = oldItems;
		}
		calculateTotalVATRate();
	}

	/**
	 * The method take the payment that the customer presents and adds it to the current sale
	 *
	 * @param paymentToAddToSale The payment the customer presents
	 */
	public void addPaymentToSale (double paymentToAddToSale)
	{
		this.amountPaid = paymentToAddToSale;

		notifyObserver();
	}

	/**
	 * Creates a instance of the object CashRegister to be used in rest of the sale
	 */
	public void startRegister()
	{
		this.cashRegister = new CashRegister();
	}

	/**
	 * All the sale observers will be notified when a sale has been paid for
	 *
	 * @param saleObservers the observer that should be notified
	 */
	public void addSaleObservers(SaleObserver[] saleObservers) {

		if(this.saleObservers== null)
		{
			this.saleObservers = new SaleObserver[0];

		}

		SaleObserver[] newSaleObservers = new SaleObserver[this.saleObservers.length + saleObservers.length];

		for(int i = 0; i < this.saleObservers.length; i++)
		{
			newSaleObservers[i] = this.saleObservers[i];
		}

		for(int j = this.saleObservers.length; j < this.saleObservers.length + saleObservers.length; j++)
		{
			newSaleObservers[j] = saleObservers[j];
		}

		this.saleObservers = newSaleObservers;
	}


	private void calculateTotalVATRate()
	{
		double totalVAT = 0;
		for(int i = 0; i < boughtItems.length; i++)
		{
			totalVAT =  totalVAT + (boughtItems[i].getvATRate() * boughtItems[i].getQuantity());
		}
		this.vATRate = totalVAT;
	}

	private void increaseAmount(ItemDTO itemToIncrease)
	{
		for(int i = 0; i < this.boughtItems.length; i++)
		{
			if(this.boughtItems[i].getIdentifier() == itemToIncrease.getIdentifier())
			{
				this.boughtItems[i].setQuantity(this.boughtItems[i].getQuantity() + itemToIncrease.getQuantity());
				break;
			}
		}
	}

	private boolean searchItemInSale(ItemDTO itemToCompare)
	{
		boolean itemAlreadyInSale = false;
		for(int i = 0; i < boughtItems.length; i++)
		{
			if(boughtItems[i].getIdentifier() == itemToCompare.getIdentifier())
			{
				itemAlreadyInSale = true;
				break;
			}
		}
		return itemAlreadyInSale;
	}

	private double calculateRunningTotal(ItemDTO itemToAdd)
	{
		this.runningTotal = this.runningTotal + (itemToAdd.getPrice() * itemToAdd.getQuantity());
		return this.runningTotal;
	}

	private void notifyObserver()
	{
		for(int i = 0; i < saleObservers.length;i++)
		{
			saleObservers[i].newSale(this);
		}
	}

}
