package ru.job4j.generics;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
class RoleStoreTest {
    @Test
    void whenAddAndFindThenIsNull() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "manager"));
        assertThat(store.findById("10")).isNull();
    }

    @Test
    void whenAddAndFindThenManager() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1","manager"));
        assertThat(store.findById("1").getRoleName()).isEqualTo("manager");
    }

    @Test
    void whenAddDuplicateAndFindThenManager() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "manager"));
        store.add(new Role("1", "director"));
        assertThat(store.findById("1").getRoleName()).isEqualTo("manager");
    }

    @Test
    void whenAddAndReplaceDuplicateThenDirector() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "manager"));
        store.replace("1", new Role("1", "director"));
        assertThat(store.findById("1").getRoleName()).isEqualTo("director");
    }

    @Test
    void whenDeleteThenIsNull() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "manager"));
        store.delete("1");
        assertThat(store.findById("1")).isNull();
    }
    @Test
    void whenReplaceNotOkThenIsFalse() {
        Store<Role> store = new RoleStore();
        store.add(new Role("1", "manager"));
        boolean result = store.replace("10", new Role("10", "director"));
        assertThat(result).isFalse();
    }
}