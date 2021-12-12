package com.example.WebJSFProject.service;

import com.example.WebJSFProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(final UserRepository repository){
        this.repository = repository;
    }

}
