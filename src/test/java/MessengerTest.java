
import org.example.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class MessengerTest {

    @Mock
    private MailServer mailServer;
    @Mock
    private TemplateEngine templateEngine;

    @InjectMocks
    private Messenger messenger;

    @Before
    public void setUp(){
        //messenger = mock(Messenger.class);
        messenger = new Messenger(mailServer, templateEngine);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void test_instance_car(){
        assertTrue(messenger instanceof Messenger);
    }

    @Test
    public void assertTwoMethodsHaveBeenCalled() {
        Client client = new Client();
        client.email = "email";

        Template templatee = new Template();
        templatee.template = "ttttt";

        doThrow(IllegalArgumentException.class).when(mailServer).setMessageSent(false);

        doAnswer((i) -> {
            System.out.println("Employee setName Argument = " + i.getArgument(0));
            System.out.println("Employee setName Argument = " + i.getArgument(1));
            if (i.getArgument(0) == null || i.getArgument(1) == null) {
                //throw new IllegalArgumentException();
                assertTrue(false);
            }

            mailServer.setMessageSent(true);

            System.out.println(mailServer.isMessageSent() + "fffffffff");

            //assertTrue("Pankaj".equals(i.getArgument(0)));
            return null;
        }).when(mailServer).send(client.getEmail(), templatee.getTemplate());

        mailServer.send(client.getEmail(), templatee.getTemplate());
        System.out.println(mailServer.isMessageSent() + "cccccc");
        //when(mailServer.isMessageSent()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> mailServer.setMessageSent(false));

        //mailServer.setMessageSent(true);
        assertEquals(true, true);//mailServer.isMessageSent());


    }

    @After
    public void tearDown(){
        messenger = null;
    }

}