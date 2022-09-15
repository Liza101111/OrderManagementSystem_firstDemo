package menu;

import model.Order;
import persistence.RepositoryOrder;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuOrder {
    RepositoryOrder repositoryOrder = new RepositoryOrder();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Create order");
        System.out.println("2: List all orders");
        System.out.println("3: Search all orders by date");
        System.out.println("4: Count total orders");
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
                    menuCreateOrder(input);
                    break;
                case 2:
                    menuListAllOrders(input);
                    break;
                case 3:
                    menuSearchOrdersByDate(input);
                    break;
                case 4:
                    menuCountOrders(input);
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
    private void menuCreateOrder(Scanner input){

        System.out.println("Type the order date: ");
        String orderDate = input.next();

        Order order = new Order();
        order.setOrderDate(orderDate);

        repositoryOrder.createOrder(order);
        System.out.println("New order created!");
        System.out.println(order.toString());
    }

    private void menuListAllOrders(Scanner input){
        List<Order> orderList = repositoryOrder.listAllOrders();

        if(orderList.size() > 0){
            System.out.println("\nList of orders:");
            for(Order order : orderList){
                System.out.println(order.toString());
            }
        }else{
            System.out.println("\nNo orders registered!\n");
            menuOptions(input);
        }
    }

    private void menuSearchOrdersByDate(Scanner input){

            System.out.println("Type the date :");
            String date = input.next();
            List<Order> orderList = repositoryOrder.searchOrdersByDate(date);
            if(orderList.size() > 0){
                System.out.println("\nList of orders registered in this day:");
                for(Order order : orderList){
                    System.out.println(order.toString());
                }
            }else{
                System.out.println("\nNo orders registered in this day!\n");
                menuOptions(input);
            }
    }
    private void menuCountOrders(Scanner input){
        System.out.println("\nTotal of orders is:");
        Long count = repositoryOrder.countOrders();
        System.out.println(count);

    }


}
