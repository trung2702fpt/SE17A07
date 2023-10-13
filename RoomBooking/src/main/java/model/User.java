/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thong
 */
public class User {
    public String id;
    public String email;
    public String name;
    public int roleid;
    public String idStudent;
    public String image;

    public User() {
    }

    public User(String email, String name, int roleid, String idStudent, String image) {
        this.email = email;
        this.name = name;
        this.roleid = roleid;
        this.idStudent = idStudent;
        this.image = image;
    }
    
    public User(String id, String email, String name, int roleid, String idStudent, String image) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.roleid = roleid;
        this.idStudent = idStudent;
        this.image = image;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getIsStudent() {
        return idStudent;
    }

    public void setIsStudent(String isStudent) {
        this.idStudent = isStudent;
    }
    
}
