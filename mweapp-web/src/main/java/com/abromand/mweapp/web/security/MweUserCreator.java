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

    MweUser amin = new MweUser(1L, "aminator", "amin@abromand.com", passwordEncoder.encode("abcdef9h"), true, false);
    MweUser kappe = new MweUser(2L, "kappe", "kappert.d@web.de", passwordEncoder.encode("init101"), false, true);
    MweUser fame = new MweUser(3L, "fame", "felixblanke@gmail.com", passwordEncoder.encode("init101"), false, false);
    MweUser poisch = new MweUser(4L, "poisch", "jenspoischbeg@gmail.com", passwordEncoder.encode("init101"), false, false);
    MweUser jay = new MweUser(5L, "jay", "jensvictor@gmx.de", passwordEncoder.encode("init101"), false, false);
    MweUser mo = new MweUser(6L, "mo", "moritz@reisers.net", passwordEncoder.encode("init101"), false, false);
    MweUser porro = new MweUser(7L, "porro", "nicogoldmann@gmail.com", passwordEncoder.encode("init101"), false, false);
    MweUser reen = new MweUser(8L, "reen", "ren.voss@gmail.com", passwordEncoder.encode("init101"), false, false);
    MweUser bart = new MweUser(9L, "bart", "arnesalehian@gmailc.om", passwordEncoder.encode("init101"), false, false);
    MweUser tobi = new MweUser(10L, "tobi", "tobiasschmitz@gmail.com", passwordEncoder.encode("init101"), false, false);
    MweUser firstclaas = new MweUser(11L, "firstclaas", "claas.windheuser@gmail.com", passwordEncoder.encode("init101"), false, false);
    MweUser pasen = new MweUser(12L, "paisen", "pascal.maas@gmx.de", passwordEncoder.encode("init101"), false, false);
    MweUser freez = new MweUser(13L, "freez", "chr.freise@googlemail.com", passwordEncoder.encode("init101"), false, false);

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
