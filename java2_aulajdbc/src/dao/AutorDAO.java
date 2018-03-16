package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            //ps.setInt(1, autor.getAutor_id());
            ps.setString(1, autor.getNome());
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Autor autor) {

    }

    public void delete(Autor autor) {

    }

    public Autor findById(int id) {
        return new Autor();
    }

    public List<Autor> findAll() {
        return new ArrayList<>();
    }
}
