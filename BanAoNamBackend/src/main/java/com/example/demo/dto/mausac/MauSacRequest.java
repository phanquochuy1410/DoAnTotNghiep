package com.example.demo.dto.mausac;


import com.example.demo.entity.MauSac;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MauSacRequest {

    String ma;

    @NotBlank
    String ten;

    public MauSac map ( MauSac mauSac ) {
        mauSac.setMa(this.getMa());
        mauSac.setTen(this.getTen());
        return mauSac;
    }
}
