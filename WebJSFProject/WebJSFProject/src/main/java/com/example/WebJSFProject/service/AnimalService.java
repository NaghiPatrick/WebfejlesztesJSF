package com.example.WebJSFProject.service;

import com.example.WebJSFProject.Entity.Animal;
import com.example.WebJSFProject.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    @Autowired
    public AnimalService(final AnimalRepository repository){
        this.repository = repository;
    }

    public Page<Animal> getAnimals(final int pageNumber, final int size){
        return repository.findAll(PageRequest.of(pageNumber, size));

    }
    public Optional<Animal> getAnimal(final UUID id){
        return repository.findById(id);
    }

    public Animal save(final Animal animal){
        return repository.save(animal);
    }

    public void delete(final UUID id){
        repository.deleteById(id);
    }

}
