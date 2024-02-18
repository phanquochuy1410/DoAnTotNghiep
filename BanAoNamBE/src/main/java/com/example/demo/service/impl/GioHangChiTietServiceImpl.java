package com.example.demo.service.impl;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.ctsp.ChiTietSanPhamRequest;
import com.example.demo.dto.gh.GioHangChiTietCustom;
import com.example.demo.dto.gh.GioHangChiTietRequest;
import com.example.demo.dto.gh.GioHangCustom.GioHangCustom;
import com.example.demo.dto.hoadon.HoaDonCustom;
import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.GioHang;
import com.example.demo.entity.GioHangChiTiet;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import com.example.demo.entity.KhachHang;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.repositories.GioHangChiTietRepository;
import com.example.demo.repositories.GioHangRepository;
import com.example.demo.repositories.HoaDonChiTietRepository;
import com.example.demo.repositories.HoaDonRepository;
import com.example.demo.service.GioHangChiTietService;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.KhachHangService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {
    @Autowired
    private GioHangChiTietRepository repo;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private GioHangRepository GHrepo;

    @Autowired
    private HoaDonChiTietService hoa;

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private KhachHangServiceImpl khachHangServiceImpl;


    @Override
    public GioHangChiTiet update(List<GioHangChiTietRequest> request, Integer trangThai) {
        for (int i = 0; i < request.size(); i++) {
            Optional<GioHangChiTiet> optional = repo.findById(Integer.valueOf(request.get(i).getId()));
            GioHangChiTiet gioHangChiTiet = optional.get();
            gioHangChiTiet.setSoLuong(request.get(i).getSoLuong());
            gioHangChiTiet.setGia(request.get(i).getThanhTien());
            gioHangChiTiet.setUpdate_at(LocalDateTime.now());
            gioHangChiTiet.setTrangThai(trangThai);
            repo.save(gioHangChiTiet);
        }
        return null;
    }

    @Override
    public GioHangChiTiet deleteSP(List<GioHangChiTietRequest> requests) {
        for (int i = 0; i < requests.size(); i++) {
            Optional<GioHangChiTiet> gioHangChiTiet = repo.findById(requests.get(i).getId());
            repo.delete(gioHangChiTiet.get());
        }
        return null;
    }

    @Override
    public GioHangChiTiet update1(GioHangChiTietRequest request, Integer trangThai) {
        Optional<GioHangChiTiet> optional = repo.findById(Integer.valueOf(request.getId()));
        GioHangChiTiet gioHangChiTiet = optional.get();
        gioHangChiTiet.setSoLuong(request.getSoLuong());
        gioHangChiTiet.setGia(request.getThanhTien());
        gioHangChiTiet.setUpdate_at(LocalDateTime.now());
        gioHangChiTiet.setTrangThai(trangThai);
        repo.save(gioHangChiTiet);
        return null;
    }

    @Override
    public ChiTietSanPhamCustom findSP(String tenSP, Integer mauSac, Integer kichCo) {
        Optional<ChiTietSanPhamCustom> chiTietSanPhamCustom = repo.getSP(tenSP, mauSac, kichCo);
        if (chiTietSanPhamCustom.isEmpty()) {
            return null;
        } else {
            return chiTietSanPhamCustom.get();
        }
    }

    @Override
    public GioHangChiTiet addToCart(ChiTietSanPhamRequest request) {
        Optional<GioHangCustom> gioHang = GHrepo.findByIdKhachHang_Email(khachHangServiceImpl.emailKhachHang);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(request.getId()).get();
        GioHangChiTietCustom gioHangChiTietCustom = repo.getGHCT(request.getId(), khachHangServiceImpl.emailKhachHang).orElse(null);
        if (gioHang.isEmpty()) {
            GioHang newGioHang = GioHang.builder()
                    .trangThai(0)
                    .ma(genMaGH())
                    .idKhachHang(khachHangService.loadKhachHang())
                    .create_at(LocalDateTime.now())
                    .create_by(khachHangService.loadKhachHang().getTen())
                    .build();
            GHrepo.save(newGioHang);
            GioHangChiTiet newGioHangChiTiet = GioHangChiTiet.builder()
                    .idGioHang(newGioHang)
                    .gia(Float.valueOf(request.getGia()))
                    .idChiTietSanPham(chiTietSanPham)
                    .soLuong(Integer.valueOf(request.getSoLuong()))
                    .create_at(LocalDateTime.now())
                    .create_by(khachHangService.loadKhachHang().getTen())
                    .trangThai(1)
                    .build();
            repo.save(newGioHangChiTiet);
        } else {
            if (gioHangChiTietCustom == null) {
                GioHangChiTiet newGioHangChiTiet = GioHangChiTiet.builder()
                        .idGioHang(GHrepo.findById(gioHang.get().getId()).get())
                        .gia(Float.valueOf(request.getGia()))
                        .idChiTietSanPham(chiTietSanPham)
                        .soLuong(Integer.valueOf(request.getSoLuong()))
                        .create_at(LocalDateTime.now())
                        .create_by(khachHangService.loadKhachHang().getTen())
                        .trangThai(1)
                        .build();
                repo.save(newGioHangChiTiet);
            } else {
                if (gioHangChiTietCustom.getTrangThai() == 3) {
                    GioHangChiTiet newGioHangChiTiet = GioHangChiTiet.builder()
                            .idGioHang(GHrepo.findById(gioHang.get().getId()).get())
                            .gia(Float.valueOf(request.getGia()))
                            .idChiTietSanPham(chiTietSanPham)
                            .soLuong(Integer.valueOf(request.getSoLuong()))
                            .create_at(LocalDateTime.now())
                            .create_by(khachHangService.loadKhachHang().getTen())
                            .trangThai(1)
                            .build();
                    repo.save(newGioHangChiTiet);
                } else {
                    GioHangChiTiet newGioHangChiTiet = GioHangChiTiet.builder()
                            .idGioHang(GHrepo.findById(gioHang.get().getId()).get())
                            .gia(Float.valueOf(request.getGia()) * (Integer.valueOf(request.getSoLuong()) + gioHangChiTietCustom.getSoLuong()))
                            .idChiTietSanPham(chiTietSanPham)
                            .id(gioHangChiTietCustom.getId())
                            .soLuong(Integer.valueOf(request.getSoLuong()) + gioHangChiTietCustom.getSoLuong())
                            .create_at(LocalDateTime.now())
                            .create_by(khachHangService.loadKhachHang().getTen())
                            .trangThai(1)
                            .build();
                    repo.save(newGioHangChiTiet);
                }
            }
        }
        return null;
    }

    @Override
    public GioHangChiTiet addToCart1(ChiTietSanPhamRequest request) {
        Optional<GioHangCustom> gioHang = GHrepo.findByIdKhachHang_Email(khachHangServiceImpl.emailKhachHang);
        ChiTietSanPham chiTietSanPham = chiTietSanPhamRepository.findById(request.getId()).get();
        GioHangChiTietCustom gioHangChiTietCustom = repo.getGHCT(request.getId(), khachHangServiceImpl.emailKhachHang).orElse(null);
        if (gioHang.isEmpty()) {
            GioHang newGioHang = GioHang.builder()
                    .trangThai(0)
                    .ma(genMaGH())
                    .idKhachHang(khachHangService.loadKhachHang())
                    .create_at(LocalDateTime.now())
                    .create_by(khachHangService.loadKhachHang().getTen())
                    .build();
            GHrepo.save(newGioHang);
            GioHangChiTiet newGioHangChiTiet = GioHangChiTiet.builder()
                    .idGioHang(newGioHang)
                    .gia(Float.valueOf(request.getGia()))
                    .idChiTietSanPham(chiTietSanPham)
                    .soLuong(Integer.valueOf(request.getSoLuong()))
                    .create_at(LocalDateTime.now())
                    .create_by(khachHangService.loadKhachHang().getTen())
                    .trangThai(2)
                    .build();
            repo.save(newGioHangChiTiet);
        } else {
            if (gioHangChiTietCustom == null) {
                GioHangChiTiet newGioHangChiTiet = GioHangChiTiet.builder()
                        .idGioHang(GHrepo.findById(gioHang.get().getId()).get())
                        .gia(Float.valueOf(request.getGia()))
                        .idChiTietSanPham(chiTietSanPham)
                        .soLuong(Integer.valueOf(request.getSoLuong()))
                        .create_at(LocalDateTime.now())
                        .create_by(khachHangService.loadKhachHang().getTen())
                        .trangThai(2)
                        .build();
                repo.save(newGioHangChiTiet);
            } else {
                if (gioHangChiTietCustom.getTrangThai() == 3) {
                    GioHangChiTiet newGioHangChiTiet = GioHangChiTiet.builder()
                            .idGioHang(GHrepo.findById(gioHang.get().getId()).get())
                            .gia(Float.valueOf(request.getGia()))
                            .idChiTietSanPham(chiTietSanPham)
                            .soLuong(Integer.valueOf(request.getSoLuong()))
                            .create_at(LocalDateTime.now())
                            .create_by(khachHangService.loadKhachHang().getTen())
                            .trangThai(2)
                            .build();
                    repo.save(newGioHangChiTiet);
                } else {
                    GioHangChiTiet newGioHangChiTiet = GioHangChiTiet.builder()
                            .idGioHang(GHrepo.findById(gioHang.get().getId()).get())
                            .gia(Float.valueOf(request.getGia()) * (Integer.valueOf(request.getSoLuong()) + gioHangChiTietCustom.getSoLuong()))
                            .idChiTietSanPham(chiTietSanPham)
                            .id(gioHangChiTietCustom.getId())
                            .soLuong(Integer.valueOf(request.getSoLuong()) + gioHangChiTietCustom.getSoLuong())
                            .create_at(LocalDateTime.now())
                            .create_by(khachHangService.loadKhachHang().getTen())
                            .trangThai(2)
                            .build();
                    repo.save(newGioHangChiTiet);
                }
            }
        }
        return null;
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

    private String genMaGH() {
        HoaDonCustom hoaDonCustom = GHrepo.findLast();
        int i = 0;
        while (i < hoaDonCustom.getIdHoaDon()) {
            i++;
            if (hoaDonCustom.getMaHoaDon().equalsIgnoreCase("GH" + i)) {
                int a = i + 1;
                System.out.println(a);
                return "GH" + a;
            }
        }
        return null;
    }


    @Override
    public HoaDonChiTiet chotDon(List<GioHangChiTietRequest> list, Float tongTien, String tenNguoiNhan, String
            soNguoiNhan) {
        HoaDon hoaDon = chotDon2(tongTien, tenNguoiNhan, soNguoiNhan);
        Optional<KhachHang> khachHang = Optional.of(khachHangService.loadKhachHang());
        KhachHang kh = khachHang.get();
        for (int i = 0; i < list.size(); i++) {
            HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                    .gia(list.get(i).getGia())
                    .giaSauKhuyenMai(list.get(i).getKhuyenMai())
                    .idChiTietSanPham(chiTietSanPhamRepository.findById(list.get(i).getIdCTSP()).get())
                    .soLuong(list.get(i).getSoLuong())
                    .idHoaDon(hoaDon)
                    .trangThai(1)
                    .create_at(LocalDateTime.now())
                    .create_by(kh.getTen())
                    .build();
            update1(list.get(i), 3);
            updateSoLuongTon(hoaDonChiTiet.getSoLuong() , hoaDonChiTiet.getIdChiTietSanPham().getId());
            hoa.save(hoaDonChiTiet);
            Optional<GioHangChiTiet> gioHangChiTiet = repo.findById(list.get(i).getId());
            removeDonHang(gioHangChiTiet.get());
        }
        return null;
    }

    @Override
    public HoaDonChiTiet chotDonVnpay1(List<GioHangChiTietRequest> list, Float tongTien, String
            tenNguoiNhan, String soNguoiNhan) {
        HoaDon hoaDon = chotDonVnpay2(tongTien, tenNguoiNhan, soNguoiNhan);
        Optional<KhachHang> khachHang = Optional.of(khachHangService.loadKhachHang());
        KhachHang kh = khachHang.get();
        for (int i = 0; i < list.size(); i++) {
            HoaDonChiTiet hoaDonChiTiet = HoaDonChiTiet.builder()
                    .gia(list.get(i).getGia())
                    .giaSauKhuyenMai(list.get(i).getKhuyenMai())
                    .idChiTietSanPham(chiTietSanPhamRepository.findById(list.get(i).getIdCTSP()).get())
                    .soLuong(list.get(i).getSoLuong())
                    .idHoaDon(hoaDon)
                    .trangThai(1)
                    .create_at(LocalDateTime.now())
                    .create_by(kh.getTen())
                    .build();
            update1(list.get(i), 3);
            updateSoLuongTon(hoaDonChiTiet.getSoLuong() , hoaDonChiTiet.getIdChiTietSanPham().getId());
            hoa.save(hoaDonChiTiet);
            Optional<GioHangChiTiet> gioHangChiTiet = repo.findById(list.get(i).getId());
            removeDonHang(gioHangChiTiet.get());
        }
        return null;
    }

    @Override
    public String chotDonVnpay(Integer tongTien, String orderInfor, String urlReturn) {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
        String orderType = "order-type";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(tongTien * 100));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", orderInfor);
        vnp_Params.put("vnp_OrderType", orderType);

        String locate = "vn";
        vnp_Params.put("vnp_Locale", locate);

        urlReturn += VNPayConfig.vnp_Returnurl;
        vnp_Params.put("vnp_ReturnUrl", urlReturn);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        return paymentUrl;
    }

    public int orderReturn(HttpServletRequest request) {
        Map fields = new HashMap();
        for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        if (fields.containsKey("vnp_SecureHashType")) {
            fields.remove("vnp_SecureHashType");
        }
        if (fields.containsKey("vnp_SecureHash")) {
            fields.remove("vnp_SecureHash");
        }
        String signValue = VNPayConfig.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    @Override
    public void removeDonHang(GioHangChiTiet gioHangChiTiet) {
        repo.deleteById(gioHangChiTiet.getId());
    }

    @Override
    public HoaDon chotDon2(Float tongTien, String tenNguoiNhan, String soNguoiNhan) {
        Optional<KhachHang> khachHang = Optional.of(khachHangService.loadKhachHang());
        if (tenNguoiNhan.equalsIgnoreCase("Không có") && soNguoiNhan.equalsIgnoreCase("Không có")) {
            KhachHang kh = khachHang.get();
            HoaDon hoaDon = HoaDon.builder()
                    .idKhachHang(kh)
                    .gia(tongTien)
                    .ma(genMaHD())
                    .trangThai(0)
                    .tenNguoiNhan(kh.getHo() + " " + kh.getTenDem() + " " + kh.getTen())
                    .soDienThoai(kh.getSoDienThoai())
                    .ngayTao(LocalDate.now())
                    .create_at(LocalDate.now())
                    .update_at(LocalDate.now())
                    .hinhThucThanhToan(0)
                    .build();
            return hoaDonService.save(hoaDon);
        } else {
            KhachHang kh = khachHang.get();
            HoaDon hoaDon = HoaDon.builder()
                    .idKhachHang(kh)
                    .gia(tongTien)
                    .ma(genMaHD())
                    .trangThai(0)
                    .tenNguoiNhan(tenNguoiNhan)
                    .soDienThoai(soNguoiNhan)
                    .ngayTao(LocalDate.now())
                    .create_at(LocalDate.now())
                    .update_at(LocalDate.now())
                    .hinhThucThanhToan(0)
                    .build();
            return hoaDonService.save(hoaDon);
        }
    }

    @Override
    public HoaDon chotDonVnpay2(Float tongTien, String tenNguoiNhan, String soNguoiNhan) {
        Optional<KhachHang> khachHang = Optional.of(khachHangService.loadKhachHang());
        if (tenNguoiNhan.equalsIgnoreCase("Không có") && soNguoiNhan.equalsIgnoreCase("Không có")) {
            KhachHang kh = khachHang.get();
            HoaDon hoaDon = HoaDon.builder()
                    .idKhachHang(kh)
                    .gia(tongTien)
                    .ma(genMaHD())
                    .trangThai(0)
                    .tenNguoiNhan(kh.getHo() + " " + kh.getTenDem() + " " + kh.getTen())
                    .soDienThoai(kh.getSoDienThoai())
                    .ngayTao(LocalDate.now())
                    .create_at(LocalDate.now())
                    .update_at(LocalDate.now())
                    .hinhThucThanhToan(1)
                    .build();
            return hoaDonService.save(hoaDon);
        } else {
            KhachHang kh = khachHang.get();
            HoaDon hoaDon = HoaDon.builder()
                    .idKhachHang(kh)
                    .gia(tongTien)
                    .ma(genMaHD())
                    .trangThai(0)
                    .tenNguoiNhan(tenNguoiNhan)
                    .soDienThoai(soNguoiNhan)
                    .ngayTao(LocalDate.now())
                    .create_at(LocalDate.now())
                    .update_at(LocalDate.now())
                    .hinhThucThanhToan(1)
                    .build();
            return hoaDonService.save(hoaDon);
        }
    }

    @Override
    public List<GioHangChiTietCustom> getListGioHang(String email) {
        return repo.getPageGH(email);
    }

    @Override
    public GioHangChiTiet update(GioHangChiTietRequest chiTiets) {
        Optional<GioHangChiTiet> optional = repo.findById(Integer.valueOf(chiTiets.getId()));
        GioHangChiTiet gioHangChiTiet = optional.get();
        gioHangChiTiet.setSoLuong(chiTiets.getSoLuong());
        gioHangChiTiet.setGia(chiTiets.getGia() * chiTiets.getSoLuong());
        gioHangChiTiet.setUpdate_at(LocalDateTime.now());
        gioHangChiTiet.setTrangThai(1);
        return repo.save(gioHangChiTiet);
    }


    private void updateSoLuongTon(Integer soLuong, Integer idChiTietSanPham) {
        Optional<ChiTietSanPham> chiTietSanPham = chiTietSanPhamRepository.findById(idChiTietSanPham);
        ChiTietSanPham chiTiet = new ChiTietSanPham();
        chiTiet.setSoLuong(chiTietSanPham.get().getSoLuong() - soLuong);
        chiTiet.setId(idChiTietSanPham);
        chiTiet.setIdHang(chiTietSanPham.get().getIdHang());
        chiTiet.setIdMauSac(chiTietSanPham.get().getIdMauSac());
        chiTiet.setCreate_at(chiTietSanPham.get().getCreate_at());
        chiTiet.setCreate_by(chiTietSanPham.get().getCreate_by());
        chiTiet.setIdChatLieu(chiTietSanPham.get().getIdChatLieu());
        chiTiet.setIdKichCo(chiTietSanPham.get().getIdKichCo());
        chiTiet.setIdTheLoai(chiTietSanPham.get().getIdTheLoai());
        chiTiet.setIdSanPham(chiTietSanPham.get().getIdSanPham());
        chiTiet.setGia(chiTietSanPham.get().getGia());
        chiTiet.setTrangThai(chiTietSanPham.get().getTrangThai());
        chiTiet.setUpdate_at(LocalDateTime.now());
        chiTiet.setMa(chiTietSanPham.get().getMa());
        chiTiet.setAnh(chiTietSanPham.get().getAnh());
        chiTiet.setAnh2(chiTietSanPham.get().getAnh2());
        chiTiet.setAnh3(chiTietSanPham.get().getAnh3());
        chiTiet.setAnh4(chiTietSanPham.get().getAnh4());
        chiTiet.setAnh5(chiTietSanPham.get().getAnh5());
        chiTiet.setGiaKhuyenMai(chiTietSanPham.get().getGiaKhuyenMai());
        chiTiet.setMota(chiTietSanPham.get().getMota());
        chiTiet.setUpdate_at(LocalDateTime.now());
        chiTietSanPhamRepository.save(chiTiet);
    }

    @Override
    public List<GioHangChiTietCustom> getListGHCheckout(String email) {
        return repo.getSPGHCheckOut(email);
    }

}
