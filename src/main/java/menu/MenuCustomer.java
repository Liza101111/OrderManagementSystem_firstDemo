package menu;

import model.Customer;
import persistence.RepositoryCustomer;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MenuCustomer {
    RepositoryCustomer repositoryCustomer = new RepositoryCustomer();

    private int menuOptions(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Select the submenu option: ");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Create customer");
        System.out.println("2: List all customer");
        System.out.println("3: Update customer");
        System.out.println("4: Delete customer");
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
                    menuCreateCustomer(input);
                    break;
                case 2:
                    menuListAllCustomers(input);
                    break;
                case 3:
                    menuUpdateCustomer(input);
                    break;
                case 4:
                    menuDeleteCustomer(input);
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
    private void menuCreateCustomer(Scanner input){
        boolean validFirstName = false;
        boolean validLastName = false;
        boolean validPhoneNum = false;
        boolean validEmail = false;
        String firstName = null;
        String lastName = null;
        String phoneNum = null;
        String email = null;

        while (!validFirstName) {
            System.out.println("Type firstName: ");
            firstName = input.next();
            boolean fNameResult = validateFirstName(firstName);
            if(fNameResult) {
                validFirstName = true;
            }
        }

        while (!validLastName) {
            System.out.println("Type lastName: ");
            lastName = input.next();
            boolean lNameResult = validateLastName(lastName);
            if(lNameResult) {
                validLastName = true;
            }
        }

        while (!validPhoneNum){
            System.out.println("Type phoneNum: ");
            phoneNum = input.next();
            boolean phoneNumResult = validatePhoneNum(phoneNum);
            if(phoneNumResult) {
                validPhoneNum = true;
            }
        }

        while (!validEmail) {
            System.out.println("Type email: ");
            email = input.next();
            boolean emailResult = validateEmail(email);
            if(emailResult) {
                validEmail = true;
            }
        }

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNum(phoneNum);
        customer.setEmail(email);

        repositoryCustomer.createCustomer(customer);
        System.out.println("New customer created!");
        System.out.println(customer.toString());

    }
    private boolean validateFirstName(String fName){
        String regexPattern = "^[A-Z](?=.{2,20}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
        return Pattern.compile(regexPattern)
                .matcher(fName)
                .matches();
    }

    private boolean validateLastName(String lName){
        String regexPattern = "^[A-Z](?=.{2,20}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";
        return Pattern.compile(regexPattern)
                .matcher(lName)
                .matches();
    }

    private boolean validatePhoneNum(String phoneNum){
        String regexPattern = "^\\d{8,15}$";
        return Pattern.compile(regexPattern)
                .matcher(phoneNum)
                .matches();
    }

    private boolean validateEmail(String email){
        String regexPattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    private void menuListAllCustomers(Scanner input){
        List<Customer> customerList = repositoryCustomer.listAllCustomers();
        if(customerList.size() > 0){
            System.out.println("\nList of customers:");
            for(Customer customer : customerList) {
                System.out.println(customer.toString());
            }
        } else{
            System.out.println("\nNo customers registered!\n");
            menuOptions(input);
        }

    }

    private void menuUpdateCustomer(Scanner input) {

        System.out.println("Type customer id:");
        int customerId = input.nextInt();
        Customer customer = repositoryCustomer.findCustomerById(customerId);
        System.out.println("Type customer firstName:");
        customer.setFirstName(input.next());
        repositoryCustomer.updateCustomer(customer);
        System.out.println("customer " + customerId + " updated!");
    }

    public void menuDeleteCustomer(Scanner input) {
        try {
            System.out.println("Type customer id:");
            int customerId = input.nextInt();

            Customer customer = new Customer();
            customer.setCustomerID(customerId);

            repositoryCustomer.deleteCustomer(customer);
            System.out.println("customer " + customerId + " deleted!");

        } catch (NoResultException e) {
            System.out.println("\nNo customers registered with inserted Id\n");
            menuOptions(input);
        }
    }



}
