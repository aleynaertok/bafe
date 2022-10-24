package com.bafe.controller;

import com.bafe.dto.AccountDto;
import com.bafe.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class AccountController {


    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody AccountDto accountDto) {

        accountService.createAccount(accountDto);
        return ResponseEntity.ok().build();


    }


    @PutMapping("/updateMail/{id}")
    public ResponseEntity<Void> updateMail(@PathVariable Long id, @RequestBody AccountDto accountDto) {

        accountService.updateMail(accountDto, id);

        return ResponseEntity.ok().build();


    }


    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<AccountDto> updatePassword(@PathVariable Long id, @RequestBody AccountDto accountDto) {

        return ResponseEntity.ok(accountService.updatePassword(accountDto, id));


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();


    }


}
