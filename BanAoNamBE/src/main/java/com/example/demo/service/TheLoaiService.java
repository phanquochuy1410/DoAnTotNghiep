package com.example.demo.service;


import com.example.demo.dto.theloai.TheLoaiRequest;
import com.example.demo.entity.TheLoai;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TheLoaiService {
    Page<TheLoai> getAll ( int page );


    List<TheLoai> getCbb();

    List<TheLoai> getElementRequired ();

    TheLoai add ( TheLoaiRequest request );

    TheLoai update ( TheLoaiRequest request , Integer id );

    TheLoai delete ( Integer id );
}
