package com.zdw.zms.entity;

import com.zdw.zms.annotion.NameValid;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Data
public class MyValidator implements Serializable {

    @NameValid(values = "haha")
    private String name;

    @Email
    private String mail;

    @Max(20)
    private Integer count;
}
