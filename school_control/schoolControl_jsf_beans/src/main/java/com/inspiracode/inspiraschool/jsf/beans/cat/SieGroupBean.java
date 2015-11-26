package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.SieGroup;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.SieGroupService;

@ManagedBean
@SessionScoped
public class SieGroupBean extends BaseFacesBean<SieGroup> {
    private static final long serialVersionUID = 7549788216996264705L;

    @ManagedProperty("#{sieGroupService}")
    private SieGroupService sieGroupService;

    public SieGroupBean() {
	super(SieGroup.class);
    }

    public SieGroupService getSieGroupService() {
	return sieGroupService;
    }

    public void setSieGroupService(SieGroupService sieGroupService) {
	super.setService((BaseService<SieGroup>) sieGroupService);
	this.sieGroupService = sieGroupService;
    }

}
