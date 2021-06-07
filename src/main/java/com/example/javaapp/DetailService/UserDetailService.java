package com.example.javaapp.DetailService;

import java.util.List;
import java.util.Optional;


import com.example.javaapp.CustomDetail.CustomUserDetail;
import com.example.javaapp.Entity.User;
import com.example.javaapp.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
// UserDetailsService được sử dụng để truy xuất dữ liệu liên quan đến người dùng    
@Service
public class UserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        
        return new CustomUserDetail(user);
    }

    public List<User> getAllUser(){
        return repo.findAll();
    }

    public void saveUser(User user){
        this.repo.save(user);
    }

    public User findUserById(Long id){
        Optional<User> optional = repo.findById(id);
        User user = null;
        if(optional.isPresent()){
            user = optional.get();
        }else{
            throw new RuntimeException("id not exist!");
        }
        return user;
    }

   
}
