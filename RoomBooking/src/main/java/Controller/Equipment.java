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
                    model.Equipment Equipment = new model.Equipment(id, name, des, price, new typeEquipment(type));
                    boolean isSuccess = eqDao.update(id, Equipment);
                    if (!isSuccess) {
                        objectJSON = objectMapper.writeValueAsString("fail");
                    }
                    break;
                case "getList":
                    List<model.Equipment> equipments = eqDao.getList();
                    objectJSON = objectMapper.writeValueAsString(equipments);
                    break;
                case "addNew":
                    int newPrice = Integer.parseInt(request.getParameter("price"));
                    int newType = Integer.parseInt(request.getParameter("type"));
                    String newDes = request.getParameter("des");
                    String newName = request.getParameter("name");
                    model.Equipment newEquipment = new model.Equipment(0, newName, newDes, newPrice, new typeEquipment(newType));
                    eqDao.create(newEquipment);
                    break;
            }
        } catch (Exception e) {
            objectJSON = objectMapper.writeValueAsString("fail");
            e.printStackTrace();
        }finally{
          response.getWriter().write(objectJSON);  
        }
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
    }
}
