package com.zdw.zms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class User implements Serializable {

    private Integer userId;


    private String userName;

    private  String salt;

    private byte state;

    private String password;
    private List<Role> roleList;

    private  String addr;

    private Date birthday;

    public User() {
    }

    public Integer getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getSalt() {
        return this.salt;
    }

    public byte getState() {
        return this.state;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Role> getRoleList() {
        return this.roleList;
    }

    public String getAddr() {
        return this.addr;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$userId = this.userId;
        final Object other$userId = other.userId;
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$userName = this.userName;
        final Object other$userName = other.userName;
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
        final Object this$salt = this.salt;
        final Object other$salt = other.salt;
        if (this$salt == null ? other$salt != null : !this$salt.equals(other$salt)) return false;
        if (this.state != other.state) return false;
        final Object this$password = this.password;
        final Object other$password = other.password;
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$roleList = this.roleList;
        final Object other$roleList = other.roleList;
        if (this$roleList == null ? other$roleList != null : !this$roleList.equals(other$roleList)) return false;
        final Object this$addr = this.addr;
        final Object other$addr = other.addr;
        if (this$addr == null ? other$addr != null : !this$addr.equals(other$addr)) return false;
        final Object this$birthday = this.birthday;
        final Object other$birthday = other.birthday;
        if (this$birthday == null ? other$birthday != null : !this$birthday.equals(other$birthday)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.userId;
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $userName = this.userName;
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $salt = this.salt;
        result = result * PRIME + ($salt == null ? 43 : $salt.hashCode());
        result = result * PRIME + this.state;
        final Object $password = this.password;
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $roleList = this.roleList;
        result = result * PRIME + ($roleList == null ? 43 : $roleList.hashCode());
        final Object $addr = this.addr;
        result = result * PRIME + ($addr == null ? 43 : $addr.hashCode());
        final Object $birthday = this.birthday;
        result = result * PRIME + ($birthday == null ? 43 : $birthday.hashCode());
        return result;
    }

    public String toString() {
        return "User(userId=" + this.userId + ", userName=" + this.userName + ", salt=" + this.salt + ", state=" + this.state + ", password=" + this.password + ", roleList=" + this.roleList + ", addr=" + this.addr + ", birthday=" + this.birthday + ")";
    }
}
