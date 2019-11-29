package cn.math.service.impl;

import cn.math.service.UserService;
import cn.math.dao.UserRepository;
import cn.math.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Condition;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userDao;

    @Override
    public User queryById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> queryByCondition(Condition condition) {
        return userDao.findAll();
    }


    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void Update(User user) {
        userDao.save(user);
    }


    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }
}
