package com.procesos.parcial.service;


import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.exceptions.NotFoundException;
import com.procesos.parcial.model.User;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceOp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        Optional<User> userFindByEmail = userRepository.findByEmail(user.getEmail());
        if (userFindByEmail.isPresent()){
            throw new AlreadyExistsException(ErrorMessage.USER_EMAIL_EXISTS.getMessage());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User userUpdated, Long id) {
        Optional<User> userBd = userRepository.findById(id);
        if(userBd.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        Optional<User> userFindByEmail = userRepository.findByEmailAndIdNot(userUpdated.getEmail(), id);
        if (userFindByEmail.isPresent()){
            throw new AlreadyExistsException(ErrorMessage.USER_EMAIL_EXISTS.getMessage());
        }
        userBd.get().setFullName(userUpdated.getFullName());
        userBd.get().setPhoneNumber(userUpdated.getPhoneNumber());
        userBd.get().setEmail(userUpdated.getEmail());
        userBd.get().setAddress(userUpdated.getAddress());
        userBd.get().setDocument(userUpdated.getDocument());
        userBd.get().setPassword(userUpdated.getPassword());
        userBd.get().setRole(userUpdated.getRole());
        userBd.get().setDocumentType(userUpdated.getDocumentType());
        return userRepository.save(userBd.get());
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("User not found!");
        }
        return user.get();
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}