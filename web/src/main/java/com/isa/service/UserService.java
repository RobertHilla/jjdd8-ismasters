package com.isa.service;

import com.isa.dao.UserDao;
import com.isa.domain.dto.EventDto;
import com.isa.domain.dto.UserDto;
import com.isa.domain.entity.User;
import com.isa.domain.entity.UserType;
import com.isa.mapper.UserMapper;
import com.isa.service.domain.EventService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Stateless
public class UserService {

    private static final String CREDENTIALS_FILE = "credentials.properties";

    @EJB
    UserDao userDao;

    @EJB
    UserMapper userMapper;

    @EJB
    EventService eventService;


    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    @Transactional
    public UserDto findUserDtoById(Long id) {
        return userMapper.mapEntityToDto(findUserById(id));
    }

    public void createNewUser(UserDto userDto) throws IOException {
        User user = userMapper.mapDtoToEntity(userDto);
        if (userDto.getEmail().equals(getAdminEmail())) {
            user.setUserType(UserType.SUPER_ADMIN);
        } else {
            user.setUserType(UserType.USER);
        }
        userDao.add(user);
    }

    public void editUser(User user) {
        userDao.update(user);

    }

    public void editDtoUser(UserDto user) {
        userDao.update(userMapper.mapDtoToEntity(user));
    }

    public Optional<UserDto> getUserByEmail(String email) {
        return userDao.findByEmail(email).map(userMapper::mapEntityToDto);
    }

    public Boolean userExists(String email) {
        return userDao.findByEmail(email).isPresent();
    }

    public Boolean isAdmin(String email) {
        Optional<User> user = userDao.findByEmail(email);

        return user.map(value -> value.getUserType()
                .equals(UserType.ADMIN))
                .orElse(false);
    }

    public Boolean isSuperAdmin(String email) {
        Optional<User> user = userDao.findByEmail(email);

        return user.map(value -> value.getUserType()
                .equals(UserType.SUPER_ADMIN))
                .orElse(false);
    }

    public List<UserDto> getUsers() {
        return userDao.findAll().stream()
                .map(user -> userMapper.mapEntityToDto(user))
                .collect(Collectors.toList());
    }

    private static String getAdminEmail() throws IOException {
        Properties settings = new Properties();
        settings.load(Objects.requireNonNull(Thread.currentThread()
                .getContextClassLoader().getResource(CREDENTIALS_FILE))
                .openStream());
        return settings.getProperty("user.name");
    }

    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }


}