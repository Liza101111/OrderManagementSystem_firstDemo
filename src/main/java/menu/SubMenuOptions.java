package menu;

import java.util.Scanner;

public class SubMenuOptions {
    boolean exit = false;
    private MenuOrder menuOrder;
    private MenuCustomer menuCustomer;
    private MenuOrderLine menuOrderLine;
    private MenuProduct menuProduct;

    public SubMenuOptions() {
        this.menuOrder = new MenuOrder();
        this.menuCustomer = new MenuCustomer();
        this.menuOrderLine = new MenuOrderLine();
        this.menuProduct = new MenuProduct();
    }

    private int menuOptions(Scanner input) {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Main menu ");
        System.out.println("-------------------------------------------------------");
        System.out.println();
        System.out.println("1: Sub Menu - Order");
        System.out.println("2: Sub Menu - Customer");
        System.out.println("3: Sub Menu - OrderLine");
        System.out.println("4: Sub Menu - Product");
        System.out.println("100 - Quit");
        System.out.println("***************************************************");

        System.out.println("Type the sub menu option: ");
        return input.nextInt();
    }

    public void menuChoice(Scanner input) {
        while(!exit) {
            int userChoice = menuOptions(input);
            switch (userChoice) {
                case 1:
                    this.menuOrder.menuChoice(input);
                    break;
                case 2:
                    this.menuCustomer.menuChoice(input);
                    break;
                case 3:
                    this.menuOrderLine.menuChoice(input);
                    break;
                case 4:
                    this.menuProduct.menuChoice(input);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 100:
                    exit = true;
                    System.out.println("System closed");
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuChoice(input);
            }
        }
    }

}
