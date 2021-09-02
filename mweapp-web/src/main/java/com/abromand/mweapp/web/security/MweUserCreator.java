package com.abromand.mweapp.web.security;

import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.data.repository.MweUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MweUserCreator implements ApplicationListener<ApplicationReadyEvent> {

  Logger logger = LoggerFactory.getLogger(MweUserCreator.class);

  @Autowired
  MweUserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

    logger.info("running creator MweUserCreator");

    MweUser amin = new MweUser(1L, "aminator", passwordEncoder.encode("abcdef9h"), "amin@abromand.com", true, false);
    MweUser kappe = new MweUser(2L, "kappe", passwordEncoder.encode("init101"), "kappert.d@web.de",  false, true);
    MweUser fame = new MweUser(3L, "fame", passwordEncoder.encode("init101"), "felixblanke@gmail.com", false, false);
    MweUser poisch = new MweUser(4L, "poisch", passwordEncoder.encode("init101"), "jenspoischbeg@gmail.com", false, false);
    MweUser jay = new MweUser(5L, "jay", passwordEncoder.encode("init101"), "jensvictor@gmx.de", false, false);
    MweUser mo = new MweUser(6L, "mo", passwordEncoder.encode("init101"), "moritz@reisers.net", false, false);
    MweUser porro = new MweUser(7L, "porro", passwordEncoder.encode("init101"), "nicogoldmann@gmail.com", false, false);
    MweUser reen = new MweUser(8L, "reen", passwordEncoder.encode("init101"), "ren.voss@gmail.com", false, false);
    MweUser bart = new MweUser(9L, "bart", passwordEncoder.encode("init101"), "arnesalehian@gmailc.om", false, false);
    MweUser tobi = new MweUser(10L, "tobi", passwordEncoder.encode("init101"), "tobiasschmitz@gmail.com", false, false);
    MweUser firstclaas = new MweUser(11L, "firstclaas", passwordEncoder.encode("init101"), "claas.windheuser@gmail.com", false, false);
    MweUser pasen = new MweUser(12L, "paisen", passwordEncoder.encode("init101"), "pascal.maas@gmx.de", false, false);
    MweUser freez = new MweUser(13L, "freez", passwordEncoder.encode("init101"), "chr.freise@googlemail.com", false, false);

    userRepository.save(amin);
    userRepository.save(kappe);
    userRepository.save(fame);
    userRepository.save(poisch);
    userRepository.save(jay);
    userRepository.save(mo);
    userRepository.save(porro);
    userRepository.save(reen);
    userRepository.save(bart);
    userRepository.save(tobi);
    userRepository.save(firstclaas);
    userRepository.save(pasen);
    userRepository.save(freez);

  }
}
