package co.norse.chat.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class User implements UserDetails {
    private final long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String type;
    private static AtomicLong atomicLong = new AtomicLong();

    public User() {
        this.id = atomicLong.incrementAndGet();
    }

    public User(String username, String password, String email, String firstName, String lastName, String imageUser, String type) {
        this.id = atomicLong.incrementAndGet();
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUser;
        this.type = type;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}