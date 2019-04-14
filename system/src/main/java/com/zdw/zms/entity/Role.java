package com.zdw.zms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author:zoudw
 * Since:JDK 7
 * Date:2019/3/15
 * Description:
 *
 * @Copyright:2018, zoudw@szinfinova.com All Rights Reserved
 */
@Data
public class Role implements Serializable {
    private Integer roleId;

    private List<User> users;

    private String roleName;

    private List<MyRight> rights;

    private String description;

    private Boolean avaliable = Boolean.FALSE;

    public Role() {
    }

}
