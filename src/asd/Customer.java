/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

/**
 *
 * @author ASUS
 */
public class Customer {
    private String custID;
    private String custName;
    private String custType;
    private int creditLimit;
    private int currentCredit;
    private String companyName;
    
    public Customer(){
        
    }
    
    public Customer(String custID, String custName, String custType, String creditLimit, String currentCredit, String companyName){
        this.custID=custID;
        this.custName=custName;
         this.custType=custType;
        this.creditLimit=Integer.parseInt(creditLimit);
        this.currentCredit=Integer.parseInt(currentCredit);
        this.companyName=companyName;
    }
    
    public String getCustID(){
        return custID;
    }
    public void setCustID(String id){
        this.custID=id;
    }
    public String getCustName(){
        return custName;
    }
    public void setCustName(String name){
        this.custName=name;
    }
    public String getCustType(){
        return custType;
    }
    public void setCustType(String type){
        this.custType=type;
    }
    
    public int getCreditLimit(){
        return creditLimit;
    }
    public void setCreditLimit(String limit){
        this.creditLimit=Integer.parseInt(limit);
    }
    public int getCurrentCredit(){
        return currentCredit;
    }
    public void setCurrentCredit(String current){
        this.currentCredit=Integer.parseInt(current);
    }
    
    public String getCompanyName(){
        return companyName;
    }
    public void setCompanyName(String company){
        this.companyName=company;
    }
}
