package com.example.demo.controller;

import com.example.demo.dto.ctsp.ChiTietSanPhamRequest;
import com.example.demo.dto.nhanvien.NhanVienRequest;
import com.example.demo.entity.NhanVien;
import com.example.demo.service.NhanVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/nhan-vien")
@CrossOrigin({"*"})
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;
    private List<NhanVien> listDL;
    private List<NhanVien> listNV;

    private int index = 0;


    @GetMapping("/hien-thi/dang-lam")
    public ResponseEntity<?> getAllDangLam(@RequestParam(name = "page", defaultValue = "0") int page) {
        Integer trangThai = 1;
        listDL = nhanVienService.getAllByTrangThai(trangThai, page);
        return ResponseEntity.ok(listDL);
    }


    @GetMapping("/hien-thi/nghi-viec")
    public ResponseEntity<?> getAllNghiViec(@RequestParam(name = "page", defaultValue = "0") int page) {
        Integer trangThai = 0;
        listNV = nhanVienService.getAllByTrangThai(trangThai, page);
        return ResponseEntity.ok(listNV);
    }

    @GetMapping("/load-nhan-vien")
    public ResponseEntity<?> loadNhanVien() {
        return ResponseEntity.ok(nhanVienService.loadNhanVien());
    }

    @GetMapping("/login-nhan-vien")
    public ResponseEntity<?> loginNhanVien(@RequestParam("email") String email, @RequestParam("password") String passWord) {
        return ResponseEntity.ok(nhanVienService.loginNhanVien(email, passWord));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid NhanVienRequest request, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            List<NhanVien> existingNhanViens = nhanVienService.getAllNhanVien();
            index = existingNhanViens.size();
            String ma = String.format("NV%02d", index);
            request.setMa(ma);
            return ResponseEntity.ok(nhanVienService.add(request));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody @Valid NhanVienRequest request, BindingResult result) {

        return ResponseEntity.ok(nhanVienService.update(request, Integer.parseInt(id)));


    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(nhanVienService.delete(id));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(nhanVienService.detail(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDL(@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam("sdtSearch") String sdt,
                                      @RequestParam("trangThai") Integer trangThai) {
        return ResponseEntity.ok(nhanVienService.searchDL(trangThai, sdt, page));
    }

    @PutMapping("/update-trang-thai")
    public ResponseEntity<?> updateTrangThai(@RequestParam("listID") List<Integer> idTT , @RequestParam("trangThai") Integer trangThai){
        return ResponseEntity.ok(nhanVienService.updateTrangThai(idTT , trangThai));
    }

}
