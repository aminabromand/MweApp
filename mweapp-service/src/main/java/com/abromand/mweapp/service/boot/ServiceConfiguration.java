package com.abromand.mweapp.service.boot;

import com.abromand.mweapp.service.util.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

  @Bean
  public BeanUtilsBean BeanUtilsBean() {
    return new BeanUtilsBean();
  }

  @Bean
  public NullAwareBeanUtilsBean nullAwareBeanUtilsBean(BeanUtilsBean beanUtilsBean) {
    return new NullAwareBeanUtilsBean(beanUtilsBean);
  }
}
