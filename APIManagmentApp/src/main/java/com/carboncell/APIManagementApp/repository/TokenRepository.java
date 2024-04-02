package com.carboncell.APIManagementApp.repository;

import com.carboncell.APIManagementApp.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    @Query("""
            select t from Token t inner join User u
            on t.user.userId = u.userId
            where t.user.userId = :userId and t.isLogout = false
            """)
    List<Token>findALlTokenByUser(Integer userId);

    Optional<Token>findByToken(String token);

}
