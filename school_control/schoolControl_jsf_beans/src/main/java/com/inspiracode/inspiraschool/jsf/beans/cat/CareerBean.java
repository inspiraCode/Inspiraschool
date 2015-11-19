package com.inspiracode.inspiraschool.jsf.beans.cat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.cat.Career;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.cat.CareerService;

@ManagedBean
@SessionScoped
public class CareerBean extends BaseFacesBean<Career> {
  private static final long serialVersionUID = 1301613298798489390L;

  private CareerService careerService;

  public CareerBean() {
    super(Career.class);
  }

  public CareerService getCareerService() {
    return careerService;
  }

  public void setCareerService(CareerService careerService) {
    super.setService((BaseService<Career>) careerService);
    this.careerService = careerService;
  }

}
