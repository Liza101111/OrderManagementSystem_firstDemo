package menu;

import model.Customer;
import model.Product;
import persistence.RepositoryProduct;

import java.util.List;
import java.util.Scanner;

public class MenuProduct {
    RepositoryProduct repositoryProduct = new RepositoryProduct();


    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Create product");
        System.out.println("2: List all products");
        System.out.println("3: ");
        System.out.println("4: ");
        System.out.println("5: ");
        System.out.println("100 - Return to Main Menu");
        System.out.println("\n/***************************************************/");
        return input.nextInt();
    }


    protected void menuChoice(Scanner input) {

        int userChoice;
        do {
            userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    menuCreateProduct(input);
                    break;
                case 2:
                    menuListAllProduct(input);
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 100:
                    MainMenu.getMainMenu();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOptions(input);
                    break;
            }
        } while (userChoice != 100);
    }

    private void menuCreateProduct(Scanner input) {
        System.out.println("Type the product name: ");
        String name = input.next();
        System.out.println("Type the product skuCode: ");
        String skuCode = input.next();
        System.out.println("Type the product unit price: ");
        Float unitPrice = input.nextFloat();

        Product product = new Product();
        product.setName(name);
        product.setSkuCode(skuCode);
        product.setUnitPrice(unitPrice);

        repositoryProduct.createProduct(product);
        System.out.println("New product created");
        System.out.println(product.toString());

    }

    private void menuListAllProduct(Scanner input){
        List<Product> productList = repositoryProduct.listAllProducts();
        if(productList.size() > 0){
            System.out.println("\nList of products:");
            for(Product product : productList) {
                System.out.println(product.toString());
            }
        } else{
            System.out.println("\nNo products registered!\n");
            menuOptions(input);
        }


    }
}
