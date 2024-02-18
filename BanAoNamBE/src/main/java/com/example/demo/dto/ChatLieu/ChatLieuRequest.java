package com.example.demo.dto.ChatLieu;

import com.example.demo.entity.ChatLieu;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatLieuRequest {

    String ma;

    @NotBlank
    String ten;

    public ChatLieu map ( ChatLieu cl ) {
        cl.setMa(this.getMa());
        cl.setTen(this.getTen());
        return cl;
    }
}
