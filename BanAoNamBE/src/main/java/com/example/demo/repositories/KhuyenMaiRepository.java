package com.example.demo.repositories;

import com.example.demo.dto.khuyenmai.KhuyenMaiCustom;
import com.example.demo.dto.khuyenmai.SearchKhuyenMai;
import com.example.demo.entity.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, Integer> {

    @Query(value = "select id , ma, ten, ngay_bat_dau as 'NgayBatDau', ngay_ket_thuc as 'NgayKetThuc', \n" +
            "chiet_khau as 'ChietKhau', trang_thai as 'TrangThai', create_at as 'NgayTao', \n" +
            "update_at as 'NgayCapNhat'\n" +
            "from khuyen_mai where trang_thai = 0", nativeQuery = true)
    List<KhuyenMaiCustom> viewCbbKm();

    @Query("select km.id as id, km.ma as ma, km.ten as ten, km.ngayBatDau as ngayBatDau, km.ngayKetThuc as ngayKetThuc, " +
            " km.chietKhau as chietKhau, km.trangThai as trangThai, km.create_at as ngayTao, " +
            " km.update_at as ngayCapNhat " +
            "from KhuyenMai km where ((km.ten like %:#{#x.tenSearch}% OR :#{#x.tenSearch} IS NULL))" +
            "and (km.trangThai = :#{#x.trangThaiSearch} OR :#{#x.trangThaiSearch} IS NULL)"
    )
    List<KhuyenMaiCustom> timKiem(@Param("x") SearchKhuyenMai x);

    List<KhuyenMai> findAllByNgayKetThucIsBefore( LocalDate now );

    @Query(value = "select * from khuyen_mai\n" +
            "where ngay_bat_dau < GETDATE() and ngay_ket_thuc > GETDATE()", nativeQuery = true)
    List<KhuyenMai> findMiddle();

}
