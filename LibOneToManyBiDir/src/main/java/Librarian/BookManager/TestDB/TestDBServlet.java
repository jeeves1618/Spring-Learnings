package Librarian.BookManager.TestDB;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //Set up Connection Variables
        String user="hbstudent";
        String password = "hbstudent";
        String jdbcURL = "jdbc:mysql://localhost:3306/lib_one_to_many_bi?useSSL=false&amp;serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        //Get connection to database.
        try{
            PrintWriter out = response.getWriter();
            log.info("Connecting to Database " + jdbcURL);
            Class.forName(driver);

            Connection myConnection = DriverManager.getConnection(jdbcURL,user,password);

            log.info("Connction successfull");

            myConnection.close();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        response.getWriter().append("Served at : ").append(request.getContextPath());
    }
}
