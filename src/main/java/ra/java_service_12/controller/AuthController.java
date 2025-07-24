package ra.java_service_12.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.java_service_12.model.dto.request.UserLogin;
import ra.java_service_12.model.dto.request.UserRegister;
import ra.java_service_12.model.dto.response.APIResponse;
import ra.java_service_12.model.dto.response.JWTResponse;
import ra.java_service_12.model.entity.User;
import ra.java_service_12.service.UserService;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<APIResponse<User>> registerUser(@RequestBody UserRegister userRegister){
        return new ResponseEntity<>(new APIResponse<>(true,"Regiser user successfully!",userService.registerUser(userRegister), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<JWTResponse>> login(@RequestBody UserLogin userLogin){
        return new ResponseEntity<>(new APIResponse<>(true,"Login successfully!",userService.login(userLogin), HttpStatus.OK),HttpStatus.OK);
    }
}