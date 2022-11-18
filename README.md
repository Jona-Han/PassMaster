# Password Manager Project

## 1. Summary

**This project stores account login information.** All of the username and password combinations can be accessed after
logging in using a master username and password. Login information is stored and encrypted onto the local file system.
The project then decrypts the stored information to provide to the user.

Anyone who needs a secure way to store their login information can use this application.

This project is interesting to me because it allows me to learn and implement object-oriented design. In addition, I
want to learn how to design and implement methods to ensure data security. In this age, keeping data secure and out of
the hands of any potential bad actors is of key importance. People log in to many applications and may have personal 
information attached to their account. They expect their data to be kept privately and securely. I want to 
understand how this is done.

## 2. User Stories

- As a user, I want to be able to log in using a master password which is stored as a file
- As a user, I want to be able to see a list of accounts
- As a user, I want to be able to click a button to view the information of a specific account
- As a user, I want to be able to add multiples accounts to a User of the application
- As a user, I want to be able to click a button to add an account
- As a user, I want to be able to remove an account from the User of the application
- As a user, I want to be able to click a button to remove an account
- As a user, I want to be able to edit the username and password of an account
- As a user, I want to be prompted to save data to file when my application ends
- As a user, I want to be prompted to load data from file when my application starts


## 3. Instructions for Grader
- If you don't load data in the beginning, then follow the user registration process and remember the password you set
- You can generate the first required event related to adding Accounts to a User by pressing the "add an account" button
and following the prompts.
- You can generate the second required event related to adding Accounts to a User by selecting an account from the list
of accounts displayed at the top and clicking "View Account Information" button
- You can locate my visual component by starting the application and seeing the splash screen
- You can save the state of my application by logging in and exiting the application. Follow the prompts.
- You can reload the state of my application by starting the application and clicking "yes" on the first prompt. Then
select the file you would like to load