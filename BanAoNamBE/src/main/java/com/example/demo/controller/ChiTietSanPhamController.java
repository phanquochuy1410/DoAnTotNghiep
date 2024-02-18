package com.example.demo.controller;

import com.example.demo.dto.ctsp.ChiTietSanPhamCustom;
import com.example.demo.dto.ctsp.ChiTietSanPhamImport;
import com.example.demo.dto.ctsp.ChiTietSanPhamRequest;
import com.example.demo.dto.cuaHang.CuaHangCustum;
import com.example.demo.dto.cuaHang.SearchThuocTinh;
import com.example.demo.entity.TheLoai;
import com.example.demo.service.ChiTietSanPhamService;
import com.example.demo.service.CuaHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/chi-tiet-san-pham")
@CrossOrigin({"*"})
public class ChiTietSanPhamController {
    @Autowired
    private ChiTietSanPhamService service;

    @Autowired
    private CuaHangService cuaHangService;

    @GetMapping ()
    public ResponseEntity<?> getElementRequired () {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping ( "/get-all" )
    public ResponseEntity<?> getAllPageable ( @RequestParam ( defaultValue = "0", name = "page" ) Integer page ) {
        return ResponseEntity.ok(service.getAllPageble(page));
    }

    @GetMapping ( "/get-all-no-page" )
    public ResponseEntity<?> getAllNoPageable () {
        return ResponseEntity.ok(service.getAllNoPage());
    }

    @GetMapping ( "/fe-khach-hang/get-khuyen-mai" )
    public ResponseEntity<?> getKhuyenMai ( @RequestParam ( defaultValue = "0", name = "page" ) Integer page ) {
        return ResponseEntity.ok(service.getKhuyenMai(page));
    }

    @GetMapping ( "/fe-khach-hang/mau-sac" )
    public ResponseEntity<?> getMauSacCTSP ( @RequestParam (name = "tenSP" ) String tenSP ) {
        return ResponseEntity.ok(service.getMauSacCTSP(tenSP));
    }

    @GetMapping ( "/fe-khach-hang/kich-co" )
    public ResponseEntity<?> getMauSacCTSP ( @RequestParam (name = "tenSP" ) String tenSP, @RequestParam("tenMS") String tenMS ) {
        return ResponseEntity.ok(service.getKichCoCTSP(tenSP, tenMS));
    }

    @GetMapping ( "/fe-khach-hang/all-anh" )
    public ResponseEntity<?> getAllAnh ( @RequestParam (name = "tenSP" ) String tenSP, @RequestParam("tenMS") String tenMS ) {
        return ResponseEntity.ok(service.getAllAnh(tenSP, tenMS));
    }

    @GetMapping ( "/fe-khach-hang/change-detail" )
    public ResponseEntity<?> getChangeDetail ( @RequestParam (name = "tenSP" ) String tenSP,
                                               @RequestParam("ms") Integer ms,
                                               @RequestParam(name = "kc") Integer kc) {
        return ResponseEntity.ok(service.getChangeDetail(tenSP, ms, kc));
    }

    @GetMapping ( "/fe-khach-hang/get-all-khuyen-mai" )
    public ResponseEntity<?> getAllKhuyenMai () {
        return ResponseEntity.ok(service.getAllKhuyenMai());
    }

    @GetMapping ( "/get-top-8" )
    public ResponseEntity<?> getTop8 (@RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.getTop8(page));
    }

    @GetMapping ( "/get-san-pham-km" )
    public ResponseEntity<?> getSanPhamKhuyenMai (@RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.hienThiSanPhamKhuyenMai(page));
    }

    @GetMapping ( "/get-top-new-4" )
    public ResponseEntity<?> getTop4 ( ) {
        return ResponseEntity.ok(service.getTop4New());
    }

    @GetMapping ( "/find-all" )
    public ResponseEntity<?> findAll (  ) {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping ( "/get-by/{id}" )
    public ResponseEntity<?> getById ( @PathVariable("id") String id) {
        return ResponseEntity.ok(service.getById(Integer.parseInt(id)));
    }


    @GetMapping ( "/search" )
    public ResponseEntity<?> search ( @RequestParam(required = false) String name,
                                      @RequestParam( defaultValue = "0", name = "page" )  Integer page,
                                      @RequestParam(defaultValue = "0", name = "min") Integer min,
                                      @RequestParam(defaultValue = "0", name = "max") Integer max,
                                      @RequestParam(defaultValue = "-1", name = "tt") Integer tt,
                                      @RequestParam ( defaultValue = "all", name = "by" ) String by) {
        return ResponseEntity.ok(service.findByAll(name, page, min,max,tt,by));
    }

    @GetMapping ( "/search-length" )
    public ResponseEntity<?> searchLength ( @RequestParam(required = false) String name,
                                      @RequestParam(defaultValue = "0", name = "min") Integer min,
                                      @RequestParam(defaultValue = "0", name = "max") Integer max,
                                      @RequestParam(defaultValue = "-1", name = "tt") Integer tt,
                                      @RequestParam ( defaultValue = "all", name = "by" ) String by) {
        return ResponseEntity.ok(service.findByAllLength(name, min,max,tt,by));
    }

    @GetMapping ( "/so-luong-ton" )
    public ResponseEntity<?> soLuongTon ( @RequestParam(name = "tenSp") String tenSp,
                                            @RequestParam( name = "tenKc") String tenKc,
                                            @RequestParam ( name = "tenMs" ) String tenMs) {
        return ResponseEntity.ok(service.getSLTon(tenSp, tenKc, tenMs));
    }


    @PostMapping ( "/add" )
    public ResponseEntity<?> add ( @RequestBody @Valid ChiTietSanPhamRequest request , BindingResult result ) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(service.add(request));
        }
    }

    @PostMapping ( "/excel-add-all" )
    public ResponseEntity<?> addAll ( @RequestBody List<ChiTietSanPhamImport> request) {
        return ResponseEntity.ok(service.addAll(request));
    }

    @PostMapping ( "/add-many-variable" )
    public ResponseEntity<?> addManyVariable ( @RequestBody List<ChiTietSanPhamRequest> listRequest) {
        return ResponseEntity.ok(service.addManyVariable(listRequest));
    }


    @PutMapping ( "/update/{id}" )
    public ResponseEntity<?> update ( @PathVariable("id") String id ,@RequestBody @Valid ChiTietSanPhamRequest request , BindingResult result ) {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.ok(errors);
        } else {
            return ResponseEntity.ok(service.update(request, Integer.parseInt(id)));
        }

    }

    @PutMapping ( "/update-trang-thai/{id}" )
    public ResponseEntity<?> update ( @PathVariable("id") Integer id, @RequestParam("tt") Integer tt ) {
            return ResponseEntity.ok(service.updateTrangThai(id, tt));
    }

    @DeleteMapping ( "/delete/{id}" )
    public ResponseEntity<?> delete ( @PathVariable ( "id" ) Integer id ) {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/search-list-sp")
    private ResponseEntity<?> searchSP (@RequestParam String ten){
        return ResponseEntity.ok(service.searchListSP(ten));
    }

    @GetMapping("/get-so-luong-ton")
    private ResponseEntity<?> getSlTon (@RequestParam("lid") List<Integer> sl){
        return ResponseEntity.ok(service.getSoLuongTon(sl));
    }

    @GetMapping("/ctsp-search")
    public ResponseEntity<List<ChiTietSanPhamCustom>> ctspSearch( final SearchThuocTinh searchThuocTinh , @RequestParam(defaultValue = "0") Integer page) {
        return ResponseEntity.ok(service.ctspSearch(searchThuocTinh , page));
    }

    @GetMapping("/ctsp-search-length")
    public ResponseEntity<?> ctspSearchLength( final SearchThuocTinh searchThuocTinh) {
        return ResponseEntity.ok(service.ctspSearchLength(searchThuocTinh));
    }

}
