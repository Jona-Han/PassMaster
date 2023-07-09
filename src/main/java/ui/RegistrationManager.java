package ui;

import model.AllUsers;
import model.User;

import javax.swing.*;
import java.awt.*;

public class RegistrationManager extends JFrame {
    private PasswordManagerApp app;
    private JLabel labelTitle;
    private JLabel labelDesc;
    private JLabel labelUsername;

    private JLabel labelPassword;
    private JLabel labelMessage;
    private JTextField username;
    private JPasswordField password;

    public RegistrationManager(PasswordManagerApp app) {
        this.app = app;
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setTitle("J Password Manager");
        setLocationRelativeTo(null);
        setLayout(null);
        addLabels();
        addButtons();
        addCheckbox();
        setVisible(true);
    }

    private void addLabels() {
        labelTitle = new JLabel("Register New User");
        labelTitle.setBounds(60, 10, 300, 40);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 20));

        labelDesc = new JLabel("<html>Welcome! Please enter a username and password to register.</html>");
        labelDesc.setBounds(60, 50, 300, 40);

        labelUsername = new JLabel("Username:");
        labelUsername.setBounds(60, 100, 100, 40);
        labelUsername.setFont(new Font("Arial", Font.BOLD, 14));

        labelPassword = new JLabel("Password:");
        labelPassword.setBounds(60, 150, 100, 40);
        labelPassword.setFont(new Font("Arial", Font.BOLD, 14));

        labelMessage = new JLabel();
        labelMessage.setBounds(60, 300, 200, 40);
        labelMessage.setForeground(Color.RED);

        username = new JTextField();
        username.setBounds(140, 100, 200, 40);

        password = new JPasswordField();
        password.setBounds(140, 150, 200, 40);

        add(labelUsername);
        add(labelPassword);
        add(labelMessage);
        add(labelDesc);
        add(labelTitle);
        add(username);
        add(password);
    }


    private void addButtons() {
        JButton confirm = new JButton("Finish Registration");
        confirm.setBounds(100, 230, 200, 30);
        confirm.addActionListener(e -> {
            app.userData = new User(username.getText(), password.getText());
            app.userData.hashPassword();
            AllUsers.getInstance().addUser(app.userData);
            setVisible(false);
            dispose();
            app.runManagerProcess();
        });
        add(confirm);
    }

    private void addCheckbox() {
        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(60, 190, 150, 30);
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                password.setEchoChar((char) 0);
            } else {
                password.setEchoChar('*');
            }
        });
        add(showPassword);
    }
}
