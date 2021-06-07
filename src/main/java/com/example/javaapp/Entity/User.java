package com.example.javaapp.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 45)
    private String email;

    @Column( nullable = false, length = 20)
    private String firstName;

    @Column( nullable = false, length = 20)
    private String lastName;


    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    private boolean enable;

    @OneToMany(targetEntity = Notes.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_note",referencedColumnName = "id")
    private List<Notes> listNote;

    public User(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEnabled() {
        return verificationCode;
    }

    public void setEnabled(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Notes> getListNote() {
        return listNote;
    }

    public void setListNote(List<Notes> listNote) {
        this.listNote = listNote;
    }

}
