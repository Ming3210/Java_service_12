package ra.java_service_12.service;

import ra.java_service_12.model.dto.request.UserLogin;
import ra.java_service_12.model.dto.request.UserRegister;
import ra.java_service_12.model.dto.response.JWTResponse;
import ra.java_service_12.model.entity.User;

public interface UserService {
    User registerUser(UserRegister userRegister);

    JWTResponse login(UserLogin userLogin);
}
