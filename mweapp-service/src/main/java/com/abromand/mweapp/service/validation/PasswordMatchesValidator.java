package com.abromand.mweapp.service.validation;

import com.abromand.mweapp.service.dto.ChangePasswordDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(PasswordMatches constraintAnnotation) {
  }

  @Override
  public boolean isValid(Object obj, ConstraintValidatorContext context){
    ChangePasswordDto user = (ChangePasswordDto) obj;
    return user.getPassword().equals(user.getMatchingPassword());
  }
}
