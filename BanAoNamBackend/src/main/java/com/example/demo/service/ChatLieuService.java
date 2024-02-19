package com.example.demo.service;

import com.example.demo.dto.ChatLieu.ChatLieuRequest;
import com.example.demo.entity.ChatLieu;
import com.example.demo.repositories.ChatLieuRepository;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatLieuService {
    @Autowired
    private ChatLieuRepository repository;

//    public List<ChatLieuCustom> getData () {
//        return repository.getData();
//    }

    public Page<ChatLieu> getAll ( int page ) {
        Pageable pageable = PageRequest.of(page , 5);
        return repository.findAll(pageable);
    }


    public List<ChatLieu> getCbb ( ) {
        return repository.findAll();
    }


    public List<ChatLieu> getElementRequired () {
        return repository.findAll();
    }

    private String genMa () {
        String maCV = " ";
        String s1 = "CL";

        for (int i = 1; i < getElementRequired().size() + 2; i++) {
            maCV = s1 + i;
        }
        return maCV;
    }


    public ChatLieu add ( ChatLieuRequest chatLieuRequest ) {
        if(repository.findAll().stream().anyMatch(cl -> cl.getTen().equalsIgnoreCase(chatLieuRequest.getTen()))){
            return null;
        }
        ChatLieu chatLieu = chatLieuRequest.map(new ChatLieu());
        chatLieu.setMa(genMa());
        repository.save(chatLieu);
        return chatLieu;
    }

    public ChatLieu update ( ChatLieuRequest request , Integer id ) {
        Optional<ChatLieu> optional = Optional.ofNullable(repository.findById(id).orElse(null));
        return optional.map(o -> {
            o.setMa(request.getMa());
            o.setTen(request.getTen());
            return repository.save(o);
        }).orElse(null);
    }

    public ChatLieu delete ( Integer id ) {
        Optional<ChatLieu> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }

}

