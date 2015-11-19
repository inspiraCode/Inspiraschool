package com.inspiracode.inspiraschool.jsf.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1309808454704952868L;
    private static final Logger logger = Logger.getLogger(LoginBean.class.getName());

    private String userName = null;
    private String password = null;

    @ManagedProperty(value = "#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;

    /**
     * Evaluate given username and password.
     * 
     * @return management bean resolution. Resolved to faces-config.xml
     *         navigation rule for login.xhtml
     */
    public String login() {
	try {
	    Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword());
	    Authentication result = authenticationManager.authenticate(request);
	    SecurityContextHolder.getContext().setAuthentication(result);
	} catch (AuthenticationException e) {
	    logger.error(e.getMessage(), e);
	    publishError("Error al ingresar al sistema");
	    publishError(e.getLocalizedMessage());
	    return "incorrect";
	}

	return "correct";
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public AuthenticationManager getAuthenticationManager() {
	return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
	this.authenticationManager = authenticationManager;
    }
    
    private void publishMessage(FacesMessage.Severity severity, String message, String details) {
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, details));
    }

    private void publishError(String message) {
	publishMessage(FacesMessage.SEVERITY_ERROR, message, null);
    }
}
