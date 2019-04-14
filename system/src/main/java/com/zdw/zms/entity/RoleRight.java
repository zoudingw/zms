package com.zdw.zms.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleRight implements Serializable {

    private  Integer roleId;
    private  Integer rightId;

}
