package CursistTests;

import datastorage.CursistDAO;
import domain.Cursist;
import org.junit.jupiter.api.*;

import java.util.Date;

public class DeleteTest {

    private final CursistDAO cursistDAO;

    public DeleteTest() {
        this.cursistDAO = new CursistDAO();
    }

    @BeforeAll
    static void createAccount() {
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
        @DisplayName("Should delete account with email")
        public void shouldDeleteAccountWithEmail() {
//            Input = BeforeAll

//            Action
            boolean res = cursistDAO.deleteByEmail("testaccount@test.com");

//            Output
            Assertions.assertTrue(res);
        }

    }

    @Nested
    @DisplayName("Should not")
    class ShouldNot {

        @Test
        @DisplayName("Should not delete account with invalid email")
        public void shouldDeleteAccountWithEmail() {
//            Input = BeforeAll

//            Action
            boolean res = cursistDAO.deleteByEmail("qwertyuiop");

//            Output
            Assertions.assertFalse(res);
        }

    }

}
