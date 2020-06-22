package com.thoughtworks.user.dao;

import com.thoughtworks.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UserDao extends JpaRepository<UserEntity, Long> {
    Page<UserEntity> findAllByName(String name, Pageable pageable);
    Page<UserEntity> findAllByAge(int age, Pageable pageable);
    Page<UserEntity> findAllByCreateAtBetween(Date startDate, Date endDate, Pageable pageable);
}
