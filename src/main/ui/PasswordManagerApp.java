package ui;

import exceptions.CollectionIndexOutOfBoundsException;
import exceptions.NullAccountException;
import model.Account;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

//Password Manager Application
public class PasswordManagerApp {
    private static final String JSON_STORE = "./data/data.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    protected User accounts;

    /*
     * EFFECTS: Starts the Password Manager App by initiating the login process
     */
    public PasswordManagerApp() {
        runLoginProcess();
    }

    /*
     * EFFECTS: Checks for correct login info and starts the password manager if info is correct
     * TODO: Remove hard-coded password
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
            try {
                displayAllAccounts();
            } catch (CollectionIndexOutOfBoundsException e) {
                System.out.println("ERROR: INDEX REFERENCED OUT OF BOUNDS");
            }
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
        accounts = new User();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
    private void displayAllAccounts() throws CollectionIndexOutOfBoundsException {
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
        command = command.toLowerCase();

        // v for view/edit, a for add account, d for remove account
        switch (command) {
            case "v":
                runViewAccountProcess();
                break;
            case "a":
                runAddAccountProcess();
                break;
            case "d":
                runRemoveAccountProcess();
                break;
            default:
                System.out.println("Command not valid. Please try again.");
        }
        pressEnterToContinue();
    }

    /*
     * MODIFIES: this, CollectionOfAccounts, Account
     * EFFECTS: Processes user command and calls corresponding method
     */
    private void runViewAccountProcess() {
        try {
            Account accountToManage;
            accountToManage = getAccountFromAccounts();
            viewSpecificAccountInformation(accountToManage);
            promptUserToEnterEditingMode(accountToManage);
        } catch (CollectionIndexOutOfBoundsException e) {
            System.out.println("Invalid account selection. Please choose a valid account number.");
        } catch (NullAccountException e) {
            System.out.println("ERROR: Account does not exist.");
        }
    }

    /*
     * MODIFIES: this, CollectionOfAccounts, Account
     * EFFECTS: Processes user command and calls corresponding method
     */
    private void runAddAccountProcess() {
        Account accountToManage;
        accountToManage = getNewAccountInfo();
        addAccount(accountToManage);
    }

    /*
     * MODIFIES: this, CollectionOfAccounts, Account
     * EFFECTS: Processes user command and calls corresponding method
     */
    private void runRemoveAccountProcess() {
        Account accountToManage;
        try {
            accountToManage = getAccountFromAccounts();
            removeAccount(accountToManage);
        } catch (CollectionIndexOutOfBoundsException e) {
            System.out.println("Invalid account selection. Please choose a valid account number.");
        } catch (NullAccountException e) {
            System.out.println("ERROR: Account does not exist.");
        }
    }

    /*
     * EFFECTS: Prompts the user for the number of the desired account and returns the account at that position
     */
    private Account getAccountFromAccounts() throws CollectionIndexOutOfBoundsException {
        System.out.println("\nWhich account number?");
        String userInput = getUserInputString();
        int indexInAccounts = Integer.parseInt(userInput) - 1;  //Converts user input into an integer
        return accounts.get(indexInAccounts);
    }

    /*
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
     * MODIFIES: this, CollectionOfAccounts, Account
     * EFFECTS: Asks user and enters editing mode if yes answer is given
     */
    private void promptUserToEnterEditingMode(Account account) throws NullAccountException {
        if (account == null) {
            throw new NullAccountException();
        }

        //Ask user if they would like to edit the account
        System.out.println("\nWould you like to edit this account? (y/n)");
        String userInput = getUserInputString();

        //Enter editing mode if yes
        if (userInput.equals("y") || userInput.equals("Y")) {
            editAccount(account);
        }
    }


    /*
     * MODIFIES: this, CollectionOfAccounts
     * EFFECTS: Adds an account to the manager
     */
    private void addAccount(Account account) {
        accounts.add(account);
        System.out.println("\nAccount successfully added!");
    }

    /*
     * EFFECTS: Asks user for new account info, instantiates new account, and returns it
     */
    private Account getNewAccountInfo() {
        char[] name;
        while (true) {
            System.out.println("\nWhat would you like to name the new account?");
            name = getUserInput();

            //Check for blank name and break out of loop if the name is not empty
            if (name.length == 0) {
                System.out.println("Sorry, name cannot be left blank. Please try again.");
            } else {
                break;
            }
        }

        System.out.println("What is the username of the new account?");
        char[] username = getUserInput();

        System.out.println("What is the password of the new account?");
        char[] password = getUserInput();

        return new Account(name, username, password);
    }

    /*
     * MODIFIES: this, CollectionOfAccounts
     * EFFECTS: Removes an account from the password manager
     */
    private void removeAccount(Account account) throws NullAccountException {
        accounts.remove(account);
        System.out.println("\nAccount successfully removed!");
    }

    /*
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
     * MODIFIES: this, CollectionOfAccounts, Account
     * EFFECTS: Changes name for a specified account
     */
    private void changeName(Account account) {
        //Get user input and check that it isn't blank
        char[] userInput;
        while (true) {
            System.out.print("\nWhat would you like to change the name to?\nCurrently: ");
            printArray(account.getName());
            System.out.println();
            userInput = getUserInput();

            //Check for blank name
            if (userInput.length == 0) {
                System.out.println("Sorry, name cannot be left blank. Please try again.");
            } else {
                break;
            }
        }

        //Change name
        account.setName(userInput);
        System.out.println("Name successfully changed!");
    }

    /*
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

    /*
     * EFFECTS: Saves the information stored in the manager to file
     */
    private void saveManager() {
        try {
            jsonWriter.open();
            jsonWriter.write(accounts);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Load the information from file into the manager
     */
    private void loadManager() {
        try {
            accounts = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to load from file path: " + JSON_STORE);
        }
    }

}
