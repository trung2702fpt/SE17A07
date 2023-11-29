package Controller;

import DataAsset.EquipmentDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.typeEquipment;

public class Equipment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        ObjectMapper objectMapper = new JsonMapper();
        String objectJSON = "";
        EquipmentDAO eqDao = new EquipmentDAO();
        try {
            switch (action) {
                case "search":
                    int idE = Integer.parseInt(request.getParameter("idEquipment"));
                    objectJSON = objectMapper.writeValueAsString(eqDao.read(idE));
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    int price = Integer.parseInt(request.getParameter("price"));
                    int type = Integer.parseInt(request.getParameter("type"));
                    String des = request.getParameter("des");
                    String name = request.getParameter("name");
                    model.Equipment newEquipment = new model.Equipment(id, name, des, price, new typeEquipment(type));
                    boolean isSuccess = eqDao.update(id, newEquipment);
                    if (!isSuccess) {
                        objectJSON = objectMapper.writeValueAsString("fail");
                    }
                    break;
                case "getList":
                    List<model.Equipment> equipments = eqDao.getList();
                    objectJSON = objectMapper.writeValueAsString(equipments);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
          response.getWriter().write(objectJSON);  
        }
    }
<<<<<<< Updated upstream
=======

    private void getList(HttpServletRequest request, HttpServletResponse response, EquipmentDAO eqDao) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String admin = request.getParameter("admin");
        String select = request.getParameter("select");
        String forSearch = "<tr class=\"candidates-list\">\n";
        PrintWriter out = response.getWriter();

        List<model.Equipment> equipments = eqDao.getList();
        if (!equipments.isEmpty()) {
            for (model.Equipment equipment : equipments) {
                if (select != null && select.equals("true")) {
                    out.println("<tr class=\"candidates-list\" id="+equipment.id+" onclick='selectEquiment(this)'  style=\"cursor: pointer\">\n"
                            + "                                            <td>" + equipment.id + "</td>\n"
                            + "                                            <td>" + equipment.name + "</td>\n"
                            + "                                            <td>" + equipment.des + "</td>\n"
                            + "                                            <td>" + equipment.price + "</td>\n"
                            + "                                            <td>" + equipment.type.name + "</td>\n"
                            + " </tr>");
                } else {
                    if (admin.equals("admin")) {
                        forSearch = "<tr class=\"candidates-list\" onclick='SearchForEdit(" + equipment.id + ")'>\n";
                    }
                    out.println(forSearch
                            + "                                            <td>" + equipment.id + "</td>\n"
                            + "                                            <td class=\"title\">\n"
                            + "                                                <div class=\"thumb\">\n"
                            + "                                                    <img class=\"img-fluid w-50\" src=\"./asset/images/equipment/" + equipment.type.name + ".png\" alt=\"\">\n"
                            + "                                                </div>\n"
                            + "                                            </td>\n"
                            + "                                            <td>" + equipment.name + "</td>\n"
                            + "                                            <td>" + equipment.des + "</td>\n"
                            + "                                            <td>" + equipment.price + "</td>\n"
                            + "                                            <td>" + equipment.type.name + "</td>\n"
                            + "                                        </tr>");
                }
            }
        } else {
            out.println("<tr>"
                    + "<td colspan=\"6\"><h2 class='text-center'>EMPTY EQUIPMETN!!</h2></td>"
                    + "</tr>");
        }
    }

>>>>>>> Stashed changes
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
    }
}
