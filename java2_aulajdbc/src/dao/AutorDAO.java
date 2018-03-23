package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.Autor;
import util.ConnectionJDBC;

public class AutorDAO {

    Connection connection;

    public AutorDAO() throws Exception {
        // Obtem uma conexão
        connection = ConnectionJDBC.getConnection();
    }

    public void save(Autor autor) throws Exception {
        String SQL = "INSERT INTO AUTOR (NOME) VALUES (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, autor.getNome());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Autor autor) throws Exception {
        String SQL = "UPDATE AUTOR SET NOME = ? WHERE AUTOR_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL); 
            ps.setString(1, autor.getNome());
            ps.setInt(2, autor.getAutor_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Autor autor) throws Exception {
        String SQL = "DELETE FROM AUTOR WHERE AUTOR_ID = ?";  
        try {
            PreparedStatement ps = connection.prepareStatement(SQL); 
            ps.setInt(1, autor.getAutor_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Autor findById(int id) {
        return new Autor();
    }

    public List<Autor> findAll() throws Exception {
        // Lista para manter os valores do ResultSet
        List<Autor> list = new ArrayList<>();
        Autor objeto;
        String SQL = "SELECT * FROM AUTOR";
        try {
            // Prepara a SQL
            PreparedStatement ps = connection.prepareStatement(SQL);
            // Executa a SQL e matem os valores no ResultSet rs
            ResultSet rs = ps.executeQuery();
            // Navega pelos registros no rs
            while (rs.next()) {
                // Instancia a classe e informa os valores do BD
                objeto = new Autor();
                objeto.setAutor_id(rs.getInt("autor_id"));
                objeto.setNome(rs.getString("nome"));
                // Inclui na lista
                list.add(objeto);                
            }            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
        // Retorna a lista
        return list;
    }    
}
