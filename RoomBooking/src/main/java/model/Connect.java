/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DataAsset.EquipmentDAO;
import DataAsset.RoomDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 *
 * @author Asus-FPT
 */
public class Connect {

    public static void main(String[] args) throws ClassNotFoundException {
        EquipmentDAO rooom = new EquipmentDAO();
        for (Equipment GetRoom : rooom.GetEquipments()) {
            System.out.println(GetRoom.toString());
        }
    }

}
