package com.kip.gillz.vaccine_guide_system.Search;


public class SearchModel {

    private String Item;
    private int Quantity;
    private int Price;
    private String Receipt;
    private String TDate;
    private String Item_id;
    private String Agent;
    private int Product_Count;
    private int Tprice;

    public SearchModel(String Item, int Quantity, int Price, String Receipt, String TDate, String Item_id, String Agent, int Product_Count, int Tprice) {
        this.Item = Item;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Receipt = Receipt;
        this.TDate = TDate;
        this.Item_id = Item_id;
        this.Agent = Agent;
        this.Product_Count = Product_Count;
        this.Tprice = Tprice;

    }

    public String getItem() {
        return Item;
    }
    public int getQuantity() {
        return Quantity;
    }
    public int getPrice() {
        return Price;
    }
    public String getReceipt() {
        return Receipt;
    }
    public String getTDate() {
        return TDate;
    }
    public String getAgent() {
        return Agent;
    }
    public String getItem_id() {
        return Item_id;
    }
    public int getProduct_Count() {
        return Product_Count;
    }
    public int getTPrice() {
        return Tprice;
    }

}
