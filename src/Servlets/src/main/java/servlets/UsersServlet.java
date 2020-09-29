package servlets;

import lombok.*;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "1815144981Misha!";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    private List<User> users;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("UsersForJSP", users);
        request.getRequestDispatcher("WEB-INF/JSP/users.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        this.users = new ArrayList<User>();
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Class.forName("org.postgresql.Driver");
        Statement statement = connection.createStatement();

        //Language=SQL
        ResultSet result = statement.executeQuery("select * from first_servlet_db");

        while (result.next()) {
            users.add(User.builder().
                    id(result.getLong("id")).
                    firstName(result.getString("firstName")).
                    lastName(result.getString("lastName")).
                    age(result.getInt("age")).
                    build());
        }
    }
}