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
import model.Country;
import util.ConnectionJDBC;

public class CountryDAO {
    Connection connection;

    public CountryDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }

    public void save(Country country) throws Exception {
        String SQL = "INSERT INTO COUNTRY (COUNTRY, LAST_UPDATE) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, country.getCountry());
            ps.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Country country) throws Exception {
        String SQL = "UPDATE COUNTRY SET COUNTRY = ?, LAST_UPDATE = ?  WHERE COUNTRY_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, country.getCountry());
            ps.setTimestamp(2, new java.sql.Timestamp (new Date().getTime()));
            ps.setInt(3, country.getCountry_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Country country) throws Exception {
        String SQL = "DELETE FROM COUNTRY WHERE COUNTRY_ID = ?";  
        try {
            PreparedStatement ps = connection.prepareStatement(SQL); 
            ps.setInt(1, country.getCountry_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Country findById(int id) {
        return new Country();
    }

    public List<Country> findAll() throws Exception {
        List<Country> list = new ArrayList<>();
        Country objeto;
        String SQL = "SELECT * FROM COUNTRY";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objeto = new Country();
                objeto.setCountry_id(rs.getInt("country_id"));
                objeto.setCountry(rs.getString("country"));
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