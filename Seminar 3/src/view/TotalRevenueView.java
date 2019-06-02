package view;

import model.Sale;
import model.SaleObserver;


public class TotalRevenueView implements SaleObserver {

    public TotalRevenueView()
    {

    }

    private double[] SalesPrice = new double[0];

    @Override
    public void newSale(Sale sale) {
        addNewSale(sale);
        printCurrentState();
    }

    private void addNewSale(Sale sale)
    {
        double[] newSalesPrice = new double[this.SalesPrice.length+1];
        for(int a = 0; a < this.SalesPrice.length; a++)
        {
            newSalesPrice[a] = this.SalesPrice[a];
        }
        newSalesPrice[this.SalesPrice.length] = sale.getAmountPaid();

        this.SalesPrice = newSalesPrice;
    }

    private void printCurrentState()
    {
        System.out.println("Total Revenue:");
        double totalRevenue = 0;
        for(int b = 0; b < this.SalesPrice.length; b++)
        {
            totalRevenue = totalRevenue + SalesPrice[b];
            System.out.println("Revenue from sale: " + SalesPrice[b]);
        }

        System.out.println("The total revenue from sale: " + totalRevenue);
        System.out.println("--------------------------------------------");
        System.out.println();
    }
}
