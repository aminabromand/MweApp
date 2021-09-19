package com.abromand.mweapp.service.mapper;

import com.abromand.mweapp.service.dto.VerificationTokenDto;
import com.abromand.mweapp.data.model.MweUser_;
import com.abromand.mweapp.data.model.VerificationToken;
import com.abromand.mweapp.data.model.VerificationToken_;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VerificationTokenMapper {

  @Mapping(source = VerificationToken_.USER + "." + MweUser_.EMAIL, target = VerificationTokenDto.EMAIL)
  VerificationTokenDto entity2Dto(VerificationToken entity);

}
