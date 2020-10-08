package servlets;

import lombok.*;
import models.User;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "1815144981Misha!";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

//    private List<User> users;
    private UsersRepository usersRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List users = usersRepository.findAll();
        request.setAttribute("UsersForJSP", users);
        request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
    }

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        try {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new IllegalStateException();
        }

//        this.users = new ArrayList<User>();
//        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//        Class.forName("org.postgresql.Driver");
//        Statement statement = connection.createStatement();
//
//        //Language=SQL
//        ResultSet result = statement.executeQuery("select * from first_servlet_db");
//
//        while (result.next()) {
//            users.add(User.builder().
//                    id(result.getLong("id")).
//                    name(result.getString("name")).
//                    password(result.getString("password")).
//                    age(result.getInt("age")).
//                    build());
//        }

    }
}