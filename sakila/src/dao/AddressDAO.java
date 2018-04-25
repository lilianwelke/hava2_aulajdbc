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
import model.Address;
import model.City;
import util.ConnectionJDBC;

public class AddressDAO {
    Connection connection;

    public AddressDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }

    public void save(Address address) throws Exception {
        String SQL = "INSERT INTO ADDRESS (ADDRESS, ADDRESS2, DISTRICT, CITY_ID, POSTAL_CODE, PHONE, LAST_UPDATE) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, address.getAddress());
            ps.setString(2, address.getAddress2());
            ps.setString(3, address.getDistrict());
            ps.setInt(4, address.getCity().getCity_id());
            ps.setString(5, address.getPostal_code());
            ps.setString(6, address.getPhone());
            ps.setTimestamp(7, new java.sql.Timestamp(new Date().getTime()));
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Address address) throws Exception {
        String SQL = "UPDATE ADDRESS SET ADDRESS = ?, ADDRESS2 = ?, CITY_ID = ?, POSTAL_CODE = ?, PHONE = ?, LAST_UPDATE = ? "
                + "WHERE ADDRESS_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, address.getAddress());
            ps.setString(2, address.getAddress2());            
            ps.setString(3, address.getDistrict());
            ps.setInt(4, address.getCity().getCity_id());
            ps.setString(5, address.getPostal_code());
            ps.setString(6, address.getPhone());
            ps.setTimestamp(7, new java.sql.Timestamp(new Date().getTime()));
            ps.setInt(8, address.getAddress_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Address address) throws Exception {
        String SQL = "DELETE FROM ADDRESS WHERE ADDRESS_ID = ?";  
        try {
            PreparedStatement ps = connection.prepareStatement(SQL); 
            ps.setInt(1, address.getAddress_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Address findById(int id) throws Exception {
        Address objeto = new Address();
        String SQL = "SELECT ADDRESS.*, CITY.CITY_ID, CITY.CITY "
                + "FROM ADDRESS "
                + "INNER JOIN CITY ON (ADDRESS.CITY_ID = CITY.CITY_ID) "
                + "WHERE ADDRESS.ADDRESS_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objeto = new Address();
                objeto.setAddress_id(rs.getInt("address_id"));
                objeto.setAddress(rs.getString("address"));
                objeto.setAddress2(rs.getString("address2"));
                objeto.setDistrict(rs.getString("district"));
                objeto.setPostal_code(rs.getString("postal_code"));
                objeto.setPhone(rs.getString("phone"));
                objeto.setLast_update(rs.getTimestamp("last_update"));
                
                City city = new City();
                city.setCity_id(rs.getInt("city_id"));
                city.setCity(rs.getString("city"));
                
                objeto.setCity(city);                
            }            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        return objeto;
    }

    public List<Address> findAll() throws Exception {
        List<Address> list = new ArrayList<>();
        Address objeto;
        String SQL = "SELECT ADDRESS.*, CITY.CITY_ID, CITY.CITY "
                + "FROM ADDRESS "
                + "INNER JOIN CITY ON (ADDRESS.CITY_ID = CITY.CITY_ID)";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objeto = new Address();
                objeto.setAddress_id(rs.getInt("address_id"));
                objeto.setAddress(rs.getString("address"));
                objeto.setAddress2(rs.getString("address2"));
                objeto.setDistrict(rs.getString("district"));
                objeto.setPostal_code(rs.getString("postal_code"));
                objeto.setPhone(rs.getString("phone"));
                objeto.setLast_update(rs.getTimestamp("last_update"));
                
                City city = new City();
                city.setCity_id(rs.getInt("city_id"));
                city.setCity(rs.getString("city"));
                
                objeto.setCity(city);  
                
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