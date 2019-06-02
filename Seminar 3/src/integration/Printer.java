package integration;


import model.Receipt;

/**
 * The Printer class represents the printer in the store
 */
public class Printer {

    /**
     * Prints teh information contained int the receipt
     * @param receipt The receipt has the information that is to be printed
     */
    public void printReceipt(Receipt receipt) {
        System.out.println("Receipt: ");
        System.out.println("Adress: " + receipt.getAdress());
        System.out.println("Stores name: " + receipt.getStoreName());
        System.out.println("Time and date: " + receipt.getTimeAndDate());
        System.out.println("Total cost: " + receipt.getTotalCost());
        System.out.println("Total amount paid: " + receipt.getTotalPaid());
        System.out.println("Total VATRate: " + receipt.getTotalVAT());
        System.out.println("Change: " + receipt.getChange());
        System.out.println("Items: ");
    }
}