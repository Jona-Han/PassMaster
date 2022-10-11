package ui;

import model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

//Password Manager Application
public class PasswordManagerApp {
    List<Account> accounts;

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
        String userInput = null;

        while (running) {
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
     * EFFECTS: Initializes list of accounts for testing
     */
    private void init() {
        accounts = new ArrayList<>();
    }


    /*
     * EFFECTS: Displays menu of options to user
     */
    private void displayCommandMenu() {
        System.out.println("\nMAIN MENU:\nv: View/Edit Account Information\na: Add Account\n" +
                "d: Delete Account\nq: Quit");
    }

    /*
     * EFFECTS: Displays a list of all stored accounts to user
     */
    private void displayAllAccounts() {
        System.out.println("....PLACEHOLDER LIST OF ACCOUNTS....");
    }

    /*
     * EFFECTS: Gets user input from the keyboard and returns it
     */
    protected String getUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user command and calls corresponding method
     */
    protected void processCommand(String command) {
        Account accountToManage;
        switch (command) {
            case "v":
                accountToManage = getAccountFromAccounts();
                viewSpecificAccountInformation(accountToManage);
                break;
            case "a":
                addAccount();
                break;
            case "d":
                accountToManage = getAccountFromAccounts();
                removeAccount(accountToManage);
                break;
        }
    }

    /*
     * EFFECTS: Prompts the user for the number of the desired account and returns its index in accounts
     */
    protected Account getAccountFromAccounts() {
        System.out.println("Which account number?");
        String userInput = getUserInput();
        return accounts.get(Integer.parseInt(userInput));
    }

    /*
     * EFFECTS: Displays information about a specific account stored in the manager
     */
    protected void viewSpecificAccountInformation(Account account) {
        //stub
    }

    /*
     * REQUIRES: name, username, and password must be longer than zero
     * MODIFIES: this
     * EFFECTS: Adds an account to the password manager
     */
    protected void addAccount() {
        //stub
    }

    /*
     * REQUIRES: 0 < index <= size of the list of accounts
     * MODIFIES: this
     * EFFECTS: Removes an account from the password manager
     */
    protected void removeAccount(Account account) {
        //stub
    }

    /*
     * REQUIRES: 0 < index <= size of the list of accounts
     * MODIFIES: this, account
     * EFFECTS: Edits account information
     */
    protected void editAccount(int indexOfAccount) {
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
}
