package startUp;
import integration.Printer;
import logHandler.LogHandler;
import view.View;
import controller.Controller;
import integration.AccountingSystem;
import integration.ItemRegistry;
import integration.RegistryCreator;
import model.CashRegister;
import integration.InvalidIdentifierException;
import java.io.IOException;

public class Main {

	public static void main (String[] args)
	{
	    try {
            RegistryCreator registryCreator = new RegistryCreator();
            CashRegister cashRegister = new CashRegister();
            ItemRegistry itemRegistry = registryCreator.getItemRegistry();
            AccountingSystem accountingSystem = registryCreator.getAccountingSystem();
            Printer printer = new Printer();
            Controller contr = new Controller(accountingSystem, itemRegistry, cashRegister);
            View view = new View(contr);
            view.startNewSale(registryCreator);
            view.addItemToView(1, 1);
            view.addItemToView(2, 2);
            view.signalAllRegistered();
            view.pay(30);
            view.requestReceipt();

            view.startNewSale(registryCreator);
            view.addItemToView(1, 1);
            view.addItemToView(3, 1);
            view.signalAllRegistered();
            view.pay(30);
            view.requestReceipt();


        }
	    catch(IOException exc)
        {
            exc.printStackTrace();
        }


	}
}
