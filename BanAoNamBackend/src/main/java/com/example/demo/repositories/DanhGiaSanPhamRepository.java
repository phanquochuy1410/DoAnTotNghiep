package com.example.demo.repositories;

import com.example.demo.dto.dgsp.DanhGiaSanPhamCustom;
import com.example.demo.entity.DanhGiaSanPham;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DanhGiaSanPhamRepository extends JpaRepository<DanhGiaSanPham, Integer> {

    @Query(value = "select dgsp.id as N'Id', sp.ten as N'SanPham', kh.email as N'KhachHang', \n" +
            "noi_dung as 'NoiDung', so_sao as 'SoSao', dgsp.trang_thai as 'TrangThai', dgsp.ngay_tao as N'NgayDang' from danh_gia_san_pham dgsp \n" +
            "join khach_hang kh on dgsp.id_khach_hang = kh.id\n" +
            "join san_pham sp on dgsp.id_san_pham = sp.id\n" +
            "where dgsp.trang_thai = 1 order by dgsp.ngay_tao desc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getAllHienThi( Pageable pageable );

    @Query(value = "select dgsp.id as N'Id', sp.ten as N'SanPham', kh.email as N'KhachHang', \n" +
            "noi_dung as 'NoiDung', so_sao as 'SoSao', dgsp.trang_thai as 'TrangThai', dgsp.ngay_tao as N'NgayDang' from danh_gia_san_pham dgsp \n" +
            "join khach_hang kh on dgsp.id_khach_hang = kh.id\n" +
            "join san_pham sp on dgsp.id_san_pham = sp.id\n" +
            "where dgsp.trang_thai = 1 and sp.ten = :tenSp order by dgsp.ngay_tao desc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getHienThiSanPham( Pageable pageable, @Param ("tenSp") String tenSp );

    @Query(value = "select dgsp.id as N'Id', sp.ten as N'SanPham', kh.email as N'KhachHang', \n" +
            "noi_dung as 'NoiDung', so_sao as 'SoSao', dgsp.trang_thai as 'TrangThai', dgsp.ngay_tao as N'NgayDang' from danh_gia_san_pham dgsp \n" +
            "join khach_hang kh on dgsp.id_khach_hang = kh.id\n" +
            "join san_pham sp on dgsp.id_san_pham = sp.id\n" +
            "where dgsp.trang_thai = 1 and sp.ten = :tenSp order by dgsp.ngay_tao desc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getHienThiSanPhamLength(@Param ("tenSp") String tenSp );

    @Query(value = "select dgsp.id as N'Id', sp.ten as N'SanPham', kh.email as N'KhachHang', \n" +
            "noi_dung as 'NoiDung', so_sao as 'SoSao', dgsp.trang_thai as 'TrangThai', dgsp.ngay_tao as N'NgayDang' from danh_gia_san_pham dgsp \n" +
            "full join khach_hang kh on dgsp.id_khach_hang = kh.id\n" +
            "join san_pham sp on dgsp.id_san_pham = sp.id\n" +
            "where dgsp.trang_thai = 0 order by dgsp.ngay_tao desc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getAllXuLy( Pageable pageable );

    @Query(value = "select dgsp.id as N'Id', sp.ten as N'SanPham', kh.email as N'KhachHang', \n" +
            "noi_dung as 'NoiDung', so_sao as 'SoSao', dgsp.trang_thai as 'TrangThai', dgsp.ngay_tao as N'NgayDang' from danh_gia_san_pham dgsp \n" +
            "join khach_hang kh on dgsp.id_khach_hang = kh.id\n" +
            "join san_pham sp on dgsp.id_san_pham = sp.id\n" +
            "where dgsp.trang_thai = 2 order by dgsp.ngay_tao desc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getAllAn (Pageable pageable );

    @Query(value = "select dgsp.id as N'Id', sp.ten as N'SanPham', kh.email as N'KhachHang', \n" +
            "noi_dung as 'NoiDung', so_sao as 'SoSao', dgsp.trang_thai as 'TrangThai', dgsp.ngay_tao as N'NgayDang' from danh_gia_san_pham dgsp \n" +
            "join khach_hang kh on dgsp.id_khach_hang = kh.id\n" +
            "join san_pham sp on dgsp.id_san_pham = sp.id\n" +
            "where dgsp.trang_thai = 1 order by dgsp.ngay_tao desc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getLengthHienThi();


    @Query(value = "select dgsp.id as N'Id', sp.ten as N'SanPham', kh.email as N'KhachHang', \n" +
            "noi_dung as 'NoiDung', so_sao as 'SoSao', dgsp.trang_thai as 'TrangThai', dgsp.ngay_tao as N'NgayDang' from danh_gia_san_pham dgsp \n" +
            "full join khach_hang kh on dgsp.id_khach_hang = kh.id\n" +
            "join san_pham sp on dgsp.id_san_pham = sp.id\n" +
            "where dgsp.trang_thai = 0 order by dgsp.ngay_tao desc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getLengthXuLy();

    @Query(value = "select dgsp.id as N'Id', sp.ten as N'SanPham', kh.email as N'KhachHang', \n" +
            "noi_dung as 'NoiDung', so_sao as 'SoSao', dgsp.trang_thai as 'TrangThai', dgsp.ngay_tao as N'NgayDang' from danh_gia_san_pham dgsp \n" +
            "join khach_hang kh on dgsp.id_khach_hang = kh.id\n" +
            "join san_pham sp on dgsp.id_san_pham = sp.id\n" +
            "where dgsp.trang_thai = 2 order by dgsp.ngay_tao desc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getLengthAn ();

    @Query(value = "select kh.email as 'KhachHang', noi_dung as 'NoiDung', ngay_tao as 'NgayDang',\n" +
            "dg.trang_thai as 'TrangThai', so_sao as 'SoSao'\n" +
            "from danh_gia_san_pham dg\n" +
            "join khach_hang kh on dg.id_khach_hang = kh.id\n" +
            "where id_san_pham = :tt order by so_sao desc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getFromSpDesc( @Param ("tt") Integer tt);

    @Query(value = "select kh.email as 'KhachHang', noi_dung as 'NoiDung', ngay_tao as 'NgayDang',\n" +
            "dg.trang_thai as 'TrangThai', so_sao as 'SoSao'\n" +
            "from danh_gia_san_pham dg\n" +
            "join khach_hang kh on dg.id_khach_hang = kh.id\n" +
            "where id_san_pham = :tt order by so_sao asc", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getFromSpAsc( @Param ("tt") Integer tt);

    @Query(value = "select dg.id as 'Id', kh.email as 'KhachHang', noi_dung as 'NoiDung', ngay_tao as 'NgayDang',\n" +
            "dg.trang_thai as 'TrangThai', so_sao as 'SoSao'\n" +
            "from danh_gia_san_pham dg\n" +
            "join khach_hang kh on dg.id_khach_hang = kh.id\n" +
            "where id_san_pham = :tt order by dg.ngay_tao desc ", nativeQuery = true)
    List<DanhGiaSanPhamCustom> getFromSpNgayTaoAsc( @Param ("tt") Integer tt);

    @Query(value = "select sp.id from san_pham sp where ten not in (select sp.ten from khach_hang kh\n" +
            "join hoa_don hd on hd.id_khach_hang = kh.id\n" +
            "join hoa_don_chi_tiet hdct on hdct.id_hoa_don = hd.id\n" +
            "join chi_tiet_san_pham ctsp on ctsp.id = hdct.id_chi_tiet_san_pham\n" +
            "join san_pham sp on ctsp.id_san_pham = sp.id\n" +
            "join kich_co kc on kc.id = ctsp.id_kich_co\n" +
            "join mau_sac ms on ms.id = ctsp.id_mau_sac\n" +
            "where kh.id = :id\n" +
            "group by sp.ten)", nativeQuery = true)
    List<Integer> getIdChuaMua(@Param("id") Integer id);

    @Query(value = "select dgsp.id from danh_gia_san_pham dgsp \n" +
            "join san_pham sp on dgsp.id_san_pham = sp.id\n" +
            "join khach_hang kh on dgsp.id_khach_hang = kh.id\n" +
            "where sp.id = :idSp and kh.id = :idKh and dgsp.trang_thai = 1", nativeQuery = true)
    Optional<Integer> getDgspDaComment( @Param("idKh") Integer idKh,
                                       @Param("idSp") Integer idSp);

}
