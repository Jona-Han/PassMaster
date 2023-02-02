package ui;

import encryption.EncryptionUtil;
import model.AllUsers;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LogInManager extends JFrame {
    private PasswordManagerApp app;
    protected JLabel labelTitle;
    private JLabel labelUsername;

    private JLabel labelPassword;
    private JLabel labelMessage;
    private JTextField username;
    private JPasswordField password;
    public LogInManager(PasswordManagerApp app) {
        this.app = app;
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("J Password Manager");
        setLocationRelativeTo(null);
        setLayout(null);
        addLabels();
        addButtons();
        addCheckbox();
        setVisible(true);
    }

    private void addLabels() {
        labelTitle = new JLabel("<html>Please enter your master username and password to continue.</html>");
        labelTitle.setBounds(60, 50, 300, 40);

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
        add(labelTitle);
        add(username);
        add(password);
    }

    private void addButtons() {
        JButton logInButton = new JButton("Log In");
        logInButton.setBounds(50, 240, 300, 30);
        logInButton.addActionListener(e -> {
            for (User user : AllUsers.getInstance()) {
                if (Objects.equals(user.getPasswordHash(),
                        EncryptionUtil.hashPassword(password.getText(), username.getText()))) {
                    setVisible(false);
                    dispose();
                    app.userData = user;
                    app.runManagerProcess();
                }
            }
        });

        JButton registerButton = new JButton("Register New User");
        registerButton.setBounds(50, 270, 300, 30);
        registerButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            new RegistrationManager(app);
        });
        add(logInButton);
        add(registerButton);
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
