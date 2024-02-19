package com.example.demo.service.impl;

import com.example.demo.dto.kichco.KichCoRequest;
import com.example.demo.entity.KichCo;
import com.example.demo.repositories.KichCoRepository;
import com.example.demo.service.KichCoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KichCoServiceImpl implements KichCoService {
    @Autowired
    private KichCoRepository repository;


    @Override
    public Page<KichCo> getAll ( int page ) {
        Pageable pageable = PageRequest.of(page , 5);
        return repository.findAll(pageable);    }

    @Override
    public List<KichCo> getCbb () {
        return repository.findAll();
    }

    public List<KichCo> getElementRequired () {
        return repository.findAll();
    }
    private String genMa(){
        String maCV = " ";
        String s1 = "KC";

        for (int i = 1; i < getElementRequired().size() +2; i++) {
            maCV = s1 +i;
        }
        return maCV;
    }

    @Override
    public KichCo add ( KichCoRequest request ) {
        if(repository.findAll().stream().anyMatch(kc -> kc.getTen().equalsIgnoreCase(request.getTen()))){
            return null;
        }
        KichCo kichCo = request.map(new KichCo());
        kichCo.setMa(genMa());
        return repository.save(kichCo);
    }

    @Override
    public KichCo update ( KichCoRequest request , Integer id ) {
        Optional<KichCo> optional = Optional.ofNullable(repository.findById(id).orElse(null));
        return optional.map(o -> {
            o.setMa(request.getMa());
            o.setTen(request.getTen());
            return repository.save(o);
        }).orElse(null);
    }

    @Override
    public KichCo delete ( Integer id ) {
        Optional<KichCo> optional = repository.findById(id);
        return optional.map(o -> {
            repository.delete(o);
            return o;
        }).orElse(null);
    }
}
