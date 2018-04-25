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
import model.City;
import model.Country;
import util.ConnectionJDBC;

public class CityDAO {
    Connection connection;

    public CityDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }

    public void save(City city) throws Exception {
        String SQL = "INSERT INTO CITY (CITY, COUNTRY_ID, LAST_UPDATE) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, city.getCity());
            ps.setInt(2, city.getCountry().getCountry_id());
            ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(City city) throws Exception {
        String SQL = "UPDATE CITY SET CITY = ?, COUNTRY_ID = ?, LAST_UPDATE = ?  WHERE CITY_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, city.getCity());
            ps.setInt(2, city.getCountry().getCountry_id());
            ps.setTimestamp(3, new java.sql.Timestamp (new Date().getTime()));
            ps.setInt(4, city.getCity_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(City city) throws Exception {
        String SQL = "DELETE FROM CITY WHERE CITY_ID = ?";  
        try {
            PreparedStatement ps = connection.prepareStatement(SQL); 
            ps.setInt(1, city.getCity_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public City findById(int id) throws Exception {
        City objeto = new City();
        String SQL = "SELECT CITY.*, COUNTRY.COUNTRY_ID, COUNTRY.COUNTRY "
                + "FROM CITY "
                + "INNER JOIN COUNTRY ON (CITY.COUNTRY_ID = COUNTRY.COUNTRY_ID) "
                + "WHERE CITY.CITY_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objeto = new City();
                objeto.setCity_id(rs.getInt("city_id"));
                objeto.setCity(rs.getString("city"));
                objeto.setLast_update(rs.getTimestamp("last_update"));
                
                Country country = new Country();
                country.setCountry_id(rs.getInt("country_id"));
                country.setCountry(rs.getString("country"));
                
                objeto.setCountry(country);                
            }            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return objeto;
    }

    public List<City> findAll() throws Exception {
        List<City> list = new ArrayList<>();
        City objeto;
        String SQL = "SELECT CITY.*, COUNTRY.COUNTRY_ID, COUNTRY.COUNTRY "
                + "FROM CITY "
                + "INNER JOIN COUNTRY ON (CITY.COUNTRY_ID = COUNTRY.COUNTRY_ID)";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objeto = new City();
                objeto.setCity_id(rs.getInt("city_id"));
                objeto.setCity(rs.getString("city"));
                objeto.setLast_update(rs.getTimestamp("last_update"));
                
                Country country = new Country();
                country.setCountry_id(rs.getInt("country_id"));
                country.setCountry(rs.getString("country"));
                
                objeto.setCountry(country);   
                
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