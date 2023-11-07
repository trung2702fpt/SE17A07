package Controller;

import DataAsset.EquipmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Equipment;

public class GetEquipments extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String admin = request.getParameter("admin");
        String forSearch = "<tr class=\"candidates-list\">\n";
        PrintWriter out = response.getWriter();
        EquipmentDAO eqDao = new EquipmentDAO();
        List<Equipment> equipments = eqDao.getList();
        if (!equipments.isEmpty()) {
            for (Equipment equipment : equipments) {
                if(admin.equals("admin")){
                    forSearch = "<tr class=\"candidates-list\" onclick='SearchForEdit("+ equipment.id +")'>\n";
                }
                out.println(forSearch
                        + "                                            <td>" + equipment.id + "</td>\n"
                        + "                                            <td class=\"title\">\n"
                        + "                                                <div class=\"thumb\">\n"
                        + "                                                    <img class=\"img-fluid w-50\" src=\"inlcude/asset/images/equipment/" + equipment.type.name + ".png\" alt=\"\">\n"
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetEquipments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetEquipments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
