package CursistTests;

import datastorage.CursistDAO;
import domain.Cursist;
import org.junit.jupiter.api.*;

import java.util.Date;

public class UpdateTest {

    private final CursistDAO cursistDAO;

    public UpdateTest() {
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
        @DisplayName("Should update account")
        public void shouldUpdateAccount() {
//            Input
            Cursist cursist = cursistDAO.getByEmail("testaccount@test.com");

            int id = cursist.getCursistId();
            String oldMail = cursist.getEmail();

            Cursist newCursist = new Cursist("accounttest@test.com", "Test", "Test",
                    new Date(), "Test", "test", "Test",
                    19, "2951DC", "Test", "Test");

//            Action
            boolean res = cursistDAO.update(id, newCursist);

//            Output
            Assertions.assertTrue(res);

            Cursist newFoundCursist = cursistDAO.get(id);

            Assertions.assertEquals(cursist.getFirstName(), newFoundCursist.getFirstName());
            Assertions.assertEquals(oldMail, newFoundCursist.getEmail());
        }

    }

    @Nested
    @DisplayName("Should not")
    class ShouldNot {

        @Test
        @DisplayName("Should return false with missing credential")
        public void shouldReturnFalseWithMissingCredential() {
//            Input
            Cursist cursist = cursistDAO.getByEmail("testaccount@test.com");
            int id = cursist.getCursistId();
            Cursist newCursist = new Cursist("", "Test", "Test",
                    new Date(), "Test", "test", "Test",
                    19, "2951DC", "Test", "Test");

//            Action
            boolean res = cursistDAO.update(id, newCursist);

//            Output
            Assertions.assertFalse(res);
        }

        @Test
        @DisplayName("Should return false with wrong email format")
        public void shouldReturnFalseWithWrongEmailFormat() {
//            Input
            Cursist cursist = cursistDAO.getByEmail("testaccount@test.com");
            int id = cursist.getCursistId();
            Cursist newCursist = new Cursist("wasd", "Test", "Test",
                    new Date(), "Test", "test", "Test",
                    19, "2951DC", "Test", "Test");

//            Action
            boolean res = cursistDAO.update(id, newCursist);

//            Output
            Assertions.assertFalse(res);
        }

        @Test
        @DisplayName("Should return false with wrong postal code format")
        public void shouldReturnFalseWithWrongPostalCodeFormat() {
//            Input
            Cursist cursist = cursistDAO.getByEmail("testaccount@test.com");
            int id = cursist.getCursistId();
            Cursist newCursist = new Cursist("testaccount@test.com", "Test", "Test",
                    new Date(), "Test", "test", "Test",
                    19, "wasd", "Test", "Test");

//            Action
            boolean res = cursistDAO.update(id, newCursist);

//            Output
            Assertions.assertFalse(res);
        }

    }

    @AfterAll
    static void deleteTestAccount() {
        new CursistDAO().deleteByEmail("accounttest@test.com");
    }

}
