package com.weghst.setaria.console.web.vo;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public class UserVo {

    private int id;
    private String email;
    private String password;
    private int[] appIds;

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

    public int[] getAppIds() {
        if (appIds == null) {
            return ArrayUtils.EMPTY_INT_ARRAY;
        }
        return appIds;
    }

    public void setAppIds(int[] appIds) {
        this.appIds = appIds;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", appIds=" + Arrays.toString(appIds) +
                '}';
    }
}
