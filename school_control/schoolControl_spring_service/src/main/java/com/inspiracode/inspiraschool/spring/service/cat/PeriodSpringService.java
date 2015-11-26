package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.PeriodDAO;
import com.inspiracode.inspiraschool.dto.cat.Period;
import com.inspiracode.inspiraschool.service.cat.PeriodService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class PeriodSpringService extends BaseSpringService<Period> implements PeriodService, PeriodDAO {
    private static final long serialVersionUID = 270815703188057102L;

    private PeriodDAO periodDAO;

    public PeriodDAO getPeriodDAO() {
	return periodDAO;
    }

    @Required
    public void setPeriodDAO(PeriodDAO periodDAO) {
	super.setDaoFactory((BaseDAO<Period>) periodDAO);
	this.periodDAO = periodDAO;
    }

}
