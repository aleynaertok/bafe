package com.bafe.repository;

import com.bafe.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Account, String> {
    Optional<Account> findAccountByMailAndPassword(String mail, String password);

}
