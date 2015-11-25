package com.inspiracode.inspiraschool.econtrol.service;

import org.springframework.beans.factory.annotation.Value;

public class EcontrolConfigService implements SysConfigService {

    @Value("${directory.avatar}")
    private String avatarDirectory;
    
    @Value("${directory.studentFile}")
    private String studentFileDirectory;
    
    @Override
    public String getAvatarDirectoryName() {
	return avatarDirectory;
    }

    @Override
    public String getStudentFileDirectoryName() {
	return studentFileDirectory;
    }

}
