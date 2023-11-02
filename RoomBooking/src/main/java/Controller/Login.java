package Controller;

import DataAsset.UserDAO;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.google.GooglePojo;
import model.google.GoogleUtils;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "login.jsp";
        String code = request.getParameter("code").trim();
        UserDAO uDao = new UserDAO();
        HttpSession session = request.getSession();
        String accessToken = GoogleUtils.getToken(code);
        GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
        try {
            if (code == null || code.isEmpty()) {
                throw new Exception();
            } else {
                String emailUser = googlePojo.getEmail();
                if (!emailUser.split("@")[1].equals("fpt.edu.vn")) {
                    request.setAttribute("ErrorMessage", "Your email is not email FPT");
                    url = "login.jsp";
                } else {
                    //String idUser = googlePojo.getId();
                    String picture = googlePojo.getPicture();
                    String idStudent = matcherSplit(emailUser.split("@")[0], "([a-z]{2})([0-9]{6})");
                    String nameUser = emailUser.split("@")[0].replace(idStudent, "");
                    User user = new User(nameUser, emailUser, 1, idStudent, picture);
                    User userLogin = uDao.InsertUser(user);
                    session.setAttribute("ACCOUNT_USER", userLogin);
                    session.setAttribute("user", user);
                    url = "home.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            url = "login.jsp";
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String matcherSplit(String base, String patternCode) {
        String result = "";
        Pattern pattern = Pattern.compile(patternCode);
        Matcher matcher = pattern.matcher(base);
        if (matcher.find()) {
            result = matcher.group(1) + matcher.group(2);
        }
        return result;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
