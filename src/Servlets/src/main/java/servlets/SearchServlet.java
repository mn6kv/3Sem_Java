package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import connection.ProjectConnector;
import models.NameRefactorer;
import models.User;
import repositories.UsersRepository;
import repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private UsersRepository usersRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepositoryJdbcImpl(ProjectConnector.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/searchUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        NameRefactorer name = objectMapper.readValue(req.getReader(), NameRefactorer.class);
        List<User> allUsers = usersRepository.findAll();
        List<User> usersForName = searchByString(name.getName(), allUsers);

//        for (User i : usersForName) {
//            System.out.println("h");
//            System.out.println(i.getName());
//        }

        String usersAsJson = objectMapper.writeValueAsString(usersForName);
        resp.setContentType("application/json");
        resp.getWriter().println(usersAsJson);
    }

    private static List<User> searchByString(String subString, List<User> users) {
        List<User> out = new ArrayList<>();
        for (User user : users)
//            if (user.getName().contains(subString))
            if (startStringEquals(user.getName(), subString))
                out.add(user);
            return out;
    }

    private static boolean startStringEquals(String name, String substring) {
        if (name.length() < substring.length())
             return false;
        for (int i = 0; i < substring.length(); i++)
            if (name.charAt(i) != substring.charAt(i))
                return false;
        return true;
    }
}
