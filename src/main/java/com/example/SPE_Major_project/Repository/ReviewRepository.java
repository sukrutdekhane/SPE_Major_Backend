package com.example.SPE_Major_project.Repository;

import com.example.SPE_Major_project.Entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Reviews,Integer>
{

    @Query("select r from Reviews r where r.movieName=?1")
    List<Reviews> getReviewByMovieName(String movieName);
}
