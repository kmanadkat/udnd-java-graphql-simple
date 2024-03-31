package com.krupeshanadkat.bootstrap.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.krupeshanadkat.bootstrap.entity.Dog;
import com.krupeshanadkat.bootstrap.exception.DogNotFoundException;
import com.krupeshanadkat.bootstrap.repository.DogRepository;

import java.util.Optional;

public class Mutation implements GraphQLMutationResolver {
    private final DogRepository dogRepository;

    public Mutation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed) {
        boolean deleted = false;
        Iterable<Dog> allDogs = dogRepository.findAll();
        for (Dog d:allDogs) {
            if(d.getBreed().equals(breed)) {
                dogRepository.delete(d);
                deleted = true;
            }
        }
        if(!deleted) {
            throw new RuntimeException("Breed not found");
        }
        return deleted;
    }

    public Dog updateDogName(String newName, Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        if(optionalDog.isPresent()) {
            Dog dog = optionalDog.get();
            dog.setName(newName);
            dogRepository.save(dog);
            return dog;
        } else {
            throw new DogNotFoundException("Dog not found", id);
        }
    }
}
