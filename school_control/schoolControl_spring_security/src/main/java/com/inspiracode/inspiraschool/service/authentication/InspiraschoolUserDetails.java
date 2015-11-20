package com.inspiracode.inspiraschool.service.authentication;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.inspiracode.inspiraschool.dto.sys.User;

public class InspiraschoolUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(InspiraschoolUserDetails.class.getName());
    private User user;
    private List<GrantedAuthority> grantedAuthorities;

    public InspiraschoolUserDetails(User user, List<GrantedAuthority> grantedAuthorities) {
	this.user = user;
	this.grantedAuthorities = grantedAuthorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
	return grantedAuthorities;
    }

    public String getPassword() {
	return user.getPass();
    }

    public String getUsername() {
	return user.getUserName();
    }

    public boolean isAccountNonExpired() {
	return true;
    }

    public boolean isAccountNonLocked() {
	log.debug("is non locked? " + user.getEnable());
	return user.getEnable();
    }

    public boolean isCredentialsNonExpired() {
	return true;
    }

    public boolean isEnabled() {
	log.debug("is Enabled? " + user.getEnable());
	return user.getEnable();
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

}
