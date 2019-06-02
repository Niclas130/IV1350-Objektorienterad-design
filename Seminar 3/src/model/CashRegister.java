package model;

/**
 * Represents the cash register in the store that is used to house cash
 */
public class CashRegister {

	private double balance;

	/**
	 * Creates the cash register to be used in the program starting with a sum of zero
	 */
	public CashRegister ()
	{
		this.balance = 0;
	}

	/**
	 * Adds the amount that the customer paid to the <code>CashRegister</code>
	 *
	 * @param paymentToAdd the amount the customer paid
	 */
	public void addPaymentToRegister(double paymentToAdd)
	{
		this.balance = this.balance + paymentToAdd;
	}

	/**
	 * Calculates the amount of change that the customer should be returned
	 *
	 * @param totalPayment the amount the customer paid
	 * @param totalPrice the price of the complete sale
	 * @return the amount of change
	 */
	public double calculateChange(double totalPayment, double totalPrice)
	{
		return totalPayment - totalPrice;
	}

	/**
	 *
	 * @return Returns tha balance in the cash register
	 */
	public double getBalance()
	{
		return this.balance;
	}
}
