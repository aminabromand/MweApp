package com.abromand.mweapp.data.service;

import com.abromand.mweapp.data.dto.MweUserDto;
import com.abromand.mweapp.data.dto.VerificationTokenDto;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.maven.wagon.ResourceDoesNotExistException;

public interface MweUserService {

  List<MweUserDto> findAll();

  MweUserDto patch(Long id, MweUserDto dto) throws InvocationTargetException, IllegalAccessException;

  VerificationTokenDto generateVerificationToken(String email);

  void sendVerificationEmail(VerificationTokenDto tokenDto);
}
