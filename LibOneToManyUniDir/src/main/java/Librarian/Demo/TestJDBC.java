package Librarian.Demo;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class TestJDBC {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/lib_one_to_many_uni?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String password = "hbstudent";
        try{
            log.info("Connecting to DB " + jdbcURL);
            Connection connection = DriverManager.getConnection(jdbcURL, user, password);
            log.info("Connection Successful");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
