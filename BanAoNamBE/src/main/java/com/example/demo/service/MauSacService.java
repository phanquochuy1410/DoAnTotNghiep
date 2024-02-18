package com.example.demo.service;


import com.example.demo.dto.mausac.MauSacRequest;
import com.example.demo.entity.KichCo;
import com.example.demo.entity.MauSac;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MauSacService {
    Page<MauSac> getAll ( int page );


    List<MauSac> getCbb();

    List<MauSac> getElementRequired ();

    MauSac add ( MauSacRequest request );

    MauSac update ( MauSacRequest request , Integer id );

    MauSac delete ( Integer id );
}
