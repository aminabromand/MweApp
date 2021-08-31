package com.abromand.mweapp.data.repository.impl;

import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.data.repository.MweUserRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MweUserRepositoryInMem implements MweUserRepository {

    private final Map<String, MweUser> users = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong(14);

    public MweUserRepositoryInMem(PasswordEncoder passwordEncoder) {
        MweUser amin = new MweUser(1L, "amin@abromand.com", passwordEncoder.encode("user"));
        MweUser kappe = new MweUser(2L, "kappert.d@web.de", passwordEncoder.encode("init101"));
        MweUser fame = new MweUser(3L, "felixblanke@gmail.com", passwordEncoder.encode("init101"));
        MweUser poisch = new MweUser(4L, "jenspoischbeg@gmail.com", passwordEncoder.encode("init101"));
        MweUser jay = new MweUser(5L, "jensvictor@gmx.de", passwordEncoder.encode("init101"));
        MweUser mo = new MweUser(6L, "moritz@reisers.net", passwordEncoder.encode("init101"));
        MweUser porro = new MweUser(7L, "nicogoldmann@gmail.com", passwordEncoder.encode("init101"));
        MweUser reen = new MweUser(8L, "ren.voss@gmail.com", passwordEncoder.encode("init101"));
        MweUser bart = new MweUser(9L, "arnesalehian@gmailc.om", passwordEncoder.encode("init101"));
        MweUser tobi = new MweUser(10L, "tobiasschmitz@gmail.com", passwordEncoder.encode("init101"));
        MweUser firstclaas = new MweUser(11L, "claas.windheuser@gmail.com", passwordEncoder.encode("init101"));
        MweUser pasen = new MweUser(12L, "pascal.maas@gmx.de", passwordEncoder.encode("init101"));
        MweUser freez = new MweUser(13L, "chr.freise@googlemail.com", passwordEncoder.encode("init101"));

        users.put(amin.getUsername(), amin);
        users.put(kappe.getUsername(), kappe);
        users.put(fame.getUsername(), fame);
        users.put(poisch.getUsername(), poisch);
        users.put(jay.getUsername(), jay);
        users.put(mo.getUsername(), mo);
        users.put(porro.getUsername(), porro);
        users.put(reen.getUsername(), reen);
        users.put(bart.getUsername(), bart);
        users.put(tobi.getUsername(), tobi);
        users.put(firstclaas.getUsername(), firstclaas);
        users.put(pasen.getUsername(), pasen);
        users.put(freez.getUsername(), freez);

    }

    @Override
    public MweUser findByUsername(String username) {
        return users.get(username);
    }

    @Override
    public synchronized MweUser save(MweUser mweUser) {
        MweUser newCaminoUser = new MweUser();
        newCaminoUser.setUsername(mweUser.getUsername());
        newCaminoUser.setPassword(mweUser.getPassword());
        newCaminoUser.setId(nextId.getAndIncrement());
        users.put(newCaminoUser.getUsername(), newCaminoUser);
        return newCaminoUser;
    }
}
