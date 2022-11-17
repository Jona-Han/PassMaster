package ui;

import exceptions.CollectionIndexOutOfBoundsException;
import model.Account;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

//Password Manager Application
public class PasswordManagerApp extends JFrame {
    private static final String JSON_STORE = "./data/userData.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private User userData;

    /*
     * EFFECTS: Starts the Password Manager App by initiating the login process
     */
    public PasswordManagerApp() {
        init();
        runLoginProcess();
        initializeGraphics();
        runManagerProcess();
    }

    /*
     * EFFECTS: Checks for correct login info and starts the password manager if info is correct
     */
    private void runLoginProcess() {
        boolean passwordCorrect = false;
        while (!passwordCorrect) {
            String input = JOptionPane.showInputDialog(null,
                    "Welcome to the Password Manager.\nPlease enter your password.",
                    "Password Manager Login", JOptionPane.PLAIN_MESSAGE);

            //If password is correct, start manager
            if (Objects.equals(input, userData.getMasterPassword())) {
                passwordCorrect = true;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input
     */
    private void runManagerProcess() {
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
            userInput = getUserInput();

            if (userInput.equals("q") || userInput.equals("Q")) {
                running = false;
            } else {
                processCommand(userInput);
            }
        }
        System.out.println("\nGood bye!");
    }

    private void initializeGraphics() {
        setSize(400,600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel menuArea = new JPanel();
        menuArea.setLayout(new GridLayout());
        menuArea.add(new JButton(new AddAccount()));
        menuArea.add(new JButton(new RemoveAccount()));
        add(menuArea, BorderLayout.SOUTH);
    }

    /*
     * EFFECTS: Initializes empty collection of accounts
     */
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadManager();
    }


    private class AddAccount extends AbstractAction {

        AddAccount() {
            super("Add New Account");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = JOptionPane.showInputDialog(null,
                    "Name of account?",
                    "Add New Account",
                    JOptionPane.QUESTION_MESSAGE);

            String username = JOptionPane.showInputDialog(null,
                    "Password?",
                    "Add New Account",
                    JOptionPane.QUESTION_MESSAGE);

            String password = JOptionPane.showInputDialog(null,
                    "Username?",
                    "Add New Account",
                    JOptionPane.QUESTION_MESSAGE);

            addAccount(new Account(name, username, password));
            saveManager();
        }
    }

    private class RemoveAccount extends AbstractAction {

        RemoveAccount() {
            super("Remove New Account");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //stub
            saveManager();
        }
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
        for (int index = 0; index < userData.size(); index++) {
            System.out.format("%n %-10s", index + 1);
            System.out.print(userData.get(index).getName());
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
        }
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
            saveManager();
        } catch (CollectionIndexOutOfBoundsException e) {
            System.out.println("Invalid account selection. Please choose a valid account number.");
        }
    }

    /*
     * EFFECTS: Prompts the user for the number of the desired account and returns the account at that position
     */
    private Account getAccountFromAccounts() throws CollectionIndexOutOfBoundsException {
        System.out.println("\nWhich account number?");
        String userInput = getUserInput();
        int indexInAccounts = Integer.parseInt(userInput) - 1;  //Converts user input into an integer
        return userData.get(indexInAccounts);
    }

    /*
     * EFFECTS: Displays information about a specific account stored in the manager
     */
    private void viewSpecificAccountInformation(Account account) {
        System.out.println("\n" + account.getName() + "\nUsername: " + account.getUsername() + "\nPassword: "
                            + account.getPassword());
    }

    /*
     * MODIFIES: this, CollectionOfAccounts, Account
     * EFFECTS: Asks user and enters editing mode if yes answer is given
     */
    private void promptUserToEnterEditingMode(Account account) {
        //Ask user if they would like to edit the account
        System.out.println("\nWould you like to edit this account? (y/n)");
        String userInput = getUserInput();

        //Enter editing mode if yes
        if (userInput.equals("y") || userInput.equals("Y")) {
            editAccount(account);
            saveManager();
        }
    }


    /*
     * MODIFIES: this, CollectionOfAccounts
     * EFFECTS: Adds an account to the manager
     */
    private void addAccount(Account account) {
        userData.add(account);
        System.out.println("\nAccount successfully added!");
    }

    /*
     * MODIFIES: this, CollectionOfAccounts
     * EFFECTS: Removes an account from the password manager
     */
    private void removeAccount(Account account) {
        userData.remove(account);
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
                    + "\t\tc: Password\t\td: Save changes and exit editing mode");
            String userInput = getUserInput();
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
        String userInput;
        while (true) {
            System.out.print("\nWhat would you like to change the name to?\nCurrently: " + account.getName());
            System.out.println();
            userInput = getUserInput();

            //Check for blank name
            if (userInput.length() == 0) {
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
        System.out.print("\nWhat would you like to change the username to?\nCurrently: " + account.getUsername());
        System.out.println();
        String userInput = getUserInput();

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
        System.out.print("\nWhat would you like to change the password to?\nCurrently: " + account.getPassword());
        System.out.println();
        String userInput = getUserInput();

        //Change password
        account.setPassword(userInput);
        System.out.println("Password successfully changed!");
    }

    /*
     * EFFECTS: Gets user input from the keyboard and returns it as a string
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

    /*
     * EFFECTS: Saves the information stored in the manager to file
     */
    private void saveManager() {
        try {
            jsonWriter.open();
            jsonWriter.write(userData);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file path: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Load the information from file into the manager
     */
    private void loadManager() {
        try {
            userData = jsonReader.read();
        } catch (IOException e) {
            registerNewUser();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Create a user with a master password. Only happens during the first time the program starts
     */
    private void registerNewUser() {
        System.out.println("Welcome! Looks like this is the first time you have opened this application.\nWhat would"
                             + " you like your master password to be?");
        String newPassword = getUserInput();
        System.out.println("Master password set. Use this password to log in every time you use this application.");
        userData = new User(newPassword);
        saveManager();
    }


}
