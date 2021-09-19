package com.abromand.mweapp.service.util;

import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtilsBean;

public class NullAwareBeanUtilsBean extends BeanUtilsBean {

  BeanUtilsBean beanUtilsBean;

  public NullAwareBeanUtilsBean(BeanUtilsBean beanUtilsBean) {
    this.beanUtilsBean = beanUtilsBean;
  }

  @Override
  public void copyProperty(Object dest, String name, Object value)
      throws IllegalAccessException, InvocationTargetException {
    if (value == null)
      return;
    beanUtilsBean.copyProperty(dest, name, value);
  }
}


