package CursistTests;

import datastorage.CursistDAO;
import domain.Cursist;
import org.junit.jupiter.api.*;

import java.util.Date;

public class LoginTest {

    private final CursistDAO cursistDAO;

    public LoginTest() {
        this.cursistDAO = new CursistDAO();
    }

    @BeforeAll
    static void createTestAccount() {
//        Input
        CursistDAO cursistDAO = new CursistDAO();

        Cursist cursist = new Cursist("testaccount@test.com", "Test", "Test",
                new Date(), "Test", "test", "Test",
                19, "2951DC", "Test", "Test");

//        Action
        cursistDAO.save(cursist);
    }

    @Nested
    @DisplayName("Should allow")
    class ShouldAllow {
        @Test
        @DisplayName("Should succesfully login")
        public void shouldLoginWithCredentialsTestAtTestComAndTest() {
//            Input = BeforeAll

//            Action
            Cursist cursist = cursistDAO.login("testaccount@test.com", "test");

            String name = cursist.getFirstName();

//            Output
            Assertions.assertEquals("Test", name);
        }
    }

    @Nested
    @DisplayName("Should not")
    class ShouldNot {
        @Test
        @DisplayName("Should return null with wrong credentials")
        public void shouldReturnNullWithWrongCredentials() {
//            Input = BeforeAll

//            Action
            Cursist cursist = cursistDAO.login("wasd", "wasd");

//            Output
            Assertions.assertNull(cursist);
        }

        @Test
        @DisplayName("Should return null with missing credentials")
        public void shouldReturnNullWithMissingCredentials() {
//            Input = BeforeAll

//            Action
            Cursist cursist = cursistDAO.login("", "test");
            Cursist cursist2 = cursistDAO.login("test@test.com", "");

//            Output
            Assertions.assertNull(cursist);
            Assertions.assertNull(cursist2);
        }
    }

    @AfterAll
    static void deleteTestAccount() {
//        Input
        CursistDAO cursistDAO = new CursistDAO();

//        Output
        cursistDAO.deleteByEmail("testaccount@test.com");
    }

}
