package Controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import model.google.GooglePojo;
import model.google.GoogleUtils;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code").trim();
        User user = null;
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            String emailUser = googlePojo.getEmail();
            if (!emailUser.split("@")[1].equals("fpt.edu.vn")) {
                request.setAttribute("ErrorMessage", "Your email is not email FPT");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                //String idUser = googlePojo.getId();
                String picture = googlePojo.getPicture();
                String nameUser = matcherSplit(emailUser.split("@")[0], "([a-z])");
                String idStudent = matcherSplit(emailUser.split("@")[0], "([a-z]{2})([0-9]{6})");
                user = new User(emailUser, nameUser.toUpperCase(), 1, idStudent,picture);

                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
        }
    }
    
    private String matcherSplit(String base, String patternCode){
        String result = "";
        Pattern pattern = Pattern.compile(patternCode);
        Matcher matcher = pattern.matcher(base);
        if(matcher.find()){
            if(matcher.groupCount()>1){
                result = matcher.group(1) + matcher.group(2);
            }else{
                result = matcher.group(1);
            }
        }
        return result;
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
