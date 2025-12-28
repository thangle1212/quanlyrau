/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controller;

import Model.Rau;
import DAO.RauDAO;
import java.util.List;

public class RauController {
    private RauDAO rauDAO;
    public RauController(){
        rauDAO =new RauDAO();
    }
    //lấy tất cả rau cho view 
    public List<Rau> getDanhSachRau(){
        return rauDAO.getAllRau();
    }
    //lấy rau theo id(khi mua hàng)
    public Rau getRauid(String rauid){
        return rauDAO.getRauById(rauid);
    }
}
