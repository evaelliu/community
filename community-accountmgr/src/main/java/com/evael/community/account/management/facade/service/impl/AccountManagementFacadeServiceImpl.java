package com.evael.community.account.management.facade.service.impl;

import com.evael.community.account.management.facade.service.AccountManagementFacadeService;
import com.evael.community.account.management.domain.Account;
import com.evael.community.account.management.domain.AccountRepository;
import com.evael.community.account.management.domain.AccountStatus;
import com.evael.community.account.management.facade.dto.AccountDTOAssembler;
import com.evael.community.account.management.facade.dto.AccountDetailDTO;
import com.evael.community.account.management.facade.dto.PageData;
import com.evael.community.account.management.service.AccountManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @Author jiyou
 * @Date  2018/2/7.
 */
@Service
@RequiredArgsConstructor
public class AccountManagementFacadeServiceImpl implements AccountManagementFacadeService {

    private final AccountManagementService accountManagementService;
    private final AccountRepository accountRepository;

    @Override
    public AccountDetailDTO getAccountDetailInfo(Long accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        return AccountDTOAssembler.convertAccountToAccountDetailDTO(account);

    }

    @Override
    public AccountDetailDTO getAccountDetailInfo(String username) {
        Account account = accountManagementService.getAccountByKey(username);
        return AccountDTOAssembler.convertAccountToAccountDetailDTO(account);
    }

    @Override
    public PageData<List<AccountDetailDTO>> list(String status,String keyword,int pageNum,int pageSize) {
        PageRequest pageRequest =  PageRequest.of(pageNum-1, pageSize);
        AccountStatus[] statuses;
        Page<Account> accounts;
        if(StringUtils.isEmpty(status)) {
            statuses = AccountStatus.values();
        }else
            statuses = new AccountStatus[]{AccountStatus.valueOf(status)};
        accounts = accountRepository.findAllByStatusAndRolesIsNullAndNickNameLike(statuses,keyword,pageRequest);
        return new PageData<List<AccountDetailDTO>>(accounts,AccountDTOAssembler.convertAccountToAccountDetailDTO(accounts.getContent()));
    }

}
