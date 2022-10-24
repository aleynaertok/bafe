package com.bafe.controller;


import com.bafe.dto.AccountDto;
import com.bafe.dto.LoginResultDto;
import com.bafe.service.ILoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {

    private final ILoginService loginService;

    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<LoginResultDto> login(@RequestBody AccountDto accountDto) {


        return ResponseEntity.ok(loginService.login(accountDto));


    }


}
