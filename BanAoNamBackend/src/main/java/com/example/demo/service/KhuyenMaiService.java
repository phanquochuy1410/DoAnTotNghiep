package com.example.demo.service;


import com.example.demo.dto.khuyenmai.KhuyenMaiCustom;
import com.example.demo.dto.khuyenmai.KhuyenMaiRequest;
import com.example.demo.dto.khuyenmai.SearchKhuyenMai;
import com.example.demo.entity.KhuyenMai;
import org.springframework.data.domain.Page;

import java.util.List;

public interface KhuyenMaiService {
    Page<KhuyenMai> getAll ( int page );

    List<KhuyenMai> getElementRequired ();

    KhuyenMai add ( KhuyenMaiRequest request );

    KhuyenMai update ( KhuyenMaiRequest request , Integer id );

    KhuyenMai delete ( Integer id );

    List<KhuyenMai> updateTrangThai();

    List<KhuyenMaiCustom> timKiem(SearchKhuyenMai searchKhuyenMai);

}
