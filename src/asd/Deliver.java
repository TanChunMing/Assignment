/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;
import java.util.Comparator;
/**
 *
 * @author Pz
 */
public class Deliver {

    String date;
    String packageID;
    String name;
    String address;
    int code;
    public Deliver(){
        
    }
    public Deliver(String date,String packageID,String name ,int code,String address) 
    {
        this.name = name;
        this.packageID = packageID;
        this.name =name;
        this.address=address;
        this.code=code;
    } 
 
}
class marksCompare implements Comparator<Deliver>
{
    @Override
    public int compare(Deliver s1, Deliver s2)
    {
        return s1.code - s2.code;
    }
}