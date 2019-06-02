package model;

/**
 * Receipt contains vital information regarding the sale
 */
public class Receipt {

	private String adress;
	private String storeName;
	private int timeAndDate;
	private double totalCost;
	private ItemDTO[] purchasedItems;
	private double totalPaid;
	private double change;
	private double totalVAT;

	/**
	 * Creates a instance of the class receipt that contains vital information regarding the sale
	 * @param sale The sale that the receipt should represent
	 */
	public Receipt (Sale sale)
	{
		this.adress = "street";
		this.storeName =  "name";
		this.timeAndDate = 0;
		this.change = sale.getChangeAmount();
		this.totalCost = sale.getRunningTotal();
		this.totalPaid = sale.getAmountPaid();
		this.purchasedItems = sale.getBoughtItems();
		this.totalVAT = sale.getVATRate();
	}

	/**
	 *
	 * @return The amount of change from the sale
	 */
	public double getChange() {
		return change;
	}

	/**
	 *
	 * @return The total cost of the entire sale
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 *
	 * @return The amount the customer paid
	 */
	public double getTotalPaid() {
		return totalPaid;
	}

	/**
	 *
	 * @return VATRate from the entire sale
	 */
	public double getTotalVAT() {
		return totalVAT;
	}

	/**
	 *
	 * @return Time and date of the sale
	 */
	public int getTimeAndDate() {
		return timeAndDate;
	}

	/**
	 *
	 * @return The list of items that where bought
	 */
	public ItemDTO[] getPurchasedItems() {
		return purchasedItems;
	}

	/**
	 *
	 * @return The adress of the store in which the sale took place
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 *
	 * @return Name of the store
			 */
public String getStoreName() {
		return storeName;
		}
		}
