package com.codeup.springblog.models;


import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

<<<<<<< HEAD
=======
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Post {


>>>>>>> f0e2ac6ff183b1be2232a202dc96b15974b2748f
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED")
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Post(){}

    public Post(String title, String body, long id, User user) {
        this.title = title;
        this.body = body;
        this.id = id;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
