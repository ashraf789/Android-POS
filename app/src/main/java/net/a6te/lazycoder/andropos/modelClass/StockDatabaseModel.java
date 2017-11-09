package net.a6te.lazycoder.andropos.modelClass;

/**
 * Created by Programmer on 5/21/2017.
 */

public class StockDatabaseModel {


    private String ProductId;
    private String ProductType;
    private String ProductQuantity;
    private String ProductFor;

    public StockDatabaseModel(){

    }

    public StockDatabaseModel(String productId, String productType, String productQuantity, String productFor) {
        ProductId = productId;
        ProductType = productType;
        ProductQuantity = productQuantity;
        ProductFor = productFor;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductFor() {
        return ProductFor;
    }

    public void setProductFor(String productFor) {
        ProductFor = productFor;
    }
}
