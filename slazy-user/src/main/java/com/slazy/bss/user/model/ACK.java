package com.slazy.bss.user.model;

public class ACK {
    private String authId;

    private String terminalName;

    private String authKey;

    private String autoStatus;

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getAutoStatus() {
        return autoStatus;
    }

    public void setAutoStatus(String autoStatus) {
        this.autoStatus = autoStatus;
    }
}