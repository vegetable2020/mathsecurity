package cn.math.service;

import cn.math.dto.User;

import java.util.List;
import java.util.concurrent.locks.Condition;


public interface UserService {
    public User queryById(Long id);
    public List<User> queryByCondition(Condition condition);
    public void save(User user);
    public void Update(User user);
    public void delete(Long id);
}
