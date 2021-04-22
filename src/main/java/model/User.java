package model;

import javax.persistence.*;

@Table(schema = "test_schema", name="users")
@NamedQuery(name = "User.findAll", query = "select p from User p")
@Entity
public class User {

    @Id
    private String username;
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Person{" +
                "usernme=" + username +
                ", pw='" + password + '\'' +
                '}';
    }
}
