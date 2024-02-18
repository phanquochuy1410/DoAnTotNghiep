package com.example.demo.repositories;

import com.example.demo.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheLoaiRepository extends JpaRepository<TheLoai, Integer> {

    TheLoai findByTen(String name);

}
