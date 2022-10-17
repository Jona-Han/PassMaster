package ui;

import model.Account;
import model.CollectionOfAccounts;

import java.util.*;

//Password Manager Application
public class PasswordManagerApp {
    protected CollectionOfAccounts accounts;

    /*
     * EFFECTS: Starts the Password Manager App by initiating the login process
     */
    public PasswordManagerApp() {
        runLoginProcess();
    }

    /*
     * EFFECTS: Checks for correct login info and starts the password manager if info is correct
     * TODO: Remove hard-coded password, accept username and password, check between multiple accounts
     */
    private void runLoginProcess() {
        String password = "password"; //Won't be hard-coded in the future
        boolean passwordCorrect = false;

        while (!passwordCorrect) {
            System.out.println("Please enter your password: ");
            String input = getUserInputString();

            //If password is correct, start manager
            if (Objects.equals(input, password)) {
                passwordCorrect = true;
                runManagerProcess();
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input
     */
    private void runManagerProcess() {
        init();

        boolean running = true;
        String userInput;

        //Main loop
        while (running) {
            displayAllAccounts();
            displayCommandMenu();
            userInput = getUserInputString();

            if (userInput.equals("q") || userInput.equals("Q")) {
                running = false;
            } else {
                processCommand(userInput);
            }
        }
        System.out.println("\nGood bye!");
    }

    /*
     * EFFECTS: Initializes empty collection of accounts
     */
    private void init() {
        accounts = new CollectionOfAccounts();
    }


    /*
     * EFFECTS: Displays menu of options to user
     */
    private void displayCommandMenu() {
        System.out.println("\nMAIN MENU:\nv: View/Edit Account Information\na: Add Account\n"
                + "d: Delete Account\nq: Quit");
    }

    /*
     * EFFECTS: Displays a list of all stored accounts to user
     */
    private void displayAllAccounts() {
        System.out.println("\n_____LIST OF ACCOUNTS_____");

        //SOURCE: https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html
        System.out.format("%-10s %-1s", "Number:", "Account Name:");

        //Print out all the names of accounts
        for (int index = 0; index < accounts.size(); index++) {
            System.out.format("%n %-10s", index + 1);
            printArray(accounts.get(index).getName());
        }
        System.out.println();
    }

    /*
     * MODIFIES: this, CollectionOfAccounts, Account
     * EFFECTS: Processes user command and calls corresponding method
     */
    private void processCommand(String command) {
        Account accountToManage;
        command = command.toLowerCase();

        // v for view/edit, a for add account, d for remove account
        switch (command) {
            case "v":
                accountToManage = getAccountFromAccounts();
                viewSpecificAccountInformation(accountToManage);
                promptUserToEnterEditingMode(accountToManage);
                break;
            case "a":
                accountToManage = getNewAccountInfo();
                addAccount(accountToManage);
                break;
            case "d":
                accountToManage = getAccountFromAccounts();
                removeAccount(accountToManage);
                break;
            default:
                System.out.println("Command not valid. Please try again.");
        }
        pressEnterToContinue();
    }

    /*
     * REQUIRES: accounts.size() > 0, 0 <= userInput/indexInAccounts < accounts.size()
     * EFFECTS: Prompts the user for the number of the desired account and returns the account at that position
     */
    private Account getAccountFromAccounts() {
        System.out.println("\nWhich account number?");
        String userInput = getUserInputString();
        int indexInAccounts = Integer.parseInt(userInput) - 1;  //Converts user input into an integer
        return accounts.get(indexInAccounts);
    }

    /*
     * REQUIRES: accounts.size() > 0
     * EFFECTS: Displays information about a specific account stored in the manager
     */
    private void viewSpecificAccountInformation(Account account) {
        //Print account information
        System.out.println();
        printArray(account.getName());
        System.out.print("\nUsername: ");
        printArray(account.getUsername());
        System.out.print("\nPassword: ");
        printArray(account.getPassword());
        System.out.println();
    }

    /*
     * REQUIRES: account must be non-null
     * MODIFIES: this, CollectionOfAccounts, Account
     * EFFECTS: Asks user and enters editing mode if yes answer is given
     */
    private void promptUserToEnterEditingMode(Account account) {
        //Ask user if they would like to edit the account
        System.out.println("\nWould you like to edit this account? (y/n)");
        String userInput = getUserInputString();

        //Enter editing mode if yes
        if (userInput.equals("y") || userInput.equals("Y")) {
            editAccount(account);
        }
    }


    /*
     * REQUIRES: account must be non-null
     * MODIFIES: this, CollectionOfAccounts
     * EFFECTS: Adds an account to the manager
     */
    private void addAccount(Account account) {
        accounts.add(account);
        System.out.println("\nAccount successfully added!");
    }

    /*
     * REQUIRES: name must have non-zero length
     * EFFECTS: Asks user for new account info, instantiates new account, and returns it
     */
    private Account getNewAccountInfo() {
        System.out.println("\nWhat would you like to name the new account?");
        char[] name = getUserInput();

        System.out.println("What is the username of the new account?");
        char[] username = getUserInput();

        System.out.println("What is the password of the new account?");
        char[] password = getUserInput();

        return new Account(name, username, password);
    }

    /*
     * REQUIRES: account is non-null and must be in accounts
     * MODIFIES: this, CollectionOfAccounts
     * EFFECTS: Removes an account from the password manager
     */
    private void removeAccount(Account account) {
        accounts.remove(account);
        System.out.println("\nAccount successfully removed!");
    }

    /*
     * REQUIRES: account is non-null and must be in accounts
     * MODIFIES: this, CollectionOfAccounts, Account
     * EFFECTS: Asks user what they would like to edit and calls appropriate method
     */
    private void editAccount(Account account) {
        boolean editing = true;
        //Continue editing until user exits editing mode
        while (editing) {
            //Prompt user and get input
            viewSpecificAccountInformation(account);
            System.out.println("\nPlease enter the field you would like to change:\na: Name\t\tb: Username"
                    + "\t\tc: Password\t\td: Exit editing mode");
            String userInput = getUserInputString();
            userInput = userInput.toLowerCase();

            //Change name if "a", change username if "b", change password if "c", exit editing mode if anything else
            switch (userInput) {
                case "a":
                    changeName(account);
                    break;
                case "b":
                    changeUsername(account);
                    break;
                case "c":
                    changePassword(account);
                    break;
                default:
                    editing = false;
            }
        }
    }

    /*
     * REQUIRES: account must be non-null and must be in accounts
     * MODIFIES: this, CollectionOfAccounts Account
     * EFFECTS: Changes name for a specified account
     */
    private void changeName(Account account) {
        //Print out prompt and get user input
        System.out.print("\nWhat would you like to change the name to?\nCurrently: ");
        printArray(account.getName());
        System.out.println();
        char[] userInput = getUserInput();

        //Change name
        account.setName(userInput);
        System.out.println("Name successfully changed!");
    }

    /*
     * REQUIRES: account must be non-null and must be in accounts
     * MODIFIES: this, account
     * EFFECTS: Changes username for a specified account
     */
    private void changeUsername(Account account) {
        //Print out prompt and get user input
        System.out.print("\nWhat would you like to change the username to?\nCurrently: ");
        printArray(account.getUsername());
        System.out.println();
        char[] userInput = getUserInput();

        //Change username
        account.setUsername(userInput);
        System.out.println("Username successfully changed!");
    }

    /*
     * REQUIRES: account must be non-null and must be in the list of accounts
     * MODIFIES: this, account
     * EFFECTS: Changes password for a specified account
     */
    private void changePassword(Account account) {
        //Print out prompt and get user input
        System.out.print("\nWhat would you like to change the password to?\nCurrently: ");
        printArray(account.getPassword());
        System.out.println();
        char[] userInput = getUserInput();

        //Change password
        account.setPassword(userInput);
        System.out.println("Password successfully changed!");
    }

    /*
     * EFFECTS: Gets user input from the keyboard and returns it as a char array
     * Primarily for securely collecting username and passwords
     */
    private char[] getUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().toCharArray();
    }

    /*
     * EFFECTS: Gets user input from the keyboard and returns it as a string
     */
    private String getUserInputString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /*
     * EFFECTS: Prompt user to press the Enter key to continue the application
     */
    private void pressEnterToContinue() {
        System.out.println("Press the Enter key to continue...");
        getUserInput();
    }

    /*
     * EFFECTS: Prints the contents of an array
     */
    private void printArray(char[] array) {
        for (char ch : array) {
            System.out.print(ch);
        }
    }
}
