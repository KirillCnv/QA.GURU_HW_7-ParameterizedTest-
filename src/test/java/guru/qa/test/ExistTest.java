package guru.qa.test;

import guru.qa.page.ExistTestPage;
import guru.qa.utils.Config;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;


public class ExistTest extends Config {

    ExistTestPage existTestPage = new ExistTestPage();


    @ValueSource(strings = {"11210501", "550046387", "15669A"})
    @ParameterizedTest(name = "Результаты поиска выдают запчасти по артикулу \"{0}\"")
    public void commonAutoTestExist(String testData) {
        existTestPage.openPage()
                .setTextInput(testData)
                .checkResult(testData);

    }


    @CsvSource(value = {
            "11210501| Масло моторное синтетическое \"QUARTZ INEO ECS 5W-30\", 4л ",
            "550046387| Масло моторное синтетическое \"Helix Ultra 5W-30\", 4л ",
            "15669A| Масло моторное синтетическое \"EDGE LL Titanium FST 5W-30\", 4л "

    },
            delimiter = '|')
    @ParameterizedTest(name = "Результаты поиска выдают запчасти с описанием \"{1}\" по артикулу \"{0}\"")
    public void ComplexAutoTestExist(String testData, String result) {
        existTestPage.openPage()
                .setTextInput(testData)
                .checkResult(result);

    }

    static Stream<Arguments> dataProviderExistMenuTest() {
        return Stream.of(
                Arguments.of("Литература и ПО", List.of("Книги и руководства", "Программное обеспечение")),
                Arguments.of("Велосипеды и самокаты", List.of("Велосипеды", "Самокаты")),
                Arguments.of("Одежда и экипировка", List.of("Аксессуары для одежды", "Головные уборы", "Комбинезон", "Куртки", "Обувь", "Одежда", "Очки", "Перчатки", "Футболки и поло", "Часы наручные", "Шлемы")));


    }

    @MethodSource("dataProviderExistMenuTest")
    @ParameterizedTest(name = "Во вкладке {0} отображаются элементы {1}")
    public void existMenuTest(String value, List<String> elements) {
        existTestPage.openPageCatalog()
                .findElement(value)
                .examinationResult(elements);


    }
}
