package com.sds.x6_user.service;

import com.sds.x6_user.exception.UserException;
import com.sds.x6_user.model.User;
import com.sds.x6_user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public User create(final User user) {
        try {
            return userRepository.insert(user);
        } catch (Exception e) {
            throw new UserException("Ошибка при создании пользователя: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public User getById(final long id) {
        try {
            return userRepository.getById(id);
        } catch (Exception e) {
            throw new UserException("Пользователь с id=" + id + " не найден");
        }
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        try {
            return userRepository.getAll();
        } catch (Exception e) {
            throw new UserException("Ошибка при получении списка пользователей: " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public User update(final long id, final User user) {
        try {
            user.setId(id);
            return userRepository.update(user);
        } catch (Exception e) {
            throw new UserException("Ошибка при обновлении пользователя: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public boolean isAvailable(final long id) {
        try {
            return userRepository.isUserAvailable(id);
        } catch (Exception e) {
            throw new UserException("Ошибка при проверке доступности пользователя: " + e.getMessage());
        }
    }
}
