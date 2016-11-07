package io.elixir.entities.model;

import com.yahoo.elide.annotation.Include;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sbelkin on 11/6/2016.
 */

@Entity
@Include(rootLevel = true)
public class Company {

    private long id;
    private String name;
    private String location;
    private String properties;
    private Collection<Beer> beers = new ArrayList<Beer>();

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    @OneToMany(mappedBy = "company")
    public Collection<Beer> getBeers() {
        return beers;
    }

    public void setBeers(Collection<Beer> beers) {
        this.beers = beers;
    }
}
