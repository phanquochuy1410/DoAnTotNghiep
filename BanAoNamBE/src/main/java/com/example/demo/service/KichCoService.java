package com.example.demo.service;



import com.example.demo.dto.kichco.KichCoRequest;
import com.example.demo.entity.KichCo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface KichCoService {
    Page<KichCo> getAll( int page);

    List<KichCo> getCbb();

    List<KichCo> getElementRequired();

    KichCo add( KichCoRequest request );
    KichCo update( KichCoRequest request, Integer id );
    KichCo delete( Integer id );
}
