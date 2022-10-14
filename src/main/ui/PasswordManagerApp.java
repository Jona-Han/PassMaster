package ui;

import model.Account;

import java.util.*;

//Password Manager Application
public class PasswordManagerApp {
    protected List<Account> accounts;

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
        boolean running = true;

        while (running) {
            System.out.println("Please enter your password: ");
            String input = getUserInput();

            if (Objects.equals(input, password)) {
                running = false;
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

        while (running) {
            System.out.println("\n");
            displayAllAccounts();
            displayCommandMenu();
            userInput = getUserInput();

            if (userInput.equals("q")) {
                running = false;
            } else {
                processCommand(userInput);
            }
        }
        System.out.println("Good bye!");
    }
    /*
     * EFFECTS: Initializes list of accounts and adds 3 accounts for testing
     */
    private void init() {
        accounts = new ArrayList<>();
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
        System.out.println("_____LIST OF ACCOUNTS_____");
        System.out.format("%-10s %-1s", "Number:", "Account Name:");
        for (int index = 0; index < accounts.size(); index++) {
            System.out.format("%n %-10s %-1s", index + 1, accounts.get(index).getName());
        }
        System.out.println("\n");
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user command and calls corresponding method
     */
    private void processCommand(String command) {
        Account accountToManage;
        command = command.toLowerCase();
        switch (command) {
            case "v":
                accountToManage = getAccountFromAccounts();
                viewSpecificAccountInformation(accountToManage);
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
        System.out.println("\n");
        pressEnterToContinue();
    }

    /*
     * EFFECTS: Prompts the user for the number of the desired account and returns its index in accounts
     */
    private Account getAccountFromAccounts() {
        System.out.println("Which account number?");
        String userInput = getUserInput();
        int indexInAccounts = Integer.parseInt(userInput) - 1;
        return accounts.get(indexInAccounts);
    }

    /*
     * EFFECTS: Displays information about a specific account stored in the manager
     */
    private void viewSpecificAccountInformation(Account account) {
        System.out.println("\n" + account.getName() + "\nUsername: " + account.getUsername() + "\nPassword: "
                + account.getPassword());
    }

    /*
     * REQUIRES: account must be non null
     * MODIFIES: this
     * EFFECTS: Adds an account to the manager
     */
    protected void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account successfully added!");
    }

    /*
     * MODIFIES: this
     * EFFECTS: Prompts user for new account info and instantiates new account
     */
    private Account getNewAccountInfo() {
        System.out.println("What would you like to name the new account?");
        String name = getUserInput();

        System.out.println("What is the username of the new account?");
        String username = getUserInput();

        System.out.println("What is the password of the new account?");
        String password = getUserInput();

        return new Account(name, username, password);
    }

    /*
     * REQUIRES: 0 < index <= size of the list of accounts
     * MODIFIES: this
     * EFFECTS: Removes an account from the password manager
     */
    private void removeAccount(Account account) {
        accounts.remove(account);
        System.out.println("Account successfully removed!");
    }

    /*
     * REQUIRES: 0 < index <= size of the list of accounts
     * MODIFIES: this, account
     * EFFECTS: Edits account information
     */
    private void editAccount(Account account) {
        //stub
    }

    /*
     * REQUIRES: must not be null and must be in the list of accounts
     * MODIFIES: this, account
     * EFFECTS: Changes username for a specified account
     */
    protected void changeUsername(Account accountToChange) {
        //stub
    }

    /*
     * REQUIRES: must not be null and must be in the list of accounts
     * MODIFIES: this, account
     * EFFECTS: Changes password for a specified account
     */
    protected void changePassword(Account accountToChange) {
        //stub
    }

    /*
     * EFFECTS: Gets user input from the keyboard and returns it
     */
    private String getUserInput() {
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
}
