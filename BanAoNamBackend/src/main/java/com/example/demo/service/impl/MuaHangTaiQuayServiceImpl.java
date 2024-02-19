package com.example.demo.service.impl;

import com.example.demo.config.InHoaDon;
import com.example.demo.config.PdfInvoiceGenerator;
import com.example.demo.dto.HoaDonChiTiet.HoaDonChiTietRequest;
import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.dto.hoadon.HoaDonCustom;
import com.example.demo.dto.hoadon.HoaDonRequest;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.KhachHang;
import com.example.demo.entity.NhanVien;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.repositories.MuaHangTaiQuayRepository;
import com.example.demo.repositories.NhanVienRepository;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.MuaHangTaiQuayService;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MuaHangTaiQuayServiceImpl implements MuaHangTaiQuayService {

    @Autowired
    private MuaHangTaiQuayRepository repository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonService hoaDonService;


    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private NhanVienRepository repoNV;

    @Override
    public List<ChiTietSanPhamCustom> hienThiSanPham(final SearchThuocTinh searchThuocTinh, Integer page) {
        Pageable pageable = PageRequest.of(page, 1000);
        return repository.hienThiSanPham(searchThuocTinh, pageable);
    }

    private String genMaHD() {
        HoaDonCustom hoaDonCustom = hoaDonRepository.findLast();
        int i = 0;
        while (i < hoaDonCustom.getIdHoaDon()) {
            i++;
            if (hoaDonCustom.getMaHoaDon().equalsIgnoreCase("HD" + i)) {
                int a = i + 1;
                System.out.println(a);
                return "HD" + a;
            }
        }
        return null;
    }

    @Override
    public HoaDonChiTiet addSanPhamIntoHoaDon(Integer idHoaDon, Integer idChiTietSanPham) {
        HoaDon hoaDon = hoaDonRepository.findById(idHoaDon).get();
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(idChiTietSanPham).get();
        List<HoaDonChiTiet> list = hoaDonChiTietRepository.findAllByIdHoaDon(hoaDon);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdChiTietSanPham().getId().equals(idChiTietSanPham)) {
                HoaDonChiTiet hoaDonChiTiet = list.get(i);
                hoaDonChiTiet.setSoLuong(list.get(i).getSoLuong() + 1);
                updateSoLuongTon(idChiTietSanPham);
                return hoaDonChiTietRepository.save(hoaDonChiTiet);
            }
        }
        HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                .idHoaDon(hoaDon)
                .idChiTietSanPham(chiTietSanPham)
                .soLuong(1)
                .giaSauKhuyenMai(chiTietSanPham.getGiaKhuyenMai().floatValue())
                .gia(chiTietSanPham.getGia().floatValue())
                .create_at(LocalDateTime.now())
                .trangThai(0)
                .build();
        updateSoLuongTon(idChiTietSanPham);
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }


    private void updateSoLuongTon(Integer idChiTietSanPham) {
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(idChiTietSanPham).get();
        chiTietSanPham.setSoLuong(chiTietSanPham.getSoLuong() - 1);
        chiTietSanPham.setUpdate_at(LocalDateTime.now());
        chiTietSanPhamRepository.save(chiTietSanPham);
    }


    @Override
    public HoaDon addHoaDon(String email) {
        NhanVien nhanVien = repoNV.findByEmail(email);
        HoaDon hoaDon = HoaDon.builder()
                .ma(genMaHD())
                .idNhanVien(nhanVien)
                .idKhachHang(KhachHang.builder().id(8).build())
                .trangThai(7)
                .ngayTao(LocalDate.now())
                .create_at(LocalDate.now())
                .update_at(LocalDate.now())
                .update_by(nhanVien.getTen())
                .build();
        return hoaDonService.save(hoaDon);
    }

    @Override
    public List<HoaDonCustom> findMaHoaDon() {
        return repository.findMaHoaDon();
    }

//    @Override
//    public HoaDonChiTiet updateSoLuong(HoaDonChiTietRequest request, Integer id) {
//        Optional<HoaDonChiTiet> optional = Optional.of(hoaDonChiTietRepository.findById(id).orElse(null));
//        return optional.map(o -> {
//            o.setSoLuong(request.getSoLuong());
//            o.setGia(request.getGia());
//            return hoaDonChiTietRepository.save(o);
//        }).orElse(null);
//    }

    @Override
    public HoaDonChiTiet updateSoLuong(HoaDonChiTiet request, Integer id) {
        HoaDonChiTiet hoaDonChiTiet = hoaDonChiTietRepository.getReferenceById(id);
        hoaDonChiTiet.setGiaSauKhuyenMai(request.getGiaSauKhuyenMai());
        hoaDonChiTiet.setCreate_at(request.getCreate_at());
        hoaDonChiTiet.setSoLuong(request.getSoLuong());
        hoaDonChiTiet.setGia(request.getGia());
        return hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public HoaDon thanhToan(HoaDonRequest request) {

        Optional<HoaDon> optional = hoaDonRepository.findById(request.getId());
        HoaDon hoaDon = optional.get();
        hoaDon.setTenNguoiNhan(request.getTenNguoiNhan());
        hoaDon.setGia(request.getTongTien());
        hoaDon.setTrangThai(1);
        hoaDon.setUpdate_at(LocalDate.now());
        hoaDon.setNgayNhan(LocalDate.now());
        hoaDon.setHinhThucThanhToan(3);
        hoaDon.setTenNguoiNhan(request.getTenNguoiNhan());
        hoaDon.setGia(request.getTongTien());
        hoaDon.setTrangThai(1);
        hoaDon.setUpdate_at(LocalDate.now());
        hoaDon.setNgayNhan(LocalDate.now());
        hoaDon.setHinhThucThanhToan(3);
        try {
            List<HoaDonChiTiet> hoaDonChiTiet = hoaDonChiTietRepository.findAllByIdHoaDon(hoaDon);
            String downloadFolder = System.getProperty("user.home") + File.separator + "Downloads";
            String filePath = downloadFolder + File.separator + hoaDon.getMa() + ".pdf";
            InHoaDon.genHoaDon(filePath, hoaDon, hoaDonChiTiet);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    public Integer soLuongTon(Integer id) {
        return repository.getSoLuong(id);
    }

    @Override
    public Integer soLuong() {
        return repository.soLuong();
    }
}
