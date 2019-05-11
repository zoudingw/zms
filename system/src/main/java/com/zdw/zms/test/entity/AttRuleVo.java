package com.zdw.zms.test.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName AttRule
 * @Description TODO
 * @Author zoudingwei
 * @Date 2019/5/11 5:04 PM
 * @Version 1.0
 **/
@Data
public class AttRuleVo extends AttRule {

    private List<AttInterval> intervals;

}
