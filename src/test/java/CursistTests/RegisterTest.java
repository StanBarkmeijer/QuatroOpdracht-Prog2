package CursistTests;

import datastorage.CursistDAO;
import domain.Cursist;
import org.junit.jupiter.api.*;

import java.util.Date;

public class RegisterTest {

    private final CursistDAO cursistDAO;

    public RegisterTest() {
        this.cursistDAO = new CursistDAO();
    }

    @Nested
    @DisplayName("Should allow")
    class ShouldAllow {

        @Test
        @DisplayName("Should return true, and add one to the size of the total records")
        public void shouldReturnTrueAndAddOneToTheSizeOfTheTotalRecords() {
            int currentRecords = cursistDAO.getAll().size();

            Cursist cursist = new Cursist("testaccount@test.com", "Test", "Test",
                    new Date(), "Test", "test", "Test",
                    19, "2951DC", "Test", "Test");

            boolean res = cursistDAO.save(cursist);
            int finalRecords = cursistDAO.getAll().size();

            Assertions.assertTrue(res);
            Assertions.assertEquals(currentRecords + 1, finalRecords);
        }

    }

    @Nested
    @DisplayName("Should not")
    class ShouldNot {

        @Test
        @DisplayName("Should return false with missing credentials")
        public void shouldReturnFalseWithMissingCredentials() {
            Cursist cursist = new Cursist("", "Stan", "Barkmeijer",
                    new Date(), "Man", "test", "Heinemanpad",
                    19, "2951DC", "Alblasserdam", "Netherlands");

            boolean res = cursistDAO.save(cursist);

            Assertions.assertFalse(res);
        }

    }

    @AfterAll
    static void deleteTestAccount() {
        new CursistDAO().deleteByEmail("testaccount@test.com");
    }

}
