package com.example.demo.repositories;

import com.example.demo.entity.ChatLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, Integer> {
//    @Query(value = "SELECT cl.id,cl.ma,cl.ten  FROM chat_lieu cl",nativeQuery = true)
//    List<ChatLieuCustom> getData();\

    ChatLieu findByTen(String name);

}
