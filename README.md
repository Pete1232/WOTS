# WOTS
Warehouse Order Tracking System for Scala project.
This project was to create an application to allow warehouse workers to process customer orders.
### Key Features:
* A ScalaFX GUI, designed with portable devices in mind.
* Ability to view information about both customer and purchase orders.
* Persistence using both a MySQL and MongoDB database.
* Databases compatible with order processing and inventory management systems.
* A genetic travelling salesman algorithm to allow for quick warehouse navigation
* Please see scaladocs folder for full documentation.

### Running the application
The project has been configured to run from dummy data, as databases will not be available.
If the databases were running a connection could be established by simply changing the 'online' boolean in
com.qa.data.DataConfig to true. Files to create the databases are contained in the database folder.
