package com.abromand.mweapp.data.repository;

import com.abromand.mweapp.data.model.MweUser;
import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MweUserRepository extends JpaRepository<MweUser, Long> {

  Optional<MweUser> findByUsername(String username);

  Optional<MweUser> findByEmail(String email);
}
