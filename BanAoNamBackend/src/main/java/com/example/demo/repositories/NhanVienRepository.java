package com.example.demo.repositories;

import com.example.demo.dto.khachhang.KhachHangCustom;
import com.example.demo.dto.nhanvien.NhanVienCustom;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {

    Page<NhanVien> findByTrangThai(Integer trangThai, Pageable pageable);

    @Query(value = "select nv.id  as id, nv.ten as ten , nv.chucVu as chucVu from NhanVien nv" +
            " where nv.email like ?1 and nv.matKhau like ?2")
    Optional<NhanVienCustom> loginNhanVien(String email, String passWord);

    NhanVien findByEmail(String email);

    Page<NhanVien> findByTrangThaiAndSoDienThoai(Integer trangThai, String sdt, Pageable pageable);
}
