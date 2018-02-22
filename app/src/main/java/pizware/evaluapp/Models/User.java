package pizware.evaluapp.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ErickPiz on 11/01/2018.
 */

public class User extends RealmObject {
    @PrimaryKey
    private String user;
    private String password;
    private String email;
    private boolean admin;
    private String hint;

    public User() {}

    public User(String user, String password, String email, boolean admin, String hint) {
        this.user = user;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.hint = hint;
}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}