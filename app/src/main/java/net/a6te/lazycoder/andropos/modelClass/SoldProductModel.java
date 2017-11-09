package net.a6te.lazycoder.andropos.modelClass;

/**
 * Created by Programmer on 6/18/2017.
 */

public class SoldProductModel {
    private String productSellsCode;
    private String productSellId;
    private String productId;
    private String productPrice;
    private String productQuantity;
    private String productTotalPrice;
    private String productPendingStatus;

    public SoldProductModel() {
    }

    public SoldProductModel(String productSellsCode, String productSellId, String productId, String productPrice, String productQuantity, String productTotalPrice, String productPendingStatus) {

        this.productSellsCode = productSellsCode;
        this.productSellId = productSellId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productTotalPrice = productTotalPrice;
        this.productPendingStatus = productPendingStatus;
    }

    public String getProductSellsCode() {
        return productSellsCode;
    }

    public void setProductSellsCode(String productSellsCode) {
        this.productSellsCode = productSellsCode;
    }

    public String getProductSellId() {
        return productSellId;
    }

    public void setProductSellId(String productSellId) {
        this.productSellId = productSellId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(String productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getProductPendingStatus() {
        return productPendingStatus;
    }

    public void setProductPendingStatus(String productPendingStatus) {
        this.productPendingStatus = productPendingStatus;
    }

}
