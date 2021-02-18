package com.evael.community.auth.domain.accountDetails;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author jiyou
 * @Date  2018/3/2.
 */
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findAccountByRegisterName(String registerName);
}
