package com.evael.community.account.management.facade.dto;

import com.evael.community.account.management.domain.Account;
import com.evael.community.account.management.domain.AccountStatus;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author jiyou
 * @Date  2016/9/23.
 */
public class AccountDTOAssembler {

    public static AccountDetailDTO convertAccountToAccountDetailDTO(Account account) {
        if(account==null)
            return null;
        AccountDetailDTO accountDetailDTO = new AccountDetailDTO();
        accountDetailDTO.setId(account.getId());
        accountDetailDTO.setRegisterName(account.getRegisterName());
        accountDetailDTO.setStatus(String.valueOf(account.getStatus()));
        //accountDetailDTO.setAvatarUrl(account.getAvatarUrl());
        accountDetailDTO.setNickName(account.getNickName());
        accountDetailDTO.setRegisterName(account.getRegisterName());
        return accountDetailDTO;
    }

    public static List<AccountDetailDTO> convertAccountToAccountDetailDTO(List<Account> accounts){
        List<AccountDetailDTO> accountDetailDTOS = new ArrayList<>();
        if (accounts!=null && accounts.size()!=0)
            return accounts.stream().map(AccountDTOAssembler::convertAccountToAccountDetailDTO).collect(Collectors.toList());
        return accountDetailDTOS;
    }

    public static Account convertAccountRegisterDTO2Account(AccountRegisterDTO accountRegisterDTO){
        Account account = new Account();
        account.generateNextId();
        account.setPassword(accountRegisterDTO.getPassword());
        String registerName=accountRegisterDTO.getRegisterName();

        account.setRegisterName(registerName);
        account.setNickName(StringUtils.isEmpty(accountRegisterDTO.getNickName())?registerName:accountRegisterDTO.getNickName());
        account.setStatus(AccountStatus.AVAILABLE);
        //account.setAvatarUrl(accountRegisterDTO.getAvatarUrl());
        return account;
    }


    public static Account convertAccountDTO2Account(AccountUpdateDTO accountDetailDTO){
        Account account = new Account();
        account.generateNextId();

        account.setNickName(accountDetailDTO.getNickName());
        //account.setAvatarUrl(accountDetailDTO.getAvatarUrl());
        return account;
    }
}
