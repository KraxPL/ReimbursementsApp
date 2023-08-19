package pl.krax.web;

import pl.krax.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static Map<String, User> userDatabase = new HashMap<>();
    static {
        User adminUser = new User(1, "Admin", "User", "admin@mail.com", "admin123", true, true);
        User regularUser = new User(2, "Regular", "User", "user@mail.com", "user123", false, true);
        userDatabase.put(adminUser.getEmail(), adminUser);
        userDatabase.put(regularUser.getEmail(), regularUser);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/loginForm.jsp")
                .forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDatabase.get(email);

        if (user != null && user.getPassword().equals(password) && user.isEnable()) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/dashboard");
        } else {
            response.sendRedirect("/login?error=1");
        }
    }
}

