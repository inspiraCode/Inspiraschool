package com.inspiracode.inspiraschool.econtrol.service;

import java.io.Serializable;

public interface SysConfigService extends Serializable {
    String getAvatarDirectoryName();

    String getStudentFileDirectoryName();
}
