package com.workintech.s17d2.dto;

import com.workintech.s17d2.model.Developer;

public class DeveloperResponse {
        private int status;
        private String message;
        private Developer developer;

    public DeveloperResponse(int status, String message, Developer developer) {
        this.status = status;
        this.message = message;
        this.developer = developer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }
}
