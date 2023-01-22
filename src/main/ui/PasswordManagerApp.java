package ui;

import model.Account;
import model.EventLog;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;

//Password Manager Application
public class PasswordManagerApp extends JFrame {
    private static final String SPLASH_ICON = "./data/lockIcon.jpeg";
    private static final String SAVE_PATH = "./data/userData.json";
    private static final int APP_WIDTH = 500;
    private static final int APP_HEIGHT = 500;
    protected User userData;
    private JList list;
    private DefaultListModel<Account> listModel;

    /*
     * EFFECTS: Starts the Password Manager App
     */
    public PasswordManagerApp() {
        runSplashScreen();
        new LogInManager(this);
    }

    /*
     * EFFECTS: Paints a splash screen
     */
    private void runSplashScreen() {
        JDialog frame = new JDialog();
        frame.getContentPane().add(new JLabel("", new ImageIcon(SPLASH_ICON), SwingConstants.CENTER));
        frame.setSize(200, 200);    //Set properties of frame
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Wait 5 seconds
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        frame.setVisible(false);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Initialize main app graphics
     */
    protected void runManagerProcess() {
        setJframeProperties();
        loadManager(SAVE_PATH);
        initializeAccountSelector();
        initializeMenu();
        setVisible(true);
    }

    /*
     * EFFECTS: Sets all the JFrame properties for this class
     */
    private void setJframeProperties() {
        setSize(APP_WIDTH,APP_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        //Change exit behavior to prompt user if they would like to save before exiting
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveManager(SAVE_PATH);
                //Print log before exiting
                for (model.Event event : EventLog.getInstance()) {
                    System.out.println(event);
                    System.out.println();
                }

                System.exit(0);
            }
        });
    }

    /*
     * EFFECTS: Initialize the list of accounts to display and add it to the Jframe
     */
    private void initializeAccountSelector() {
        //Convert userData to a default list model
        listModel = new DefaultListModel<>();
        for (Account account : userData.getInnerList()) {
            listModel.addElement(account);
        }

        //create a new Jlist from the default list model and set its properties
        list = new JList<>(listModel);
        list.setFixedCellHeight(40);
        list.setFixedCellWidth(300);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        add(listScrollPane, BorderLayout.CENTER);
    }

    /*
     * EFFECTS: Initialize the menu buttons to display and add it to the Jframe
     */
    private void initializeMenu() {
        final int gap = 5;
        //Create container to store button and set its properties
        JPanel menuArea = new JPanel();
        menuArea.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
        menuArea.setLayout(new GridLayout(0,1));
        //Add buttons
        menuArea.add(new JButton(new ViewAccount()));
        menuArea.add(new JButton(new AddAccount()));
        menuArea.add(new JButton(new RemoveAccount()));
        add(menuArea, BorderLayout.SOUTH);
    }

    //This represents the view an account action a user can perform
    private class ViewAccount extends AbstractAction {
        /*
         * EFFECTS: Constructs a ViewAccount action
         */
        ViewAccount() {
            super("View Account Information");
        }

        /*
         * EFFECTS: On mouse click, create a popup that displays the account information
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            AccountDialog dialog = new AccountDialog();

            if (listModel.getSize() == 0) {
                this.setEnabled(false);
            }
        }
    }

    //This represents a dialog box that displays account information
    private class AccountDialog extends JDialog {
        /*
         * EFFECTS: Constructs an account dialog box which displays name, username, and password of an account
         */
        private AccountDialog() {
            Account account = (Account) list.getSelectedValue();

            //Create formatted account information
            String output = "<html><h1>" + account.getName() + "</h1>"
                    + "<div>Username: " + account.getUsername() + "</div>"
                    + "<div>Password: " + account.getPassword() + "</div></html>";
            JLabel name = new JLabel(output, SwingConstants.CENTER);

            //Create a close button and set its close action
            JButton closeButton = new JButton("Close");
            closeButton.addActionListener(e -> setVisible(false));

            //Set dialog box properties and add account information and close button
            setLayout(new BorderLayout());
            add(name, BorderLayout.CENTER);
            add(closeButton, BorderLayout.SOUTH);
            setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
            setResizable(false);
            pack();
            setSize(APP_WIDTH, APP_HEIGHT / 2);
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    //This represents the add an account action a user can perform
    private class AddAccount extends AbstractAction {
        /*
         * EFFECTS: Constructs an AddAccount action
         */
        AddAccount() {
            super("Add New Account");
        }

        /*
         * MODIFIES: this
         * EFFECTS: On mouse click, Prompts user to add an account
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            String name = JOptionPane.showInputDialog(null,
                    "Name of account?",
                    "Add New Account",
                    JOptionPane.QUESTION_MESSAGE);

            String username = JOptionPane.showInputDialog(null,
                    "Username?",
                    "Add New Account",
                    JOptionPane.QUESTION_MESSAGE);

            String password = JOptionPane.showInputDialog(null,
                    "Password?",
                    "Add New Account",
                    JOptionPane.QUESTION_MESSAGE);

            Account accountToAdd = new Account(name, username, password);
            userData.add(accountToAdd);
            listModel.addElement(accountToAdd);
            list.setSelectedIndex(listModel.getSize());
            list.ensureIndexIsVisible(listModel.getSize());
        }
    }

    //This represents the remove an account action a user can perform
    private class RemoveAccount extends AbstractAction {
        /*
         * EFFECTS: Constructs a RemoveAccount action
         */
        RemoveAccount() {
            super("Remove Account");
        }

        /*
         * MODIFIES: this
         * EFFECTS: On mouse click, removes the selected account
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            int index = list.getSelectedIndex();
            userData.remove((Account) list.getSelectedValue());
            listModel.remove(index);
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    /*
     * EFFECTS: Saves the information stored in the manager to file at the path location
     */
    private void saveManager(String path) {
        try {
            JsonWriter jsonWriter = new JsonWriter(path);
            jsonWriter.open();
            jsonWriter.write(userData);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to file path: " + path);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Load the information from file into the manager from location path
     */
    private void loadManager(String path) {
        try {
            JsonReader jsonReader = new JsonReader(path);
            userData = jsonReader.read();
        } catch (Exception e) {
            //TODO: FIX THIS
        }
    }
}
