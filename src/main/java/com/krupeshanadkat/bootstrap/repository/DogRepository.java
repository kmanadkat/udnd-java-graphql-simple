package com.krupeshanadkat.bootstrap.repository;

import com.krupeshanadkat.bootstrap.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog, Long> {
}
