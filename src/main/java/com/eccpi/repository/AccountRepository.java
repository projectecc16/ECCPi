package com.eccpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eccpi.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByUsername(String username);
	//Account findOne(Long id);
    //Account save(Account account);
	@Modifying
    @Query("UPDATE Account a SET a.keyStore.privateKey = :privateKey, a.keyStore.publicKey = :publicKey  WHERE a.id = :accountId")
    int updateKey(@Param("accountId") Long accountId, @Param("privateKey") String privateKey, @Param("publicKey") String publicKey);
    
	@Modifying
	@Query("UPDATE Account a SET a.keyStore.remoteKey = :remoteKey,a.keyStore.sharedKey = :sharedKey  WHERE a.id = :accountId")
    int updateRemoteKey(@Param("accountId") Long accountId, @Param("remoteKey") String publicKey,@Param("sharedKey") String sharedKey);
}
