package com.example.demo.service;

import com.example.demo.dto.hang.HangRequest;
import com.example.demo.entity.Hang;
import com.example.demo.entity.KichCo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HangService {

    Page<Hang> getAll ( int page );

    List<Hang> getElementRequired ();

  List<Hang> getCbb();
  
    Hang add ( HangRequest hangRequest );

    Hang update ( HangRequest hangRequest , Integer id );

    Hang delete ( Integer id );

}
