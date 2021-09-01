package com.abromand.mweapp.data.mapper;

import com.abromand.mweapp.data.dto.MweUserDto;
import com.abromand.mweapp.data.model.MweUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MweUserMapper {

  MweUserDto mweUser2MweUserDto(MweUser mweUser);

}
