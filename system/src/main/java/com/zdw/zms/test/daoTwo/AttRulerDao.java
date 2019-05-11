package com.zdw.zms.test.daoTwo;

import com.zdw.zms.entity.User;
import com.zdw.zms.test.entity.AttInterval;
import com.zdw.zms.test.entity.AttRule;

import java.util.List;

public interface AttRulerDao {
    AttRule selectByPrimaryKey(String id);
  List<AttInterval> selectIntervalById(String id);

}
