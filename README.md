# Password Manager Project

## 1. Summary

**This project stores account login information.** All of the username and passwordHash combinations can be accessed after
logging in using a master username and passwordHash. Login information is stored and encrypted onto the local file system.
The project then decrypts the stored information to provide to the user.

Anyone who needs a secure way to store their login information can use this application.

This project is interesting to me because it allows me to learn and implement object-oriented design. In addition, I
want to learn how to design and implement methods to ensure data security. In this age, keeping data secure and out of
the hands of any potential bad actors is of key importance. People log in to many applications and may have personal 
information attached to their account. They expect their data to be kept privately and securely. I want to 
understand how this is done.

## 2. User Stories

- As a user, I want to be able to log in using a master passwordHash which is stored as a file
- As a user, I want to be able to see a list of accounts
- As a user, I want to be able to click a button to view the information of a specific account
- As a user, I want to be able to add multiples accounts to a User of the application
- As a user, I want to be able to click a button to add an account
- As a user, I want to be able to remove an account from the User of the application
- As a user, I want to be able to click a button to remove an account
- As a user, I want to be prompted to save data to file when my application ends
- As a user, I want to be prompted to load data from file when my application starts


## 3. Instructions for Grader
- If you don't load data in the beginning, then follow the user registration process and remember the passwordHash you set
- You can generate the first required event related to adding Accounts to a User by pressing the "Add New Account" button
and following the prompts.
- You can generate the second required event related to adding Accounts to a User by selecting an account from the list
of accounts displayed at the top and clicking the "Remove Account" button
- You can locate my visual component by starting the application and seeing the splash screen
- You can save the state of my application by logging in and exiting the application. Follow the prompts.
- You can reload the state of my application by starting the application and clicking "yes" on the first prompt. Then
select the file you would like to load

## 4. Phase 4: Task 2
Fri Dec 02 20:32:47 PST 2022
Account added to User: Google - username: jon passwordHash: pass

Fri Dec 02 20:33:00 PST 2022
Account added to User: Yahoo - username: bobby passwordHash: 1234556

Fri Dec 02 20:33:25 PST 2022
Account added to User: Facebook - username: michael passwordHash: j132j

Fri Dec 02 20:33:30 PST 2022
Account removed from User: Facebook - username: michael passwordHash: j132j

Fri Dec 02 20:33:31 PST 2022
Account removed from User: Yahoo - username: bobby passwordHash: 1234556

Fri Dec 02 20:33:33 PST 2022
Account removed from User: Google - username: jon passwordHash: pass

Fri Dec 02 20:33:46 PST 2022
User data converted to JSON.

## 5. Phase 4: Task 3
The main refactoring I would do would be to extract common functionality out of the PasswordManagerApp class into
separate classes. Currently, the PasswordManagerApp class does too many things at once. First, I would likely extract 
out a login manager class which would handle the UI of the login verification. I would also extract a class that
handles the UI of the save and load data functions. I would also extract out a class that handles the UI of 
registering a new user. This would allow the PasswordManagerApp to only handle the main UI once the user has
successfully logged in. I believe that incorporating some of these changes would help to apply the single responsibility
principle and improve coupling.