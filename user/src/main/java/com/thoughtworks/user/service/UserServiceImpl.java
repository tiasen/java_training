package com.thoughtworks.user.service;

import com.thoughtworks.user.dao.UserDao;
import com.thoughtworks.user.dto.PageMeta;
import com.thoughtworks.user.entity.UserEntity;
import com.thoughtworks.user.exception.UserInvalidException;
import com.thoughtworks.user.feign.EmailFeign;
import com.thoughtworks.user.model.User;
import com.thoughtworks.user.model.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    EmailFeign emailFeign;

    @Override
    public PageResult<User> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> userEntityPage = userDao.findAll(pageable);

        return convertPageUserEntity2Result(userEntityPage);
    }

    @Override
    public User findById(Long id) {
        try{
            UserEntity userEntity = userDao.getOne(id);
            User user = UserMapper.INSTANCE.entityToUser(userEntity);
            String email = emailFeign.getEmail(id);
            user.setEmail(email);
            return user;

        }catch (EntityNotFoundException e) {
            log.info("no user found by id: {}", id);
            return null;
        }
    }

    @Override
    public long deleteById(Long id) {
        try{
            userDao.deleteById(id);
            return id;
        } catch (Exception e) {
            throw new UserInvalidException("用户删除失败");
        }
    }

    @Override
    public User updateUser(User user) {

        Optional<UserEntity> userEntityOptional = userDao.findById(user.getId());
        if (userEntityOptional.isPresent()) {
            var userById = userEntityOptional.get();
            userById.setName(user.getName());
            userById.setAge(user.getAge());
            UserEntity userEntity = userDao.save(userById);
            return UserMapper.INSTANCE.entityToUser(userEntity);
        } else {
            log.info("user can't find, {}", user);
            throw new UserInvalidException("此ID不存在");
        }

    }

    @Override
    public User addUser(User user) {
        return UserMapper.INSTANCE.entityToUser(userDao.save(UserMapper.INSTANCE.userToEntity(user)));
    }

    @Override
    public PageResult<User> findAllByName(String name, int page, int size) {
        Page<UserEntity> userEntities = userDao.findAllByName(name, PageRequest.of(page, size));
        return convertPageUserEntity2Result(userEntities);
    }

    @Override
    public PageResult<User> findAllByAge(int age, int page, int size) {
        Page<UserEntity> userEntities = userDao.findAllByAge(age, PageRequest.of(page, size));
        return convertPageUserEntity2Result(userEntities);
    }

    @Override
    public PageResult<User> findAllByCreateBetween(Date startDate, Date endDate, int page, int size) {
        Page<UserEntity> userEntities = userDao.findAllByCreateAtBetween(startDate, endDate, PageRequest.of(page, size));
        return convertPageUserEntity2Result(userEntities);
    }

    private PageResult<User> convertPageUserEntity2Result(Page<UserEntity> userEntityPage){
        Page<User> users = userEntityPage.map(UserMapper.INSTANCE::entityToUser);
        return PageResult.<User>builder().meta(PageMeta.builder().page(userEntityPage.getNumber())
                .size(userEntityPage.getSize())
                .total(userEntityPage.getTotalElements())
                .build()).data(users.getContent()).build();
    }
}
