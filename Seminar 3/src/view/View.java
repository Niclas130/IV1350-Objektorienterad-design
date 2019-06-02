package view;
import controller.AddItemOperationFailException;
import controller.Controller;
import integration.DatabaseErrorException;
import integration.InvalidIdentifierException;
import integration.Printer;
import integration.RegistryCreator;
import logHandler.LogHandler;
import model.ItemDTO;
import model.Receipt;

import java.io.IOException;

/**
 * The view class represent what the customer and cashier is shown.
 * It is also responsible for the interaction between cashier and system
 */
public class View {

	private Controller contr;

	/**
	 * View creates an instance of the class View that the cashier uses to interact with the system
	 *
	 * @param contr Is a instance of the controller class used for connecting the view with the rest of the system
	 */
	public View(Controller contr) {
		this.contr = contr;
		contr.addSaleObserver(new TotalRevenueView());
	}

	/**
	 * Represent the cashier telling the system to start a new sale
	 *
	 * @param creator Is a instance of the class RegistryCreator giving acces to the external systems AccountingSystem
	 *                and ItemRegistry
	 */
	public void startNewSale(RegistryCreator creator) {
		contr.startNewSale(creator);
		System.out.println("New sale has started--------------------------");
	}

	/**
	 * The method add an item or items to the sale depending on the items itendifier and how many are to be added
	 *
	 * @param itemIdentifier Proves which item that is to be added
	 * @param quantity informs the system on how many od said item are to be added
	 */
	public void addItemToView(int itemIdentifier, int quantity) throws IOException {
		try {
			ItemDTO item = contr.addItem(itemIdentifier, quantity);
			double currentTotal = contr.getRunningTotal();
			printItemToScreen(item);
			System.out.println("The current running total " + currentTotal);
			System.out.println();
		}
		catch(InvalidIdentifierException exception)
		{
			System.out.println(exception.getMessage());
			LogHandler.getLogHandler().logException(exception);
		}
		catch(AddItemOperationFailException exception_2)
		{
			System.out.println(exception_2.getMessage());
			LogHandler.getLogHandler().logException(exception_2);
		}
	}

	/**
	 * The method is called when the cashier indicates that all item that the customer have to buy are registered
	 */
	public void signalAllRegistered()
	{
		double total = contr.signalAllRegistered();
		System.out.println("The total price " + total);
		System.out.println();
	}

	/**
	 * Represents the customer paying for the purchase after all items are scanned
	 *
	 * @param amount The amount of cash that the customer paid
	 */
	public void pay (int amount)
	{
		double change = contr.pay(amount);
		if(change < 0)
		{
			System.out.println("Amount paid is not enough");
		}
		else
		{
			System.out.println("The change is " + change);
			System.out.println();
		}
	}

	/**
	 * Request the receipt for the purchase. The receipt contains all relevant information regarding the sale.
	 * The method den prints the receipt
	 */
	public void requestReceipt() {
		Receipt receipt = contr.getReceipt();
		Printer printer = new Printer();
		printer.printReceipt(receipt);
		for (int c = 0; c < receipt.getPurchasedItems().length; c++) {
			printItemToScreen(receipt.getPurchasedItems()[c]);
		}
	}

	private void printItemToScreen(ItemDTO itemToShow) {
		System.out.print(itemToShow.getItemDescription() + " price " + itemToShow.getPrice() + " VATRate " + itemToShow.getvATRate());
		System.out.println(" Quantity " + itemToShow.getQuantity());
	}
}