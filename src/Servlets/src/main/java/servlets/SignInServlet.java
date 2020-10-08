package servlets;

import lombok.SneakyThrows;
import models.User;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UsersRepository usersRepository;

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "1815144981Misha!";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie cookie = new Cookie("signCookie", UUID.randomUUID().toString());
//        response.addCookie(cookie);
        request.getRequestDispatcher("WEB-INF/jsp/signIn.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection);
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Class.forName("org.postgresql.Driver");

        String username = request.getParameter("name_input");
        String password = request.getParameter("password_input");
        Integer age = Integer.valueOf(request.getParameter("age_input"));

        if (usersRepository.isUserExist(username, password) && !usersRepository.ifUserHasUuid(username, password)) {
            String uuid = UUID.randomUUID().toString();
            response.addCookie(new Cookie("sign", uuid));
            usersRepository.saveUUIDtoExistingUser(username, password, uuid);
        }
        else {
            String usersUiid = usersRepository.getUsersUuid(username, password);
            if (usersUiid != null)
                response.addCookie(new Cookie("sign", usersUiid));
        }

//        response.sendRedirect("/signIn");
    }
}
