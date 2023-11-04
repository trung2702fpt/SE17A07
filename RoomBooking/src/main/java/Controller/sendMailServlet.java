package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Report;

public class sendMailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sendMailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sendMailServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String userId = ((model.User) session.getAttribute("user")).getIdStudent();
        String time = LocalDate.now().toString();
        String title = request.getParameter("title");
        String message = request.getParameter("message");
        String email = request.getParameter("email");
        String error = "";
        if (title != null && message != null && email != null) {
            // Các trường đều không null
            error += "All field not null. ";
            // Kiểm tra định dạng email
            if (Utils.Validate.validateEmail(email)) {
                // Email hợp lệ
                // Thực hiện xử lý tiếp theo (ví dụ: lưu thông tin vào cơ sở dữ liệu)
            } else {
                // Email không hợp lệ
                error+="Email not valid. ";
                // Xử lý lỗi hoặc hiển thị thông báo lỗi
            }
        } else {
            // Có ít nhất một trường là null
            error+="One field is null. ";
            // Xử lý lỗi hoặc hiển thị thông báo lỗi
        }

        PrintWriter printWriter = response.getWriter();

        try {
            if (Utils.Validate.validateEmail(email)) {
                Report report = new Report(Integer.parseInt("2"), time, title, message, false, ""); // Sử dụng giá trị boolean thay vì chuỗi "Status"
                DataAsset.ReportDAO.insertReport(report);
            } else {
                printWriter.print("Email wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("error", error);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
