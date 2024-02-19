package com.example.demo.dto.theloai;


import com.example.demo.entity.TheLoai;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheLoaiRequest {

    String ma;

    @NotBlank
    String ten;

    public TheLoai map ( TheLoai theLoai ) {
        theLoai.setMa(this.getMa());
        theLoai.setTen(this.getTen());
        return theLoai;
    }
}
