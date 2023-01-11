import ddd.LoginController;
import ddd.LoginDao;
import ddd.LoginService;
import ddd.UserForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class LoginControllerTest {

    @Mock
    private LoginDao loginDao;

//    @Spy
//    @InjectMocks
//    private LoginService spiedLoginService;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setUp() {
        loginController = new LoginController(loginService);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void assertTwoMethodsHaveBeenCalled() {
        UserForm userForm = new UserForm();
        userForm.username = "foo";
        Mockito.when(loginService.login(userForm)).thenReturn(true);

        String login = loginController.login(userForm);

        Assert.assertEquals("OK", login);
        Mockito.verify(loginService).login(userForm);
        Mockito.verify(loginService).setCurrentUser("foo");
    }
}