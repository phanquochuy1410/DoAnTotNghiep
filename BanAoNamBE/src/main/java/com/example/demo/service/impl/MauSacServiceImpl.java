package com.example.demo.service.impl;

import com.example.demo.dto.mausac.MauSacRequest;
import com.example.demo.entity.MauSac;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    private MauSacRepository repository;
    @Override
    public Page<MauSac> getAll ( int page ) {
        Pageable pageable = PageRequest.of(page , 5);
        return repository.findAll(pageable);
    }

    @Override
    public List<MauSac> getCbb () {
        return repository.findAll();
    }

    public List<MauSac> getElementRequired () {
        return repository.findAll();
    }
    private String genMa(){
        String maCV = " ";
        String s1 = "MS";

        for (int i = 1; i < getElementRequired().size() +2; i++) {
            maCV = s1 +i;
        }
        return maCV;
    }

    @Override
    public MauSac add ( MauSacRequest request ) {
        if(repository.findAll().stream().anyMatch(ms -> ms.getTen().equalsIgnoreCase(request.getTen()))){
            return null;
        }
        MauSac mauSac = request.map(new MauSac());
        mauSac.setMa(genMa());
        return repository.save(mauSac);
    }

    @Override
    public MauSac update ( MauSacRequest request , Integer id ) {
        Optional<MauSac> optional = Optional.ofNullable(repository.findById(id).orElse(null));
        return optional.map(o -> {
            o.setMa(request.getMa());
            o.setTen(request.getTen());
            return repository.save(o);
        }).orElse(null);
    }

    @Override
    public MauSac delete ( Integer id ) {
        Optional<MauSac> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }
}
