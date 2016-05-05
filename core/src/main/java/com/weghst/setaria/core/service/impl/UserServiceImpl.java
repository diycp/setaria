package com.weghst.setaria.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.weghst.setaria.core.domain.User;
import com.weghst.setaria.core.domain.UserApp;
import com.weghst.setaria.core.repository.UserAppRepository;
import com.weghst.setaria.core.repository.UserRepository;
import com.weghst.setaria.core.service.PasswordNotMatchedException;
import com.weghst.setaria.core.service.UserNotFoundException;
import com.weghst.setaria.core.service.UserService;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Value("${setaria.root}")
    private String root;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAppRepository userAppRepository;

    @Override
    public void save(User user) {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setCreatedTime(System.currentTimeMillis());
        userRepository.save(user);
    }

    @Override
    public void save(User user, int[] appIds) {
        save(user);
        saveUserApps(user.getId(), appIds);
    }

    @Override
    public void update(User user) {
        User dbUser = findById(user.getId());
        if (root.equals(dbUser.getEmail())) {
            throw new RuntimeException("ROOT 用户不能修改");
        }

        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userRepository.update(user);
    }

    @Override
    public void updatePassword(int id, String oldPassword, String newPassword) {
        User user = findById(id);
        String encOldPassword = DigestUtils.md5Hex(oldPassword);
        if (!encOldPassword.equalsIgnoreCase(user.getPassword())) {
            throw new PasswordNotMatchedException("输入的当前密码不正确");
        }

        user.setPassword(DigestUtils.md5Hex(newPassword));
        userRepository.update(user);
    }

    @Override
    public void update(User user, int[] appIds) {
        User dbUser = findById(user.getId());
        if (root.equals(dbUser.getEmail())) {
            throw new RuntimeException("ROOT 用户不能修改");
        }

        if (!ObjectUtils.nullSafeEquals(user.getEmail(), dbUser.getEmail())) {
            dbUser.setEmail(user.getEmail());
        }
        if (!ObjectUtils.nullSafeEquals(user.getPassword(), dbUser.getPassword())) {
            dbUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }

        dbUser.setManager(user.isManager());
        userRepository.update(dbUser);

        // 更新APP
        int[] dbAppIds = findUserAppIds(user.getId());
        int[] addAppIds = ArrayUtils.removeElements(appIds, dbAppIds);
        int[] deleteAppIds = ArrayUtils.removeElements(dbAppIds, appIds);

        saveUserApps(user.getId(), addAppIds);
        if (deleteAppIds.length > 0) {
            userAppRepository.deleteUserApps(user.getId(), deleteAppIds);
        }
    }

    @Override
    public void deleteById(int id) {
        User user = findById(id);
        if (root.equals(user.getEmail())) {
            throw new RuntimeException("ROOT 用户不能删除");
        }

        userRepository.deleteById(id);
        userAppRepository.deleteByUserId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public int[] findUserAppIds(int id) {
        List<Integer> list = userAppRepository.findUserAppIds(id);
        return ArrayUtils.toPrimitive(list.toArray(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY));
    }

    @Override
    public List<User> findOrdinaryUsers() {
        return userRepository.findUserByManager(false);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User authenticate(String email, String password) throws UserNotFoundException, PasswordNotMatchedException {
        User user = findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("未找到邮箱为 [" + email + "] 的用户");
        }

        String encPassword = DigestUtils.md5Hex(password);
        if (!encPassword.equalsIgnoreCase(user.getPassword())) {
            throw new PasswordNotMatchedException("用户 [" + email + "] 输入的密码错误");
        }
        return user;
    }

    private void saveUserApps(int userId, int[] appIds) {
        if (appIds.length == 0) {
            return;
        }

        List<UserApp> userApps = new ArrayList<>();
        for (int appId : appIds) {
            UserApp userApp = new UserApp();
            userApp.setUserId(userId);
            userApp.setAppId(appId);
            userApps.add(userApp);
        }
        userAppRepository.saveUserApps(userApps);
    }
}
