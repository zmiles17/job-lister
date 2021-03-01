package com.talentpath.JobLister.exceptions;

import java.util.function.Supplier;

public class ResourceNotFoundException extends Exception implements Supplier<String> {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public String get() {
        return "Resource not found";
    }
}
