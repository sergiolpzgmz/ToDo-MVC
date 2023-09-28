# ToDo App

This desktop app allows you to keep track of your tasks, using popular design patterns such as MVC or repository. Add data persistence in a local MySQL database. 

# Architecture

* MVC: Model View Controller.
* Repository: CRUD methods are implemented from an only one interface.
* Singleton: A single class to create the database connection.

# How to install
Once you have downloaded the project, go to src/main/resources/DBScript, that folder contains the script you need to import into your local database.

After importing you may need to change the database access data in the DataBaseConnection class, inside the src/main/Java/Util directory.

# UML Class diagram
<div class="container">
        <img src="https://github.com/sergiolpzgmz/ToDo-MVC/blob/master/documentation/TodoAppUML.png" alt="UML" width="600em">
</div>

# Demo
<div class="container">
        <img src="https://github.com/sergiolpzgmz/ToDo-MVC/blob/master/previews/Screenshot1.png" alt="preview1" width="300em">
        <img src="https://github.com/sergiolpzgmz/ToDo-MVC/blob/master/previews/Screenshot2.png" alt="preview2" width="800em">
</div>
