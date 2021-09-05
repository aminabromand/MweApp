package com.abromand.mweapp.data.service.impl;

import com.abromand.mweapp.data.dto.MweUserDto;
import com.abromand.mweapp.data.mapper.MweUserMapper;
import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.data.repository.MweUserRepository;
import com.abromand.mweapp.data.service.MweUserService;
import com.abromand.mweapp.data.util.NullAwareBeanUtilsBean;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.maven.wagon.ResourceDoesNotExistException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MweUserServiceImpl implements MweUserService {

  @Autowired
  MweUserRepository userRepository;

  @Autowired
  MweUserMapper userMapper;

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
  public void patch(Long id, MweUserDto dto)
      throws InvocationTargetException, IllegalAccessException {
    MweUser mweUser = userRepository.getById(id);
    dto.setCsb(mweUser.isCsb());
    dto.setCto(mweUser.isCto());
    nullAwareBeanUtilsBean.copyProperties(mweUser, dto);
    userRepository.save(mweUser);
  }


}
