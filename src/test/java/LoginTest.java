import datastorage.CursistDAO;
import domain.Cursist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private final CursistDAO cursistDAO;

    public LoginTest() {
        this.cursistDAO = new CursistDAO();
    }

    @Nested
    @DisplayName("Should allow")
    class ShouldAllow {
        @Test
        @DisplayName("Should succesfully login")
        public void shouldLoginWithCredentialsTestAtTestComAndTest() {
            Cursist cursist = cursistDAO.login("test@test.com", "test");

            String name = cursist.getFirstName();

            Assertions.assertEquals("Stan", name);
        }
    }

    @Nested
    @DisplayName("Should not")
    class ShouldNot {
        @Test
        @DisplayName("Should return null with wrong credentials")
        public void shouldReturnNullWithWrongCredentials() {
            Cursist cursist = cursistDAO.login("wasd", "wasd");

            Assertions.assertNull(cursist);
        }

        @Test
        @DisplayName("Should return null with missing credentials")
        public void shouldReturnNullWithMissingCredentials() {
            Cursist cursist = cursistDAO.login("", "test");
            Cursist cursist2 = cursistDAO.login("test@test.com", "");

            Assertions.assertNull(cursist);
            Assertions.assertNull(cursist2);
        }
    }

}
