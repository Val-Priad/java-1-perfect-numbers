package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @ParameterizedTest(name = "getMersseneNubmer({0}) -> {1}")
    @CsvSource({
            "2, 3",
            "3, 7",
            "5, 31",
            "7, 127"
    })
    void getMersseneNumberValid(long p, long expected) {
        assertEquals(expected, Main.getMersseneNumber(p));
    }

    @ParameterizedTest(
            name = "getMersseneNubmer({0}) -> throws IllegalArgumentException")
    @CsvSource({
            "-2",
            "0",
    })
    void getMersseneNumberInvalid(long p) {
        assertThrows(IllegalArgumentException.class,
                () -> Main.getMersseneNumber(p));
    }

    @ParameterizedTest(name = "getPerfectNumber({0}) -> {1}")
    @CsvSource({
            "2, 6",
            "3, 28",
            "5, 496",
            "7, 8128",
            "13, 33550336"
    })
    void getPerfectNumberValid(long p, long expected) {
        assertEquals(expected, Main.getPerfectNumber(p));
    }

    @ParameterizedTest(
            name = "getPerfectNumber({0}) -> throws IllegalArgumentException")
    @CsvSource({
            "1",
            "-1",
            "0",
            "4",
            "6",
            "8",
            "9",
            "10",
            "11",
            "12"
    })
    void getPerfectNumberInvalid(long p) {
        assertThrows(IllegalArgumentException.class,
                () -> Main.getPerfectNumber(p));
    }

    @ParameterizedTest(name = "lucasLehmer({0}) -> {1}")
    @CsvSource({
            "2, true",
            "17, true",
            "13, true",
            "3, true",
            "5, true",
            "7, true",
            "11, false",
            "23, false",
            "29, false",
    })
    void lucasLehmerValid(long p, boolean expected) {
        assertEquals(expected, Main.lucasLehmer(p));
    }

    @ParameterizedTest(
            name = "lucasLehmer({0}) -> throws IllegalArgumentException")
    @CsvSource({
            "1",
            "0",
            "-1",
    })
    void lucasLehmerInvalid(long p) {
        assertThrows(IllegalArgumentException.class,
                () -> Main.lucasLehmer(p));
    }

    @ParameterizedTest(name = "isPrime({0}) -> {1}")
    @CsvSource({
            "2, true",
            "19, true",
            "113, true",
            "1061, true",
            "104729, true",
            "4, false",
            "21, false",
            "120, false",
            "1050, false",
            "104730, false"
    })
    void isPrimeTrue(long p, boolean expected) {
        assertEquals(expected, Main.isPrime(p));
    }

    @ParameterizedTest(
            name = "isPrime({0}) -> throws IllegalArgumentException")
    @CsvSource({
            "1",
            "0",
            "-1"
    })
    void isPrimeInvalid(long p) {
        assertThrows(IllegalArgumentException.class,
                () -> Main.isPrime(p));
    }

    @ParameterizedTest(name = "nextPrime({0}) -> {1}")
    @CsvSource({
            "2, 3",
            "3, 5",
            "41, 43",
            "67, 71",
            "89, 97"
    })
    void nextPrimeValid(long p, long expected) {
        assertEquals(expected, Main.nextPrime(p));
    }


    @ParameterizedTest(name = "getPerfectNumbersUpTo({0}) -> {1}")
    @MethodSource("arrayProvider")
    void getPerfectNumbersUpToValid(long p, List<Long> expected) {
        assertEquals(expected, Main.getPerfectNumbersUpTo(p));
    }

    static Stream<Arguments> arrayProvider() {
        return Stream.of(
                Arguments.of(1L, List.of()),
                Arguments.of(10L, List.of(6L)),
                Arguments.of(30L, List.of(6L, 28L)),
                Arguments.of(137438691329L,
                        List.of(6L, 28L, 496L, 8128L, 33550336L, 8589869056L,
                                137438691328L))

        );
    }

    @ParameterizedTest()
    @CsvSource({
            "-1000",
            "-5",
            "0",
    })
    void getPerfectNumbersUpToInvalid(long topLimit) {
        assertThrows(IllegalArgumentException.class,
                () -> Main.getPerfectNumbersUpTo(topLimit));

    }
}
