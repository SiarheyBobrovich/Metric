package ru.clevertec.metrics.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class StringsUtilTest {

    public static Stream<Arguments> getCamelCaseAndSnakeCase() {
        return Stream.of(
                Arguments.of("getProductById", "get_product_by_id"),
                Arguments.of("isMain", "is_main"),
                Arguments.of("findByReference", "find_by_reference"),
                Arguments.of("getCountOfAllProducts", "get_count_of_all_products"),
                Arguments.of("isIO", "is_io")
        );
    }

    @ParameterizedTest
    @MethodSource("getCamelCaseAndSnakeCase")
    void toLowerSnakeCase(String current, String expected) {
        String lowerSnakeCase =  StringsUtil.toLowerSnakeCase(current);

        assertThat(lowerSnakeCase).isEqualTo(expected);
    }
}
