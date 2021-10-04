package com.abromand.mweapp.service.service;

import com.abromand.mweapp.service.dto.MweUserDto;
import com.abromand.mweapp.service.dto.VerificationTokenDto;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface MweUserService {

  List<MweUserDto> findAll();

  MweUserDto patch(Long id, MweUserDto dto) throws InvocationTargetException, IllegalAccessException;

  VerificationTokenDto generateVerificationToken(String email);

  void sendVerificationEmail(VerificationTokenDto tokenDto);

  void setPasswordWithToken(String password, String token);
}
