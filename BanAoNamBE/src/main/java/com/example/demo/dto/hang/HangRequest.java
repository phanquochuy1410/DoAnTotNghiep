package com.example.demo.dto.hang;

import com.example.demo.entity.Hang;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HangRequest {

    String ma;

    @NotBlank
    String ten;

    public Hang map ( Hang hang ) {
        hang.setMa(this.getMa());
        hang.setTen(this.getTen());
        return hang;
    }
}
