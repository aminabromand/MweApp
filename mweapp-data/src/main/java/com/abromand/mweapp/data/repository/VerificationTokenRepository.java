package com.abromand.mweapp.data.repository;

import com.abromand.mweapp.data.model.VerificationToken;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

  Optional<VerificationToken> findByUserId(long userId);

  Optional<VerificationToken> findByTokenString(String tokenString);

  List<VerificationToken> findAllByUserId(long userId);
}
