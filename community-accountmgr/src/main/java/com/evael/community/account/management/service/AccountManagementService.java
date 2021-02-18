package com.evael.community.account.management.service;

import com.evael.community.account.management.domain.Account;


/**
 * @Author jiyou
 * @Date  2016/9/20.
 */
public interface AccountManagementService {

    void changePassword(String username, String oldPassword, String newPassword);
    void changePassword(Long accountId, String oldPassword, String newPassword);
    void resetPassword(Long accountId, String newPassword);
    Account getAccountByKey(String key);
    Account registerNewAccount(Account account,String password);
    void enableAccount(Long accountId);
    void disableAccount(Long accountId);
    Account update(Long accountId, Account account,String newPassword);
}
