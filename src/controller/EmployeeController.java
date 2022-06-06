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


/**
 *
 * @author jocpa
 */
public class EmployeeController {
    
    public static boolean authenticarUsuario(String email , String password){
        try {
            if("3".equals(getStateAuthentication(email,password))){ 
                // Estableciendo conexión
                Connection conexion = Conexion.getConnection();
                String query="UPDATE employee set " +
                            "is_authenticate = 'Y' " +
                            "where " +
                            "email_employee = ? and " +
                            "password_employee = ? and " +
                            "id_role = '3' ";
                PreparedStatement auth = conexion.prepareStatement(query);
                auth.setString(1, email);
                auth.setString(2, password);
                //Ejecutamos y retornamos el resultado de la query
                ResultSet rs = auth.executeQuery();
                System.out.println("Autenticación -> "+ rs.next());
                return true;
            }else{
                System.err.println("El usuario no cuenta con los permisos ncesarios");
                return false;
            }
            } catch (SQLException e) {
            System.err.println("Ha ocurrido un error al authenticar : " + e);
            
        }
        return false;
    }
    
    public static String getStateAuthentication(String email , String password){
        try{
            Connection conexion = Conexion.getConnection();
            String query="SELECT id_role " +
                        " FROM employee " +
                        " where email_employee = ? and " +
                        " password_employee = ? ";
            PreparedStatement result = conexion.prepareStatement(query);
            result.setString(1, email);
            result.setString(2, password);
            ResultSet rs = result.executeQuery();
            rs.next();
            System.out.println("El usuario tiene el rol : " + rs.getString("id_role"));
        return rs.getString("id_role"); 
        }catch(Exception e){
            System.err.println("Ha ocurrido un al consultar el rol : " + e);
        } 
        
        return null;
    }
    
   
    
    public void disconectUser(String email, String password) throws SQLException{
        Connection conexion = Conexion.getConnection();
        String query="UPDATE employee set " +
                    "is_authenticate = 'N' " +
                    "where \n" +
                    "email_employee = ? and " +
                    "password_employee = . and " +
                    "id_role = '1' ";
        PreparedStatement result = conexion.prepareStatement(query);
        result.setString(1, email);
        result.setString(2, password);
        ResultSet rs = result.executeQuery(); 
        if(rs.next()){
            System.out.println("Se ha desconectado con éxito el usuario : " + email);
        }else{
            System.err.println("No se ha podido desconectar al usuario : " + email);
        }
    }
    
//    public static void main(String[] args) {
//        authenticarUsuario("st.rogger@ba.com", "employee");
//    }
   
}
