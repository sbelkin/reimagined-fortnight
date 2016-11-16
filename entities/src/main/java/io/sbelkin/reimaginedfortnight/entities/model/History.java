package io.sbelkin.reimaginedfortnight.entities.model;

/**
 * Created by sbelkin on 11/8/2016.
 */

import javax.persistence.*;

import com.yahoo.elide.annotation.Include;

@Entity
//@ReadPermission(expression = "user is a superuser OR user is this user")
//@UpdatePermission(expression = "user is a superuser OR user is this user")
//@CreatePermission(expression = "user is a superuser OR user is this user")
//@DeletePermission(expression = "user is a superuser OR user is this user")
@Include(rootLevel = true)
public class History {

    private long id;
    private Account account;
    private Beer beer;
    private String rating;
    private String comments;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @ManyToOne
    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

//    @UpdatePermission(expression = "user made this history")
//    boolean suppressed;
}
