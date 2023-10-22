/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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
import model.Room;

public class GetRooms extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RoomDAO roomDao = new RoomDAO();
        List<Room> Listrooms = roomDao.GetRooms();
        if (Listrooms.size() > 0) {
            for (Room room : Listrooms) {
                out.println("<tr class=\"candidates-list\">\n"
                        + " <td>" + room.id + "</td>\n"
                        + " <td class=\"title\">\n"
                        + "      <div class=\"thumb\">\n"
                        + "           <img class=\"img-fluid w-25\" src=\"inlcude/asset/images/gallery1.jpg\" alt=\"\">\n"
                        + "       </div>\n"
                        + " </td>\n"
                        + " <td>" + room.roomNumber + "</td>\n"
                        + " <td>" + room.price + "</td>\n"
                        + "</tr>");
            }
        } else {
            out.println("<tr>"
                    + "<td colspan=\"4\"><h2 class='text-center'>EMPTY ROOM!!</h2></td>"
                    + "</tr>");

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetRooms.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetRooms.class.getName()).log(Level.SEVERE, null, ex);
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
