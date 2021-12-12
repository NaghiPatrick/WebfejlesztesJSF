package com.example.WebJSFProject;

import com.example.WebJSFProject.Entity.Animal;
import com.example.WebJSFProject.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class WebJsfProjectApplication {

	@Autowired
	private AnimalRepository  animalRepository;


	public static void main(String[] args) {
		SpringApplication.run(WebJsfProjectApplication.class, args);
	}
	@Bean
	public ApplicationRunner initializeAnimals() {

		final Animal defaultAnimal1 = new Animal(UUID.randomUUID(),"Lion","Africa","Fast");
		final Animal defaultAnimal2 = new Animal(UUID.randomUUID(),"Elephant","Africa, Asia","Slow");
		final Animal defaultAnimal3 = new Animal(UUID.randomUUID(),"Cheetah","Africa","Very Fast");
		final Animal defaultAnimal4 = new Animal(UUID.randomUUID(),"Panda","Asia","Slow");
		final Animal defaultAnimal5 = new Animal(UUID.randomUUID(),"Sloth","South America","Very Slow");
		final Animal defaultAnimal6 = new Animal(UUID.randomUUID(),"Bison","Europe","Moderate");

		return args -> animalRepository.saveAll(Arrays.asList(defaultAnimal1, defaultAnimal2, defaultAnimal3, defaultAnimal4, defaultAnimal5, defaultAnimal6));
	}
}
