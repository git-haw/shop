package com.haw.shop.vo;

/**
 * Created by aiwei on 2020-3-14.
 */
public class UserInfoVo {
    private Integer id;
    private String name;
    private Boolean isLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isLogin=" + isLogin +
                '}';
    }
}
