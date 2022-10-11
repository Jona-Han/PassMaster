package ui;

import model.Account;

//Password Manager Application
public class PasswordManagerApp {

    /*
     * EFFECTS: Starts the Password Manager App by initiating the login process
     */
    public PasswordManagerApp() {}

    /*
     * EFFECTS: Checks for correct login info and starts the password manager if info is correct
     */
    private void runLoginProcess() {
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input
     */
    private void runManager() {}

    /*
     * EFFECTS: Displays menu of options to user
     */
    private void displayMenu() {}

    /*
     * EFFECTS: Displays a list of all stored accounts to user
     */
    private void displayAllAccounts() {}

    /*
     * EFFECTS: Gets user input from the keyboard and returns it
     */
    private String getUserInput() {}

    /*
     * MODIFIES: this
     * EFFECTS: Processes user command and calls corresponding method
     */
    private void processCommand(String command) {}

    /*
     * EFFECTS: Displays information about a specific account stored in the manager
     */
    private void displaySpecificAccountInformation() {}

    /*
     * REQUIRES: name, username, and password must be longer than zero
     * MODIFIES: this
     * EFFECTS: Adds an account to the password manager
     */
    private void addAccount(String name, String username, String password) {}

    /*
     * REQUIRES: 0 < index <= size of the list of accounts
     * MODIFIES: this
     * EFFECTS: Removes an account from the password manager
     */
    private void removeAccount(int indexOfAccount) {}

    /*
     * REQUIRES: 0 < index <= size of the list of accounts
     * MODIFIES: this, account
     * EFFECTS: Edits account information
     */
    private void editAccount(int indexOfAccount) {}

    /*
     * REQUIRES: must not be null and must be in the list of accounts
     * MODIFIES: this, account
     * EFFECTS: Changes username for a specified account
     */
    private void changeUsername(Account accountToChange){}

    /*
     * REQUIRES: must not be null and must be in the list of accounts
     * MODIFIES: this, account
     * EFFECTS: Changes password for a specified account
     */
    private void changePassword(Account accountToChange) {}
}
