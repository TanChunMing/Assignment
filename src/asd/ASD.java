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
import java.util.Comparator;

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

    public static void updateArray() throws IOException {
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

        if (exist) {
            //count number of records in catalog.txt
            BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
            int linesCatalog = 0;
            while (readCatalog.readLine() != null) {
                linesCatalog++;
            }
            readCatalog.close();

            //add item to different arrayList based on their type
            Scanner read = new Scanner(catalog);
            for (int i = 0; i < linesCatalog; i++) {
                String str = read.nextLine();
                String[] cols = str.split(";");
                if (cols[4].equals("flower")) {
                    arrayFlower.add(cols[1]);
                    arrayFlowerPrice.add(cols[2]);
                    arrayFlowerQuantity.add(cols[3]);
                }
                if (cols[4].equals("bouquet")) {
                    arrayBouquet.add(cols[1]);
                    arrayBouquetPrice.add(cols[2]);
                    arrayBouquetQuantity.add(cols[3]);
                }
                if (cols[4].equals("arrangement")) {
                    arrayArrangement.add(cols[1]);
                    arrayArrangementPrice.add(cols[2]);
                    arrayArrangementQuantity.add(cols[3]);
                }
            }
            read.close();
        }
    }

    public static void mainMenu() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;

        System.out.println("Fiore Flowershop");
        System.out.println("================");
        System.out.println("1. Catalog Maintenance");
        System.out.println("2. Customer Maintenance and Invoice Payment");
        System.out.println("3. Catalog Order");
        System.out.println("4. Order Pickup/Delivery and Consumer Payment Management");
        System.out.println("\n0. Exit");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                module1();
            } else if (selection.equals("2")) {
                invalidInput = false;
                module2();
            } else if (selection.equals("3")) {
                invalidInput = false;
                module3();
            } else if (selection.equals("4")) {
                invalidInput = false;
                module4();
            } else if (selection.equals("0")) {
                invalidInput = false;
                System.exit(0);
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2, 3, 4 or 0");
            }
        }
    }

    public static void module1() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;

        System.out.println("Product Type");
        System.out.println("============");
        System.out.println("1. Fresh Flower");
        System.out.println("2. Bouquet");
        System.out.println("3. Floral Arrangement");
        System.out.println("\n0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                catalog("flower");
            } else if (selection.equals("2")) {
                invalidInput = false;
                catalog("bouquet");
            } else if (selection.equals("3")) {
                invalidInput = false;
                catalog("arrangement");
            } else if (selection.equals("0")) {
                invalidInput = false;
                mainMenu();
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2, 3 or 0");
            }
        }
    }

    public static void catalog(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;

        File catalog = new File("catalog.txt");
        boolean exist = catalog.exists();

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

        System.out.println("\n1. Add new product");
        System.out.println("2. Edit existing product");
        System.out.println("3. Delete existing product");
        System.out.println("\n0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                addNewProduct(type);
            } else if (selection.equals("2")) {
                invalidInput = false;
                updateProduct(type);
            } else if (selection.equals("3")) {
                invalidInput = false;
                deleteProduct(type);
            } else if (selection.equals("0")) {
                invalidInput = false;
                module1();
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2, 3 or 0");
            }
        }
    }

    public static void addNewProduct(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean blankInput = true;
        File catalog = new File("catalog.txt");
        boolean exist = catalog.exists();
        String productID;
        int productCount = 0;

        //determine first letter of productID
        if (type.equals("flower")) {
            productID = "F";
        } else if (type.equals("bouquet")) {
            productID = "B";
        } else {
            productID = "A";
        }

        if (exist) {
            //count number of records in catalog.txt
            BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
            int linesCatalog = 0;
            while (readCatalog.readLine() != null) {
                linesCatalog++;
            }
            readCatalog.close();

            //calculate linesCatalog with particular product type
            Scanner read = new Scanner(catalog);
            for (int i = 0; i < linesCatalog; i++) {
                String str = read.nextLine();
                String[] cols = str.split(";");
                if (cols[4].equals(type)) {
                    productCount++;
                }
            }
            read.close();

            //create productID
            if (productCount < 9) {
                productID = productID + "0000" + (productCount + 1);
            } else if (productCount < 99) {
                productID = productID + "000" + (productCount + 1);
            } else if (productCount < 999) {
                productID = productID + "00" + (productCount + 1);
            } else if (productCount < 9999) {
                productID = productID + "0" + (productCount + 1);
            } else {
                productID = productID + (productCount + 1);
            }
            productID = checkID(productID, productCount);
        } else {
            productID = productID + "00001";
        }

        while (blankInput) {
            System.out.print("Product Name: ");
            String name = sc.nextLine();
            System.out.print("Price: ");
            String price = sc.nextLine();
            System.out.print("Quantity in stock: ");
            String quantity = sc.nextLine();

            if (name.equals("") || price.equals("")) {
                System.out.println("Please do not leave any input field blank.");
            } else if (!isInteger(price)) {
                System.out.println("Price entered is not integer.");
            } else if (!isInteger(quantity)) {
                System.out.println("Quantity entered is not integer.");
            } else {
                blankInput = false;

                Writer output;
                output = new BufferedWriter(new FileWriter("catalog.txt", true));
                if (!exist) {
                    output.append(productID + ";" + name + ";" + price + ";" + quantity + ";" + type);
                } else {
                    output.append(System.lineSeparator() + productID + ";" + name + ";" + price + ";" + quantity + ";" + type);
                }
                output.close();

                System.out.println("\nNew product added!");
                module1();
            }
        }
    }

    public static void updateProduct(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        Product p1 = new Product();
        boolean invalidInput = true;

        System.out.print("Please enter Product ID: ");
        String productID = sc.nextLine();

        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) {
            linesCatalog++;
        }
        readCatalog.close();

        //find the items with the flower as its type and the productID matched
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
                Writer output;
                output = new BufferedWriter(new FileWriter(new File("temp.txt"), true));
                output.append(str + System.lineSeparator());
                output.close();
            }
        }
        read.close();

        if (p1.getProductID() == null) {
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

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                System.out.print("New Product Name: ");
                p1.setProductName(sc.nextLine());
            } else if (selection.equals("2")) {
                invalidInput = false;
                System.out.print("New Price: ");
                p1.setPrice(sc.nextLine());
            } else if (selection.equals("3")) {
                invalidInput = false;
                System.out.print("New Quantity in Stock: ");
                p1.setQuantity(sc.nextLine());
            } else if (selection.equals("0")) {
                invalidInput = false;
                catalog(type);
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2, 3 or 0");
            }
        }
        System.out.println("\nUpdate success!");

        Writer output;
        output = new BufferedWriter(new FileWriter(new File("temp.txt"), true));
        output.append(p1.getProductID() + ";" + p1.getProductName() + ";" + p1.getPrice() + ";" + p1.getQuantity() + ";" + p1.getType());
        output.close();

        File originalFile = new File("catalog.txt");
        originalFile.delete();

        File tempFile = new File("temp.txt");
        tempFile.renameTo(originalFile);

        updateArray();

        catalog(type);
    }

    public static void deleteProduct(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        Product p1 = new Product();
        boolean invalidInput = true;

        System.out.print("Please enter Product ID: ");
        String productID = sc.nextLine();

        //count number of records in catalog.txt
        BufferedReader readCatalog = new BufferedReader(new FileReader("catalog.txt"));
        int linesCatalog = 0;
        while (readCatalog.readLine() != null) {
            linesCatalog++;
        }
        readCatalog.close();

        //find the items with the flower as its type but the productID not matched
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
                Writer output;
                output = new BufferedWriter(new FileWriter(new File("temp.txt"), true));
                if (read.hasNextLine()) {
                    output.append(str + System.lineSeparator());
                    output.close();
                } else {
                    output.append(str);
                    output.close();
                }
            }
        }
        read.close();

        if (p1.getProductID() == null) {
            System.out.println("The product ID you entered is not exist, please try again.\n");
            File tempFile = new File("temp.txt");
            tempFile.delete();
            catalog(type);
        }

        while (invalidInput) {
            System.out.println("Do you really want to delete " + p1.getProductID() + ", " + p1.getProductName() + "?(Please enter Y or N)");
            String selection = sc.nextLine();

            if (selection.equals("Y") || selection.equals("y")) {
                invalidInput = false;
                File originalFile = new File("catalog.txt");
                originalFile.delete();

                File tempFile = new File("temp.txt");
                tempFile.renameTo(originalFile);

                System.out.println(p1.getProductID() + ", " + p1.getProductName() + " is deleted.");
            } else if (selection.equals("N") || selection.equals("n")) {
                invalidInput = false;
                File tempFile = new File("temp.txt");
                tempFile.delete();
            } else {
                System.out.println("Invalid input. Please enter only Y or N.");
            }
        }
        updateArray();

        catalog(type);
    }

    private static String checkID(String productID, int productCount) throws IOException {
        String ID = productID;

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
            if (cols[0].equals(ID)) {
                productCount++;
                if (productCount < 9) {
                    ID = productID.charAt(0) + "0000" + (productCount + 1);
                } else if (productCount < 99) {
                    ID = productID.charAt(0) + "000" + (productCount + 1);
                } else if (productCount < 999) {
                    ID = productID.charAt(0) + "00" + (productCount + 1);
                } else if (productCount < 9999) {
                    ID = productID.charAt(0) + "0" + (productCount + 1);
                }
                checkID(ID, productCount);
            }
        }
        read.close();

        return ID;
    }

    private static boolean isInteger(String str) {
        boolean result = false;

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                result = false;
                break;
            } else {
                result = true;
            }
        }

        return result;
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

    public static void orderItem(String type) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        File catalog = new File("catalog.txt");
        boolean exist = catalog.exists();
        Product p1 = new Product();
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
            File tempFile = new File("temp1.txt");
            tempFile.delete();
            module3();
        }

        System.out.print("\nEnter quantities: ");
        p1.setQuantity(sc.nextLine());

        System.out.print("Did u want to paid by cash when you pick up deliver? Yes or No :");
        p1.setPayment(sc.nextLine());

        while (invalidInput) {

            System.out.print("Do you still want to add orders ? Y or N only ");
            str1 = sc.next();
            if (str1.equals("Y")) {
                invalidInput = false;
                orderItem("flower");
            } else if (str1.equals("N")) {
                invalidInput = false;
                System.out.println("Thank for the orders");
            } else {
                System.out.println("Invalid input. Please enter Y or N only");
            }

        }

        Writer output;
        output = new BufferedWriter(new FileWriter(new File("temp1.txt"), true));
        output.append(p1.getProductID() + ";" + p1.getProductName() + ";" + p1.getQuantity() + ";" + p1.getPayment());
        output.close();

        File originalFile = new File("order.txt");
        originalFile.delete();

        File tempFile = new File("temp1.txt");
        tempFile.renameTo(originalFile);

    }

    public static void module4() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean invalidInput = true;
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
        String formattedString = localDate.format(formatter);

        System.out.println("Order Pickup/Delivery and Consumer Payment Management");
        System.out.println("============");
        System.out.println("1. Pickup List");
        System.out.println("2. Deliver List");
        System.out.println("0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                pickupmenu();
            } else if (selection.equals("2")) {
                invalidInput = false;
                delivermenu();
            } else if (selection.equals("0")) {
                invalidInput = false;
                mainMenu();
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2 and 0");
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
        System.out.println("2. Add deliver list");
        System.out.println("3. bbb");
        System.out.println("0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                deliver(formattedString);
            } else if (selection.equals("2")) {
                invalidInput = false;
                adddeliver();
            } else if (selection.equals("3")) {
                invalidInput = false;

            } else if (selection.equals("0")) {
                invalidInput = false;
                module4();
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2, 3 and 0");
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

        System.out.println("   PackageID      Customer Name         Area        Address");
        System.out.println("   =========      =============         ====        =======");
        for (Deliver d : arrayDeliver) 
        {
            System.out.print(number + ". " +d.packageID+"         "+d.name+"                 "+d.code+"           "+d.address+"\n");
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
    public static void adddeliver() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean blankInput = true;
        File deliver = new File("deliver.txt");
        boolean exist = deliver.exists();
        String packageID = "D00001";
        int deliverCount = 1;

        if (exist) {
            //count number of records
            BufferedReader readDeliver = new BufferedReader(new FileReader("deliver.txt"));
            int totalRecord = 0;
            while (readDeliver.readLine() != null) {
                totalRecord++;
            }
            readDeliver.close();

            //calculate the latest pickupID
            Scanner read = new Scanner(deliver);
            for (int i = 0; i < totalRecord; i++) {
                String str = read.nextLine();
                String[] cols = str.split(",");
                if (!cols[1].equals(packageID)) {
                    deliverCount++;
                }
            }
            read.close();

            //create packageID
            if (deliverCount < 9) {
                packageID = "D0000" + (deliverCount + 1);
            } else if (deliverCount < 99) {
                packageID = "D000" + (deliverCount + 1);
            } else if (deliverCount < 999) {
                packageID = "D00" + (deliverCount + 1);
            } else if (deliverCount < 9999) {
                packageID = "D0" + (deliverCount + 1);
            }
        } else {
            packageID = "D00001";
        }

        while (blankInput) {
            System.out.print("\nPackageID: " + packageID);
            System.out.print("\nCustomer Name: ");
            String name = sc.nextLine();
            System.out.print("Date(DD-MM-YYYY): ");
            String date = sc.nextLine();
            System.out.print("Area Code: ");
            String area = sc.nextLine();
            System.out.print("Address: ");
            String address = sc.nextLine();

            if (name.equals("") || date.equals("") || area.equals("") || address.equals("")) {
                System.out.println("Please do not leave any input field blank.");
            } else {
                blankInput = false;
                Writer output;
                output = new BufferedWriter(new FileWriter("deliver.txt", true));
                if (!exist) {
                    output.append(date + "," + packageID + "," + name + "," + area + "," + address);
                } else {
                    output.append(System.lineSeparator() + date + "," + packageID + "," + name + "," + area + "," + address);
                }
                output.close();

                System.out.println("\nNew packageID added!\n");
                delivermenu();
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
        System.out.println("2. Add New Pickup");
        System.out.println("3. Update timestamp");
        System.out.println("0. Back");

        while (invalidInput) {
            System.out.print("\nPlease select a choice: ");
            String selection = sc.next();
            sc.nextLine();

            if (selection.equals("1")) {
                invalidInput = false;
                pickup(formattedString);
            } else if (selection.equals("2")) {
                invalidInput = false;
                addpickup();
            } else if (selection.equals("3")) {
                invalidInput = false;
                timestamp(formattedString);

            } else if (selection.equals("0")) {
                invalidInput = false;
                module4();
            } else {
                System.out.println("Invalid Input. Please enter only 1, 2, 3 and 0");
            }
        }
    }

    public static void pickup(String formattedString) throws IOException {
        Scanner sc = new Scanner(System.in);
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

        System.out.println("   PickUpID     Time       Customer Name");
        System.out.println("   ========     =====      =============");

        Scanner read = new Scanner(deliver);
        for (int i = 0; i < linesDeliver; i++) {
            String str = read.nextLine();
            String[] cols = str.split(",");
            if (cols[0].equals(formattedString)) {
                System.out.println(number + ". " + cols[1] + "       " + cols[2]+"      "+cols[3]);
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
        read.close();
        System.out.println("\n");
        pickupmenu();
    }

    public static void addpickup() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean blankInput = true;
        File pickup = new File("pickup.txt");
        boolean exist = pickup.exists();
        String pickupID = "P00001";
        int pickupCount = 0;
        String time = "null";

        if (exist) {
            //count number of records
            BufferedReader readPickup = new BufferedReader(new FileReader("pickup.txt"));
            int totalPickup = 0;
            while (readPickup.readLine() != null) {
                totalPickup++;
            }
            readPickup.close();

            //calculate the latest pickupID
            Scanner read = new Scanner(pickup);
            for (int i = 0; i < totalPickup; i++) {
                String str = read.nextLine();
                String[] cols = str.split(",");
                if (!cols[2].equals(pickupID)) {
                    pickupCount++;
                }
            }
            read.close();

            //create pickupID
            if (pickupCount < 9) {
                pickupID = "P0000" + (pickupCount + 1);
            } else if (pickupCount < 99) {
                pickupID = "P000" + (pickupCount + 1);
            } else if (pickupCount < 999) {
                pickupID = "P00" + (pickupCount + 1);
            } else if (pickupCount < 9999) {
                pickupID = "P0" + (pickupCount + 1);
            }
        } else {
            pickupID = "P00001";
        }

        while (blankInput) {
            System.out.print("\nPickupID: " + pickupID);
            System.out.print("\nDate(DD-MM-YYYY): ");
            String date = sc.nextLine();
            System.out.print("Customer name: ");
            String name = sc.nextLine();

            if (name.equals("") || date.equals("")) {
                System.out.println("Please do not leave any input field blank.");
            } else {
                blankInput = false;
                Writer output;
                output = new BufferedWriter(new FileWriter("pickup.txt", true));
                if (!exist) {
                    output.append(date + "," + pickupID + "," + time + "," + name);
                } else {
                    output.append(System.lineSeparator() + date + "," + pickupID + ","  + time + "," + name);
                }
                output.close();

                System.out.println("\nNew pickupID added!\n");
                pickupmenu();
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
        System.out.println("   PickUpID     Time       Customer Name");
        System.out.println("   ========     =====      =============");

        Scanner reada = new Scanner(deliver);
        for (int i = 0; i < pickupline; i++) {
            String str = reada.nextLine();
            String[] cols = str.split(",");
            if (cols[0].equals(formattedString)) {
                System.out.println(number + ". " + cols[1] + "       " + cols[2]+"      "+cols[3]);
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
        System.out.print("Please enter PickupID: ");
        String pickupID = sc.nextLine();
        //find the customer name
        Scanner read = new Scanner(new File("pickup.txt"));
        for (int i = 0; i < pickupline; i++) {
            String str = read.nextLine();
            String[] cols = str.split(",");
            if (cols[1].equals(pickupID)) {
                p1.setPickupID(pickupID);
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

        if (p1.getPickupID() == null) {
            System.out.println("There are no record for the customer, please try again.\n");
            File tempFile = new File("temp.txt");
            tempFile.delete();
            timestamp(formattedString);
        }

        System.out.println("\nPickupID Information");
        System.out.println("====================");
        System.out.print("Pickup ID: ");
        System.out.print(p1.getPickupID());
        System.out.print("\nPickup Date: ");
        System.out.print(p1.getPickupDate());
        System.out.print("\nPickup Time: ");
        System.out.print(p1.getTime());
        System.out.print("\nCustomer Name: ");
        System.out.print(p1.getCustomerName());

        System.out.print("\nPlease insert a time(24:00): ");
        p1.setTime(sc.nextLine());
        System.out.println("\nUpdate success!");

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
