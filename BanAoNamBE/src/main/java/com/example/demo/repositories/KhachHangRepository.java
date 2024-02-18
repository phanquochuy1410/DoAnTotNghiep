package com.example.demo.repositories;

import com.example.demo.dto.gh.GioHangChiTietCustom;
import com.example.demo.dto.gh.GioHangChiTietRequest;
import com.example.demo.dto.khachhang.KhachHangCheckOutCustom;
import com.example.demo.dto.khachhang.KhachHangCustom;
import com.example.demo.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, Integer> {
    @Query(value = "select kh.id  as idKhachHang, kh.ten as tenKhachHang from KhachHang kh" +
            " where kh.email like ?1 and kh.matKhau like ?2")
    Optional<KhachHangCustom> loginKhachHang(String email, String passWord);

    Optional<KhachHang> findByEmail(String email);

    @Query(value = "select kh.id as idKH , kh.email as email , kh.ten  as ten ,kh.tenDem as tenDem , kh.ho as ho , kh.soDienThoai as sdt from KhachHang kh" +
            " where kh.email like ?1")
    Optional<KhachHangCheckOutCustom> loadKHCheckOut(String email);

    @Query(value = "SELECT Top 1" +
            " kh.ma as maKhachHang , kh.id as idKhachHang FROM khach_hang kh" +
            " ORDER BY kh.id DESC", nativeQuery = true)
    KhachHangCustom findLast();

    List<KhachHang> findBySoDienThoai(String sdt);

    @Query(value = "select  kh.id as idKH , count(ghct.id_gio_hang) as soLuongSanPhamInGioHang from gio_hang gh join gio_hang_chi_tiet ghct on gh.id = ghct.id_gio_hang join khach_hang kh on kh.id = gh.id_khach_hang \n" +
            "where kh.email = ?1 and ghct.trang_thai = 1 group by kh.id" , nativeQuery = true)
    List<GioHangChiTietCustom> getSoLuongSanPham(String email);
}
