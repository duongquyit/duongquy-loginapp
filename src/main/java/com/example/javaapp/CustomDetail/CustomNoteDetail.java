package com.example.javaapp.CustomDetail;

import java.util.Collection;

import com.example.javaapp.Entity.Notes;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 

public class CustomNoteDetail implements UserDetails{
    
    private Notes note;

    public CustomNoteDetail(Notes note) {
        this.note = note;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return note.getContent();
    }

    @Override
    public String getUsername() {
        return note.getTitle();
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

    
}
