package com.example.demo.service;


import com.example.demo.dto.khuyenmai.KhuyenMaiCustom;
import com.example.demo.dto.sanpham.SanPhamCustom;
import com.example.demo.dto.sanpham.SanPhamRequest;
import com.example.demo.entity.KhuyenMai;
import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamService {
    Page<SanPham> getAll ( int page );

    Integer getChietKhau(Integer idSp);

    List<SanPham> getCbb();

    List<KhuyenMaiCustom> getCbbKM ();

    List<SanPham> getElementRequired ();

    SanPham getById(Integer id);

    SanPham getByTen(String ten);

    SanPham add ( SanPhamRequest request );

    SanPham detail(Integer id);

    SanPham update ( SanPhamRequest request , Integer id );

    SanPham delete ( Integer id );

    Boolean updateKM(List<Integer> listKM, Integer idKM );

    List<SanPhamCustom> findByDG(Integer page, String name, String by, Integer order);

    List<SanPhamCustom> findByDGLength(String name, String by, Integer order);

    List<SanPham> searchTen(String ten);


}
