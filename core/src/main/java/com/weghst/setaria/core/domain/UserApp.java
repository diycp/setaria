package com.weghst.setaria.core.domain;

import java.io.Serializable;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class UserApp implements Serializable {

    private static final long serialVersionUID = 1515460880439336865L;

    private int id;
    private int userId;
    private int appId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "id=" + id +
                ", userId=" + userId +
                ", appId=" + appId +
                '}';
    }
}
