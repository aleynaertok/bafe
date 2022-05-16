package com.bafe.dto;


import com.bafe.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResultDto {

    private Boolean admin;
    private Boolean activity;

    public LoginResultDto(Account account) {
        this.admin = account.getAdmin();
        this.activity = account.getActivity();
    }


}
