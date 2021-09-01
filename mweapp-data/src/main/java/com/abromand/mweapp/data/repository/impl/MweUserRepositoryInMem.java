//package com.abromand.mweapp.data.repository.impl;
//
//import com.abromand.mweapp.data.model.MweUser;
//import com.abromand.mweapp.data.repository.MweUserRepository;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicLong;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class MweUserRepositoryInMem implements MweUserRepository {
//
//    private final Map<String, MweUser> users = new HashMap<>();
//    private final AtomicLong nextId = new AtomicLong(14);
//
//    @Override
//    public MweUser findByUsername(String username) {
//        return users.get(username);
//    }
//
//    @Override
//    public synchronized MweUser save(MweUser mweUser) {
//        MweUser newMweUser = new MweUser();
//        newMweUser.setUsername(mweUser.getUsername());
//        newMweUser.setPassword(mweUser.getPassword());
//        newMweUser.setCsb(mweUser.isCsb());
//        newMweUser.setCto(mweUser.isCto());
//        if(mweUser.getId() == null) {
//            newMweUser.setId(nextId.getAndIncrement());
//        } else {
//            newMweUser.setId(mweUser.getId());
//        }
//        users.put(newMweUser.getUsername(), newMweUser);
//        return newMweUser;
//    }
//
//    @Override
//    public Collection<MweUser> findAll() {
//        return users.values();
//    }
//}
