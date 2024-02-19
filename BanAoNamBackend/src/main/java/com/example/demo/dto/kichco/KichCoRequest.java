package com.example.demo.dto.kichco;

import com.example.demo.entity.KichCo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KichCoRequest {

    String ma;

    @NotBlank
    String ten;

    public KichCo map ( KichCo kichCo ) {
        kichCo.setMa(this.getMa());
        kichCo.setTen(this.getTen());
        return kichCo;
    }

}
