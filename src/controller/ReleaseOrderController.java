/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import object.ReleaseOrder;
import object.ReleaseOrderReport;

/**
 *
 * @author jocpa
 */
public class ReleaseOrderController {
    
    public static ArrayList<ReleaseOrderReport> getAllReleaseOrderReportByQuery(){
        try {
            Connection conexion = Conexion.getConnection();
            String query ="SELECT * from release_order";
            String query1 = "SELECT " +
                            "ro.id_release_order as \"idReleaseOrder\"," +
                            "ro.id_invoice as \"idInvoice\"," +
                            "cu.name_customer ||' '|| cu.lastname_customer as \"fullNameCustomer\"," +
                            "pr.name as \"nameProduct\"," +
                            "sro.state as \"stateReleaseOrder\" " +
                            "FROM RELEASE_ORDER ro " +
                            "JOIN CUSTOMER cu on cu.id_customer = ro.id_customer " +
                            "JOIN PRODUCT pr on pr.id = ro.id_product " +
                            "JOIN STATE_RELEASE_ORDER sro on sro.id_state_release_order = ro.id_state"; 
            
            PreparedStatement bus = conexion.prepareStatement(query1);
            ResultSet rs = bus.executeQuery();
            
            ArrayList<ReleaseOrderReport> releaseOrderReportList = new ArrayList<>();
            while(rs.next()){ 
                ReleaseOrderReport releaseOrderReport = new ReleaseOrderReport();
                
                releaseOrderReport.setIdReleaseOrder(rs.getString("idReleaseOrder"));
                releaseOrderReport.setIdInvoice(rs.getString("idInvoice"));
                releaseOrderReport.setFullNameCustomer(rs.getString("fullNameCustomer"));
                releaseOrderReport.setNameProduct(rs.getString("nameProduct"));
                releaseOrderReport.setStateReleaseOrder(rs.getString("stateReleaseOrder"));
                
                releaseOrderReportList.add(releaseOrderReport);
                System.out.println(releaseOrderReport);
             
            }
            
            return releaseOrderReportList; 
        } catch (SQLException e) {
            System.err.println("Ha ocurrido un error" + e);
        }
        
        return null; 
   }
    
    
}
