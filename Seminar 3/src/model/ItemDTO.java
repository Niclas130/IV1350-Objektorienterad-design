package model;

/**
 * A DTO of a item that contains relevant information about a certain item
 */
public class ItemDTO {
	
	private String itemDescription;
	private double price;
	private double vATRate;
	private int itemIdentifier;
	private int quantity;

	/**
	 *  Creates a itemDTO that make transferring item information between methods easier
	 *
	 * @param itemDescription A description of the item
	 * @param price The price of the item
	 * @param vATRate The VATrate of the item
	 * @param itemIdentifier ID that specifies the item
	 * @param quantity Specifies the quantity of the item
	 */
	public ItemDTO(String itemDescription, double price, double vATRate, int itemIdentifier, int quantity)
	{
		this.price = price;
		this.vATRate = vATRate;
		this.itemDescription = itemDescription;
		this.itemIdentifier = itemIdentifier;
		this.quantity = quantity;
	}

	/**
	 *
	 * @return Returns the items ID
	 */
	public int getIdentifier()
	{
		return itemIdentifier;
	}

	/**
	 *
	 * @return Returns the price of the item
	 */
	public double getPrice()
	{
		return price;
	}

	/**
	 *
	 * @return Returns the quantity of the item
	 */
	public int getQuantity()
	{
		return quantity;
	}

	/**
	 *
	 * @return returns the items VATrate
	 */
	public double getvATRate() {
		return vATRate;
	}

	/**
	 * Sets a nes VATrate to a item
	 *
	 * @param vATRate The VATrate to set
	 */
	public void setvATRate(double vATRate) {
		this.vATRate = vATRate;
	}

	/**
	 * Sets a new quantity to the item
	 *
	 * @param quantity The quantity to set
	 */
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 *
	 * @return Returns the items description
	 */
	public String getItemDescription()
	{
		return itemDescription;
	}


}
