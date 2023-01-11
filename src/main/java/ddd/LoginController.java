package ddd;

public class LoginController {

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    public LoginService loginService;

    public String login(UserForm userForm){
        if(null == userForm){
            return "ERROR";
        }else{
            boolean logged;

            try {
                logged = loginService.login(userForm);
            } catch (Exception e) {
                return "ERROR";
            }

            if(logged){
                loginService.setCurrentUser(userForm.getUsername());
                return "OK";
            }else{
                return "KO";
            }
        }
    }
}