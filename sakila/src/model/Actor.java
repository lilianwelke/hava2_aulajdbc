package model;

import java.sql.Timestamp;

public class Actor {

    private int actor_id;
    private String first_nome;
    private String last_nome;
    private Timestamp last_upd;

    public int getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirst_nome() {
        return first_nome;
    }

    public void setFirst_nome(String first_nome) {
        this.first_nome = first_nome;
    }

    public String getLast_nome() {
        return last_nome;
    }

    public void setLast_nome(String last_nome) {
        this.last_nome = last_nome;
    }

    public Timestamp getLast_upd() {
        return last_upd;
    }

    public void setLast_upd(Timestamp last_upd) {
        this.last_upd = last_upd;
    }
    
}