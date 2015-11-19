package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.CompanyDAO;
import com.inspiracode.inspiraschool.dto.cat.Company;
import com.inspiracode.inspiraschool.service.cat.CompanyService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class CompanySpringService extends BaseSpringService<Company> implements CompanyService {
    private CompanyDAO companyDAO;

    public CompanyDAO getCompanyDAO() {
	return companyDAO;
    }

    @Required
    public void setCompanyDAO(CompanyDAO companyDAO) {
	super.setDaoFactory((BaseDAO<Company>) companyDAO);
	this.companyDAO = companyDAO;
    }
}
