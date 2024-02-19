package com.example.demo.service;


import com.example.demo.dto.dgsp.DanhGiaSanPhamCustom;
import com.example.demo.dto.dgsp.DanhGiaSanPhamRequest;
import com.example.demo.entity.DanhGiaSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface DanhGiaSanPhamService {

    List<DanhGiaSanPhamCustom> getElementRequired();

    List<DanhGiaSanPham> getAllPageble( Integer page);

    List<DanhGiaSanPhamCustom> getAllHienThi( Integer page );

    List<DanhGiaSanPhamCustom> getHienThiSp( Integer page, String tenSp );

    List<DanhGiaSanPhamCustom> getHienThiSpLength(String tenSp);

    List<DanhGiaSanPhamCustom> getAllXuLy( Integer page );

    List<DanhGiaSanPhamCustom> getAllAn( Integer page );

    List<DanhGiaSanPhamCustom> getLength(String by);

    Boolean updateTrangThai(List<Integer> listId , Integer trangThai);

    DanhGiaSanPham add( DanhGiaSanPhamRequest request, String tenSP);

    DanhGiaSanPham update( DanhGiaSanPhamRequest request, Integer id);

//    public DanhGiaSanPham add( DanhGiaSanPhamRequest request, LocalDate addDay, String addBy );
//
//    public DanhGiaSanPham update( DanhGiaSanPhamRequest request, LocalDate updateDay, String updateBy);

    DanhGiaSanPham delete(Integer id);

    List<DanhGiaSanPhamCustom> getFromSp(Integer by, Integer id);

    List<Integer> getIdChuaMua(Integer id);

    Boolean isExits(Integer idKh, String tenSp);

}
