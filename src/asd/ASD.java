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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.time.ZoneId;

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
    
    //array for deliverlist
    static ArrayList<Deliver> arrayDeliver = new ArrayList<Deliver>();

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
        File catalog = new File("catalog.txt");
        boolean exist = catalog.exists();
        
        if(exist){
            //count number of records in catalog.txt
            BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
            int linesCatalog = 0;
            while (readCatalog.readLine() != null) linesCatalog++;
            readCatalog.close();
            
            //add item to different arrayList based on their type
            Scanner read  = new Scanner(catalog);
            for (int i=0;i<linesCatalog;i++){
                String str = read.nextLine();
                if (!str.equals("")){
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
        
        System.out.println("Options");
        System.out.println("=======");
        System.out.println("1. View Catalog");
        System.out.println("2. View Monthly Promotion");
        System.out.println("\n0. Back to Main Menu");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                viewProduct();
            }
            else if(selection.equals("2")){
                invalidInput=false;
                monthlyPromotion();
            }
            else if (selection.equals("0")){
                invalidInput=false;
                mainMenu();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2 or 0");
            }
        }
    }
    
    public static void viewProduct() throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        
        System.out.println("Product Type");
        System.out.println("============");
        System.out.println("1. Fresh Flower");
        System.out.println("2. Bouquet");
        System.out.println("3. Floral Arrangement");
        System.out.println("\n0. Back to Catalog Maintenance");
        
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
                module1();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2, 3 or 0");
            }
        }
    }
    
    public static void catalog(String type) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        String outOfStock = "";
        
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
                       
            //find the items with the type selected by the user
            Scanner read  = new Scanner(catalog);
            for (int i=0;i<linesCatalog;i++){
                String str = read.nextLine();
                if (!str.equals("")){
                    String[] cols = str.split(";");
                    if (cols[4].equals(type)){
                        System.out.println(number + ". " + cols[0] + "\t\t" + cols[1] + "\t\t" + cols[2] + "\t\t" + cols[3]);
                        number++;

                        //display the items with quantity less than 3 in stock
                        if(Integer.parseInt(cols[3]) <= 3){
                            outOfStock += "** \"" + cols[1] + "\" is less in stock, which is " + cols[3] + " item left.\n";
                        }
                    }
                }
            }
            read.close();
        }
        
        //display the details of items that are out of stock if the string is not empty
        if (!outOfStock.equals("")){
            System.out.println("\n" + outOfStock);
        }
        
        System.out.println("\n1. Add new product");
        System.out.println("2. Edit existing product");
        System.out.println("3. Delete existing product");
        System.out.println("\n0. Back to Product Type Selection");
                
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
                deleteProduct(type);
            }
            else if (selection.equals("0")){
                invalidInput=false;
                viewProduct();
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
                while (readCatalog.readLine() != null) linesCatalog++;
                readCatalog.close();

            //calculate amount of particular product type
            Scanner read  = new Scanner(catalog);
                for (int i=0;i<linesCatalog;i++){
                    String str = read.nextLine();
                    if (!str.equals("")){
                        String[] cols = str.split(";");
                        if (cols[4].equals(type)){
                            productCount++;
                        }
                    }
                }
            read.close();

                //create productID
                if (productCount <9){
                    productID = productID + "0000"+(productCount+1);
                }
                else if (productCount <99){
                    productID = productID + "000"+(productCount+1);
                }
                else if (productCount < 999){
                    productID = productID + "00"+(productCount+1);
                }  
                else if (productCount < 9999){
                    productID = productID + "0"+(productCount+1);
                }
                else{
                    productID = productID +(productCount+1);
                }
                productID = checkID(productID, productCount);
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
                
                //write the new line of data into the catalog.txt
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
                    viewProduct();
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
                       
        //find the items with selected type and the productID matched
        Scanner read  = new Scanner(new File("catalog.txt"));
        for (int i=0;i<linesCatalog;i++){
            String str = read.nextLine();
            if (!str.equals("")){
                String[] cols = str.split(";");
                if (cols[0].equals(productID)&&cols[4].equals(type)){
                    p1.setProductID(productID);
                    p1.setProductName(cols[1]);
                    p1.setPrice(cols[2]);
                    p1.setQuantity(cols[3]);
                    p1.setType(cols[4]);
                }
                else{
                    //items that are not matched are saved into temp.txt
                    Writer output;
                    output = new BufferedWriter(new FileWriter(new File("temp.txt"),true));
                    output.append(str+System.lineSeparator());
                    output.close(); 
                }
            }
        }
        read.close();
        
        //check if productID entered exist in catalog.txt
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
                String price;
                do{
                    System.out.print("New Price: ");
                    price = sc.nextLine();
                
                    if(isInteger(price))
                        p1.setPrice(price);
                    else
                        System.out.println("Please enter a valid price.");
                }while(!isInteger(price));
            }
            else if (selection.equals("3")){
                invalidInput=false;
                String quantity;
                do{
                    System.out.print("New Quantity in Stock: ");
                    quantity = sc.nextLine();
                    
                    if(isInteger(quantity))
                        p1.setQuantity(quantity);
                    else
                        System.out.println("Please enter a valid quantity.");
                }while(!isInteger(quantity));
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
        
        //save the new line of updated data into temp.txt
        Writer output;
        output = new BufferedWriter(new FileWriter(new File("temp.txt"),true));
        output.append(p1.getProductID() + ";" + p1.getProductName() + ";" + p1.getPrice() + ";" + p1.getQuantity() + ";" + p1.getType());
        output.close(); 
        
        //delete the original catalog.txt and rename temp.txt to catalog.txt
        File originalFile = new File("catalog.txt");
        originalFile.delete();
        
        File tempFile = new File("temp.txt");
        tempFile.renameTo(originalFile);
        
        updateArray();
        
        catalog(type);
    }
    
    public static void deleteProduct(String type) throws IOException{
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
                       
        //copy lines that not matched with the productID and the selected type into temp.txt
        Scanner read  = new Scanner(new File("catalog.txt"));
        for (int i=0;i<linesCatalog;i++){
            String str = read.nextLine();
            if (!str.equals("")){
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
                    if(read.hasNextLine()){
                        output.append(str+System.lineSeparator());
                        output.close(); 
                    }
                    else{
                        output.append(str);
                        output.close(); 
                    }
                }
            }
        }
        read.close();

        if(p1.getProductID() == null){
            System.out.println("The product ID you entered is not exist, please try again.\n");
            File tempFile = new File("temp.txt");
            tempFile.delete();
            catalog(type);
        }
        
        while (invalidInput){
            System.out.print("Do you really want to delete " + p1.getProductID() + ", " + p1.getProductName() + "?(Please enter Y or N)" );
            String selection = sc.nextLine();

            if(selection.equals("Y")||selection.equals("y")){
                invalidInput=false;
                File originalFile = new File("catalog.txt");
                originalFile.delete();

                File tempFile = new File("temp.txt");
                tempFile.renameTo(originalFile);
                
                System.out.println(p1.getProductID() + ", " + p1.getProductName() + " is deleted.\n");
            }
            else if(selection.equals("N") || selection.equals("n")){
                invalidInput=false;
                File tempFile = new File("temp.txt");
                tempFile.delete();
                
                System.out.println("\n");
            }  
            else{
                System.out.println("Invalid input. Please enter only Y or N.");
            }
        }
        updateArray();
        
        catalog(type);
    }
    
    private static String checkID(String productID, int productCount) throws IOException{
        String ID = productID;
        
        //count number of records in catalog.txt
            BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
            int linesCatalog = 0;
            while (readCatalog.readLine() != null) linesCatalog++;
            readCatalog.close();
            
            //find whether the productID is exist in catalog.txt
            //if exist, the productID will be increased by one
            //call this method again to check again
            Scanner read  = new Scanner(new File("catalog.txt"));
            for (int i=0;i<linesCatalog;i++){
                String str = read.nextLine();
                if (!str.equals("")){
                    String[] cols = str.split(";");
                    if (cols[0].equals(ID)){
                        productCount++;
                        if (productCount <9){
                            ID = productID.charAt(0) + "0000"+(productCount+1);
                        }
                        else if (productCount <99){
                            ID = productID.charAt(0) + "000"+(productCount+1);
                        }
                        else if (productCount < 999){
                            ID = productID.charAt(0) + "00"+(productCount+1);
                        }  
                        else if (productCount < 9999){
                            ID = productID.charAt(0) + "0"+(productCount+1);
                        }
                        checkID(ID, productCount);
                    }
                }
            }
            read.close();
            
            return ID;
    }
    
    private static boolean isInteger(String str){
        boolean result = false;
        
        //check whether the string entered is integer
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
    
    public static void monthlyPromotion() throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        
        System.out.println("Options");
        System.out.println("=======");
        System.out.println("1. View Past Monthly Promotion");
        System.out.println("2. Add New Monthly Promotion Catalog");
        System.out.println("\n0. Back to Catalog Maintenance");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                viewMonthlyPromotion();
            }
            else if(selection.equals("2")){
                invalidInput=false;
                addMonthlyPromotion();
            }
            else if (selection.equals("0")){
                invalidInput=false;
                module1();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2 or 0");
            }
        }
    }
    
    public static void viewMonthlyPromotion() throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        String flower ="";
        String bouquet = "";
        String arrangement = "";
        String month = "";
        String year = "";
        
        while (invalidInput){
            System.out.print("Enter Month (digit): ");
            month = sc.nextLine();
            System.out.print("Enter year (digit): ");
            year = sc.nextLine();
            
            //check if the month and year entered are both integer
            if(!isInteger(month) || !isInteger(year))
                System.out.println("\nPlease enter only digit for month and year.\n");
            //check if the month entered is between 1 to 12
            else if(Integer.parseInt(month)<1 || Integer.parseInt(month) > 12)
                System.out.println("\nPlease enter a valid month.\n");
            else
                invalidInput = false;
        }
        
        //count number of records in promotion.txt
        BufferedReader readPromotion = new BufferedReader(new FileReader("promotion.txt"));
        int linesPromotion = 0;
        while (readPromotion.readLine() != null) linesPromotion++;
        readPromotion.close();
        
        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) linesCatalog++;
        readCatalog.close();
        
        System.out.println("\nPromotion for " + month(month) + " " + year + "\n");
        System.out.println("Product ID\tProduct Name\t\tOriginal Price(RM)\tDiscount Rate(%)\tDiscounted Price(RM)");
        System.out.println("==========\t============\t\t==================\t================\t====================");
                       
        //list the promotion items based on month and year entered
        Scanner read  = new Scanner(new File("promotion.txt"));
        for (int i=0;i<linesPromotion;i++){
            String str = read.nextLine();
            if (!str.equals("")){
                String[] cols = str.split(";");
                if (cols[3].equals(month) && cols[4].equals(year)){
                    Scanner read1  = new Scanner(new File("catalog.txt"));
                    for (int j=0;j<linesCatalog;j++){
                        String str1 = read1.nextLine();
                        String[] cols1 = str1.split(";");
                        if (cols[0].equals(cols1[0])){ 
                            //arrange items according to product type
                            if (cols[0].charAt(0)=='F')
                                flower += cols1[0] + "\t\t" + cols1[1] + "\t\t" + cols1[2] + "\t\t\t" + cols[2] + "\t\t\t" + cols[1] + "\n";
                            else if(cols[0].charAt(0)=='B')
                                bouquet += cols1[0] + "\t\t" + cols1[1] + "\t\t" + cols1[2] + "\t\t\t" + cols[2] + "\t\t\t" + cols[1] + "\n";
                            else
                                arrangement += cols1[0] + "\t\t" + cols1[1] + "\t\t" + cols1[2] + "\t\t\t" + cols[2] + "\t\t\t" + cols[1] + "\n";
                            break;
                        }
                    }
                    read1.close();
                }
            }
        }
        read.close();
        
        //display flower, bouquet, or arrangement string if not empty
        if (!flower.equals("")){
            System.out.println("----------");
            System.out.println("| FLOWER |");
            System.out.println("----------");
            System.out.println(flower);
        }
        if (!bouquet.equals("")){
            System.out.println("-----------");
            System.out.println("| BOUQUET |");
            System.out.println("-----------");
            System.out.println(bouquet);
        }
        if (!arrangement.equals("")){
            System.out.println("--------------");
            System.out.println("| ARRANGEMENT |");
            System.out.println("--------------");
            System.out.println(arrangement);
        }

        monthlyPromotion();
    }
    
    private static String month(String month){
        String str="";
        
        //return month name based on the integer number of month entered by user
        switch(month){
            case "1": str = "January";
            break;
            case "2": str = "February";
            break;
            case "3": str = "March";
            break;
            case "4": str = "April";
            break;
            case "5": str = "May";
            break;
            case "6": str = "June";
            break;
            case "7": str = "July";
            break;
            case "8": str = "August";
            break;
            case "9": str = "September";
            break;
            case "10": str = "October";
            break;
            case "11": str = "November";
            break;
            case "12": str = "December";
            break;
        }
        
        return str;
    }
    
    public static void addMonthlyPromotion() throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        Queue<String> productID = new CircularLinkedQueue();
        Queue<String> productName = new CircularLinkedQueue();
        Queue<String> oriPrice = new CircularLinkedQueue();
        Queue<String> discountRate = new CircularLinkedQueue();
        Queue<String> disPrice = new CircularLinkedQueue();
        
        //get current month and year integer
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        
        System.out.println("\nNew Promotion List for " + month(Integer.toString(month)) + " " +  year);
        System.out.println("   Product ID\t\tProduct Name\t\tOriginal Price(RM)\tDiscount Rate(%)\tDiscounted Price(RM)");
        System.out.println("   ==========\t\t============\t\t==================\t================\t====================");
        
        //count number of records in promotion.txt
        BufferedReader readPromotion = new BufferedReader(new FileReader("promotion.txt"));
        int linesPromotion = 0;
        while (readPromotion.readLine() != null) linesPromotion++;
        readPromotion.close();
        
        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) linesCatalog++;
        readCatalog.close();
        
        int number = 1;
        //add the details into CircularLinkedQueue if the month and year of the records matched
        Scanner read  = new Scanner(new File("promotion.txt"));
        for (int i=0;i<linesPromotion;i++){
            String str = read.nextLine();
            if (!str.equals("")){
                String[] cols = str.split(";");
                if (cols[3].equals(Integer.toString(month)) && cols[4].equals(Integer.toString(year))){
                    Scanner read1  = new Scanner(new File("catalog.txt"));
                    for (int j=0;j<linesCatalog;j++){
                        String str1 = read1.nextLine();
                        if (!str1.equals("")){
                            String[] cols1 = str1.split(";");
                            if (cols[0].equals(cols1[0])){
                                productID.enqueue(cols[0]);
                                productName.enqueue(cols1[1]);
                                oriPrice.enqueue(cols1[2]);
                                discountRate.enqueue(cols[2]);
                                disPrice.enqueue(cols[1]);
                                break;
                            }
                        }
                    }
                    read1.close();
                }
            }
        }
        read.close();
        
        //display the data in CircularLinkedQueue
        while(!productID.isEmpty()){
            System.out.println(number + ". " + productID.dequeue() + "\t\t" + productName.dequeue() + "\t\t" + oriPrice.dequeue() + "\t\t\t" + discountRate.dequeue() + "\t\t\t" + disPrice.dequeue());
            number++;
        }
        
        System.out.println("\nOptions");
        System.out.println("=======");
        System.out.println("1. Add new promotion product");
        System.out.println("2. Delete promotion product");
        System.out.println("\n0. Exit");
        
        while(invalidInput){
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();
            
            if (selection.equals("1")){
                invalidInput=false;
                addPromotionProduct(Integer.toString(month),Integer.toString(year));
            }
            else if(selection.equals("2")){
                invalidInput=false;
                deletePromotionProduct(Integer.toString(month),Integer.toString(year));
            }
            else if (selection.equals("0")){
                invalidInput=false;
                monthlyPromotion();
            }
            else{
                System.out.println("Invalid Input. Please enter only 1, 2 or 0");
            }
        }
    }
    
    public static void addPromotionProduct(String month, String year) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        boolean invalidID = true;
        double price = 0;
        String flower ="";
        String bouquet = "";
        String arrangement = "";
        String productID = "";
        String discountRate = "";
        
        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) linesCatalog++;
        readCatalog.close();
                
        System.out.println("Product ID\tProduct Name\t\tPrice(RM)\tQuantity In Stock");
        System.out.println("==========\t============\t\t=========\t=================");
                       
        //read all items out from the catalog.txt
        Scanner read  = new Scanner(new File("catalog.txt"));
        for (int i=0;i<linesCatalog;i++){
            String str = read.nextLine();
            if (!str.equals("")){
                String[] cols = str.split(";");
                //separate items based on their product type
                if (cols[0].charAt(0) == 'F')
                    flower += cols[0] + "\t\t" + cols[1] + "\t\t" + cols[2] + "\t\t" + cols[3] + "\n";
                else if(cols[0].charAt(0) == 'B')
                    bouquet += cols[0] + "\t\t" + cols[1] + "\t\t" + cols[2] + "\t\t" + cols[3] + "\n";
                else
                    arrangement += cols[0] + "\t\t" + cols[1] + "\t\t" + cols[2] + "\t\t" + cols[3] + "\n";
            }
        }
        read.close();
        
        //display items in catalog
        if (!flower.equals("")){
            System.out.println("----------");
            System.out.println("| FLOWER |");
            System.out.println("----------");
            System.out.println(flower);
        }
        if (!bouquet.equals("")){
            System.out.println("-----------");
            System.out.println("| BOUQUET |");
            System.out.println("-----------");
            System.out.println(bouquet);
        }
        if (!arrangement.equals("")){
            System.out.println("--------------");
            System.out.println("| ARRANGEMENT |");
            System.out.println("--------------");
            System.out.println(arrangement);
        }
        
        while(invalidID){           
            System.out.print("\nProduct ID: ");
            productID = sc.nextLine();
            do{
                System.out.print("Discount Rate: ");
                discountRate = sc.nextLine();
                
                //check if the discountRate entered is digit
                if (!isInteger(discountRate)){
                    System.out.println("Please enter a valid discount rate.");
                }
                
            }while(!isInteger(discountRate));
            
            //read the price of the specific item based on productID
            //this block of code also can check whether the productID is exist
            //if the productID not exist, the invalidID will not become false
            Scanner read1  = new Scanner(new File("catalog.txt"));
            for (int i=0;i<linesCatalog;i++){
                String str = read1.nextLine();
                if (!str.equals("")){
                    String[] cols = str.split(";");
                    if (cols[0].equals(productID)){
                        price = Double.parseDouble(cols[2]) * (1-(Double.parseDouble(discountRate)/100));
                        invalidID = false;
                    }
                }
            }
            read1.close();
        
            if (invalidID)
                System.out.println("The product ID you entered is not exist.Please enter again.");
            
            //count number of records in promotion.txt
            BufferedReader readPromotion = new BufferedReader(new FileReader("promotion.txt"));
            int linesPromotion = 0;
            while (readPromotion.readLine() != null) linesPromotion++;
            readPromotion.close();
        
            //search whether there is existing productID for the same month and year
            Scanner read2  = new Scanner(new File("promotion.txt"));
            for (int i=0;i<linesPromotion;i++){
                String str = read2.nextLine();
                if (!str.equals("")){
                    String[] cols = str.split(";");
                    if (cols[0].equals(productID)&&cols[3].equals(month)&&cols[4].equals(year)){
                        System.out.println("The product ID you entered is already in the promotion list.");
                        invalidID = false;
                        addMonthlyPromotion();
                    }
                }
            }
            read2.close();
        }
        
        //ask the user whether they want to add more item 
        while(invalidInput){
            System.out.print("\nDo you want to add more item in this promotion list? (Y or N): ");
            String selection = sc.nextLine();
            if (selection.equals("Y") || selection.equals("y")){
                invalidInput = false;
                    
                    //add the records into promotion.txt
                    Writer output;
                    output = new BufferedWriter(new FileWriter("promotion.txt",true));
                    if (!new File("promotion.txt").exists()){
                        output.append(productID+ ";"+price+ ";"+discountRate+ ";"+month +";"+year);
                    }
                    else{
                        output.append(System.lineSeparator()+productID+ ";"+price+ ";"+discountRate+ ";"+month +";"+year);
                    }
                    output.close();
                    
                    System.out.println("\nNew promotion product added!");
                
                //call the addPromotionProduct() again to add more items into promotion list
                addPromotionProduct(month, year);
            }
            else if(selection.equals("N") || selection.equals("n")){
                invalidInput = false;
                
                    //add the records into promotion.txt
                    Writer output;
                    output = new BufferedWriter(new FileWriter("promotion.txt",true));
                    if (!new File("promotion.txt").exists()){
                        output.append(productID+ ";"+Double.toString(price)+ ";"+discountRate+ ";"+month +";"+year);
                    }
                    else{
                    output.append(System.lineSeparator()+productID+ ";"+Double.toString(price)+ ";"+discountRate+ ";"+month +";"+year);
                    }
                    output.close();
                    
                    System.out.println("\nNew promotion product added!");
                
                //return to last page which is addMonthlyPromotion()
                addMonthlyPromotion();
                
            }
            else
                System.out.println("Invalid Input. Please enter only Y or N");
        }
    }
    
    public static void deletePromotionProduct(String month, String year) throws IOException{
        Scanner sc = new Scanner(System.in);
        Product p1 = new Product();
        boolean invalidInput = true;
        
        System.out.print("Please enter Product ID: ");
        String productID = sc.nextLine();
        
        //count number of records in promotion.txt
        BufferedReader readPromotion = new BufferedReader(new FileReader("promotion.txt"));
        int linesPromotion = 0;
        while (readPromotion.readLine() != null) linesPromotion++;
        readPromotion.close();
                       
        //find the items with month, year, and productID matched
        //items that not matched will be copied to temp.txt
        Scanner read  = new Scanner(new File("promotion.txt"));
        for (int i=0;i<linesPromotion;i++){
            String str = read.nextLine();
            if (!str.equals("")){
                String[] cols = str.split(";");
                if (cols[3].equals(month)&&cols[4].equals(year)&&cols[0].equals(productID)){
                    p1.setProductID(productID);
                }
                else{
                    Writer output;
                    output = new BufferedWriter(new FileWriter(new File("temp.txt"),true));
                    if(read.hasNextLine()){
                        output.append(str+System.lineSeparator());
                        output.close(); 
                    }
                    else{
                        output.append(str);
                        output.close(); 
                    }
                }
            }
        }
        read.close();

        //productID not exist in promotion.txt, the temp.txt will be deleted to avoid system error
        if(p1.getProductID() == null){
            System.out.println("The product ID you entered is not exist, please try again.\n");
            File tempFile = new File("temp.txt");
            tempFile.delete();
            addMonthlyPromotion();
        }
        
        //ask for delete confirmation of selected item
        while (invalidInput){
            System.out.print("Do you really want to delete item " + p1.getProductID() + "?(Please enter Y or N)" );
            String selection = sc.nextLine();

            if(selection.equals("Y")||selection.equals("y")){
                invalidInput=false;
                //delete original promotion.txt and rename temp.txt to promotion.txt
                File originalFile = new File("promotion.txt");
                originalFile.delete();

                File tempFile = new File("temp.txt");
                tempFile.renameTo(originalFile);
                
                System.out.println("Item " + p1.getProductID() + " is deleted from monthly promotion list.");
            }
            else if(selection.equals("N") || selection.equals("n")){
                //delete temp.txt
                invalidInput=false;
                File tempFile = new File("temp.txt");
                tempFile.delete();
            }  
            else{
                System.out.println("Invalid input. Please enter only Y or N.");
            }
        }
        addMonthlyPromotion();
    }
   
    public static void module2() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;

        System.out.println("Option");
        System.out.println("============");
        System.out.println("1. Manage Corporate Customer");
        System.out.println("2. View Corporate Customer Detail");
        System.out.println("3. Generate Invoice");
        System.out.println("4. Pay Invoice");
        System.out.println("\n0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                corporatecustomer("cc");
            } else if (selection.equals("2")) {
                invalidInput = false;
                viewcorporatecustomerdetail("cc");

            } else if (selection.equals("3")) {
                invalidInput = false;

            } else if (selection.equals("4")) {
                invalidInput = false;

            } else if (selection.equals("0")) {
                invalidInput = false;
                mainMenu();
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2, 3, 4 or 0");
            }
        }
    }

    public static void corporatecustomer(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;

        File corporatecustomer = new File("customer.txt");
        boolean exist = corporatecustomer.exists();

        //check if catalog file exist
        if (!exist) {
            System.out.println("-------------------------------------");
            System.out.println("|                                   |");
            System.out.println("|    Corporate Customer is empty    |");
            System.out.println("|                                   |");
            System.out.println("-------------------------------------");
        } else {
            //count number of records in catalog.txt
            BufferedReader readCorporateCustomer = new BufferedReader(new FileReader("customer.txt"));
            int linesCorporateCustomer = 0;
            int number = 1;
            while (readCorporateCustomer.readLine() != null) {
                linesCorporateCustomer++;
            }
            readCorporateCustomer.close();

            System.out.println("   Customer ID\t\tName\t\t\tCredit Limit\t\tCurrent Credit\t\tCompanyName");
            System.out.println("   ============\t\t=========\t\t=============\t\t=============\t\t===============");

            //find the items with the flower as its type
            Scanner read = new Scanner(corporatecustomer);
            for (int i = 0; i < linesCorporateCustomer; i++) {
                String str = read.nextLine();
                String[] cols = str.split(",");
                if (cols[2].equals(type)) {
                    System.out.println(number + ". " + cols[0] + "\t\t\t" + cols[1] + "\t\t" + cols[3] + "\t\t\t" + cols[4] + "\t\t\t" + cols[5]);
                    number++;
                }
            }
            read.close();
        }

        System.out.println("\n1. Add New Corporate Customer");
        System.out.println("2. Edit Existing Corporate Customer");

        System.out.println("\n0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                addNewCorporateCustomer(type);
            } else if (selection.equals("2")) {
                invalidInput = false;
                updateCustomer(type);

            } else if (selection.equals("0")) {
                invalidInput = false;
                module2();
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2 or 0");
            }
        }
    }

    public static void addNewCorporateCustomer(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean blankInput = true;
        File corporatecustomer = new File("customer.txt");
        boolean exist = corporatecustomer.exists();

        while (blankInput) {
            System.out.print("Customer ID: ");
            String id = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Credit Limit: ");
            String creditlimit = sc.nextLine();
            System.out.print("Current Credit: ");
            String currentcredit = sc.nextLine();
            System.out.print("Company Name: ");
            String companyname = sc.nextLine();

            if (id.equals("") || name.equals("") || creditlimit.equals("")) {
                System.out.println("Please do not leave any input field blank.");
            } else {
                blankInput = false;

                Writer output;
                output = new BufferedWriter(new FileWriter("customer.txt", true));
                if (!exist) {
                    output.append(id + "," + name + "," + type + "," + creditlimit + "," + currentcredit + "," + companyname);
                } else {
                    output.append(System.lineSeparator() + id + "," + name + "," + type + "," + creditlimit + "," + currentcredit + "," + companyname);
                }
                output.close();

                System.out.println("\nNew customer added!");
                module2();
            }
        }
    }

    public static void updateCustomer(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer cust = new Customer();
        boolean invalidInput = true;

        System.out.print("Please enter Customer ID: ");
        String custID = sc.nextLine();

        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("customer.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) {
            linesCatalog++;
        }
        readCatalog.close();

        //find the items with the flower as its type
        Scanner read = new Scanner(new File("customer.txt"));
        for (int i = 0; i < linesCatalog; i++) {
            String str = read.nextLine();
            String[] cols = str.split(",");
            if (cols[0].equals(custID) && cols[2].equals(type)) {
                cust.setCustID(custID);
                cust.setCustName(cols[1]);
                cust.setCustType(cols[2]);
                cust.setCreditLimit(cols[3]);
                cust.setCurrentCredit(cols[4]);
                cust.setCompanyName(cols[5]);
            } else {
                Writer output;
                output = new BufferedWriter(new FileWriter(new File("tempcust.txt"), true));
                output.append(str + System.lineSeparator());
                output.close();
            }
        }
        read.close();

        if (cust.getCustID() == null) {
            System.out.println("The product ID you entered is not exist, please try again.\n");
            File tempFile = new File("tempcust.txt");
            tempFile.delete();
            corporatecustomer(type);
        }

        System.out.println("\nCustomer Information");
        System.out.println("===================");
        System.out.println("Customer ID\t\tName\t\t\tCustomer Type\t\tCredit Limit\t\tCurrent Credit\t\tCompany Name");
        System.out.println("============\t\t=========\t\t==============\t\t=============\t\t=============\t\t==================");
        System.out.print(cust.getCustID());
        System.out.print("\t\t\t");
        System.out.print(cust.getCustName());
        System.out.print("\t\t");
        System.out.print(cust.getCustType());
        System.out.print("\t\t\t");
        System.out.print(cust.getCreditLimit());
        System.out.print("\t\t\t");
        System.out.print(cust.getCurrentCredit());
        System.out.print("\t\t\t");
        System.out.print(cust.getCompanyName());

        System.out.println("\n\n===============================");
        System.out.println("What You Want To Update?");
        System.out.println("===============================");
        System.out.println("1. Change Customer Name");
        System.out.println("2. Change Customer Type");
        System.out.println("3. Change Credit Limit");
        System.out.println("4. Change Current Credit");
        System.out.println("5. Change Company Name");
        System.out.println("\n0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                System.out.print("New Customer Name: ");
                cust.setCustName(sc.nextLine());
            } else if (selection.equals("2")) {
                invalidInput = false;
                System.out.print("New Customer Type: ");
                cust.setCustType(sc.nextLine());
            } else if (selection.equals("3")) {
                invalidInput = false;
                System.out.print("New Credit Limit: ");
                cust.setCreditLimit(sc.nextLine());
            } else if (selection.equals("4")) {
                invalidInput = false;
                System.out.print("New Current Credit: ");
                cust.setCurrentCredit(sc.nextLine());
            } else if (selection.equals("5")) {
                invalidInput = false;
                System.out.print("New Company Name: ");
                cust.setCompanyName(sc.nextLine());
            } else if (selection.equals("0")) {
                invalidInput = false;
                corporatecustomer(type);
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2, 3, 4, 5 or 0");
            }
        }
        System.out.println("\nUpdate success!");

        Writer output;
        output = new BufferedWriter(new FileWriter(new File("tempcust.txt"), true));
        output.append(cust.getCustID() + "," + cust.getCustName() + "," + cust.getCustType() + "," + cust.getCreditLimit() + "," + cust.getCurrentCredit() + "," + cust.getCompanyName());
        output.close();

        File originalFile = new File("customer.txt");
        originalFile.delete();

        File tempFile = new File("tempcust.txt");
        tempFile.renameTo(originalFile);

        updateArray();

        corporatecustomer(type);
    }

    public static void viewcorporatecustomerdetail(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;

        File corporatecustomer = new File("customer.txt");
        boolean exist = corporatecustomer.exists();

        //check if catalog file exist
        if (!exist) {
            System.out.println("-------------------------------------");
            System.out.println("|                                   |");
            System.out.println("|    Corporate Customer is empty    |");
            System.out.println("|                                   |");
            System.out.println("-------------------------------------");
        } else {
            //count number of records in catalog.txt
            BufferedReader readCorporateCustomer = new BufferedReader(new FileReader("customer.txt"));
            int linesCorporateCustomer = 0;
            int number = 1;
            while (readCorporateCustomer.readLine() != null) {
                linesCorporateCustomer++;
            }
            readCorporateCustomer.close();

            System.out.println("   Customer ID\t\tName\t\t\tCredit Limit\t\tCurrent Credit\t\tCompany Name");
            System.out.println("   ============\t\t=========\t\t=============\t\t=============\t\t==================");

            //find the items with the flower as its type
            Scanner read = new Scanner(corporatecustomer);
            for (int i = 0; i < linesCorporateCustomer; i++) {
                String str = read.nextLine();
                String[] cols = str.split(",");
                if (cols[2].equals(type)) {
                    System.out.println(number + ". " + cols[0] + "\t\t\t" + cols[1] + "\t\t" + cols[3] + "\t\t\t" + cols[4] + "\t\t\t" + cols[5]);
                    number++;
                }
            }
            read.close();
        }

        System.out.println("\n0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("0")) {
                invalidInput = false;
                module2();
            } else {
                System.out.println("Invalid Input. Please enter only 0");
            }
        }
    }

    public static void module3() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;

        System.out.println("Option");
        System.out.println("============");
        System.out.println("1.Nomral Customer");
        System.out.println("2.C - Customer");
        System.out.println("3.View C - Customer Credit Limit");
        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                orderItem("flower");
            } else if (selection.equals("2")) {
                invalidInput = false;
                corderItem("flower");
            } else if (selection.equals("3")) {
                invalidInput = false;
                viewCustomerLimit("cc");
            } else if (selection.equals("0")) {
                invalidInput = false;
                mainMenu();
            } else {
                System.out.println("Invalid Input. Please enter only 1, or 0");
            }
        }
    }

    public static void viewCustomerLimit(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        Customer cust = new Customer();
        boolean invalidInput = true;

        File corporatecustomer = new File("customer.txt");
        boolean exist = corporatecustomer.exists();

        //check if catalog file exist
        if (!exist) {
            System.out.println("-------------------------------------");
            System.out.println("|                                   |");
            System.out.println("|    Corporate Customer is empty    |");
            System.out.println("|                                   |");
            System.out.println("-------------------------------------");
        } else {
            //count number of records in catalog.txt
            BufferedReader readCorporateCustomer = new BufferedReader(new FileReader("customer.txt"));
            int linesCorporateCustomer = 0;
            int number = 1;
            while (readCorporateCustomer.readLine() != null) {
                linesCorporateCustomer++;
            }
            readCorporateCustomer.close();

            //find the items with the flower as its type
            Scanner read = new Scanner(corporatecustomer);
            for (int i = 0; i < linesCorporateCustomer; i++) {
                String str = read.nextLine();
                String[] cols = str.split(",");
                if (cols[2].equals(type)) {

                    number++;
                }
            }
            read.close();
        }

        System.out.print("Please enter Customer ID: ");
        String custID = sc.nextLine();

        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("customer.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) {
            linesCatalog++;
        }
        readCatalog.close();

        //find the items with the flower as its type
        Scanner read = new Scanner(new File("customer.txt"));
        for (int i = 0; i < linesCatalog; i++) {
            String str = read.nextLine();
            String[] cols = str.split(",");
            if (cols[0].equals(custID) && cols[2].equals(type)) {
                cust.setCustID(custID);
                cust.setCustName(cols[1]);
                cust.setCustType(cols[2]);
                cust.setCreditLimit(cols[3]);
                cust.setCurrentCredit(cols[4]);
                cust.setCompanyName(cols[5]);
            } else {

            }
        }
        read.close();

        System.out.println("\nCustomer Information");
        System.out.println("===================");
        System.out.println("Customer ID\t\tName\t\t\tCustomer Type\t\tCredit Limit\t\tCurrent Credit\t\tCompany Name");
        System.out.println("============\t\t=========\t\t==============\t\t=============\t\t=============\t\t==================");
        System.out.print(cust.getCustID());
        System.out.print("\t\t\t");
        System.out.print(cust.getCustName());
        System.out.print("\t\t");
        System.out.print(cust.getCustType());
        System.out.print("\t\t\t");
        System.out.print(cust.getCreditLimit());
        System.out.print("\t\t\t");
        System.out.print(cust.getCurrentCredit());
        System.out.print("\t\t\t");
        System.out.print(cust.getCompanyName());

        System.out.println("Option");
        System.out.println("============");
        System.out.println("1.Nomral Customer");
        System.out.println("2.C - Customer");
        System.out.println("3.View C - Customer Credit Limit");
        System.out.println("0.Back");
        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                orderItem("flower");
            } else if (selection.equals("2")) {
                invalidInput = false;
                corderItem("flower");
            } else if (selection.equals("3")) {
                invalidInput = false;
                viewCustomerLimit("cc");
            } else if (selection.equals("0")) {
                invalidInput = false;
                mainMenu();
            } else {
                System.out.println("Invalid Input. Please enter only 1, or 0");
            }

        }
    }

    public static void corderItem(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        File catalog = new File("catalog.txt");
        boolean exist = catalog.exists();
        Product p1 = new Product();
        Customer cust = new Customer();
        
        String str1;

        //check if catalog file exist
        if (!exist) {
            System.out.println("--------------------------");
            System.out.println("|                        |");
            System.out.println("|    Catalog is empty    |");
            System.out.println("|                        |");
            System.out.println("--------------------------");
        } else {
            //count number of records in catalog.txt
            BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
            int linesCatalog = 0;
            int number = 1;

            while (readCatalog.readLine() != null) {
                linesCatalog++;
            }
            readCatalog.close();

            System.out.println("   Product ID\t\tProduct Name\t\tPrice(RM)\tQuantity In Stock");
            System.out.println("   ==========\t\t============\t\t=========\t=================");

            //find the items with the flower as its type
            Scanner read = new Scanner(catalog);
            for (int i = 0; i < linesCatalog; i++) {
                String str = read.nextLine();
                String[] cols = str.split(";");
                if (cols[4].equals(type)) {
                    System.out.println(number + ". " + cols[0] + "\t\t" + cols[1] + "\t\t" + cols[2] + "\t\t" + cols[3]);
                    number++;

                }

            }
            read.close();
        }

        System.out.print("Enter Customer ID:");
        cust.setCustID(sc.nextLine());
        String custIDtmp = cust.getCustID();

        System.out.print("Please enter Product ID: ");
        String productID = sc.nextLine();

        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) {
            linesCatalog++;
        }
        readCatalog.close();

        //find the items with the flower as its type
        Scanner read = new Scanner(new File("catalog.txt"));
        for (int i = 0; i < linesCatalog; i++) {
            String str = read.nextLine();
            String[] cols = str.split(";");
            if (cols[0].equals(productID) && cols[4].equals(type)) {
                p1.setProductID(productID);
                p1.setProductName(cols[1]);
                p1.setPrice(cols[2]);
                p1.setQuantity(cols[3]);
                p1.setType(cols[4]);
            } else {

            }
        }
        read.close();
        
        if (p1.getProductID() == null) {
            System.out.println("The product ID you entered is not exist, please try again.\n");
            File tempFile = new File("tempc.txt");
            tempFile.delete();
            module3();
        }
        
        System.out.print("\nEnter quantities: ");
        p1.setQuantity(sc.nextLine());

        System.out.print("\nEnter again coperate customer ID :");
        cust.setCustID(sc.nextLine());

        while (invalidInput) {

            System.out.print("Do you still want to add orders ? Y or N only ");
            str1 = sc.next();
            if (str1.equals("Y")) {
                invalidInput = false;
                corderItem("flower");
            } else if (str1.equals("N")) {
                invalidInput = false;
                System.out.println("Thank for the orders");

            } else {
                System.out.println("Invalid input. Please enter Y or N only");
            }

        }

        Writer output;
        output = new BufferedWriter(new FileWriter(new File("tempc.txt"), true));
        output.append(cust.getCustID() + ";" + p1.getProductID() + ";" + p1.getProductName() + ";" + p1.getQuantity());
        output.close();

        File originalFile = new File("corder.txt");
        originalFile.delete();

        File tempFile = new File("tempc.txt");
        tempFile.renameTo(originalFile);

    }
        private static int tmpTotal=0;
    public static void orderItem(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        File catalog = new File("catalog.txt");
        File order = new File("order.txt");
        boolean exist = catalog.exists();
        Product p1 = new Product();
        Customer c1 = new Customer();
        String str1;
        String orderID = "A00001";
        int orderCount = 0;
        String cust = "null";

        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) {
            linesCatalog++;
        }
        readCatalog.close();
        //display catalog
        System.out.println("   Product ID\t\tProduct Name\t\tPrice(RM)\tQuantity In Stock");
            System.out.println("   ==========\t\t============\t\t=========\t=================");

            //find the items with the flower as its type
            Scanner read = new Scanner(new File("catalog.txt"));
            for (int i = 0; i < linesCatalog; i++) {
                String str = read.nextLine();
                String[] cols = str.split(";");
                if (cols[4].equals(type)) {
                    System.out.println(cols[0] + "\t\t" + cols[1] + "\t\t" + cols[2] + "\t\t" + cols[3]);

                }

            }
            read.close();
            //read productID
        System.out.print("Please enter Product ID: ");
        String productID = sc.nextLine();

        //find the items with the flower as its type
        Scanner read2 = new Scanner(new File("catalog.txt"));
        for (int i = 0; i < linesCatalog; i++) {
            String str = read2.nextLine();
            String[] cols = str.split(";");
            if (cols[0].equals(productID) && cols[4].equals(type)) {
                p1.setProductID(productID);
                p1.setProductName(cols[1]);
                p1.setPrice(cols[2]);
                p1.setQuantity(cols[3]);
                p1.setType(cols[4]);
            } else {

            }
        }
        read.close();

        if (p1.getProductID() == null) {
            System.out.println("The product ID you entered is not exist, please try again.\n");

            module3();
        }
        //get quantity
        System.out.print("\nEnter quantities: ");
        p1.setQuantity(sc.nextLine());
        p1.setTotal(p1.getQuantity()*p1.getPrice());
        tmpTotal += p1.getTotal();
        
        if (exist) {
            //count number of records
            BufferedReader readOrder = new BufferedReader(new FileReader("order.txt"));
            int totalOrder = 0;
            while (readOrder.readLine() != null) {
                totalOrder++;
            }
            readOrder.close();

            //calculate the latest pickupID
            Scanner read1 = new Scanner(order);
            for (int i = 0; i < totalOrder; i++) {
                String str = read1.nextLine();
                String[] cols = str.split(";");
                if (!cols[0].equals(orderID)) {
                    orderCount++;
                }
            }
            read1.close();

            //create pickupID
            if (orderCount < 9) {
                orderID = "A0000" + (orderCount + 1);
            } else if (orderCount < 99) {
                orderID = "A000" + (orderCount + 1);
            } else if (orderCount < 999) {
                orderID = "A00" + (orderCount + 1);
            } else if (orderCount < 9999) {
                orderID = "A0" + (orderCount + 1);
            }else {
            orderID = "A00001";
        }
        } 
        
        
        Writer output;
        output = new BufferedWriter(new FileWriter(new File("order.txt"), true));
        
         if (!exist) {
                    output.append(orderID+";"+p1.getProductID() + ";" + p1.getProductName() + ";" + p1.getQuantity()+ ";" + p1.getTotal());
                } else {
                    output.append(System.lineSeparator()+orderID +";" + p1.getProductID() + ";" + p1.getProductName() + ";" + p1.getQuantity()+";" + p1.getTotal()+ ";");
                }
                output.close();

        while (invalidInput) {

            System.out.print("Do you still want to add item ? Y or N only ");
            str1 = sc.next();
            if (str1.equals("Y")) {
                invalidInput = false;
                addnew(orderID,"flower");
            } else if (str1.equals("N")) {
                invalidInput = false;
                System.out.println("Thank for the orders");
                tambah(tmpTotal,orderID,"N",c1.getCustID());
            } else {
                System.out.println("Invalid input. Please enter Y or N only");
            }

        }
    }
    public static void addnew(String orderID,String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        File catalog = new File("catalog.txt");
        File order = new File("order.txt");
        boolean exist = catalog.exists();
        Product p1 = new Product();
        Customer c1 = new Customer();
        String str1;

        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) {
            linesCatalog++;
        }
        readCatalog.close();
        //display catalog
        System.out.println("   Product ID\t\tProduct Name\t\tPrice(RM)\tQuantity In Stock");
            System.out.println("   ==========\t\t============\t\t=========\t=================");

            //find the items with the flower as its type
            Scanner read = new Scanner(new File("catalog.txt"));
            for (int i = 0; i < linesCatalog; i++) {
                String str = read.nextLine();
                String[] cols = str.split(";");
                if (cols[4].equals(type)) {
                    System.out.println(cols[0] + "\t\t" + cols[1] + "\t\t" + cols[2] + "\t\t" + cols[3]);

                }

            }
            read.close();
            //read productID
        System.out.print("Please enter Product ID: ");
        String productID = sc.nextLine();

        //find the items with the flower as its type
        Scanner read2 = new Scanner(new File("catalog.txt"));
        for (int i = 0; i < linesCatalog; i++) {
            String str = read2.nextLine();
            String[] cols = str.split(";");
            if (cols[0].equals(productID) && cols[4].equals(type)) {
                p1.setProductID(productID);
                p1.setProductName(cols[1]);
                p1.setPrice(cols[2]);
                p1.setQuantity(cols[3]);
                p1.setType(cols[4]);
            } else {

            }
        }
        read.close();

        if (p1.getProductID() == null) {
            System.out.println("The product ID you entered is not exist, please try again.\n");

            module3();
        }
        //get quantity
        System.out.print("\nEnter quantities: ");
        p1.setQuantity(sc.nextLine());
        p1.setTotal(p1.getQuantity()*p1.getPrice());
        tmpTotal += p1.getTotal();
        
        
        Writer output;
        output = new BufferedWriter(new FileWriter(new File("order.txt"), true));
        
         if (!exist) {
                    output.append(orderID+";"+p1.getProductID() + ";" + p1.getProductName() + ";" + p1.getQuantity()+ ";" + p1.getTotal());
                } else {
                    output.append(System.lineSeparator()+orderID +";" + p1.getProductID() + ";" + p1.getProductName() + ";" + p1.getQuantity()+";" + p1.getTotal());
                }
                output.close();

        while (invalidInput) {

            System.out.print("Do you still want to add item ? Y or N only ");
            str1 = sc.next();
            if (str1.equals("Y")) {
                invalidInput = false;
                addnew(orderID,"flower");
            } else if (str1.equals("N")) {
                invalidInput = false;
                System.out.println("Thank for the orders");
                tambah(tmpTotal,orderID,"N",c1.getCustID());
            } else {
                System.out.println("Invalid input. Please enter Y or N only");
            }

        }
    }
    public static void tambah(int tmpTotal, String orderID,String a,String b) throws IOException {
        File order = new File("orderprice.txt");
        boolean exist = order.exists();
        //read total record in orderprice
        Writer output;
        output = new BufferedWriter(new FileWriter(new File("orderprice.txt"), true));
        
        if (!exist) {
                    output.append(orderID+";"+tmpTotal);
                } else {
                    output.append(System.lineSeparator()+orderID+";"+tmpTotal+";"+a+";"+b);
                }
        output.close();
    }

    public static void module4() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        /*LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
        String formattedString = localDate.format(formatter);*/

        System.out.println("Order Pickup/Delivery and Consumer Payment Management");
        System.out.println("============");
        System.out.println("1. Payment");
        System.out.println("2. Pickup List");
        System.out.println("3. Deliver List");
        System.out.println("0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                payment();
            } else if (selection.equals("2")) {
                invalidInput = false;
                pickupmenu();
            } else if (selection.equals("3")) {
                invalidInput = false;
                delivermenu();
            } else if (selection.equals("0")) {
                invalidInput = false;
                mainMenu(); 
            }else {
                System.out.println("Invalid Input. Please enter only 1, 2, 3 and 0");
            }
        }
    }
    public static void payment() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        File order = new File("orderprice.txt");
        File customer = new File("customer.txt");
        int price = 0;
        String name = "";
        int number = 1;
        Customer c1 = new Customer();

        //count number of records in order.txt
        BufferedReader readOrder = new BufferedReader(new FileReader("orderprice.txt"));
        BufferedReader readCustomer = new BufferedReader(new FileReader("customer.txt"));
        //read order total record
        int linesOrder = 0;
        while (readOrder.readLine() != null) {
            linesOrder++;
        }
        readOrder.close();
        //read total customer record
        int linesCust = 0;
        while (readCustomer.readLine() != null) {
            linesCust++;
        }
        readCustomer.close();
        //read customer ID
        System.out.println("Consumer Payment");
        System.out.println("================");
        System.out.print("Please enter customer ID: ");
        String custID = sc.nextLine();
        
        System.out.println("   0rder ID\tTotal Price");
        System.out.println("   ========\t===========");
        //display the order that the customer ID have
        Scanner reada = new Scanner(order);
        for (int i = 0; i < linesOrder; i++) {
            String str = reada.nextLine();
            String[] cols = str.split(";");
            if (cols[3].equals(custID)) {
                System.out.println(number + ". " + cols[0] + "\t" + cols[1]);
                number++;
            }
        }
        //get orderID
        System.out.print("\nPlease enter order ID: ");
        String orderID = sc.nextLine();
        //make payment for the OrderID that input
        Scanner read = new Scanner(customer);
        for (int i = 0; i < linesCust; i++) {
            String str = read.nextLine();
            String[] cols = str.split(",");
            if (cols[0].equals(custID)) {
                c1.setCustID(cols[0]);
                c1.setCustName(cols[1]);
                name = c1.getCustName();
                c1.setCustType(cols[2]);
                c1.setCreditLimit(cols[3]);
                c1.setCurrentCredit(cols[4]);
                c1.setCompanyName(cols[5]);
                
                Scanner read2 = new Scanner(order);
                for (int j = 0; j < linesOrder; j++) {
                    String str1 = read2.nextLine();
                    String[] cols1 = str1.split(";");
                    if (cols1[0].equals(orderID)) {
                        System.out.println("Customer ID: "+cols1[3]);
                        System.out.println("Order ID: "+cols1[0]);
                        System.out.println("Total Price: "+cols1[1]);
                        price = Integer.parseInt(cols1[1]);
                        System.out.println("Current Credit Limit: "+cols[4]);
                        
                    }
                }
                read2.close();
            }else{
                Writer output;
                output = new BufferedWriter(new FileWriter(new File("temp.txt"), true));
                output.append(str + System.lineSeparator());
                output.close();
            }
        }
        if(custID.equals("")){
            System.out.println("\nPlease do not leave the input field blank.\n");
            File tempFile = new File("temp.txt");
            tempFile.delete();
            payment();
        }
        else if(c1.getCustID()==null){
            System.out.println("\nNo record for the customer. Please try again.\n");
            File tempFile = new File("temp.txt");
            tempFile.delete();
            payment();
        }
        else if(c1.getCurrentCredit()<price){
            System.out.println("\nInsufficient credit. Please try again.\n");
            File tempFile = new File("temp.txt");
            tempFile.delete();
            payment();
        } 
        read.close();
        //calculate balance
        int balance = c1.getCurrentCredit()-price;
        System.out.println("Credit Limit Balance: "+ balance);
        
        //update customer credit limit
        Writer output;
        output = new BufferedWriter(new FileWriter(new File("temp.txt"), true));
        output.append(c1.getCustID() + "," + c1.getCustName() + "," + c1.getCustType() + "," + c1.getCreditLimit()+ "," + balance+ "," + c1.getCompanyName());
        output.close();

        File originalFile = new File("customer.txt");
        originalFile.delete();

        File tempFile = new File("temp.txt");
        tempFile.renameTo(originalFile);
        
        //add order to delivery or pickup
        while(invalidInput){
            System.out.print("\nPlease choose a method to receive your order Pickup(P) or Delivery(D): ");
            String selection = sc.next();
            sc.nextLine();
            if (selection.equals("P")||selection.equals("p")) {
                invalidInput = false;
                addpickup(orderID,name);
            } else if (selection.equals("D")||selection.equals("d")) {
                invalidInput = false;
                adddeliver(orderID,name);
            } else {
                System.out.println("Invalid Input. Please enter only P for pickup or D for delivery.");
            }
        }
        
    }
    public static void delivermenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
        String formattedString = localDate.format(formatter);

        System.out.println("Deliver List");
        System.out.println("============");
        System.out.println("1. Check Today's Deliver List");
        System.out.println("0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                deliver(formattedString);
            } else if (selection.equals("0")) {
                invalidInput = false;
                module4();
            }else {
                System.out.println("Invalid Input. Please enter only 1 and 0");
            }
        }
    }
    public static void deliver(String formattedString) throws IOException {
        arrayDeliver.clear();
        Scanner sc = new Scanner(System.in);
        File deliver = new File("deliver.txt");
        System.out.println("Deliver list for " + formattedString);
        int linesDeliver = 0;
        int number = 1;
        
        BufferedReader readDeliver = new BufferedReader(new FileReader("deliver.txt"));
        //count number of records in deliver.txt
        while (readDeliver.readLine() != null) {
            linesDeliver++;
        }
        //compare record with the date and store to arraylist
        Scanner read = new Scanner(deliver);
        for (int i = 0; i < linesDeliver; i++) {
            String str = read.nextLine();
            String[] cols = str.split(",");
            if (cols[0].equals(formattedString)) {
            String date = cols[0];
            String packageID = cols[1];
            String name = cols[2];
            String address = cols[4];
            int code = Integer.valueOf(cols[3]);

            arrayDeliver.add(new Deliver(date, packageID, name, code, address));
            }
        }
        //compare the areacode
        Collections.sort(arrayDeliver, new marksCompare());
        
        //display the record that store in arraylist according to area code
        System.out.println("   Ordere ID\tCustomer Name\t\tArea\tAddress");
        System.out.println("   =========\t=============\t\t====\t=======");
        for (Deliver d : arrayDeliver) 
        {
            System.out.print(number + ". " +d.packageID+"\t"+d.name+"\t\t\t"+d.code+"\t"+d.address+"\n");
            number++;
        }
        if (number == 1) {
            System.out.println("\n-------------------------------");
            System.out.println("|                             |");
            System.out.println("|     No deliver for today    |");
            System.out.println("|                             |");
            System.out.println("-------------------------------");
        }
        readDeliver.close();

        read.close();

        System.out.println("\n");
        
        delivermenu();
    }
    public static void adddeliver(String orderID,String name) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean blankInput = true;
        File deliver = new File("deliver.txt");
        boolean exist = deliver.exists();
        String area = "0";

        while (blankInput) {
           while (blankInput) {  System.out.print("\nOrder ID: " + orderID);
            System.out.print("\nCustomer Name: "+ name);
            System.out.print("Date(DD-MM-YYYY): ");
            String date = sc.nextLine();
            System.out.print("Postcode: ");
            String postcode = sc.nextLine();
            System.out.print("Address: ");
            String address = sc.nextLine();
            
            if (date.equals("") ||address.equals("")||postcode.equals("")) {
                System.out.println("Please do not leave any input field blank.");
            } 
            else if(!isInteger(postcode)){
                System.out.println("Postcode entered is invalid.");
            }
            else if(Integer.parseInt(postcode) < 50000 || Integer.parseInt(postcode) > 60000){
                System.out.println("Area out of delivery.");
            }
            else {
                if (Integer.parseInt(postcode) >= 50000 && Integer.parseInt(postcode) < 51000){
                area = "1";
            } else if(Integer.parseInt(postcode) >= 51000 && Integer.parseInt(postcode) < 52000){
                area = "2";
            }else if(Integer.parseInt(postcode) >= 52000 && Integer.parseInt(postcode) < 53000){
                area = "3";
            }else if(Integer.parseInt(postcode) >= 53000 && Integer.parseInt(postcode) < 54000){
                area = "4";
            }else if(Integer.parseInt(postcode) >= 54000 && Integer.parseInt(postcode) < 55000){
                area = "5";
            }else if(Integer.parseInt(postcode) >= 55000 && Integer.parseInt(postcode) < 56000){
                area = "6";
            }else if(Integer.parseInt(postcode) >= 560000 && Integer.parseInt(postcode) < 57000){
                area = "7";
            }else if(Integer.parseInt(postcode) >= 57000 && Integer.parseInt(postcode) < 58000){
                area = "8";
            }else if(Integer.parseInt(postcode) >= 58000 && Integer.parseInt(postcode) < 59000){
                area = "9";
            }else if(Integer.parseInt(postcode) >= 59000 && Integer.parseInt(postcode) < 60000){
                area = "10";
            }
                blankInput = false;
                Writer output;
                output = new BufferedWriter(new FileWriter("deliver.txt", true));
                if (!exist) {
                    output.append(date + "," + orderID + "," + name + "," + area + "," + address);
                } else {
                    output.append(System.lineSeparator() + date + "," + orderID + "," + name + "," + area + "," + address);
                }
                output.close();

                System.out.println("\nYour have successfully done the payment!\n");
                module4();
            }
        }
    }
    }
    public static void pickupmenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
        String formattedString = localDate.format(formatter);

        System.out.println("Pickup List");
        System.out.println("============");
        System.out.println("1. Check Today's Pickup List");
        System.out.println("2. Update timestamp");
        System.out.println("0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                pickup(formattedString);
            }else if (selection.equals("2")) {
                invalidInput = false;
                timestamp(formattedString);
            } else if (selection.equals("0")) {
                invalidInput = false;
                module4();
            } 
             else if (selection.equals("0")) {
                System.out.println("Please do not leave the input field blank.");
            }else {
                System.out.println("Invalid Input. Please enter only 1, 2 and 0");
            }
        }
    }
    public static void pickup(String formattedString) throws IOException {
        Scanner sc = new Scanner(System.in);
        Queue<String> pickupID = new CircularLinkedQueue();
        Queue<String> time = new CircularLinkedQueue();
        Queue<String> name = new CircularLinkedQueue();
        File deliver = new File("pickup.txt");
        System.out.println("Pickup list for " + formattedString);
        //count number of records in deliver.txt
        BufferedReader readPickup = new BufferedReader(new FileReader("pickup.txt"));
        int linesDeliver = 0;
        int number = 1;
        while (readPickup.readLine() != null) {
            linesDeliver++;
        }
        readPickup.close();

        System.out.println("   Order ID\tTime\t\tCustomer Name");
        System.out.println("   ========\t=====\t\t=============");
        //compare check record for today's date and store in circularlinkedqueue
        Scanner read = new Scanner(deliver);
        for (int i = 0; i < linesDeliver; i++) {
            String str = read.nextLine();
            String[] cols = str.split(",");
            if (cols[0].equals(formattedString)) {
                pickupID.enqueue(cols[1]);
                time.enqueue(cols[2]);
                name.enqueue(cols[3]);
            }
        }
        //dequeue record in circularlinkedqueue
        while(!pickupID.isEmpty()){
            System.out.println(number + ". " + pickupID.dequeue() + "\t" + time.dequeue() + "\t\t" + name.dequeue());
            number++;
        }
        if (number == 1) {
            System.out.println("------------------------------");
            System.out.println("|                            |");
            System.out.println("|     No pickup for today    |");
            System.out.println("|                            |");
            System.out.println("------------------------------");
        }
        read.close();
        System.out.println("\n");
        pickupmenu();
    }
    public static void addpickup(String orderID,String name) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean blankInput = true;
        File pickup = new File("pickup.txt");
        boolean exist = pickup.exists();
        String time = "null";
        
        while (blankInput) {
            System.out.print("\nOrder ID: " + orderID);
            System.out.print("\nCustomer name: "+ name);
            System.out.print("\nDate(DD-MM-YYYY): ");
            String date = sc.nextLine();
            
            //check error and store in pickup.txt
            if (date.equals("")) {
                System.out.println("Please do not leave the input field blank.");
            } else {
                blankInput = false;
                Writer output;
                output = new BufferedWriter(new FileWriter("pickup.txt", true));
                if (!exist) {
                    output.append(date + "," + orderID + "," + time + "," + name);
                } else {
                    output.append(System.lineSeparator() + date + "," + orderID + ","  + time + "," + name);
                }
                output.close();

                System.out.println("\nYour have successfully done the payment!\n");
                module4();
            }
        }
    }
    public static void timestamp(String formattedString) throws IOException {
        Scanner sc = new Scanner(System.in);
        Pickup p1 = new Pickup();
        int number = 1;
        File deliver = new File("pickup.txt");
        //count number of records in pickup.txt
        BufferedReader readPickup = new BufferedReader(new FileReader("pickup.txt"));
        int pickupline = 0;
        while (readPickup.readLine() != null) {
            pickupline++;
        }
        readPickup.close();
        System.out.println("   0rder ID\tTime\tCustomer Name");
        System.out.println("   ========\t=====\t=============");
        //check record with today's date
        Scanner reada = new Scanner(deliver);
        for (int i = 0; i < pickupline; i++) {
            String str = reada.nextLine();
            String[] cols = str.split(",");
            if (cols[0].equals(formattedString)) {
                System.out.println(number + ". " + cols[1] + "\t" + cols[2]+"\t"+cols[3]);
                number++;
            }
        }
        if (number == 1) {
            System.out.println("------------------------------");
            System.out.println("|                            |");
            System.out.println("|     No pickup for today    |");
            System.out.println("|                            |");
            System.out.println("------------------------------");
        }
        reada.close();
        System.out.print("Please enter Order ID: ");
        String pickupID = sc.nextLine();
        //find the pickupID
        Scanner read = new Scanner(new File("pickup.txt"));
        for (int i = 0; i < pickupline; i++) {
            String str = read.nextLine();
            String[] cols = str.split(",");
            if (cols[1].equals(pickupID)) {
                p1.setPickupID(cols[1]);
                p1.setPickupDate(cols[0]);
                p1.setTime(cols[2]);
                p1.setCustomerName(cols[3]);
            } else {
                Writer output;
                output = new BufferedWriter(new FileWriter(new File("temp.txt"), true));
                output.append(str + System.lineSeparator());
                output.close();
            }
        }
        read.close();
        //check blank input
        if (p1.getPickupID() == null) {
            System.out.println("There are no record for the orderID, please try again.\n");
            File tempFile = new File("temp.txt");
            tempFile.delete();
            timestamp(formattedString);
        }
        //display the pickupID info
        System.out.println("\nOrderID Information");
        System.out.println("====================");
        System.out.print("Order ID: ");
        System.out.print(p1.getPickupID());
        System.out.print("\nPickup Date: ");
        System.out.print(p1.getPickupDate());
        System.out.print("\nPickup Time: ");
        System.out.print(p1.getTime());
        System.out.print("\nCustomer Name: ");
        System.out.print(p1.getCustomerName());
        //get time
        System.out.print("\nPlease insert a time(24:00): ");
        p1.setTime(sc.nextLine());
        System.out.println("\nUpdate successful!");
        //overwrite the time of the pickupID in pickup.txt
        Writer output;
        output = new BufferedWriter(new FileWriter(new File("temp.txt"), true));
        output.append(p1.getPickupDate() + "," + p1.getPickupID() + "," + p1.getTime() + "," + p1.getCustomerName());
        output.close();

        File originalFile = new File("pickup.txt");
        originalFile.delete();

        File tempFile = new File("temp.txt");
        tempFile.renameTo(originalFile);

        pickupmenu();
    }
}
