package com.abromand.mweapp.service.service.impl;

import com.abromand.mweapp.service.dto.MweUserDto;
import com.abromand.mweapp.service.dto.VerificationTokenDto;
import com.abromand.mweapp.service.exception.MweServiceException;
import com.abromand.mweapp.service.mapper.MweUserMapper;
import com.abromand.mweapp.service.mapper.VerificationTokenMapper;
import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.data.model.VerificationToken;
import com.abromand.mweapp.data.repository.MweUserRepository;
import com.abromand.mweapp.data.repository.VerificationTokenRepository;
import com.abromand.mweapp.service.service.MweUserService;
import com.abromand.mweapp.service.util.NullAwareBeanUtilsBean;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MweUserServiceImpl implements MweUserService {

  @Autowired
  MweUserRepository userRepository;

  @Autowired
  VerificationTokenRepository tokenRepository;

  @Autowired
  MweUserMapper userMapper;

  @Autowired
  VerificationTokenMapper tokenMapper;

  @Autowired
  NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

  @Override
  public List<MweUserDto> findAll() {
    return userRepository.findAll().stream()
        .map(userMapper::mweUser2MweUserDto)
        .collect(Collectors.toList());
  }

  @Transactional
  @Override
  public MweUserDto patch(Long id, MweUserDto dto)
      throws InvocationTargetException, IllegalAccessException {
    MweUser mweUser = userRepository.getById(id);
    dto.setCsb(mweUser.isCsb());
    dto.setCto(mweUser.isCto());
    nullAwareBeanUtilsBean.copyProperties(mweUser, dto);
    return userMapper.mweUser2MweUserDto(userRepository.save(mweUser));
  }

  @Override
  public VerificationTokenDto generateVerificationToken(String email) {
//      String init102encrypted = "$2a$10$d2cabNZUoC43e.OJ4TDx/.Z6TQ.8U.5nzvV5js1n1m37JhnDAr/By";

    Optional<MweUser> mweUserOptional = userRepository.findByEmail(email);
    if (mweUserOptional.isEmpty()) {
      throw new MweServiceException("user not found");
    }

    tokenRepository.deleteAll(tokenRepository.findAllByUserId(mweUserOptional.get().getId()));

    String tokenString = UUID.randomUUID().toString();
    VerificationToken token = new VerificationToken();
    token.setTokenString(tokenString);
    token.setUser(mweUserOptional.get());
    token.setExpiryDate(token.getStandardExpiryDate());
    VerificationToken savedToken = tokenRepository.save(token);

    return tokenMapper.entity2Dto(savedToken);
  }

  @Override
  public void setPasswordWithToken(String password, String token) {

    Optional<VerificationToken> tokenOptional = tokenRepository.findByTokenString(token);
    if (tokenOptional.isEmpty()) {
      throw new MweServiceException("token not found");
    }

    MweUser mweUser = tokenOptional.get().getUser();
    mweUser.setPassword(password);
    tokenRepository.delete(tokenOptional.get());
    userRepository.save(mweUser);
  }
}
