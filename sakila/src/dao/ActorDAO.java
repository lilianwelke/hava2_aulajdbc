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
import model.Actor;
import util.ConnectionJDBC;

public class ActorDAO {

    Connection connection;

    public ActorDAO() throws Exception {
        connection = ConnectionJDBC.getConnection();
    }

    public void save(Actor actor) throws Exception {
        String SQL = "INSERT INTO ACTOR (FIRST_NAME, LAST_NAME, LAST_UPDATE) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, actor.getFirst_nome());
            ps.setString(2, actor.getLast_nome());
            ps.setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));
            ps.execute();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void update(Actor actor) throws Exception {
        String SQL = "UPDATE ACTOR SET FIRST_NAME = ?, LAST_NAME = ?, LAST_UPDATE = ?  WHERE ACTOR_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, actor.getFirst_nome());
            ps.setString(2, actor.getLast_nome());
            ps.setTimestamp(3, new java.sql.Timestamp (new Date().getTime()));
            ps.setInt(4, actor.getActor_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Actor actor) throws Exception {
        String SQL = "DELETE FROM ACTOR WHERE ACTOR_ID = ?";  
        try {
            PreparedStatement ps = connection.prepareStatement(SQL); 
            ps.setInt(1, actor.getActor_id());
            ps.execute();            
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public Actor findById(int id) {
        return new Actor();
    }

    public List<Actor> findAll() throws Exception {
        List<Actor> list = new ArrayList<>();
        Actor objeto;
        String SQL = "SELECT * FROM ACTOR";
        try {
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                objeto = new Actor();
                objeto.setActor_id(rs.getInt("actor_id"));
                objeto.setFirst_nome(rs.getString("first_name"));
                objeto.setLast_nome(rs.getString("last_name"));
                objeto.setLast_upd(rs.getTimestamp("last_update"));
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