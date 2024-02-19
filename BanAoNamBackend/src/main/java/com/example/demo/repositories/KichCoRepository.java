package com.example.demo.repositories;

import com.example.demo.entity.KichCo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KichCoRepository extends JpaRepository<KichCo, Integer> {

    KichCo findByTen(String name);

}
