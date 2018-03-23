package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Editora;
import util.ConnectionJDBC;

public class EditoraDAO {
    
    Connection connection;
    
    public EditoraDAO() throws Exception { 
        
        connection = ConnectionJDBC.getConnection();
    }
    
    public void save(Editora editora) throws Exception { 
        String SQL = "INSERT INTO EDITORA (NOME, MUNICIPIO) VALUES (?, ?)";        
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, editora.getNome());
            ps.setString(2, editora.getMunicipio());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Editora editora) throws Exception { 
        String SQL = "UPDATE EDITORA SET NOME = ?, MUNICIPIO = ? WHERE EDITORA_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, editora.getNome());
            ps.setString(2, editora.getMunicipio());
            ps.setInt(3, editora.getEditora_id());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Editora editora) throws Exception { 
        String SQL = "DELETE FROM EDITORA WHERE EDITORA_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, editora.getEditora_id());            
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public Editora findById(int id) {
        return new Editora();
    }
    
    public List<Editora> findAll() throws Exception { 
        List<Editora> list = new ArrayList<>();
        Editora objeto;
        String SQL = "SELECT * FROM EDITORA";        
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                objeto = new Editora();
                objeto.setEditora_id(rs.getInt("editora_id"));
                objeto.setNome(rs.getString("nome"));
                objeto.setMunicipio(rs.getString("municipio"));
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
