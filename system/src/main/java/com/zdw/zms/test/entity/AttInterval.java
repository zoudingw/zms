package com.zdw.zms.test.entity;

import lombok.Data;

/**
 * @ClassName AttInterval
 * @Description TODO
 * @Author zoudingwei
 * @Date 2019/5/11 5:06 PM
 * @Version 1.0
 **/
@Data
public class AttInterval {

    private Byte enableFlag;

    private String timeInterval;

    private Byte attType;
}
