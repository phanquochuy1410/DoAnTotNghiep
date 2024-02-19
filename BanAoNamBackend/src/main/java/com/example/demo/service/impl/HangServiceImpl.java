package com.example.demo.service.impl;

import com.example.demo.dto.hang.HangRequest;
import com.example.demo.entity.Hang;
import com.example.demo.entity.KichCo;
import com.example.demo.repositories.HangRepository;
import com.example.demo.service.HangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HangServiceImpl implements HangService {
    @Autowired
    private HangRepository repository;

    @Override
    public Page<Hang> getAll ( int page ) {
        Pageable pageable = PageRequest.of(page , 5);
        return repository.findAll(pageable);
    }

    @Override
    public List<Hang> getCbb () {
        return repository.findAll();
    }


    public List<Hang> getElementRequired () {
        return repository.findAll();
    }

    private String genMa(){
        String maCV = " ";
        String s1 = "H";

        for (int i = 1; i < getElementRequired().size() +2; i++) {
            maCV = s1 +i;
        }
        return maCV;
    }


    @Override
    public Hang add ( HangRequest hangRequest ) {
        if(repository.findAll().stream().anyMatch(h -> h.getTen().equalsIgnoreCase(hangRequest.getTen()))){
            return null;
        }
        Hang hang = hangRequest.map(new Hang());
        hang.setMa(genMa());
        return repository.save(hang);
    }

    @Override
    public Hang update ( HangRequest hangRequest , Integer id ) {
        Optional<Hang> optional = Optional.ofNullable(repository.findById(id).orElse(null));
        return optional.map(o -> {
            o.setMa(hangRequest.getMa());
            o.setTen(hangRequest.getTen());
            return repository.save(o);
        }).orElse(null);
    }

    @Override
    public Hang delete ( Integer id ) {
        Optional<Hang> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }
}
