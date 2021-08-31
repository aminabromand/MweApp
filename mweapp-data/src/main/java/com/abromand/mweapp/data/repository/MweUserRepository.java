package com.abromand.mweapp.data.repository;

import com.abromand.mweapp.data.model.MweUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MweUserRepository {//extends JpaRepository<MweUser, Long> {

  MweUser findByUsername(String username);

  MweUser save(MweUser caminoUser);
}
