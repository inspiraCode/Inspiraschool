package com.inspiracode.inspiraschool.spring.service.cat;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dao.cat.CareerDAO;
import com.inspiracode.inspiraschool.dto.cat.Career;
import com.inspiracode.inspiraschool.service.cat.CareerService;
import com.inspiracode.inspiraschool.spring.service.BaseSpringService;

@Transactional(readOnly = true)
public class CareerSpringService extends BaseSpringService<Career> implements CareerService {
  private CareerDAO careerDAO;

  public CareerDAO getCareerDAO() {
    return careerDAO;
  }

  @Required
  public void setCareerDAO(CareerDAO careerDAO) {
    super.setDaoFactory((BaseDAO<Career>) careerDAO);
    this.careerDAO = careerDAO;
  }
}
