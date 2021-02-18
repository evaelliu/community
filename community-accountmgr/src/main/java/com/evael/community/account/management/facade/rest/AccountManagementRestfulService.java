package com.evael.community.account.management.facade.rest;

import com.evael.community.account.management.facade.dto.AccountDTOAssembler;
import com.evael.community.account.management.facade.dto.AccountRegisterDTO;
import com.evael.community.account.management.facade.dto.AccountUpdateDTO;
import com.evael.community.account.management.facade.dto.UpdatePasswordDTO;
import com.evael.community.account.management.facade.service.AccountManagementFacadeService;
import com.evael.community.account.management.service.AccountManagementService;
import com.evael.community.account.management.domain.Account;
import com.evael.community.account.management.facade.dto.*;
import com.evael.community.common.dto.ResponseDTO;
import com.evael.community.common.util.ParamValidateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author jiyou
 * @Date  2016/3/4.
 */
@RestController
@RequiredArgsConstructor
public class AccountManagementRestfulService {

    private final AccountManagementService accountManagementService;
    private final AccountManagementFacadeService accountManagementFacadeService;

    @RequestMapping(value = "/api/account/login", method = RequestMethod.GET)
    public ResponseDTO getLoginUser(@RequestHeader(required = false) String username) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(accountManagementFacadeService.getAccountDetailInfo(username));
        return responseDTO;
    }

    @RequestMapping(value = "/api/account/{accountId}", method = RequestMethod.GET)
    public ResponseDTO getAccountInfo( @PathVariable Long accountId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(accountManagementFacadeService.getAccountDetailInfo(accountId));
        return responseDTO;
    }


    /**
     * replace /api/user/detail in user manager
     *
     */
    @RequestMapping(value = "/api/account/detail", method = RequestMethod.GET)
    public ResponseDTO getLoginUserInfo(@RequestHeader(required = false) String username) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(accountManagementFacadeService.getAccountDetailInfo(username));
        return responseDTO;
    }

    @RequestMapping(value = "/api/account/password", method = RequestMethod.PUT)
    public ResponseDTO changeMyPassword(@RequestHeader(required = false) String username,
            @RequestBody UpdatePasswordDTO updatePasswordDTO) {

        ParamValidateUtils.checkNotNull(updatePasswordDTO.getNewPassword(), "newPassword is required");
        accountManagementService.changePassword(
                username,
                updatePasswordDTO.getOldPassword(),
                updatePasswordDTO.getNewPassword()
        );
        return new ResponseDTO();
    }

    @RequestMapping(value = "/api/account",method = RequestMethod.POST)
    public ResponseDTO createNewUser(@RequestBody AccountRegisterDTO accountRegisterDTO){
        ParamValidateUtils.checkNotNull(accountRegisterDTO);
        ParamValidateUtils.checkNotNull(accountRegisterDTO.getPassword());
        Account account = AccountDTOAssembler.convertAccountRegisterDTO2Account(accountRegisterDTO);
        account = accountManagementService.registerNewAccount(account,accountRegisterDTO.getPassword());
        return new ResponseDTO(AccountDTOAssembler.convertAccountToAccountDetailDTO(account));
    }

    @RequestMapping(value = "/api/account",method = RequestMethod.GET)
    public ResponseDTO list(@RequestParam(required = false,defaultValue = "AVAILABLE") String status,
                            @RequestParam(required = false,defaultValue = "") String keyword,
                            @RequestParam(required = false,defaultValue = "1") int page,
                            @RequestParam(required = false,defaultValue = "10") int size){
        return new ResponseDTO(accountManagementFacadeService.list(status,keyword,page,size));
    }

    @RequestMapping(value = "/api/account/{accountId}/enable",method = RequestMethod.PUT)
    public ResponseDTO enableAccount(@PathVariable Long accountId){
        accountManagementService.enableAccount(accountId);
        return new ResponseDTO();
    }

    @PutMapping("/api/account/disable")
    public ResponseDTO disableAccount(@RequestBody List<Long> accountIds){
        if(!CollectionUtils.isEmpty(accountIds))
            accountIds.parallelStream().forEach(accountManagementService::disableAccount);
        return new ResponseDTO();
    }

    @RequestMapping(value = "/api/account/{accountId}/disable",method = RequestMethod.PUT)
    public ResponseDTO disableAccount(@PathVariable Long accountId){
        accountManagementService.disableAccount(accountId);
        return new ResponseDTO();
    }

    @RequestMapping(value = "/api/account",method = RequestMethod.PUT)
    public ResponseDTO update(@RequestBody AccountUpdateDTO accountDetailDTO){
        Account account = accountManagementService.update(accountDetailDTO.getAccountId(), AccountDTOAssembler.convertAccountDTO2Account(accountDetailDTO),accountDetailDTO.getPassword());
        return new ResponseDTO(AccountDTOAssembler.convertAccountToAccountDetailDTO(account));
    }


    @PostMapping("/api/account/import")
    public ResponseDTO importData(@RequestPart MultipartFile importFile)  {
        return new ResponseDTO();
    }

    @GetMapping("/api/account/export")
    public void get(@RequestParam(required = false) String keyword,HttpServletResponse response) throws IOException {

        response.reset();
        response.addHeader("Content-Disposition", "test");
        response.addHeader("Content-Length", "0");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("service/octet-stream;charset=UTF-8");

        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.print("test");
        outputStream.flush();
        outputStream.close();
    }

}
