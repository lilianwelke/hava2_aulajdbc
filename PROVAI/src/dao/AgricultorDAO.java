package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Agricultor;
import util.ConnectionJDBC;

public class AgricultorDAO {
    
    Connection connection;

    public AgricultorDAO() throws Exception {        
        
        connection = ConnectionJDBC.getConnection();
        
    } 
    
    public void save(Agricultor agricultor) throws Exception {
        String SQL = "INSERT INTO CADASTROAGRICULTOR (NOME, DATA_CADASTRO, MUNICIPIO, LOCALIDADE, PRODUTOS, STATUS, TELEFONE)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, agricultor.getNome());
            ps.setDate(2, new java.sql.Date (agricultor.getData().getTime()));            
            ps.setString(3, agricultor.getMunicipio());
            ps.setString(4, agricultor.getLocalidade());
            ps.setString(5, agricultor.getProdutos());
            ps.setInt(6, agricultor.getStatus());
            ps.setString(7, agricultor.getTelefone());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Agricultor agricultor) throws Exception { 
        String SQL = "UPDATE CADASTROAGRICULTOR SET NOME = ?, DATA_CADASTRO = ?, MUNICIPIO = ?, LOCALIDADE = ?, PRODUTOS = ?, "
                + "STATUS = ?, TELEFONE = ? WHERE ID_AGRICULTOR = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, agricultor.getNome());            
            ps.setDate(2, new java.sql.Date (agricultor.getData().getTime()));
            ps.setString(3, agricultor.getMunicipio());
            ps.setString(4, agricultor.getLocalidade());
            ps.setString(5, agricultor.getProdutos());
            ps.setInt(6, agricultor.getStatus());
            ps.setString(7, agricultor.getTelefone());
            ps.setInt(8, agricultor.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Agricultor agricultor) throws Exception { 
        String SQL = "DELETE FROM CADASTROAGRICULTOR WHERE ID_AGRICULTOR = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, agricultor.getId());            
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public Agricultor findById(int id) {
        return new Agricultor();
    }
    
    public List<Agricultor> findAll() throws Exception { 
        List<Agricultor> list = new ArrayList<>();
        Agricultor objeto;
        String SQL = "SELECT * FROM CADASTROAGRICULTOR";        
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                objeto = new Agricultor();
                objeto.setId(rs.getInt("ID_AGRICULTOR"));
                objeto.setNome(rs.getString("NOME"));
                objeto.setData(rs.getDate("DATA_CADASTRO"));
                objeto.setMunicipio(rs.getString("MUNICIPIO"));
                objeto.setLocalidade(rs.getString("LOCALIDADE"));
                objeto.setProdutos(rs.getString("PRODUTOS"));                
                objeto.setStatus(rs.getInt("STATUS"));
                objeto.setTelefone(rs.getString("TELEFONE"));
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
