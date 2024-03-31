package com.krupeshanadkat.bootstrap.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.krupeshanadkat.bootstrap.entity.Dog;
import com.krupeshanadkat.bootstrap.exception.DogNotFoundException;
import com.krupeshanadkat.bootstrap.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {
    private final DogRepository dogRepository;

    public Query(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public Iterable<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    public Dog findDogById(Long id) {
        Optional<Dog> dogOptional = dogRepository.findById(id);
        if(dogOptional.isPresent()) {
            return dogOptional.get();
        }
        throw new DogNotFoundException("Dog not found", id);
    }
}
