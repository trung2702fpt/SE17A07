package Controller;

import DataAsset.RoomDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Room extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        ObjectMapper objectMapper = new JsonMapper();
        String objectJSON = "";
        int idRoom = 0;
        RoomDAO roomdao = new RoomDAO();
        try {
            switch (action) {
                case "search":
                    idRoom = Integer.parseInt(request.getParameter("idRoom"));
                    objectJSON = objectMapper.writeValueAsString(roomdao.read(idRoom));
                    response.getWriter().write(objectJSON);
                    break;
                case "edit":
                    idRoom = Integer.parseInt(request.getParameter("idRoom"));
                    int price = Integer.parseInt(request.getParameter("price"));
                    model.Room newRoom = new model.Room(idRoom, price);
                    boolean isSuccess = roomdao.update(idRoom, newRoom);
                    if (!isSuccess) {
                        objectJSON = objectMapper.writeValueAsString("fail");
                    }
                    response.getWriter().write(objectJSON);
                    break;
                case "getList":
                    getList(response, roomdao);
                    break;
                case "filterRoom":
                    filRooms(request, response, roomdao);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void filRooms(HttpServletRequest request, HttpServletResponse response, RoomDAO roomdao) throws IOException, ClassNotFoundException, Exception {
        String dateRequest = request.getParameter("dateSelect");
        String slotRequest = request.getParameter("slotSelect");
        int slotSelected = Integer.parseInt(slotRequest);
        RoomDAO db = new RoomDAO();
        try {
            List<model.Room> rooms = db.searchByDateAndSlOT(slotSelected, dateRequest);
            PrintWriter out = response.getWriter();
            int index = 1;
            for (model.Room o : rooms) {
                out.println("<tr class=\"candidates-list\">\n"
                + "                                    <td>" + index++ + "</td>\n"
                + "                                    <td class=\"title\">\n"
                + "                                        <div class=\"thumb\">\n"
                + "                                            <img class=\"img-fluid w-25\" src=\"./asset/images/gallery1.jpg\" alt=\"\">\n"
                + "                                        </div>\n"
                + "                                    </td>\n"
                + "                                    <td>" + o.getRoomNumber() + "</td>\n"
                + "                                    <td>" + o.getPrice() + "</td>\n"
                + "                                    <td>\n"
                + "                                        <a type=\"button\" href='#' onclick='openDialog(" + o.getId() + ","+o.getPrice()+")' class=\"btn btn-primary\" data-toggle=\"modal\"\n"
                + "            data-target=\".bd-example-modal-lg\">Booking</a>\n"
                + "                                    </td>\n"
                + "                                </tr>");
            };
        } catch (IOException e) {
            throw new Exception();
        }
    }
    
    private void getList(HttpServletResponse response, RoomDAO roomdao) throws IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<model.Room> Listrooms = roomdao.getList();

        if (Listrooms.size() <= 0) {
            out.println("<tr>"
                    + "<td colspan=\"4\"><h2 class='text-center'>EMPTY ROOM!!</h2></td>"
                    + "</tr>");
            return;
        }

        for (model.Room room : Listrooms) {
            out.println("<tr class=\"candidates-list\">\n"
                    + " <td>" + room.id + "</td>\n"
                    + " <td class=\"title\">\n"
                    + "      <div class=\"thumb\">\n"
                    + "           <img class=\"img-fluid w-25\" src=\"./asset/images/gallery1.jpg\" alt=\"\">\n"
                    + "       </div>\n"
                    + " </td>\n"
                    + " <td>" + room.roomNumber + "</td>\n"
                    + " <td>" + room.price + "</td>\n"
                    + "</tr>");
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
