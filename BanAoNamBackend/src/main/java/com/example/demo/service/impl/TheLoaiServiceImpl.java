package com.example.demo.service.impl;

import com.example.demo.dto.theloai.TheLoaiRequest;
import com.example.demo.entity.TheLoai;
import com.example.demo.repositories.TheLoaiRepository;
import com.example.demo.service.TheLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheLoaiServiceImpl implements TheLoaiService {
    @Autowired
    private TheLoaiRepository repository;

    @Override
    public Page<TheLoai> getAll ( int page ) {
        Pageable pageable = PageRequest.of(page , 5);
        return repository.findAll(pageable);

    }

    @Override
    public List<TheLoai> getCbb () {
        return repository.findAll();
    }

    public List<TheLoai> getElementRequired () {
        return repository.findAll();
    }

    private String genMa () {
        String maCV = " ";
        String s1 = "TL";

        for (int i = 1; i < getElementRequired().size() + 2; i++) {
            maCV = s1 + i;
        }
        return maCV;
    }

    @Override
    public TheLoai add ( TheLoaiRequest request ) {
        if(repository.findAll().stream().anyMatch(tl -> tl.getTen().equalsIgnoreCase(request.getTen()))){
            return null;
        }
        TheLoai theLoai = request.map(new TheLoai());
        theLoai.setMa(genMa());
        return repository.save(theLoai);
    }

    @Override
    public TheLoai update ( TheLoaiRequest request , Integer id ) {
        Optional<TheLoai> optional = Optional.ofNullable(repository.findById(id).orElse(null));
        return optional.map(o -> {
            o.setMa(request.getMa());
            o.setTen(request.getTen());
            return repository.save(o);
        }).orElse(null);
    }

    @Override
    public TheLoai delete ( Integer id ) {
        Optional<TheLoai> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }
}
