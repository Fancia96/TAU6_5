
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

        messenger = new Messenger(mailServer, templateEngine);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void test_instance(){
        assertTrue(messenger instanceof Messenger);
    }

    @Test
    public void assertSendMessage() {
        Client client = new Client();
        client.email = "email";

        Template templatee = new Template();
        templatee.template = "ttttt";

        doThrow(IllegalArgumentException.class).when(mailServer).setMessageSent(false);

        doAnswer((i) -> {
            System.out.println(i.getArgument(0)+"");
            if (i.getArgument(0) == null || i.getArgument(1) == null) {
                //throw new IllegalArgumentException();
                assertTrue(false);
            }

            mailServer.setMessageSent(true);
            assertTrue(true);
            return null;
        }).when(mailServer).send(anyString(), anyString());

        messenger.sendMessage(client, templatee);
        assertThrows(IllegalArgumentException.class, () -> mailServer.setMessageSent(false));

    }

    @Test
    public void assertSendMessageBadData() {
        Client client = new Client();

        Template templatee = new Template();

        doThrow(IllegalArgumentException.class).when(mailServer).setMessageSent(false);

        doAnswer((i) -> {

            if (i.getArgument(0) == null || i.getArgument(1) == null) {

                assertTrue(true);
            }
            else {
                assertTrue(false);
            }
            return null;
        }).when(mailServer).send(any(), any());

        messenger.sendMessage(client, templatee);
        assertThrows(IllegalArgumentException.class, () -> mailServer.setMessageSent(false));

    }

    @Test
    public void assertMailServerSendMessage() {
        Client client = new Client();
        client.email = "email";

        Template templatee = new Template();
        templatee.template = "ttttt";

        doThrow(IllegalArgumentException.class).when(mailServer).setMessageSent(false);

        doAnswer((i) -> {
            if (i.getArgument(0) == null || i.getArgument(1) == null) {

                assertTrue(false);
            }
            else {
                mailServer.setMessageSent(true);
                System.out.println(mailServer.isMessageSent() + "dddd");
                assertTrue(true);
            }
            return null;
        }).when(mailServer).send(any(), any());

        mailServer.send(client.getEmail(), templatee.getTemplate());
        assertThrows(IllegalArgumentException.class, () -> mailServer.setMessageSent(false));

    }


    @Test
    public void assertTemplateEngine() {
        Client client = new Client();
        client.email = "email";

        Template templatee = new Template();
        templatee.template = "ttttt";

        doThrow(IllegalArgumentException.class).when(mailServer).setMessageSent(false);

        doAnswer((i) -> {

            if(((Client)i.getArgument(1)).getEmail() == null
                    || ((Template)i.getArgument(0)).getTemplate() == null){
                templateEngine.template_engine = null;
            }
            else {
                templateEngine.template_engine =
                        ((Template)i.getArgument(0)).getTemplate() + " " + ((Client)i.getArgument(1)).getEmail();
            }

            System.out.println(templateEngine.template_engine+"uuuuuuuuu");

            if (templateEngine.template_engine == null) {
                assertTrue(false);
            }
            else {
                assertEquals("ttttt email", templateEngine.template_engine);
            }
            return null;
        }).when(templateEngine).prepareMessage(any(), any());

        templateEngine.prepareMessage(templatee, client);
        assertThrows(IllegalArgumentException.class, () -> mailServer.setMessageSent(false));

    }

    @Test
    public void assertTemplateEngineWithNulls() {
        Client client = new Client();
        client.email = "email";

        Template templatee = new Template();

        doThrow(IllegalArgumentException.class).when(mailServer).setMessageSent(false);

        doAnswer((i) -> {

            if(((Client)i.getArgument(1)).getEmail() == null
                    || ((Template)i.getArgument(0)).getTemplate() == null){
                templateEngine.template_engine = null;
            }
            else {
                templateEngine.template_engine =
                        ((Template)i.getArgument(0)).getTemplate() + " " + ((Client)i.getArgument(1)).getEmail();
            }

            System.out.println(templateEngine.template_engine+"uuuuuuuuu");

            if (templateEngine.template_engine == null) {
                assertTrue(true);
            }
            else {
                assertTrue(false);
            }
            return null;
        }).when(templateEngine).prepareMessage(any(), any());

        templateEngine.prepareMessage(templatee, client);
        assertThrows(IllegalArgumentException.class, () -> mailServer.setMessageSent(false));

    }

    @After
    public void tearDown(){
        messenger = null;
    }

}