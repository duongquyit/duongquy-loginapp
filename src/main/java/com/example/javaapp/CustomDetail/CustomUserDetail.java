package com.example.javaapp.CustomDetail;

import java.util.Collection;

import com.example.javaapp.Entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 

public class CustomUserDetail implements UserDetails{
    
    private User user;

    public CustomUserDetail(User user) {
        this.user = user;
    }

    public boolean isEnable() {
        return user.isEnable();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // Cho biết tài khoản của người dùng đã hết hạn
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
// Cho biết liệu người dùng có bị khóa hoặc mở khóa không
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
// Cho biết liệu thông tin đăng nhập của người dùng (mật khẩu) đã hết hạn.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
// Cho biết liệu người dùng có được bật hoặc tắt.
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }
    
}
