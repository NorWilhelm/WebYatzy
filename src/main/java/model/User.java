package model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "users")
public class User {

    @Id
    private Integer userID;

    private String username;

    private String password;

    public User(Integer userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public User() {}

    @Override
    public String toString() {
        return "User: " + username + "password=" + password + "]";
    }
}