# WOTS
Warehouse Order Tracking System for Scala project.
## Info
This project was to create an application to allow warehouse workers to process customer orders for a fictional company
### Key Features:
- A ScalaFX GUI, designed with portable devices in mind.
- Ability to view information about both customer and purchase orders.
- Persistence using both a MySQL and MongoDB database.
- Please see scaladocs folder for full documentation.
### Running the application
The project has been configured to run from dummy data, as no database will be available.
If the databases were running a connection could be established by simply changing the 'online' boolean in
com.qa.data.DataConfig to true.