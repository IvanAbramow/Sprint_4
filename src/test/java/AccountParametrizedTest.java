import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccountParametrizedTest {

    private final String name;
    private final Boolean expectedName;

    public AccountParametrizedTest(String name, boolean expectedName) {
        this.name = name;
        this.expectedName = expectedName;
    }

    @Parameterized.Parameters
    public static Object[][] dataForTest() {
        return new Object[][]{
                {"Ив", false},
                {"Ив ", false},
                {"И А", true},
                {"", false},
                {null, false},
                {"Абдрахман Абрамович", true},
                {"Абдрахмана Абрамович", false},
                {"Абдрахма Абрамович", true},
                {"Иван Абрамов", true},
                {" Иван Абрамов", false},
                {"Иван Абрамов ", false},
                {" Иван Абрамов ", false},
                {" ИванАбрамов", false},
                {"ИванАбрамов ", false},
                {"Иван  Абрамов", false},
        };
    }

    @Test
    @DisplayName("Проверка имени держателя банковской карты")
    @Description("Проверка, что имя держателя банковской карты подходит под заданные условия")
    public void checkCardHolderNameTest(){
        Account account = new Account(name);
        boolean actual = account.checkNameToEmboss();
        assertEquals(expectedName, actual);
    }
}
