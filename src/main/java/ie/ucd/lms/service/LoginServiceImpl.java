package ie.ucd.lms.service;

import ie.ucd.lms.configuration.LoginConfig;
import ie.ucd.lms.dao.LoginRepository;
import ie.ucd.lms.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginConfig loginConfig;

    // @Override
    // public void validate(Login login, Errors errors) {
    //     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");

    //     if (loginRepository.getEmailByEmail(login.getEmail()) == null) {
    //         errors.rejectValue("email", "Duplicate.loginForm.email");
    //     }

    //     ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

    //     String password = login.getHash();

    //     if (password.length() < 8) {
    //         errors.rejectValue("password", "Size.loginForm.password");
    //     }

    //     if (!password.equals(loginRepository.getPasswordByEmail(login.getEmail()))) {
    //         errors.rejectValue("wrongPassword", "Wrong.loginForm.wrongPassword");
    //     }
    // }

    @Override
    public boolean exists(Login login) {
        return loginRepository.exists(Example.of(login));
    }

    @Override
    public void save(Login login) {
        loginRepository.save(login);
    }

    @Override
    public Login createLogin(String email, String password) {
        Login login = new Login();
        login.setEmail(email);
        // login.setHash(password);
        login.setHash(loginConfig.getEncoder().encode(password));

        return login;
    }
}