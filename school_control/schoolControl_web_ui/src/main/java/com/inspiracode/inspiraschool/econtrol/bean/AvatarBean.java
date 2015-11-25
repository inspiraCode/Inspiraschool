package com.inspiracode.inspiraschool.econtrol.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.inspiracode.inspiraschool.econtrol.service.SysConfigService;

@SessionScoped
@ManagedBean(name = "avatarBean")
public class AvatarBean implements Serializable {
    private static final long serialVersionUID = 4633136876971005098L;

    @ManagedProperty("#{sysConfigService}")
    private SysConfigService configService;

    public SysConfigService getConfigService() {
	return configService;
    }

    public StreamedContent getAvatar() throws FileNotFoundException {
	StreamedContent result = new DefaultStreamedContent();
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext ec = context.getExternalContext();

	if (context.getCurrentPhaseId() != PhaseId.RENDER_RESPONSE) {
	    String userId = ec.getRequestParameterMap().get("userId");

	    if (userId == null) {
		userId = "generic";
	    }
	    // browser is requesting the image
	    File file = new File(configService.getAvatarDirectoryName() + "/" + userId + ".jpg");

	    if (file.exists()) {
		result = new DefaultStreamedContent(new FileInputStream(file), "image/jpeg");
	    } else {
		result = new DefaultStreamedContent(ec.getResourceAsStream("/resources/img/avatar.jpg"), "image/jpeg");
	    }
	}
	return result;
    }

    public void setConfigService(SysConfigService configService) {
	this.configService = configService;
    }

}
