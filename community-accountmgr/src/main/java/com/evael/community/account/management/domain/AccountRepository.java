package com.evael.community.account.management.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author jiyou
 * @Date  2016/9/20.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findOneByRegisterName(String registerName);
    @Query("from Account a where a.status in :statuses and (a.nickName like %:keyword% or a.registerName like %:keyword%) and a.roles is null")
    Page<Account> findAllByStatusAndRolesIsNullAndNickNameLike(AccountStatus[] statuses,String keyword ,Pageable pageable);
}
