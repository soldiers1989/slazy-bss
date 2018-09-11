package com.slazy.bss.user.model;

public class SysRole {
    private String roleId;

    private String roleKey;

    private String createTimr;

    private String description;

    private String roleValue;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getCreateTimr() {
        return createTimr;
    }

    public void setCreateTimr(String createTimr) {
        this.createTimr = createTimr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }
}