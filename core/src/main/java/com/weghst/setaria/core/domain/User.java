package com.weghst.setaria.core.domain;

import java.io.Serializable;

/**
 * 用户实体.
 *
 * @author Kevin Zou (zouyong@shzhiduan.com)
 */
public class User implements Serializable {

    private static final long serialVersionUID = -5331182262867932385L;

    /**
     * 主键.
     */
    private int id;
    /**
     * 邮箱.
     */
    private String email;
    /**
     * 密码.
     */
    private String password;
    /**
     * 创建时间.
     */
    private long createdTime;
    /**
     * 用户是否为管理员.
     */
    private boolean manager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdTime=" + createdTime +
                ", manager=" + manager +
                '}';
    }
}
