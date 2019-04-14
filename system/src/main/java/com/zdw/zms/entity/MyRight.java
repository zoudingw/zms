package com.zdw.zms.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author:zoudw
 * Since:JDK 7
 * Date:2019/3/19
 * Description:
 *
 * @Copyright:2018, zoudw@szinfinova.com All Rights Reserved
 */
@Data
public class MyRight implements Serializable {
    private Integer rightId;


    private  String rightName;

   private String resourceType;

    private String url;
    private List<Role> roles;

    public MyRight() {
    }


}
