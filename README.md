# Password Manager Project

Warning: This application does not follow all of the best security practices. Do not store private information in this application.
This was solely a learning experience for me.

## STATUS: COMPLETE

## 1. Summary

**This project stores account login information.** All of the username and password combinations can be accessed after logging in using a master username and password. All data is stored and encrypted onto the local file system. Specific information for an account is unencrypted only when needed.

This project is interesting to me because it allows me to practice implementing object-oriented design. In addition, I want to learn how to design and implement methods to ensure data security. In this age, keeping data secure and out of the hands of any potential threat actors is of key importance. People log in to many applications and expect personally identifiable information to be protected. They expect their data to be kept privately and securely. I want to gain a basic understanding how this can be achieved.

## 2. Technologies used
- JSON : data is stored in JSON form. JSON was chosen since it is a very-widely used data format when exchanging data through the web. I wanted to understand how to create JSON objects in Java, thus opening an opportunity in the future to learn how to transmit JSON objects to a database.
- JUnit : The JUnit library was used in order to practice **test-driven development** practices
- Swing : The Swing library was used for the UI. This was chosen due to its maturity and big library of GUI

## 3. User Stories

- As a user, I want to be able to log in using a master passwordHash which is stored as a file
- As a user, I want to be able to see a list of accounts
- As a user, I want to be able to click a button to view the information of a specific account
- As a user, I want to be able to add multiples accounts to a User of the application
- As a user, I want to be able to click a button to add an account
- As a user, I want to be able to remove an account from the User of the application
- As a user, I want to be able to click a button to remove an account
- As a user, I want to be prompted to save data to file when my application ends
- As a user, I want to be prompted to load data from file when my application starts
