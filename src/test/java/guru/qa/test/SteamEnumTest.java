package guru.qa.test;

import com.codeborne.selenide.CollectionCondition;
import guru.qa.utils.Config;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SteamEnumTest extends Config {

    static Stream<Arguments> enumDellPageTest() {
        return Stream.of(
                Arguments.of(Lang.FR, List.of("MAGASIN", "COMMUNAUTÉ", "À PROPOS", "SUPPORT")),
                Arguments.of(Lang.IT, List.of("NEGOZIO", "COMUNITÀ", "INFORMAZIONI", "ASSISTENZA")));

    }

    @EnumSource(Lang.class)
    @MethodSource("enumDellPageTest")
    @ParameterizedTest(name = "Для локали {0} отображаются кнопки {1}")
    public void enumDellPage(Lang lang, List<String> enumResult) {
        open("https://store.steampowered.com/");
        sleep(2000);
        // Selenide.clearBrowserLocalStorage();
        $(".pulldown").click();
        $$("#language_dropdown").findBy(text(lang.name())).click();
        $$(".supernav_container .menuitem ").shouldHave(CollectionCondition.texts(enumResult));
    }
}
