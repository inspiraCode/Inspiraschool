package com.inspiracode.inspiraschool.jsf.beans.ctrl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.inspiracode.inspiraschool.dto.ctrl.Score;
import com.inspiracode.inspiraschool.jsf.beans.BaseFacesBean;
import com.inspiracode.inspiraschool.service.BaseService;
import com.inspiracode.inspiraschool.service.ctrl.ScoreService;

@ManagedBean
@SessionScoped
public class ScoreBean extends BaseFacesBean<Score> {
  private static final long serialVersionUID = 1965513980898329854L;

  private ScoreService scoreService;

  public ScoreBean() {
    super(Score.class);
  }

  public ScoreService getScoreService() {
    return scoreService;
  }

  public void setScoreService(ScoreService scoreService) {
    super.setService((BaseService<Score>) scoreService);
    this.scoreService = scoreService;
  }

}
