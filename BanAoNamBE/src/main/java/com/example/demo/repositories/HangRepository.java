package com.example.demo.repositories;

import com.example.demo.entity.Hang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HangRepository extends JpaRepository<Hang, Integer>{

    Hang findByTen(String name);

}
