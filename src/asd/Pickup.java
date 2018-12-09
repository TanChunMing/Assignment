/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

/**
 *
 * @author Pz
 */
public class Pickup {
    private String pickupID;
    private String pickupDate;
    private String pickupTime;
    private String customerName;
    
    public Pickup(){
        
    }
    
    public Pickup(String pickupID, String pickupDate, String pickupTime, String customerName){
        this.pickupID=pickupID;
        this.pickupDate=pickupDate;
        this.pickupTime=pickupTime;
        this.customerName=customerName;
    }
    
    public String getPickupID(){
        return pickupID;
    }
    public void setPickupID(String id){
        this.pickupID=id;
    }
    public String getPickupDate(){
        return pickupDate;
    }
    public void setPickupDate(String date){
        this.pickupDate=date;
    }
    public String getTime(){
        return pickupTime;
    }
    public void setTime(String time){
        this.pickupTime=time;
    }
    public String getCustomerName(){
        return customerName;
    }
    public void setCustomerName(String name){
        this.customerName=name;
    }
}
