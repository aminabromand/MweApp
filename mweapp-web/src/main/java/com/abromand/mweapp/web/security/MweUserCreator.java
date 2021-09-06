package com.abromand.mweapp.web.security;

import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.data.repository.MweUserRepository;
import java.util.Optional;
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

    logger.info("MweUserCreator running");

    Optional<MweUser> mweUserOptional = userRepository.findByUsername("aminator");
    if(mweUserOptional.isPresent()) {
      logger.info("MweUserCreator user already created");
      return;
    }

    logger.info("MweUserCreator creating");

    MweUser amin = new MweUser(1L, "aminator", passwordEncoder.encode("abcdef9h"), "amin@abromand.com", true, false, "MWEler", "telenummer", "hauptstrasse", 3, "Software Engineer");
    MweUser kappe = new MweUser(2L, "kappe", passwordEncoder.encode("init101"), "kappert.d@web.de",  false, true, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser fame = new MweUser(3L, "fame", passwordEncoder.encode("init101"), "felixblanke@gmail.com", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser poisch = new MweUser(4L, "poisch", passwordEncoder.encode("init101"), "jenspoischbeg@gmail.com", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser jay = new MweUser(5L, "jay", passwordEncoder.encode("init101"), "jensvictor@gmx.de", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser mo = new MweUser(6L, "mo", passwordEncoder.encode("init101"), "moritz@reisers.net", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser porro = new MweUser(7L, "porro", passwordEncoder.encode("init101"), "nicogoldmann@gmail.com", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser reen = new MweUser(8L, "reen", passwordEncoder.encode("init101"), "ren.voss@gmail.com", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser bart = new MweUser(9L, "bart", passwordEncoder.encode("init101"), "arnesalehian@gmailc.om", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser tobi = new MweUser(10L, "tobi", passwordEncoder.encode("init101"), "tobiasschmitz@gmail.com", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser firstclaas = new MweUser(11L, "firstclaas", passwordEncoder.encode("init101"), "claas.windheuser@gmail.com", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser pasen = new MweUser(12L, "paisen", passwordEncoder.encode("init101"), "pascal.maas@gmx.de", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");
    MweUser freez = new MweUser(13L, "freez", passwordEncoder.encode("init101"), "chr.freise@googlemail.com", false, false, "MWEler", "telenummer", "hauptstrasse", 3, "Angestellter");

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

    logger.info("MweUserCreator created");

  }
}
