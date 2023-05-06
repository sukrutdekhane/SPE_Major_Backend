package com.example.SPE_Major_project.Repository;

import com.example.SPE_Major_project.Entity.Actors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actors,Integer> {
}
