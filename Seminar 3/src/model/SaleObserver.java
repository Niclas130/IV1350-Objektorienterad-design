package model;

/**
 * A interface that receives information about tha amount paid in a sale
 */
public interface SaleObserver {

    /**
     * Used when a sale has been paid for
     *
     * @param sale the sale that is paid for
     */
    void newSale (Sale sale);
}
