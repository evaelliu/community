package com.evael.community.auth.domain.clientdetails;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Author jiyou
 * @Date  2016/6/17.
 */
public interface ClientDetailRepository extends JpaRepository<ClientDetailInfo,Long> {
    Optional<ClientDetailInfo> findOneByClientId(String clientId);
}
