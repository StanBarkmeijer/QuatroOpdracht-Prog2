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
        CursistDAO cursistDAO = new CursistDAO();

        Cursist cursist = new Cursist("testaccount@test.com", "Test", "Test",
                new Date(), "Test", "test", "Test",
                19, "2951DC", "Test", "Test");

        cursistDAO.save(cursist);
    }

    @Nested
    @DisplayName("Should allow")
    class ShouldAllow {

        @Test
        @DisplayName("Should update account")
        public void shouldUpdateAccount() {
            Cursist cursist = cursistDAO.getByEmail("testaccount@test.com");

            int id = cursist.getCursistId();
            String oldMail = cursist.getEmail();

            Cursist newCursist = new Cursist("accounttest@test.com", "Test", "Test",
                    new Date(), "Test", "test", "Test",
                    19, "2951DC", "Test", "Test");

            boolean res = cursistDAO.update(id, newCursist);

            Assertions.assertTrue(res);

            Cursist newFoundCursist = cursistDAO.get(id);

            Assertions.assertEquals(cursist.getFirstName(), newFoundCursist.getFirstName());
            Assertions.assertEquals("accounttest@test.com", newFoundCursist.getEmail());
        }

    }

    @Nested
    @DisplayName("Should not")
    class ShouldNot {

        @Test
        @DisplayName("Should return false with missing credential")
        public void shouldReturnFalseWithMissingCredential() {
            Cursist cursist = cursistDAO.getByEmail("testaccount@test.com");
            int id = cursist.getCursistId();
            Cursist newCursist = new Cursist("", "Test", "Test",
                    new Date(), "Test", "test", "Test",
                    19, "2951DC", "Test", "Test");

            boolean res = cursistDAO.update(id, newCursist);

            Assertions.assertFalse(res);
        }

    }

    @AfterAll
    static void deleteTestAccount() {
        new CursistDAO().deleteByEmail("accounttest@test.com");
    }

}
