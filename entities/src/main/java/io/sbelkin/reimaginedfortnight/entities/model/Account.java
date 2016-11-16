package io.sbelkin.reimaginedfortnight.entities.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.*;

import com.yahoo.elide.annotation.Include;

/**
 * Created by sbelkin on 11/6/2016.
 */
@Entity
//@ReadPermission(expression = "user is a superuser OR user is this user")
//@UpdatePermission(expression = "user is a superuser OR user is this user")
//@CreatePermission(expression = "user is a superuser OR user is this user")
//@DeletePermission(expression = "user is a superuser OR user is this user")
@Include(rootLevel = true)
public class Account {
    private long id;
    private String name;
    private String email;
    private Collection<History> histories = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "account")
    public Collection<History> getHistories() {
        return histories;
    }

    public void setHistories(Collection<History> histories) {
        this.histories = histories;
    }
}
