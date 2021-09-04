package com.abromand.mweapp.data.boot;

import com.abromand.mweapp.data.util.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfiguration {

  @Bean
  public BeanUtilsBean BeanUtilsBean() {
    return new BeanUtilsBean();
  }

  @Bean
  public NullAwareBeanUtilsBean nullAwareBeanUtilsBean(BeanUtilsBean beanUtilsBean) {
    return new NullAwareBeanUtilsBean(beanUtilsBean);
  }
}
