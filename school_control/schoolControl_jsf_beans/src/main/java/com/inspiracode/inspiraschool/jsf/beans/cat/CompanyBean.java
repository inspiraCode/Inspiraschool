package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Company;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.CompanyService;

@ManagedBean
@SessionScoped
public class CompanyBean extends BaseFacesBean<Company> {
    private static final long serialVersionUID = 1241126928793312089L;
    
    private CompanyService companyService;
    
    public CompanyBean(){
	super(Company.class);
    }

    public CompanyService getCompanyService() {
	return companyService;
    }

    public void setCompanyService(CompanyService companyService) {
	super.setService((BaseService<Company>) companyService);
	this.companyService = companyService;
    }
}
