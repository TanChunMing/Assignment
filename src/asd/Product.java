/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

/**
 *
 * @author USER
 */
public class Product {
    private String productID;
    private String productName;
    private int price;
    private int quantity;
    private String type;
    private String payment;
    
    public Product(){
        
    }
    
    public Product(String productID, String productName, String price, String quantity, String type, String payment){
        this.productID=productID;
        this.productName=productName;
        this.price=Integer.parseInt(price);
        this.quantity=Integer.parseInt(quantity);
        this.type=type;
        this.payment=payment;
    }
    
    public String getProductID(){
        return productID;
    }
    public void setProductID(String id){
        this.productID=id;
    }
    public String getProductName(){
        return productName;
    }
    public void setProductName(String name){
        this.productName=name;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(String price){
        this.price=Integer.parseInt(price);
    }
    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(String quantity){
        this.quantity=Integer.parseInt(quantity);
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }
        public String getPayment(){
        return payment;
    }
    public void setPayment(String type){
        this.payment=payment;
    }
}
