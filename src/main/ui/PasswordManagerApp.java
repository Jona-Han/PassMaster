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
    private static final String SPLASH_ICON = "./data/lockIcon.jpeg";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private User userData;
    private JList list;
    private DefaultListModel<Account> listModel;

    /*
     * EFFECTS: Starts the Password Manager App by initiating the login process
     */
    public PasswordManagerApp() {
        runSplashScreen();
        init();
        runLoginProcess();
        runManagerProcess();
    }

    /*
     * EFFECTS: Paints a splash screen
     */
    private void runSplashScreen() {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel("", new ImageIcon(SPLASH_ICON), SwingConstants.CENTER));
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        frame.setVisible(false);
    }

    /*
     * EFFECTS: Initializes empty collection of accounts
     */
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        loadManager();
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
        setSize(400,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        for (Account account : userData.getInnerList()) {
            listModel.addElement(account);
        }

        list = new JList(listModel);
        list.setFixedCellHeight(40);
        list.setFixedCellWidth(300);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);

        JPanel menuArea = new JPanel();
        menuArea.setLayout(new GridLayout(4,1));
        menuArea.add(new JButton(new ViewAccount()));
        menuArea.add(new JButton(new AddAccount()));
        menuArea.add(new JButton(new RemoveAccount()));
        menuArea.add(new JButton(new EditAccount()));
        add(menuArea, BorderLayout.SOUTH);


        setVisible(true);
    }

    private class ViewAccount extends AbstractAction {
        ViewAccount() {
            super("View Account Information");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

            int index = list.getSelectedIndex();

            JTextPane pane = new JTextPane();
            pane.setText(list.getSelectedValue().toString());

            JDialog view = new JDialog();
            view.add(pane);
            view.setSize(300, 200);
            view.setVisible(true);
        }
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
                    "username?",
                    "Add New Account",
                    JOptionPane.QUESTION_MESSAGE);

            String password = JOptionPane.showInputDialog(null,
                    "password?",
                    "Add New Account",
                    JOptionPane.QUESTION_MESSAGE);

            Account accountToAdd = new Account(name, username, password);
            userData.add(accountToAdd);
            listModel.addElement(accountToAdd);
            list.setSelectedIndex(listModel.getSize());
            list.ensureIndexIsVisible(listModel.getSize());
            saveManager();
        }
    }

    private class RemoveAccount extends AbstractAction {

        RemoveAccount() {
            super("Remove Account");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            int index = list.getSelectedIndex();
            userData.remove((Account) list.getSelectedValue());
            listModel.remove(index);

            if (listModel.getSize() == 0) {
                this.setEnabled(false);
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
            saveManager();
        }
    }

    private class EditAccount extends AbstractAction {
        EditAccount() {
            super("Edit Account");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            int index = list.getSelectedIndex();
            //stub
            saveManager();
        }
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
     * MODIFIES: this, CollectionOfAccounts
     * EFFECTS: Create a user with a master password. Only happens during the first time the program starts
     */
    private void registerNewUser() {
        String input = JOptionPane.showInputDialog(null,
                "Welcome! Looks like this is the first time you have opened this application.\nWhat would"
                        + " you like your master password to be?",
                "Password Manager Registration", JOptionPane.PLAIN_MESSAGE);
        userData = new User(input);
        saveManager();
    }


}
