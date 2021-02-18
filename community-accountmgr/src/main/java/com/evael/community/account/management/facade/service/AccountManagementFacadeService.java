package com.evael.community.account.management.facade.service;

import com.evael.community.account.management.facade.dto.AccountDetailDTO;
import com.evael.community.account.management.facade.dto.PageData;

import java.util.List;

/**
 * @Author jiyou
 * @Date  2018/2/7.
 */
public interface AccountManagementFacadeService {

    AccountDetailDTO getAccountDetailInfo(Long accountId);
    AccountDetailDTO getAccountDetailInfo(String username);
    PageData<List<AccountDetailDTO>> list(String status,String keyword, int page , int size);
}
