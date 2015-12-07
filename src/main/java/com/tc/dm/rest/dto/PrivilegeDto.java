package com.tc.dm.rest.dto;

import java.security.PrivateKey;

/**
 * Created by sg40304 on 12/7/15.
 */
public class PrivilegeDto {

    private String displayText;
    private String rout;

    public String getDisplayText() {
        return displayText;
    }

    public String getRout() {
        return rout;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public void setRout(String rout) {
        this.rout = rout;
    }
}
