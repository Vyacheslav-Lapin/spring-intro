package com.luxoft.training.java.spring.intro.dao;

import com.luxoft.training.java.spring.intro.model.Cat;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CatRepository extends JpaRepository<Cat, UUID> {
}
