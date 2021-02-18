package com.evael.community.account.management.service.impl;

import com.evael.community.account.management.domain.Account;
import com.evael.community.account.management.domain.AccountRepository;
import com.evael.community.account.management.domain.AccountStatus;
import com.evael.community.account.management.domain.exception.AccountNotExistException;
import com.evael.community.account.management.domain.exception.DuplicatedUserException;
import com.evael.community.account.management.domain.exception.IncorrectUserPasswordException;
import com.evael.community.account.management.service.AccountManagementService;
import com.evael.community.account.management.domain.*;
import com.evael.community.account.management.domain.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @Author jiyou
 * @Date  2016/9/22.
 */
@Service
@RequiredArgsConstructor
public class AccountManagementServiceImpl implements AccountManagementService {

    private static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();;

    private final AccountRepository accountRepository;

    @Override
    public void resetPassword(Long accountId, String newPassword) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotExistException(accountId));
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        Account account = getAccountByKey(username);
        if (account == null)
            throw new AccountNotExistException(username);
        changePassword(oldPassword, newPassword, account);
    }

    @Override
    public void changePassword(Long accountId, String oldPassword, String newPassword) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotExistException(accountId));
        changePassword(oldPassword, newPassword, account);
    }

    private void changePassword(String oldPassword, String newPassword, Account account) {
        String passwordInDB = account.getPassword();
        if ((oldPassword == null || oldPassword.isEmpty())) {
            changePassword(account, newPassword);
        } else if (passwordEncoder.matches(oldPassword, passwordInDB)) {
            changePassword(account, newPassword);
        } else {
            throw new IncorrectUserPasswordException(account.getRegisterName(), oldPassword);
        }
    }

    private void changePassword(Account account, String newPassword) {
        account.setPassword(passwordEncoder.encode(newPassword));
        accountRepository.save(account);
    }

    @Override
    public Account getAccountByKey(String key) {
        return accountRepository.findOneByRegisterName(key);
    }

    @Override
    public Account registerNewAccount(Account account, String password) {
        checkAccountExist(account);
        account.generateNextId();
        account.setPassword(passwordEncoder.encode(password));
        return accountRepository.save(account);
    }

    @Override
    public void enableAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotExistException(accountId));
        if(account.getStatus().equals(AccountStatus.AVAILABLE))
            return;
        account.setStatus(AccountStatus.AVAILABLE);
        accountRepository.save(account);
    }

    @Override
    public void disableAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotExistException(accountId));
        if(account.getStatus().equals(AccountStatus.UNAVAILABLE))
            return;
        account.setStatus(AccountStatus.UNAVAILABLE);
        accountRepository.save(account);
    }

    @Override
    public Account update(Long accountId, Account account,String newPassword) {
        Account originalAccount = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotExistException(accountId));
        checkAccountExist(account);
        if(!StringUtils.isEmpty(account.getNickName()))
            originalAccount.setNickName(account.getNickName());
        if(!StringUtils.isEmpty(newPassword))
            changePassword(originalAccount,newPassword);
        return accountRepository.save(originalAccount);
    }

    private void checkAccountExist(Account account) {
        Account existsAccount=null;
        existsAccount = accountRepository.findOneByRegisterName(account.getRegisterName());
        if(existsAccount!=null&&existsAccount.getStatus().equals(AccountStatus.AVAILABLE))
            throw new DuplicatedUserException(account.getRegisterName());
        if(existsAccount!=null)
            account.setId(existsAccount.getId());
    }
}
