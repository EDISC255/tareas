/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author eduardo
 */
public class Conexion {
    private Connection co;
    private ResultSet rs;
    private Statement st;
    private PreparedStatement ps;
    
    public Conexion() {
          try {
            File db=new File("tareas.db");
            Class.forName("org.sqlite.JDBC");
            co=DriverManager.getConnection("jdbc:sqlite:"+db.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getCo() {
        return co;
    }

    
    public void setRS(Connection co, String sql){
        try {
            st=co.createStatement();
            rs=st.executeQuery(sql);
        } catch (Exception e) {
        }
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setPs(Connection co,String sql) {
        try{
            ps=co.prepareStatement(sql);
        }catch(Exception e){
            
        }
    }

    public PreparedStatement getPs() {
        return ps;
    }
    
    
    
}
