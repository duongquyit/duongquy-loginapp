package com.example.javaapp.DetailService;

import java.util.Collection;
import java.util.Optional;

import com.example.javaapp.CustomDetail.CustomNoteDetail;
import com.example.javaapp.Entity.Notes;
import com.example.javaapp.Entity.User;
import com.example.javaapp.Repository.NoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// UserDetailsService được sử dụng để truy xuất dữ liệu liên quan đến người dùng    
@Service
public class NoteDetailService implements UserDetailsService, UserDetails{

    @Autowired
    private NoteRepository noterep;

    @Override
    public UserDetails loadUserByUsername(String title) throws UsernameNotFoundException {
        Notes note = noterep.findByTitle(title);
        if(note == null){
            throw new UsernameNotFoundException("Note not found");
        }
        
        return new CustomNoteDetail(note);

    }

    public Notes findNoteById(Long id){
        Optional<Notes> optional = noterep.findById(id);
        Notes note = null;
        if(optional.isPresent()){
            note = optional.get();
        }else{
            throw new RuntimeException("id not exist!");
        }
        return note;
    }

    public boolean checkUser(User user, Long idNote){
        Notes note = findNoteById(idNote);
        if(user.getId().equals(note.getUser().getId())){
            return true;
        }
        return false;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

}
