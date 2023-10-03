package io.ies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.ies.entities.PlansEntity;

public interface PlansRepo extends JpaRepository<PlansEntity, Integer>{

}
