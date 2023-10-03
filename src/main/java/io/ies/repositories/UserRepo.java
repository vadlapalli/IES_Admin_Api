package io.ies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.ies.entities.UsersEntity;

public interface UserRepo extends JpaRepository<UsersEntity, Integer> {

	public UsersEntity findByEmail(String email);

	@Query("update UsersEntity set acctStatus=:status where userId=:userId")
	public Integer updateAcctStatus(Integer userId, String status);
	
	public UsersEntity findByEmailAndPzzwd(String email,String pzzwd);

}
