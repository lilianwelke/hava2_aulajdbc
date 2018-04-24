package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Category;
import util.ConnectionJDBC;

public class CategoryDAO {
    Connection connection;

    public CategoryDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }

    public void save(Category category) throws Exception {
        String SQL = "INSERT INTO CATEGORY (NAME, LAST_UPDATE) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, category.getName());
            ps.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Category category) throws Exception {
        String SQL = "UPDATE CATEGORY SET NAME = ?, LAST_UPDATE = ?  WHERE CATEGORY_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, category.getName());
            ps.setTimestamp(2, new java.sql.Timestamp (new Date().getTime()));
            ps.setInt(3, category.getCategory_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Category category) throws Exception {
        String SQL = "DELETE FROM CATEGORY WHERE CATEGORY_ID = ?";  
        try {
            PreparedStatement ps = connection.prepareStatement(SQL); 
            ps.setInt(1, category.getCategory_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Category findById(int id) {
        return new Category();
    }

    public List<Category> findAll() throws Exception {
        List<Category> list = new ArrayList<>();
        Category objeto;
        String SQL = "SELECT * FROM CATEGORY";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objeto = new Category();
                objeto.setCategory_id(rs.getInt("category_id"));
                objeto.setName(rs.getString("name"));
                objeto.setLast_update(rs.getTimestamp("last_update"));
                list.add(objeto);                
            }            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return list;
    }    
}