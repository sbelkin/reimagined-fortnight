package io.elixir.entities.model;

import javax.persistence.*;

import com.yahoo.elide.annotation.Include;

/**
 * Created by sbelkin on 11/6/2016.
 */
@Entity
//@ReadPermission(expression = "Prefab.Roll.All")
//@UpdatePermission(expression = "user is a superuser")
//@CreatePermission(expression = "user is a superuser")
//@DeletePermission(expression = "user is a superuser")
//@SharePermission(expression = "Prefab.Role.All")
@Include(rootLevel = true)
public class Beer {

    private long id;
    private Company company;
    private String name;
    private String properties;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToOne
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

//    @UpdatePermission(expression = "user is a superuser now")
//    boolean suppressed;
}
