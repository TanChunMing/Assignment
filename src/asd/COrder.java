/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

/**
 *
 * @author Frankie
 */
public class COrder {
    private String custID;
    private String productID;
    private String productType;
    private int int1;
    private int int2;
    
     public COrder(){
        
    }
     
     public COrder(String custID, String productID, String productType, String int1, String int2){
         this.custID=custID;
        this.productID=productID;
         this.productType=productType;
        this.int1=Integer.parseInt(int1);
        this.int2=Integer.parseInt(int2);
     }
     
     public String getCustID(){
        return custID;
    }
    public void setCustID(String id){
        this.custID=id;
    }
    
    public String getProductID(){
        return productID;
    }
    public void setProductID(String id){
        this.productID=id;
    }
    
    public String getProductType(){
        return productType;
    }
    public void setProductType(String type){
        this.productType=type;
    }
    
    public int getInt1(){
        return int1;
    }
    public void setInt1(String limit){
        this.int1=Integer.parseInt(limit);
    }
    
    public int getInt2(){
        return int2;
    }
    public void setInt2(String current){
        this.int2=Integer.parseInt(current);
    }
}
