package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.SieGroupDAO;
import com.inspiracode.inspiraschool.dto.cat.SieGroup;
import com.inspiracode.inspiraschool.service.cat.SieGroupService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class SieGroupSpringService extends BaseSpringService<SieGroup> implements SieGroupService {
    private static final long serialVersionUID = 5890838234368213627L;
    private SieGroupDAO sieGroupDAO;

    public SieGroupDAO getSieGroupDAO() {
	return sieGroupDAO;
    }
    @Required
    public void setSieGroupDAO(SieGroupDAO sieGroupDAO) {
	super.setDaoFactory((BaseDAO<SieGroup>) sieGroupDAO);
	this.sieGroupDAO = sieGroupDAO;
    }

}
