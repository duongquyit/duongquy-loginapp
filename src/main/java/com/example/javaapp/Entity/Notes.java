package com.example.javaapp.Entity;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "note")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 64)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(name = "Datetime")
    private LocalDate dateTime; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_note", referencedColumnName = "id", nullable = false)
    private User user;

    public Notes(){
        
    }

    public Notes(String title, String conten){
        this.title = title;
        this.content = conten;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }


    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
