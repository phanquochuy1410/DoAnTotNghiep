package com.example.demo.service;

import com.example.demo.dto.HoaDonChiTiet.DonMuaKhachHang;
import com.example.demo.dto.hoadon.DonHangCustom;
import com.example.demo.dto.hoadon.HoaDonCustom;
import com.example.demo.dto.hoadon.HoaDonRequest;
import com.example.demo.dto.hoadon.HoaDonSearch;
import com.example.demo.entity.HoaDon;
import org.springframework.data.domain.Page;

import java.util.List;


public interface HoaDonService {

    HoaDon save(HoaDon hoaDon);

    Page<HoaDonCustom> getPage(Integer pageNo);

    List<HoaDonCustom> getAll();

    HoaDon updateHoaDon(HoaDonRequest request);

    List<HoaDonCustom> getAllHD();

    List<HoaDonCustom> getAllXL();

    List<DonHangCustom> findAllHDByEmail(String email);

    List<DonHangCustom> findAllHDByEmailAndTrangThai(String email, Integer trangThai);

    List<DonHangCustom> findAllHDByEmailAndIdHoaDon(String email, Integer id);

    HoaDon updateDonHang(Integer id, Integer trangThai, String lyDo);

    HoaDon xuLyHoanTra(Integer idHoaDon);

    Page<HoaDonCustom> findHoaDonByTrangThai(Integer i, Integer pageNo);

    Page<HoaDonCustom> findHoaDonHuyHoan(Integer i, Integer pageNo);

    Page<HoaDonCustom> findHoaDonXacNhan(Integer i, Integer pageNo);

    List<HoaDonCustom> findHoaDonByMa(Integer trangThai, String ma);

    List<HoaDonCustom> searchHuyHoan(Integer trangThai, String ma);

    List<HoaDonCustom> findHoaDonChoXacNhanByMa(Integer trangThai, String ma);

    List<HoaDonCustom> timKiemNgay(HoaDonSearch hoaDonSearch);

    List<HoaDonCustom> timKiemNgay1(HoaDonSearch hoaDonSearch);

    List<HoaDonCustom> timKiemKhoangNgay(HoaDonSearch hoaDonSearch);

    List<HoaDonCustom> timKiemKhoangNgay1(HoaDonSearch hoaDonSearch);

    List<HoaDon> updateTrangThai(List<Integer> listId, Integer trangThai, String email, String lyDo);

    List<HoaDonCustom> findAllHDDH();

}
