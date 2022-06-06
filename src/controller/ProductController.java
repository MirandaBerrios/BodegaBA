/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import object.Product;
import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * @author jocpa
 */
public  class ProductController {
    
    public static ArrayList<Product> getAllProductByQuery(){
        try {
            Connection conexion = Conexion.getConnection();
            String query="Select * from product";
            PreparedStatement bus = conexion.prepareStatement(query);
            ResultSet rs = bus.executeQuery();
            ArrayList<Product> productList = new ArrayList<Product>();
            
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getString("ID"));
                product.setName(rs.getString("NAME"));
                product.setValue(rs.getString("VALUE"));
                product.setMark(rs.getString("MARK"));
                product.setIsAvailable(rs.getString("IS_AVAILABLE"));
                product.setIsOfert(rs.getString("IS_OFERT"));
                product.setModel(rs.getString("MODEL"));
                product.setCategory(rs.getString("CATEGORY"));
                product.setDescription(rs.getString("DESCRIPTION"));
                productList.add(product);
                
                
            }
            return productList; 
        } catch (Exception e) {
        }
        
        return null; 
   }
    
    
    public static void main(String[] args) {
        getAllProductByQuery(); 
        
    }
}