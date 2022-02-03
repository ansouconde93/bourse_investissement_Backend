package bourse.bourse.project.security;

import bourse.bourse.project.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MyJWTTokenManager myJWTTokenManager;

    @PostMapping("/login")
    public Object getAccesToken(@RequestBody Login login) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        headers.add("Access-Control-Allow-Headers",
                "Origin, Accept, X-Requested-With, Content-Type, "
                        + "Access-Control-Request-Method, Access-Control-Request-Headers");
        headers.add("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
        headers.add("contentType", "application/octet-stream");
        return userRepository.findByEmail(login.getUsername())
                .flatMap(userFromDB -> {
                    if (login == null || login.getUsername() == null || login.getPassword() == null) {
                        return Mono.just(new ResponseEntity(headers, HttpStatus.BAD_REQUEST));
                    }else {
                        if (userFromDB != null && bCryptPasswordEncoder.matches(login.getPassword(), userFromDB.getPassword())) {
                            headers.set(SecurityConstante.HEADER_STRING,
                                    SecurityConstante.TOKEN_PREFIX + myJWTTokenManager.genereteToken(userFromDB));
                            return Mono.just(new ResponseEntity<String>(null, headers, HttpStatus.OK));
                        }
                    }
                    return Mono.just(new ResponseEntity(headers, HttpStatus.BAD_REQUEST));
                }).switchIfEmpty(Mono.just(new ResponseEntity(headers, HttpStatus.BAD_REQUEST)));
    }
}
