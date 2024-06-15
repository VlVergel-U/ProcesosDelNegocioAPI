package com.procesos.parcial.service;


import com.procesos.parcial.exceptions.AlreadyExistsException;
import com.procesos.parcial.exceptions.NotFoundException;
import com.procesos.parcial.model.User;
import com.procesos.parcial.model.dto.Response;
import com.procesos.parcial.model.dto.ResponseUtil;
import com.procesos.parcial.model.enums.ErrorMessage;
import com.procesos.parcial.model.enums.SuccessMessage;
import com.procesos.parcial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceOp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResponseUtil response;

    @Override
    public Response createUser(User user) {
        Optional<User> userFindByEmail = userRepository.findByEmail(user.getEmail());
        if (userFindByEmail.isPresent()){
            throw new AlreadyExistsException(ErrorMessage.USER_EMAIL_EXISTS.getMessage());
        }
        userRepository.save(user);
        return response.generateResponse(SuccessMessage.USER_CREATED.getMessage(), user, String.valueOf(HttpStatus.CREATED));

    }

    @Override
    public Response updateUser(User userUpdated, Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage());
        }
        Optional<User> userFindByEmail = userRepository.findByEmailAndIdNot(userUpdated.getEmail(), id);
        if (userFindByEmail.isPresent()){
            throw new AlreadyExistsException(ErrorMessage.USER_EMAIL_EXISTS.getMessage());
        }
        user.get().setFullName(userUpdated.getFullName());
        user.get().setPhoneNumber(userUpdated.getPhoneNumber());
        user.get().setEmail(userUpdated.getEmail());
        user.get().setAddress(userUpdated.getAddress());
        user.get().setDocument(userUpdated.getDocument());
        user.get().setPassword(userUpdated.getPassword());
        user.get().setRole(userUpdated.getRole());
        user.get().setDocumentType(userUpdated.getDocumentType());
        userRepository.save(user.get());
        return response.generateResponse(SuccessMessage.USER_UPDATED.getMessage(), userUpdated, String.valueOf(HttpStatus.OK));
    }

    @Override
    public Response getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException(ErrorMessage.USER_NOT_FOUND.getMessage());
        }
        return response.generateResponse(SuccessMessage.USER_FOUND.getMessage(), user, String.valueOf(HttpStatus.OK));
    }

    @Override
    public Response findAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        if(users.isEmpty()){
            throw new NotFoundException(ErrorMessage.USERS_NOT_FOUND.getMessage());
        }
        return response.generateResponse(SuccessMessage.USERS_FOUND.getMessage(), users, String.valueOf(HttpStatus.OK));

    }
}