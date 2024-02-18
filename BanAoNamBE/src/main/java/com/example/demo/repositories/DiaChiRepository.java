package com.example.demo.repositories;

import com.example.demo.dto.diachi.DiaChiCustom;
import com.example.demo.dto.khachhang.KhachHangCustom;
import com.example.demo.entity.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, Integer> {

    @Query(value = " select dc.thanhPho as thanhPho , dc.huyen as quan , dc.xa as phuong , dc.diaChiCuThe as dcct ,dc.id as id from KhachHang kh" +
            " inner join DiaChi dc on dc.id = kh.idDiaChi.id" +
            " where kh.email = ?1")
    DiaChiCustom getListDC(String email);

    @Query(value = "SELECT Top 1" +
            " kh.ma as ma , kh.id as id FROM dia_chi kh" +
            " ORDER BY kh.id DESC", nativeQuery = true)
    DiaChiCustom findLast();
}
