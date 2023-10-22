package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DataAsset.EquipmentDAO;
import DataAsset.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Equipment;

/**
 *
 * @author thong
 */
public class GetEquipments extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "listEquipments.jsp";
        HttpSession session = request.getSession();
        EquipmentDAO eqDao = new EquipmentDAO();
        List<Equipment> equipments = eqDao.GetEquipments();
        if (equipments.size() > 0) {
            for (Equipment equipment : equipments) {
                out.println("<tr class=\"candidates-list\">\n"
                        + "                                            <td>" + equipment.id + "</td>\n"
                        + "                                            <td class=\"title\">\n"
                        + "                                                <div class=\"thumb\">\n"
                        + "                                                    <img class=\"img-fluid w-25\" src=\"inlcude/asset/images/equipment/" + equipment.type.name + ".png\" alt=\"\">\n"
                        + "                                                </div>\n"
                        + "                                            </td>\n"
                        + "                                            <td>" + equipment.name + "</td>\n"
                        + "                                            <td>" + equipment.des + "</td>\n"
                        + "                                            <td>" + equipment.price + "</td>\n"
                        + "                                            <td>" + equipment.type.name + "</td>\n"
                        + "                                        </tr>");
            }
        } else {
            out.println("<tr>"
                    + "<td colspan=\"6\"><h2 class='text-center'>EMPTY EQUIPMETN!!</h2></td>"
                    + "</tr>");
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetEquipments.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetEquipments.class.getName()).log(Level.SEVERE, null, ex);
        }
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
