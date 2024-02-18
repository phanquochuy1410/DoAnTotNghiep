package com.example.demo.repositories;

import com.example.demo.dto.gh.GioHangCustom.GioHangCustom;
import com.example.demo.dto.hoadon.HoaDonCustom;
import com.example.demo.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GioHangRepository extends JpaRepository<GioHang, Integer> {

    Optional<GioHangCustom> findByIdKhachHang_Email(String email);

    List<GioHang> findAllByIdKhachHang_Email(String email);


    @Query(value = "SELECT Top 1" +
            " gh.ma as maHoaDon , gh.id as idHoaDon FROM gio_hang gh" +
            " ORDER BY gh.id DESC", nativeQuery = true)
    HoaDonCustom findLast();
}
