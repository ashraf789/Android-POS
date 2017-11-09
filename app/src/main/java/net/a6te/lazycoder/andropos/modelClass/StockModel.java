package net.a6te.lazycoder.andropos.modelClass;

import java.util.ArrayList;

/**
 * Created by Programmer on 5/7/2017.
 */

public class StockModel {
    private int sId;
    private String pName;
    private String brandName;
    private String pCode;
    private String pSize;
    private String stockLimit;

    public StockModel(int sId, String pName, String brandName, String pCode, String pSize, String stockLimit) {
        this.sId = sId;
        this.pName = pName;
        this.brandName = brandName;
        this.pCode = pCode;
        this.pSize = pSize;
        this.stockLimit = stockLimit;
    }

    public StockModel(String pName, String brandName, String pCode, String pSize, String stockLimit) {
        this.pName = pName;
        this.brandName = brandName;
        this.pCode = pCode;
        this.pSize = pSize;
        this.stockLimit = stockLimit;
    }

    public StockModel() {
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpSize() {
        return pSize;
    }

    public void setpSize(String pSize) {
        this.pSize = pSize;
    }

    public String getStockLimit() {
        return stockLimit;
    }

    public void setStockLimit(String stockLimit) {
        this.stockLimit = stockLimit;
    }

    public ArrayList<StockModel> getAllStock(){
        ArrayList<StockModel> stocks = new ArrayList<>();
        stocks.add(new StockModel("Tv","Sony","1001","DAN 28' ","100pcs"));
        stocks.add(new StockModel("Tv","Dell","1005","DAN 18.5' ","300pcs"));
        stocks.add(new StockModel("Laptop","Dell","101","DAN 28' ","10pcs"));
        stocks.add(new StockModel("Computer","Sony","109","DAN 22' ","500pcs"));
        stocks.add(new StockModel("Laptop","Samsung","101","DAN 27' ","400pcs"));
        return stocks;
    }
}
