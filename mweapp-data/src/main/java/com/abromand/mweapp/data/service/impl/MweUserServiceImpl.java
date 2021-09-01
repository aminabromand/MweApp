package com.abromand.mweapp.data.service.impl;

import com.abromand.mweapp.data.dto.MweUserDto;
import com.abromand.mweapp.data.mapper.MweUserMapper;
import com.abromand.mweapp.data.repository.MweUserRepository;
import com.abromand.mweapp.data.service.MweUserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MweUserServiceImpl implements MweUserService {

  @Autowired
  MweUserRepository userRepository;

  @Autowired
  MweUserMapper userMapper;

  @Override
  public List<MweUserDto> findAll() {

    return userRepository.findAll().stream()
        .map(userMapper::mweUser2MweUserDto)
        .collect(Collectors.toList());

  }

}
