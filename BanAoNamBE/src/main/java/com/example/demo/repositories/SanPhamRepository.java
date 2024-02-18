package com.example.demo.repositories;

import com.example.demo.dto.sanpham.SanPhamCustom;
import com.example.demo.entity.SanPham;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "group by sp.id, sp.ten order by count(dg.id) desc ", nativeQuery = true)
    List<SanPhamCustom> getDGByCountDesc( Pageable pageable);

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "group by sp.id, sp.ten order by count(dg.id) asc ", nativeQuery = true)
    List<SanPhamCustom> getDGByCountAsc( Pageable pageable);

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "group by sp.id, sp.ten order by (sum(dg.so_sao)/count(dg.id)) asc", nativeQuery = true)
    List<SanPhamCustom> getDGBySumAsc( Pageable pageable);

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "group by sp.id, sp.ten order by (sum(dg.so_sao)/count(dg.id)) desc", nativeQuery = true)
    List<SanPhamCustom> getDGBySumDesc( Pageable pageable);

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "where sp.ten like %:ten% group by sp.id, sp.ten ", nativeQuery = true)
    List<SanPhamCustom> getDGByTen( Pageable pageable,
                                    @Param("ten") String ten);

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "group by sp.id, sp.ten order by count(dg.id) desc ", nativeQuery = true)
    List<SanPhamCustom> getDGByCountDescLength();

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "group by sp.id, sp.ten order by count(dg.id) asc ", nativeQuery = true)
    List<SanPhamCustom> getDGByCountAscLength();

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "group by sp.id, sp.ten order by (sum(dg.so_sao)/count(dg.id)) asc", nativeQuery = true)
    List<SanPhamCustom> getDGBySumAscLength();

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "group by sp.id, sp.ten order by (sum(dg.so_sao)/count(dg.id)) desc", nativeQuery = true)
    List<SanPhamCustom> getDGBySumDescLength();

    @Query(value = "select sp.id as 'Id', sp.ten as 'Ten', count(dg.id) as 'DanhGia', \n" +
            "sum(dg.so_sao) as 'TongSao' from san_pham sp \n" +
            "full join danh_gia_san_pham dg on sp.id = dg.id_san_pham\n" +
            "where sp.ten like %:ten% group by sp.id, sp.ten ", nativeQuery = true)
    List<SanPhamCustom> getDGByTenLength(@Param("ten") String ten);

    @Query(value = "select km.chiet_khau as 'ChietKhau' from san_pham sp\n" +
            "join khuyen_mai km on sp.id_khuyen_mai = km.id\n" +
            "where sp.id = :id", nativeQuery = true)
    Integer getChietKhau(@Param("id") Integer sp);

    SanPham getSanPhamByTen(String ten);

    @Query(value = "select sp.id from san_pham sp \n" +
            "join khuyen_mai km on sp.id_khuyen_mai = km.id\n" +
            "where km.ngay_ket_thuc < GETDATE()", nativeQuery = true)
    List<Integer> getIdSpHH();

    @Query("SELECT sp FROM SanPham sp where sp.ten like %:searchTen% ")
    List<SanPham> searchTen(@Param("searchTen") String ten);

}
