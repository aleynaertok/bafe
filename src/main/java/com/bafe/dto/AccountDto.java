package com.bafe.dto;

import com.bafe.model.Account;
import lombok.*;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AccountDto extends BaseDto {


    private String password;
    private String mail;


    public AccountDto(Account account) {
        super(account.getId());
        this.password = account.getPassword();
        this.mail = account.getMail();

    }
}
