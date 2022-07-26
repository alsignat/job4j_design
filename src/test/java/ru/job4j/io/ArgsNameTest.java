package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ArgsNameTest {
    @Test
    void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertEquals(jvm.get("Xmx"), "512");
    }

    @Test
    void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertEquals(jvm.get("Xmx"), "512");
    }

    @Test
    void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertEquals(jvm.get("request"), "?msg=Exit=");
    }

    @Test
    void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        assertNull(jvm.get("Xms"));
    }

    @Test
    void whenNoPrefix() {
        String[] args = new String[] {"requestmsg=Exit"};
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(args));
    }

    @Test
    void whenNoSeparator() {
        String[] args = new String[] {"-requestmsgExit"};
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(args));
    }

    @Test
    void whenEmptyKey() {
        String[] args = new String[] {"-=requestmsgExit"};
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(args));
    }

    @Test
    void whenEmptyValue() {
        String[] args = new String[] {"-requestmsg="};
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(args));
    }

    @Test
    void whenEmptyKeyValue() {
        String[] args = new String[] {"-="};
        assertThrows(IllegalArgumentException.class, () -> ArgsName.of(args));
    }

}