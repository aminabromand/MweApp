package com.abromand.mweapp.data.service;

import com.abromand.mweapp.data.dto.MweUserDto;
import java.util.List;

public interface MweUserService {

  List<MweUserDto> findAll();

}
