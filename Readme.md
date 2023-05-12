# BetterBudget

BetterBudget is a JavaFX-based budgeting application designed to help users manage their savings and transactions. With its easy-to-navigate interface, BetterBudget offers users a way to visualize their financial transactions, set saving goals, and receive suggestions for improving their financial management.

## Table of Contents

- [Statement](#Statement)
- [How to Run](#How-To-Run)
- [Technical Outline](#technical-outline)
- [Objectives](#objectives)
- [Dream Objectives](#dream-objectives)
- [Getting Started](#getting-started)
- [Contributors](#contributors)

## Statement

Our final project is a budgeting application that helps users manage their savings and transactions. The goal of the application is to help users visualize their financial transactions, set saving goals, and make suggestions on where to reduce spending or allocate extra money. The application will showcase our proficiency in JavaFX, graphical user interfaces, and software design principles. The program was created to address a problem we see in the financial applications that we use. Often, the applications that give you up-to-date info on how much money you spend and receive, such as bank statements, do not include tools that help you manage your finances. These tools should do things such as break down the nature of your transactions and give relevant analytics, set budgeting goals, or list things that the user needs or wants to pay for. This application integrates these features into a single package, thereby addressing the aforementioned problem.

## How to Run

You can run this project using IntelliJ IDEA IDE.
Furthermore, you will need to download Maven to get this running for the dependencies used. IntelliJ comes with Maven support so once you have that installed, you can navigate to the App.Java file and click the run button on the top navigation bar.

## Technical Outline

### Classes

- `User` class manages user accounts
- `LogoTransition` This is what does the logo animation. Interpolates upwards by translating
- `App` runs the application and initiates the scene switching 
- `LoginScene` Holds the initial scene after the splash screen animation. 
- `MainWindow` from here or register a new account with `RegisterScene`
- `RegisterScene` Here you can register your user and will be saved into data.csv
- `MainWindow.java` contains the main TabPane holding the following tabs:
	- `BudgetInput.java` allows the user to create and view Budget objects
	- `BudgetView.java` allows the user to visualize charts of their Budgets
	- `TransactionInput.java` allows the user to log transactions and assign them to a budget
	- `TransactionView.java` allows the user to view a line chart of transactions over time
- `Budget` objects represent budget categories
- `BudgetList` stores Budget objects
- `Transaction` objects represent user transactions
- `TransactionList` stores a list of Transaction objects

- `ImageRectangle` is the logo used in the Animation, Logging in, and Registering. Works as a container.
- `DataStorage` Handles complete File Persistency, and creation and deletion of certain objects like Budget, Transaction, and Users.
- `data.csv` Where All data is stored and then loaded into the application.

### Design Details & Rationale

Many design choices in our application, such as the use shades of green as the main color theme for our application and the use of charts to visualize the user’s financial data, were informed by the design of other finance applications. The charts are important because they help users gain meaningful insight from raw user financial data, such as their spending on certain categories over time and how much a category occupies their budget. The use of TabPanes separates the different components and workloads of the application, such as transactions/budgets and their charts, so that the user isn’t overwhelmed by a large window comprised of multiple workflows.

## Objectives

1. Create an attractive and visually appealing intro screen and overall user interface
2. Implement a login screen for accessing persistent user data
3. Design a page for creating Budget objects
4. Develop a transactions page for inputting and storing individual transactions
5. Create transaction trends and metrics visualization
6. Implement spending category breakdown
7. Add smooth animations for transaction-related actions
8. Implement drag-and-drop functionality for easy organization
9. Design data persistence and file handling system
10. Ensure application resizability and responsiveness
11. Integrate back-end data between modules
12. Provide suggestions and allowances based on user spending habits

## Dream Objectives

1. Implement real-time suggestions and feedback based on user spending habits
2. Deploy the application either as an executable or web app
3. Create an automated testing harness

## Getting Started

To get started with BetterBudget, clone the repository and import the project into your favorite Java IDE. Ensure you have JavaFX installed and configured in your environment.

## Contributions

- Malcolm Bell
- Minh Tao
- Zul Ahmed
- Lily Tran (unofficial contributor)

We would like to thank our project members for their hard work and dedication in creating BetterBudget.

- Zul: For the beginning part of the project, this is what I was trying to get right. I was trying to get a visually appealing splash screen/intro animation. Which took a long time. I had to do a lot of research and reassess my knowledge because I thought it would be similar to my other experiences, but it was a different thing entirely. In the end, I got it done, and I was really happy with not only the result but the process that I came to because I thought it was really clever and utilized other resources and Applications well. I also pioneered on the color branding and the theme which was after this hex code #00BB62. Furthermore, I designed both renditions of the logo and implemented it into the code when needed. The logo was made with the BubbleBody Neue font. User Auth was harder than it looked because I wanted to simply copy and use my older project’s login scene but it was so closely tied to the backend of that project that it made it very difficult to do so. I then remade it in a different format but with similar elements. This was different from what I had before because this loginScene wasn’t based on a class of Scenes, rather a file-by-file basis. Data persistence like that isn’t something I was experienced with, however, I was able to find a solution that worked for our application and add in the User Authentication. This came thanks to Riley who helped me on 2 occasions so I really thank her for the resources to look at and her code which guided me in the right direction for where I wanted to go with this. Towards the end, I also stitched the backend and frontend together once the frontend was developed. As of yet, Data Persistence with whole application works
- Minh Tao: I was responsible for implementing Lily’s color palette into our program via CSS. I was also responsible for selecting and implementing a font for our entire application. I settled on the current font for our program because as a finance app, we want the font to evoke a sense of security and reliability, so we wanted our font to strike a balance between not looking too outlandish or too generic. The pages displaying the user’s transaction list and breakdown graph comprised the bulk of my work. I carried over some knowledge from my Project 1 work to improve the functionality of this page, such as input validation and the use of a delete button in the table. The page was also the initial testing ground for implementing Lily’s design palette and my chosen font before they would be used for the rest of the program. Overall, I am happy with how the page looks and my contributions to the application’s design.
- Malcolm Bell: I was responsible for the BudgetInput and BudgetView pages, which required front-end development as well as backend integration for managing Budget objects and their interactions with transactions. I also took on a large planning role in this project. I used tools like Canva and Trello to map out the design of our project architecture as well as create a roadmap for the project’s objectives. I learned how to build upon my knowledge from my SpendingTracker project to shape the Budget interfaces. I learned how to use new features such as PieChart and StackedBarChart, which came with difficulties. I was able to tackle these challenges through research, experimentation, and help from Professor Fourquet and my classmates.
- Lily Tran: Lily is an unofficial contributor to the project and friend of Minh. She created and designed a color palette at Minh’s request; the palette informed the colors and design details used throughout the application. 

## Acknowledgements

Thanks to our Professor, Elodie Fourquet, and all of our wonderful classmates. Collaboration with our classmates throughout this course allowed us to learn how to use unfamiliar features and reinforce our understanding of concepts we used readily. 

* [JavaFX CSS Skinning](https://docs.oracle.com/javafx/2/css_tutorial/jfxpub-css_tutorial.htm)
* [StackedBarChart](http://java-buddy.blogspot.com/2013/03/javafx-2-update-stackedbarchart.html)
* [PieChart](https://docs.oracle.com/javafx/2/charts/pie-chart.htm)
* [OpenCSV](https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/)
* [Hanken Grotesk (font) - Google](https://fonts.google.com/specimen/Hanken+Grotesk)
* [Bubbleboddy Neue (font) - Zetafonts](https://www.zetafonts.com/bubbleboddy)


* Berns, Roy S. Color Science and the Visual Arts. John Wiley & Sons, Inc., 2000.
  - Helped us understand complex color concepts
* Yau, Nathan. Visualize This: The FlowingData Guide to Design, Visualization, and Statistics. John Wiley & Sons, Inc., 2011.
  - Helped inspire and guide us in visualizing financial data
* Spolsky, Joel. User Interface Design for Programmers. Apress, 2001.
  - Gave us inspiration for our interface and how to integrate principles of design

## Mockup Images

![image](https://github.com/Minh-tao/BudgetTracking/assets/93014001/3e18477e-8064-4499-aaef-e24ed79f676c)
![image](https://github.com/Minh-tao/BudgetTracking/assets/93014001/b24ab2f1-78b1-4084-bd16-8f2d94c0f2b4)
![image](https://github.com/Minh-tao/BudgetTracking/assets/93014001/2f389c15-352e-4d13-ac96-52607e471a95)
![image](https://github.com/Minh-tao/BudgetTracking/assets/93014001/5d0bdef5-bf6a-472d-bfea-b5815cc586ec)
![image](https://github.com/Minh-tao/BudgetTracking/assets/93014001/556fcf2d-f195-408d-8661-4c6ecf443b57)
![image](https://github.com/Minh-tao/BudgetTracking/assets/93014001/2d8403ce-7d16-4e78-8536-3e22edeb65f7)
![image](https://github.com/Minh-tao/BudgetTracking/assets/93014001/01cfdbaf-09a0-4870-87eb-d27d173a76d2)
![image](https://github.com/Minh-tao/BudgetTracking/assets/93014001/9596cad6-df00-474f-b28b-83f7cfdcfc0d)
![image](https://github.com/Minh-tao/BudgetTracking/assets/93014001/1abf3fac-2b5f-4d98-8c01-0b077ffe8369)

