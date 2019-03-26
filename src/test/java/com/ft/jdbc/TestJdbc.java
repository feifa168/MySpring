package com.ft.jdbc;

import org.junit.Test;

//RDBMS
//JDBC驱动程序名称
//URL格式
//
//MySQL
//com.mysql.jdbc.Driver
//jdbc:mysql://hostname/databaseName
//
//ORACLE
//oracle.jdbc.driver.OracleDriver
//jdbc:oracle:thin:@hostname:portNumber:databaseName
//
//PostgreSQL
//org.postgresql.Driver
//jdbc:postgresql://hostname:port/dbname
//
//DB2
//com.ibm.db2.jdbc.net.DB2Driver
//jdbc:db2:hostname:port Number/databaseName
//
//Sybase
//com.sybase.jdbc.SybDriver
//jdbc:sybase:Tds:hostname: portNumber/databaseName

//STEP 1. Import required packages
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;

public class TestJdbc {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://172.16.39.251:12239/test?characterEncoding=UTF-8";

    //  Database credentials -- 数据库名和密码自己修改
    static final String USER = "root";
    static final String PASS = "123456";

    static {
        //STEP 2: Register JDBC driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStatement() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM Employees";
//            stmt.addBatch("SELECT id, first, last, age FROM Employees where id=1");
//            stmt.addBatch("SELECT id, first, last, age FROM Employees where id=2");
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                try {
                    String rsNull = rs.getString("last");
                    if (rs.wasNull()) {
                        rsNull = "";
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    @Test
    public void testPrepareStatement() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String sql = "UPDATE Employees set age=? WHERE id=?";
            stmt = conn.prepareStatement(sql);

            //Bind values into the parameters.
            stmt.setInt(1, 35);  // This would set age
            stmt.setInt(2, 2); // This would set ID

            // Let us update age of the record with ID = 102;
            int rows = stmt.executeUpdate();
            System.out.println("Rows impacted : " + rows );

            // Let us select all the records and display them.
            sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    @Test
    public void testCallableStatement() {
        Connection conn = null;
        CallableStatement stmt = null;
        try{
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String sql = "{call getEmpName (?, ?)}";
            stmt = conn.prepareCall(sql);

            //Bind IN parameter first, then bind OUT parameter
            int empID = 1;
            stmt.setInt(1, empID); // This would set ID as 102
            // Because second parameter is OUT so register it
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);

            //Use execute method to run stored procedure.
            System.out.println("Executing stored procedure..." );
            stmt.execute();

            //Retrieve employee name with getXXX method
            String empName = stmt.getString(2);
            System.out.println("Emp Name with ID:" + empID + " is " + empName);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    @Test
    public void testResultSet() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query to create statment with
            // required arguments for RS example.
            System.out.println("Creating statement...");
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql;
            sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            // Move cursor to the last row.
            System.out.println("Moving cursor to the last...");
            rs.last();

            //STEP 5: Extract data from result set
            System.out.println("Displaying record...");
            //Retrieve by column name
            int id  = rs.getInt("id");
            //int age = rs.getInt(4); // getInt(index)索引做参数，从下表1开始，实际测试发现建表字段书序与这里的顺序不一致
            int age = rs.getInt("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);

            // Move cursor to the first row.
            System.out.println("Moving cursor to the first row...");
            rs.first();

            //STEP 6: Extract data from result set
            System.out.println("Displaying record...");
            //Retrieve by column name
            id  = rs.getInt("id");
            age = rs.getInt("age");
            first = rs.getString("first");
            last = rs.getString("last");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);
            // Move cursor to the first row.

            System.out.println("Moving cursor to the next row...");
            rs.next();

            //STEP 7: Extract data from result set
            System.out.println("Displaying record...");
            id  = rs.getInt("id");
            age = rs.getInt("age");
            first = rs.getString("first");
            last = rs.getString("last");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);

            //STEP 8: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    @Test
    public void testResultSetMore() {
        Connection conn = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Execute a query to create statment with
            // required arguments for RS example.
            System.out.println("Creating statement...");
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            //STEP 5: Execute a query
            String sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("List result set for reference....");
            printRs(rs);

            //STEP 6: Loop through result set and add 5 in age
            //Move to BFR postion so while-loop works properly
            rs.beforeFirst();
            //STEP 7: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int newAge = rs.getInt("age") + 5;
                rs.updateDouble( "age", newAge );
                rs.updateRow();
            }
            System.out.println("List result set showing new ages...");
            printRs(rs);
            // Insert a record into the table.
            //Move to insert row and add column data with updateXXX()
            System.out.println("Inserting a new record...");
            rs.moveToInsertRow();
            //rs.updateInt("id",104);
            rs.updateString("first","John");
            rs.updateString("last","Paul");
            rs.updateInt("age",40);
            //Commit row
            rs.insertRow();

            System.out.println("List result set showing new set...");
            printRs(rs);

            // Delete second record from the table.
            // Set position to second record first
            rs.absolute( 2 );
            System.out.println("List the record before deleting...");
            //Retrieve by column name
            int id  = rs.getInt("id");
            int age = rs.getInt("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);

            //Delete row
            rs.deleteRow();
            System.out.println("List result set after deleting one records...");
            printRs(rs);

            //STEP 8: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
    public void printRows(Statement stmt) throws SQLException{
        System.out.println("Displaying available rows...");
        // Let us select all the records and display them.
        String sql = "SELECT id, first, last, age FROM Employees";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            int age = rs.getInt("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);
        }
        System.out.println();
        rs.close();
    }
    public void printRs(ResultSet rs) throws SQLException{
        //Ensure we start with first row
        rs.beforeFirst();
        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            int age = rs.getInt("age");
            String first = rs.getString("first");
            String last = rs.getString("last");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Age: " + age);
            System.out.print(", First: " + first);
            System.out.println(", Last: " + last);
        }
        System.out.println();
    }

    @Test
    public void testTransactions() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Set auto commit as false.
            conn.setAutoCommit(false);

            //STEP 5: Execute a query to create statment with
            // required arguments for RS example.
            System.out.println("Creating statement...");
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            //STEP 6: INSERT a row into Employees table
            System.out.println("Inserting one row....");
            String SQL = "INSERT INTO Employees " +
                    "VALUES (200, 28, 'Curry1', 'Stephen1')";
            stmt.executeUpdate(SQL);

            //STEP 7: INSERT one more row into Employees table
            SQL = "INSERT INTO Employees " +
                    "VALUES (106, 32, 'Kobe1', 'Bryant1')";
            stmt.executeUpdate(SQL);

            //STEP 8: Commit data here.
            System.out.println("Commiting data here....");
            conn.commit();

            //STEP 9: Now list all the available records.
            String sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List result set for reference....");
            printRs(rs);

            //STEP 10: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            System.out.println("error code is " + se.getErrorCode() + ", message is " + se.getMessage() + ", state is " + se.getSQLState());
            se.printStackTrace();
            // If there is an error then rollback the changes.
            System.out.println("Rolling back data here....");
            try{
                if(conn!=null)
                    conn.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try

        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    @Test
    public void testSavePoint() {
        Connection conn = null;
        Statement stmt = null;
        try{
            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 4: Set auto commit as false.
            conn.setAutoCommit(false);

            //STEP 5: Execute a query to delete statment with
            // required arguments for RS example.
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            //STEP 6: Now list all the available records.
            String sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("List result set for reference....");
            printRs(rs);

            // STEP 7: delete rows having ID grater than 104
            // But save point before doing so.
            Savepoint savepoint1 = conn.setSavepoint("ROWS_DELETED_1");
            System.out.println("Deleting row....");
            String SQL = "DELETE FROM Employees " +"WHERE ID = 106";
            stmt.executeUpdate(SQL);
            // oops... we deleted too wrong employees!
            //STEP 8: Rollback the changes afetr save point 2.
            conn.rollback(savepoint1);

            // STEP 9: delete rows having ID grater than 104
            // But save point before doing so.
            Savepoint savepoint2 = conn.setSavepoint("ROWS_DELETED_2");
            System.out.println("Deleting row....");
            SQL = "DELETE FROM Employees " + "WHERE ID = 107";
            stmt.executeUpdate(SQL);

            //STEP 10: Now list all the available records.
            sql = "SELECT id, first, last, age FROM Employees";
            rs = stmt.executeQuery(sql);
            System.out.println("List result set for reference....");
            printRs(rs);

            //STEP 10: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
            // If there is an error then rollback the changes.
            System.out.println("Rolling back data here....");
            try{
                if(conn!=null)
                    conn.rollback();
            }catch(SQLException se2){
                se2.printStackTrace();
            }//end try

        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    @Test
    public void testBatch() {

        Connection conn = null;
        Statement stmt = null;
        try{
            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // Create statement
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            // Set auto-commit to false
            conn.setAutoCommit(false);

            // First, let us select all the records and display them.
            printRows( stmt );

            // Create SQL statement
            String SQL = "INSERT INTO Employees (first, last, age) " +
                    "VALUES('Curry', 'Stephen', 30)";
            // Add above SQL statement in the batch.
            stmt.addBatch(SQL);

            // Create one more SQL statement
            SQL = "INSERT INTO Employees (first, last, age) " +
                    "VALUES('Kobe', 'Bryant', 35)";
            // Add above SQL statement in the batch.
            stmt.addBatch(SQL);

            // Create one more SQL statement
            SQL = "UPDATE Employees SET age = 35 " +
                    "WHERE id = 2";
            // Add above SQL statement in the batch.
            stmt.addBatch(SQL);

            // Create an int[] to hold returned values
            int[] count = stmt.executeBatch();

            //Explicitly commit statements to apply changes
            conn.commit();

            // Again, let us select all the records and display them.
            printRows( stmt );

            // Clean-up environment
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    @Test
    public void testPrepareBatch() {

        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // Create SQL statement
            String SQL = "INSERT INTO Employees(first,last,age) " +
                    "VALUES(?, ?, ?)";

            // Create preparedStatemen
            System.out.println("Creating statement...");
            stmt = conn.prepareStatement(SQL);

            // Set auto-commit to false
            conn.setAutoCommit(false);

            // First, let us select all the records and display them.
            printRows( stmt );

            // Set the variables
            //stmt.setInt( 1, 400 );
            stmt.setString( 1, "Python" );
            stmt.setString( 2, "Zhang" );
            stmt.setInt( 3, 33 );
            // Add it to the batch
            stmt.addBatch();

            // Set the variables
            //stmt.setInt( 1, 401 );
            stmt.setString( 1, "C++" );
            stmt.setString( 2, "Huang" );
            stmt.setInt( 3, 31 );
            // Add it to the batch
            stmt.addBatch();

            // Create an int[] to hold returned values
            int[] count = stmt.executeBatch();

            //Explicitly commit statements to apply changes
            conn.commit();

            // Again, let us select all the records and display them.
            printRows( stmt );

            // Clean-up environment
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    @Test
    public void testXml2Db() {

        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //Create a Statement object and build table
            stmt = conn.createStatement();
            createXMLTable(stmt);

            //Open a FileInputStream
            File f = new File("xml_data.xml");
            long fileLength = f.length();
            FileInputStream fis = new FileInputStream(f);

            //Create PreparedStatement and stream data
            String SQL = "INSERT INTO XML_Data VALUES (?,?)";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,125);
            pstmt.setAsciiStream(2,fis,(int)fileLength);
            pstmt.execute();

            //Close input stream
            fis.close();

            // Do a query to get the row
            SQL = "SELECT Data FROM XML_Data WHERE id=125";
            rs = stmt.executeQuery (SQL);
            // Get the first row
            if (rs.next ()){
                //Retrieve data from input stream
                InputStream xmlInputStream = rs.getAsciiStream (1);
                int c;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                while (( c = xmlInputStream.read ()) != -1)
                    bos.write(c);
                //Print results
                System.out.println(bos.toString());
            }
            // Clean-up environment
            rs.close();
            stmt.close();
            pstmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(pstmt!=null)
                    pstmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
    private void createXMLTable(Statement stmt)  throws SQLException{
        System.out.println("Creating XML_Data table..." );
        //Drop table first if it exists.
        try{
            stmt.executeUpdate("DROP TABLE XML_Data");
            //Build table.
            //Create SQL Statement
            String streamingDataSql = "CREATE TABLE XML_Data (id INTEGER, Data LONG)";
            stmt.executeUpdate(streamingDataSql);
        }catch(SQLException se){
            se.printStackTrace();
        }// do nothing
    }

    @Test
    public void testSqlTime() {
        //Get standard date and time
        java.util.Date javaDate = new java.util.Date();
        long javaTime = javaDate.getTime();
        System.out.println("The Java Date is:" + javaDate.toString());

        //Get and display SQL DATE
        java.sql.Date sqlDate = new java.sql.Date(javaTime);
        System.out.println("The SQL DATE is: " + sqlDate.toString());

        //Get and display SQL TIME
        java.sql.Time sqlTime = new java.sql.Time(javaTime);
        System.out.println("The SQL TIME is: " + sqlTime.toString());
        //Get and display SQL TIMESTAMP
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(javaTime);
        System.out.println("The SQL TIMESTAMP is: " + sqlTimestamp.toString());
    }
}
