package com.bafe.controller;

import com.bafe.dto.AccountDto;
import com.bafe.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class AccountController {

    //Post bir şeyleri body ile çağırmak için ya da create işlemleri.
    //Put güncelleme işlemleri için.
    //Deletemapping direkt delete işlemleri.
    //Getmapping veri almak için

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

   /* @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){

        return ResponseEntity.ok(acc)

    }

    */

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {

        return ResponseEntity.ok(accountService.createAccount(accountDto));

      /*  try{

            return new ResponseDto(accountService.createAccount(accountDto));

        }
        catch (RuntimeException runtimeException){

            return new ResponseDto(runtimeException.getMessage());

        }

            */
    }



    /*
    @PutMapping("/{id}")
    public ResponseDto updateAccount(@PathVariable Long id,@RequestBody AccountDto accountDto){

        try{
            accountService.updateAccount(accountDto,id);
            return new ResponseDto();
        }

        catch(RuntimeException runtimeException){
            return new ResponseDto(runtimeException.getMessage());
        }

    }

     */


    @PutMapping("/updateMail/{id}")
    public ResponseEntity<AccountDto> updateMail(@PathVariable Long id, @RequestBody AccountDto accountDto) {

        return ResponseEntity.ok(accountService.updateMail(accountDto, id));



        /*
        try{
            accountService.updateMail(accountDto,id);
            return new ResponseDto();

        }
        catch(RuntimeException runtimeException){
            return new ResponseDto(runtimeException.getMessage());
        }
        */

    }


    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<AccountDto> updatePassword(@PathVariable Long id, @RequestBody AccountDto accountDto) {

        return ResponseEntity.ok(accountService.updatePassword(accountDto, id));

                /*
        try {
            accountService.updatePassword(accountDto, id);
            return new ResponseDto();

        } catch (RuntimeException runtimeException) {
            return new ResponseDto(runtimeException.getMessage());
        }

                 */

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();


    }


}
