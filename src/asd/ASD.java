/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class ASD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        mainMenu();
    }
    
    public static void mainMenu() throws IOException{
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
    public static void module1() throws IOException{
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
                catalog("flower");
            }
            else if(selection.equals("2")){
                invalidInput=false;
                catalog("bouquet");
            }
            else if (selection.equals("3")){
                invalidInput=false;
                catalog("arrangement");
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
    
    public static void catalog(String type) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        
        File catalog = new File("catalog.txt");
        boolean exist = catalog.exists();
        
        //check if catalog file exist
        if (!exist){
            System.out.println("--------------------------");
            System.out.println("|                        |");
            System.out.println("|    Catalog is empty    |");
            System.out.println("|                        |");
            System.out.println("--------------------------");
        }
        else{
            //count number of records in catalog.txt
            BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
            int linesCatalog = 0;
            int number = 1;
            while (readCatalog.readLine() != null) linesCatalog++;
            readCatalog.close();
                
            System.out.println("   Product Name\t\tPrice(RM)");
            System.out.println("   ============\t\t=========");
                       
            //find the items with the flower as its type
            Scanner read  = new Scanner(catalog);
            for (int i=0;i<linesCatalog;i++){
                String str = read.nextLine();
                String[] cols = str.split(",");
                if (cols[2].equals(type)){
                    System.out.println(number + ". " + cols[0] + "\t\t" + cols[1]);  
                    number++;
                }
            }
            read.close();
        }
        
        System.out.println("\n1. Add new product");
        System.out.println("2. Edit existing product");
        System.out.println("3. Delete existing product");
        System.out.println("\n0. Back");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                addNewProduct(type);
            }
            else if(selection.equals("2")){
                invalidInput=false;
                
            }
            else if (selection.equals("3")){
                invalidInput=false;
                
            }
            else if (selection.equals("0")){
                invalidInput=false;
                module1();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2, 3 or 0");
            }
        }
    }
    
    public static void addNewProduct(String type) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean blankInput = true;
        File catalog = new File("catalog.txt");
        boolean exist = catalog.exists();
        
        while(blankInput){
            System.out.print("Product Name: ");
            String name = sc.nextLine();
            System.out.print("Price: ");
            String price = sc.nextLine();
            
            if(name.equals("")||price.equals(""))
                System.out.println("Please do not leave any input field blank.");
            else{
                blankInput = false;
                
                Writer output;
                    output = new BufferedWriter(new FileWriter("catalog.txt",true));
                    if (!exist){
                        output.append(name+ ","+price+ ","+type);
                    }
                    else{
                    output.append(System.lineSeparator()+name+ ","+price+ ","+type);
                    }
                    output.close();
                    
                    System.out.println("\nNew product added!");
                    module1();
            }
        }
    }
}
