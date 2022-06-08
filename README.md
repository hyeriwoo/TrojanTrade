# TrojanTrade

Used Goods Market App: A web app that allows users, especially for university students, to buy and sell used items through direct dealing. Users can upload their items with an intent to sell or even browse the app with a desire to purchase. The app includes functionality to allow the user to create a “wishlist” of  saved items, purchase history, and a messaging feature allowing potential buyers to contact sellers regarding the listed item

# Required Jar (Included with zip file)
1. annotations-13.0.jar
2. gson-2.8.6.jar
3. kotlin-stdlib-1.3.72.jar
4. kotlin-stdlib-common-1.3.70.jar
5. okhttp-4.8.0.jar
6. okio-2.7.0.jar
7. mysql-connector-java-8.0.27.jar
8. httpclient-4.5.13.jar
9. httpcore-4.4.14.jar
10. jakarta.activation-2.0.0.jar
11. jakarta.el-api-4.0.0.jar
12. jakarta.servlet-api-5.0.0.jar
13. jakarta.servlet.jsp.jstl-2.0.0.jar
14. jakarta.servlet.jsp.jstl-api-2.0.0.jar
15. jakarta.xml.bind-api-3.0.0.jar
16. jsr305-3.0.2.jar

# Importing Field
1. Put all jar files mentioned above in CSCI201_FinalProject_TrojanTrade/src/main/webapp/WEB-INF/lib
2. Right click on Eclipse project
3. Click “Refresh” and Choose “Properties”.
4. Select “Java Build Path”
5. Click the “Libraries” tab, and select “Classpath”
6. Click “Add JARs”
7. Find the JAR under WEB-INF/lib and select them.
8. Click “OK” and “Apply and Close”

# Access to SQL
1. Create new database on mySQL with “trojantrade.sql” 
2. Open “trojantrade.sql” file and click the left thunder button to run sql code. 
3. It will create tables and add example data into tables. 
4. Change value of database, user, and password in mySQL.java under src/main/java

# Run the code
1. Tomcat v10.0 needs to be used. 
2. Right click on home.jsp (Homepage) under src/main/webapp 
3. Click “Run As” and “Run on Server”
