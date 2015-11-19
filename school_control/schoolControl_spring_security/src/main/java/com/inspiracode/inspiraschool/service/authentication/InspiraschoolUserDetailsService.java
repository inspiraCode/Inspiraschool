package com.inspiracode.inspiraschool.service.authentication;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.sys.UserDAO;
import com.inspiracode.inspiraschool.dto.sys.Role;
import com.inspiracode.inspiraschool.dto.sys.User;

@Service
@Transactional(readOnly = true)
public class InspiraschoolUserDetailsService implements UserDetailsService {
    private static final Logger logger = Logger.getLogger(InspiraschoolUserDetailsService.class.getName());

    @Autowired
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	User user = null;
	/*try {
	    user = userDAO.getByName(userName);
	} catch (ItemByNameException e) {
	    logger.error(e.getMessage(), e);
	}*/
	if (user != null) {
	    logger.info(String.format("ScsproUserDetailsService.loadUserByUsername() = %s", user));
	    List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

	    for (Role role : user.getRoles()) {
		grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		logger.info("role found: " + role.getName());
	    }

	    UserDetails userDetails = new InspiraschoolUserDetails(user, grantedAuthorities);
	    return userDetails;
	} else
	    throw new UsernameNotFoundException("User not registered in database.");
    }

    public UserDAO getUserDAO() {
	return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
	this.userDAO = userDAO;
    }
}
