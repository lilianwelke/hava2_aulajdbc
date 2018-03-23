package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Estudante;
import util.ConnectionJDBC;

public class EstudanteDAO {
    
    Connection connection;
    
    public EstudanteDAO() throws Exception { 
        
        connection = ConnectionJDBC.getConnection();
        
    }
    
    public void save(Estudante estudante) throws Exception { 
        String SQL = "INSERT INTO ESTUDANTE (NOME, CURSO, DATA_MATRICULA, STATUS) VALUES (?, ?, ?, ?)";        
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, estudante.getNome());
            ps.setString(2, estudante.getCurso());
            ps.setDate(3, new java.sql.Date (estudante.getData().getTime()));
            ps.setString(4, estudante.getStatus());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void update(Estudante estudante) throws Exception { 
        String SQL = "UPDATE ESTUDANTE SET NOME = ?, CURSO = ?, DATA_MATRICULA = ?, STATUS = ? WHERE ESTUDANTE_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, estudante.getNome());
            ps.setString(2, estudante.getCurso());
            ps.setDate(3, new java.sql.Date (estudante.getData().getTime()));
            ps.setString(4, estudante.getStatus());
            ps.setInt(5, estudante.getEstudante_id());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public void delete(Estudante estudante) throws Exception { 
        String SQL = "DELETE FROM ESTUDANTE WHERE ESTUDANTE_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, estudante.getEstudante_id());            
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
    public Estudante findById(int id) {
        return new Estudante();
    }
    
    public List<Estudante> findAll() throws Exception { 
        List<Estudante> list = new ArrayList<>();
        Estudante objeto;
        String SQL = "SELECT * FROM ESTUDANTE";        
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                objeto = new Estudante();
                objeto.setEstudante_id(rs.getInt("estudante_id"));
                objeto.setNome(rs.getString("nome"));
                objeto.setCurso(rs.getString("curso"));
                objeto.setData(rs.getDate("data_matricula"));
                objeto.setStatus(rs.getString("status"));
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
