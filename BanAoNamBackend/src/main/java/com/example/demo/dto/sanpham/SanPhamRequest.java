package com.example.demo.dto.sanpham;



import com.example.demo.entity.SanPham;
import com.example.demo.entity.TheLoai;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SanPhamRequest {

    String ma;

    @NotBlank
    String ten;

    public SanPham map ( SanPham sanPham ) {
        sanPham.setMa(this.getMa());
        sanPham.setTen(this.getTen());
        return sanPham;
    }
}
