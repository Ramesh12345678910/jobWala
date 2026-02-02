package com.project.profile_Service.exception;

public class  ProfileAlreadyExistsForUser extends RuntimeException {
    public  ProfileAlreadyExistsForUser(String message) {
        super(message);
    }
}
