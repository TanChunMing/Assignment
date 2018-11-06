/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

import java.util.Scanner;

/**
 *
 * @author USER
 */
public class ASD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mainMenu();
    }
    
    public static void mainMenu(){
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        
        System.out.println("Fiore Flowershop");
        System.out.println("================");
        System.out.println("1. Catalog Maintenance");
        System.out.println("2. Customer Maintenance and Invoice Payment");
        System.out.println("3. Catalog Order");
        System.out.println("4. Order Pickup/Delivery and Consumer Payment Management");
        System.out.println("\n0. Exit");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                module1();
            }
            else if(selection.equals("2")){
                invalidInput=false;
            }
            else if (selection.equals("3")){
                invalidInput=false;
            }
            else if (selection.equals("4")){
                invalidInput=false;
            }
            else if (selection.equals("0")){
                invalidInput=false;
                System.exit(0);
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2, 3, 4 or 0");
            }
        }
    }
    public static void module1(){
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        
        System.out.println("Product Type");
        System.out.println("============");
        System.out.println("1. Fresh Flower");
        System.out.println("2. Bouquet");
        System.out.println("3. Floral Arrangement");
        System.out.println("\n0. Back");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                
            }
            else if(selection.equals("2")){
                invalidInput=false;
            }
            else if (selection.equals("3")){
                invalidInput=false;
            }
            else if (selection.equals("0")){
                invalidInput=false;
                mainMenu();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2, 3 or 0");
            }
        }
    }
    
}
