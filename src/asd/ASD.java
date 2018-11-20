/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author USER
 */
public class ASD {

    /**
     * @param args the command line arguments
     */
    
    //array for flower type
    static ArrayList<String> arrayFlower = new ArrayList(); 
    static ArrayList<String> arrayFlowerPrice = new ArrayList();
    static ArrayList<String> arrayFlowerQuantity = new ArrayList();
    
    //array for bouquet type
    static ArrayList<String> arrayBouquet = new ArrayList(); 
    static ArrayList<String> arrayBouquetPrice = new ArrayList();
    static ArrayList<String> arrayBouquetQuantity = new ArrayList();
    
    //array for flower arrangement type
    static ArrayList<String> arrayArrangement = new ArrayList(); 
    static ArrayList<String> arrayArrangementPrice = new ArrayList();
    static ArrayList<String> arrayArrangementQuantity = new ArrayList();
    
    public static void main(String[] args) throws IOException {
        updateArray();
        
        mainMenu();
    }
    
    public static void updateArray() throws IOException{
        //clear item in array
        arrayFlower.clear();
        arrayFlowerPrice.clear();
        arrayFlowerQuantity.clear();
        arrayBouquet.clear();
        arrayBouquetPrice.clear();
        arrayBouquetQuantity.clear();
        arrayArrangement.clear();
        arrayArrangementPrice.clear();
        arrayArrangementQuantity.clear();

        //load data into arrays
        Scanner sc = new Scanner(System.in);
        File catalog = new File("catalog.txt");
        boolean exist = catalog.exists();
        
        if(exist){
            //count number of records in catalog.txt
            BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
            int linesCatalog = 0;
            while (readCatalog.readLine() != null) linesCatalog++;
            readCatalog.close();
            
            //find the items with the flower as its type
            Scanner read  = new Scanner(catalog);
            for (int i=0;i<linesCatalog;i++){
                String str = read.nextLine();
                String[] cols = str.split(";");
                if (cols[4].equals("flower")){
                    arrayFlower.add(cols[1]);
                    arrayFlowerPrice.add(cols[2]);
                    arrayFlowerQuantity.add(cols[3]);
                }
                if (cols[4].equals("bouquet")){
                    arrayBouquet.add(cols[1]);
                    arrayBouquetPrice.add(cols[2]);
                    arrayBouquetQuantity.add(cols[3]);
                }
                if (cols[4].equals("arrangement")){
                    arrayArrangement.add(cols[1]);
                    arrayArrangementPrice.add(cols[2]);
                    arrayArrangementQuantity.add(cols[3]);
                }
            }
            read.close();
        }
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
                module2();
            }
            else if (selection.equals("3")){
                invalidInput=false;
                module3();
            }
            else if (selection.equals("4")){
                invalidInput=false;
                module4();
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
                
            System.out.println("   Product ID\t\tProduct Name\t\tPrice(RM)\tQuantity In Stock");
            System.out.println("   ==========\t\t============\t\t=========\t=================");
                       
            //find the items with the flower as its type
            Scanner read  = new Scanner(catalog);
            for (int i=0;i<linesCatalog;i++){
                String str = read.nextLine();
                String[] cols = str.split(";");
                if (cols[4].equals(type)){
                    System.out.println(number + ". " + cols[0] + "\t\t" + cols[1] + "\t\t" + cols[2] + "\t\t" + cols[3]);
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
                updateProduct(type);
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
        String productID;
        int productCount = 0;
        
        //determine first letter of productID
            if (type.equals("flower"))
                productID = "F";
            else if(type.equals("bouquet"))
                productID = "B";
            else
                productID = "A";
            
        if(exist){
            //count number of records in catalog.txt
                BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
                int linesCatalog = 0;
                int number = 1;
                while (readCatalog.readLine() != null) linesCatalog++;
                readCatalog.close();

            //calculate linesCatalog
            Scanner read  = new Scanner(catalog);
                for (int i=0;i<linesCatalog;i++){
                    String str = read.nextLine();
                    String[] cols = str.split(";");
                    if (cols[4].equals(type)){
                        productCount++;
                    }
                }
            read.close();

            //create productID
                if (productCount <10){
                    productID = productID + "0000"+(productCount+1);
                }
                else if (productCount <100){
                    productID = productID + "000"+(productCount+1);
                }
                else if (productCount >= 100 && productCount < 1000){
                    productID = productID + "00"+(productCount+1);
                }  
                else if (productCount >= 1000 && productCount < 10000){
                    productID = productID + "0"+(productCount+1);
                }
                else{
                    productID = productID +(productCount+1);
                }
        }
        else
            productID = productID + "00001";
        
        while(blankInput){
            System.out.print("Product Name: ");
            String name = sc.nextLine();
            System.out.print("Price: ");
            String price = sc.nextLine();
            System.out.print("Quantity in stock: ");
            String quantity = sc.nextLine();
            
            if(name.equals("")||price.equals(""))
                System.out.println("Please do not leave any input field blank.");
            else if(!isInteger(price)){
                System.out.println("Price entered is not integer.");
            }
            else if(!isInteger(quantity)){
                System.out.println("Quantity entered is not integer.");
            }
            else{               
                blankInput = false;
                
                Writer output;
                    output = new BufferedWriter(new FileWriter("catalog.txt",true));
                    if (!exist){
                        output.append(productID+ ";"+name+ ";"+price+ ";"+quantity +";"+type);
                    }
                    else{
                    output.append(System.lineSeparator()+productID+ ";"+name+ ";"+price+ ";"+quantity +";"+type);
                    }
                    output.close();
                    
                    System.out.println("\nNew product added!");
                    module1();
            }
        }
    }
    
    public static void updateProduct(String type) throws IOException{
        Scanner sc = new Scanner(System.in);
        Product p1 = new Product();
        boolean invalidInput = true;
        
        System.out.print("Please enter Product ID: ");
        String productID = sc.nextLine();
        
        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) linesCatalog++;
        readCatalog.close();
                       
        //find the items with the flower as its type
        Scanner read  = new Scanner(new File("catalog.txt"));
        for (int i=0;i<linesCatalog;i++){
            String str = read.nextLine();
            String[] cols = str.split(";");
            if (cols[0].equals(productID)&&cols[4].equals(type)){
                p1.setProductID(productID);
                p1.setProductName(cols[1]);
                p1.setPrice(cols[2]);
                p1.setQuantity(cols[3]);
                p1.setType(cols[4]);
            }
            else{
                Writer output;
                output = new BufferedWriter(new FileWriter(new File("temp.txt"),true));
                output.append(str+System.lineSeparator());
                output.close(); 
            }
        }
        read.close();
        
        if(p1.getProductID() == null){
            System.out.println("The product ID you entered is not exist, please try again.\n");
            File tempFile = new File("temp.txt");
            tempFile.delete();
            catalog(type);
        }
        
        System.out.println("\nProduct Information");
        System.out.println("===================");
        System.out.print("Product ID: ");
        System.out.print(p1.getProductID());
        System.out.print("\nProduct Name: ");
        System.out.print(p1.getProductName());
        System.out.print("\nPrice: ");
        System.out.print(p1.getPrice());
        System.out.print("\nQuantity In Stock: ");
        System.out.print(p1.getQuantity());
        System.out.print("\nType of Product: ");
        System.out.print(p1.getType());
        
        System.out.println("\n\nOptions");
        System.out.println("=======");
        System.out.println("1. Change Product Name");
        System.out.println("2. Change Price");
        System.out.println("3. Change Quantity in Stock");
        System.out.println("\n0. Back");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                System.out.print("New Product Name: ");
                p1.setProductName(sc.nextLine());
            }
            else if(selection.equals("2")){
                invalidInput=false;
                System.out.print("New Price: ");
                p1.setPrice(sc.nextLine());
            }
            else if (selection.equals("3")){
                invalidInput=false;
                System.out.print("New Quantity in Stock: ");
                p1.setQuantity(sc.nextLine());
            }
            else if (selection.equals("0")){
                invalidInput=false;
                catalog(type);
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2, 3 or 0");
            }
        }
        System.out.println("\nUpdate success!");
        
        Writer output;
        output = new BufferedWriter(new FileWriter(new File("temp.txt"),true));
        output.append(p1.getProductID() + ";" + p1.getProductName() + ";" + p1.getPrice() + ";" + p1.getQuantity() + ";" + p1.getType());
        output.close(); 
                    
        File originalFile = new File("catalog.txt");
        originalFile.delete();
        
        File tempFile = new File("temp.txt");
        tempFile.renameTo(originalFile);
        
        updateArray();
        
        catalog(type);
    }
    
    private static boolean isInteger(String str){
        boolean result = false;
        
        for(int i=0; i<str.length();i++){
            if(!Character.isDigit(str.charAt(i))){
                result = false;
                break;
            }
            else
                result=true;
        }
        
        return result;
    }
    
    public static void module2() throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        
        System.out.println("Option");
        System.out.println("============");
        System.out.println("1. Manage Corporate Customer");
        System.out.println("2. View Corporate Customer Detail");
        System.out.println("3. Generate Invoice");
        System.out.println("4. Pay Invoice");
        System.out.println("\n0. Back");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                corporatecustomer("cc");
            }
            else if(selection.equals("2")){
                invalidInput=false;
                viewcorporatecustomerdetail("cc");
                
            }
            else if(selection.equals("3")){
                invalidInput=false;
                
            }
            else if(selection.equals("4")){
                invalidInput=false;
                
            }
           
            else if (selection.equals("0")){
                invalidInput=false;
                mainMenu();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2, 3, 4 or 0");
            }
        }
    } 
    
    public static void corporatecustomer(String type) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        
        File corporatecustomer = new File("customer.txt");
        boolean exist = corporatecustomer.exists();
        
        //check if catalog file exist
        if (!exist){
            System.out.println("-------------------------------------");
            System.out.println("|                                   |");
            System.out.println("|    Corporate Customer is empty    |");
            System.out.println("|                                   |");
            System.out.println("-------------------------------------");
        }
        else{
            //count number of records in catalog.txt
            BufferedReader readCorporateCustomer = new BufferedReader(new FileReader("customer.txt"));
            int linesCorporateCustomer = 0;
            int number = 1;
            while (readCorporateCustomer.readLine() != null) linesCorporateCustomer++;
            readCorporateCustomer.close();
                
            System.out.println("   Customer ID\t\tName\t\t\tCredit Limit\t\tCurrent Credit");
            System.out.println("   ============\t\t=========\t\t=============\t\t=============");
                       
            //find the items with the flower as its type
            Scanner read  = new Scanner(corporatecustomer);
            for (int i=0;i<linesCorporateCustomer;i++){
                String str = read.nextLine();
                String[] cols = str.split(",");
                if (cols[2].equals(type)){
                    System.out.println(number + ". " + cols[0] + "\t\t\t" + cols[1] + "\t\t" + cols[3]+ "\t\t\t"+ cols[4]);  
                    number++;
                }
            }
            read.close();
        }
        
        System.out.println("\n1. Add New Corporate Customer");
        System.out.println("2. Edit Existing Corporate Customer");
        System.out.println("3. Delete Existing Corporate Customer");
        System.out.println("\n0. Back");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                addNewCorporateCustomer(type);
            }
            else if(selection.equals("2")){
                invalidInput=false;
                
            }
            else if (selection.equals("3")){
                invalidInput=false;
                
            }
            else if (selection.equals("0")){
                invalidInput=false;
                module2();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2, 3 or 0");
            }
        }
    }
    
    public static void addNewCorporateCustomer(String type) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean blankInput = true;
        File corporatecustomer = new File("customer.txt");
        boolean exist = corporatecustomer.exists();
        
        while(blankInput){
            System.out.print("Customer ID: ");
            String id = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Credit Limit: ");
            String creditlimit = sc.nextLine();
            
            if(id.equals("")||name.equals("")||creditlimit.equals(""))
                System.out.println("Please do not leave any input field blank.");
            else{
                blankInput = false;
                
                Writer output;
                    output = new BufferedWriter(new FileWriter("customer.txt",true));
                    if (!exist){
                        output.append(id+ ","+name+ ","+type+","+creditlimit);
                    }
                    else{
                    output.append(System.lineSeparator()+id+ ","+name+ ","+type+","+creditlimit);
                    }
                    output.close();
                    
                    System.out.println("\nNew customer added!");
                    module2();
            }
        }
    }
    
    public static void viewcorporatecustomerdetail(String type) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        
        File corporatecustomer = new File("customer.txt");
        boolean exist = corporatecustomer.exists();
        
        //check if catalog file exist
        if (!exist){
            System.out.println("-------------------------------------");
            System.out.println("|                                   |");
            System.out.println("|    Corporate Customer is empty    |");
            System.out.println("|                                   |");
            System.out.println("-------------------------------------");
        }
        else{
            //count number of records in catalog.txt
            BufferedReader readCorporateCustomer = new BufferedReader(new FileReader("customer.txt"));
            int linesCorporateCustomer = 0;
            int number = 1;
            while (readCorporateCustomer.readLine() != null) linesCorporateCustomer++;
            readCorporateCustomer.close();
                
            System.out.println("   Customer ID\t\tName\t\t\tCredit Limit\t\tCurrent Credit");
            System.out.println("   ============\t\t=========\t\t=============\t\t=============");
                       
            //find the items with the flower as its type
            Scanner read  = new Scanner(corporatecustomer);
            for (int i=0;i<linesCorporateCustomer;i++){
                String str = read.nextLine();
                String[] cols = str.split(",");
                if (cols[2].equals(type)){
                    System.out.println(number + ". " + cols[0] + "\t\t\t" + cols[1] + "\t\t" + cols[3]+ "\t\t\t"+ cols[4]);  
                    number++;
                }
            }
            read.close();
        }
        
        System.out.println("\n0. Back");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            
            if (selection.equals("0")){
                invalidInput=false;
                module2();
            }
            else{
                System.out.println("Invalid Input. Please enter only 0");
            }
        }
    }
    public static void module3() throws IOException{
         Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        System.out.println("\nPlease Select a Item to Order:");
        System.out.println("============");
        System.out.println("1. Fresh Flower");
        System.out.println("2. Bouquet");
        
          while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                orderItem("flower");
            }
                       
            else if (selection.equals("0")){
                invalidInput=false;
                mainMenu();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2, or 0");
            }
        }
     }

     
     public static void orderItem(String type) throws IOException{
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
            int temp =0;
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
                    temp=number;
                    
                    number++;
                    
                }
          
            }read.close();
        }
            while(invalidInput){
                
            System.out.print("Please select your orders:");
            String selection = sc.next();
           
                
            
            
            
            if(selection.equals("1")){
               updateOrder(selection);
                invalidInput=false;
                
                 
            }else{
                System.out.println("Invalid Input. Please enter only valid number");
            }
            

        
                
                
        }
        
            
     }
    
     
     
      public static void updateOrder(String selection) throws IOException{
          Scanner sc=new Scanner(System.in);
          String quantities;  
          String str ;
          boolean blankInput = true;
          boolean nextOrder = true;
          File order = new File("order.txt");
          boolean exist = order.exists();
          while(blankInput){
          System.out.print("Enter your Quantities ");
          quantities=sc.nextLine();
           if(quantities.equals(""))
                System.out.println("Please do not leave any input field blank.");
            else{
                blankInput = false;
                
               Writer output;
               output = new BufferedWriter(new FileWriter("order.txt",true));
                if (!exist){
                        output.append(quantities);
                    }
                    else{
                    output.append(System.lineSeparator()+quantities);
                    }
                    output.close();
                    
                    System.out.println("\nNew Order added!");
          }
           while(nextOrder){
               System.out.print("Do you still want to add orders ? Y or N only ");
               str = sc.next();
               if(str.equals("Y")){
                 nextOrder=false;
                 orderItem("flower");
               }else if(str.equals("N")){
                   nextOrder=false;
                   System.out.println("Thank for the orders");
                     }
               else {
                   System.out.println("Invalid input. Please enter Y or N only");
                     }
                        
                     
                   
                   }
      }
     
      }
    
    public static void module4() throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
        String formattedString = localDate.format(formatter);
        
        System.out.println("Check");
        System.out.println("============");
        System.out.println("1. Pickup");
        System.out.println("2. Deliver");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
            }
            else if(selection.equals("2")){
                invalidInput=false;
                deliver(formattedString);
            }
            else if (selection.equals("0")){
                invalidInput=false;
                mainMenu();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2 and 0");
            }
        }
    }
    public static void deliver(String formattedString) throws IOException{
        Scanner sc = new Scanner(System.in); 
        File deliver = new File("deliver.txt");

            //count number of records in deliver.txt
            BufferedReader readDeliver = new BufferedReader(new FileReader("deliver.txt"));
            int linesDeliver = 0;
            int number = 1;
            while (readDeliver.readLine() != null) linesDeliver++;
            readDeliver.close();
                
            System.out.println("   OrderID");
            System.out.println("   =======");
                       
            Scanner read  = new Scanner(deliver);
            for (int i=0;i<linesDeliver;i++){
                String str = read.nextLine();
                String[] cols = str.split(",");
                if (cols[1].equals(formattedString)){
                    System.out.println(number + ". " + cols[0]);  
                    number++;
                }
            }
            read.close();
            module4();
    }
}
