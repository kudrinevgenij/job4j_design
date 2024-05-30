package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isUnknown() {
        Box box = new Box(5, 4);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenSphereThen0() {
        Box box = new Box(0, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
    }

    @Test
    void whenEdge0ThenFalse() {
        Box box = new Box(0, 0);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenEdge1ThenTrue() {
        Box box = new Box(0, 1);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenSphereEdge5ThenArea314() {
        Box box = new Box(0, 5);
        assertThat(box.getArea()).isEqualTo(314d, withPrecision(0.2d));
    }

    @Test
    void whenCubeEdge5ThenArea150() {
        Box box = new Box(8, 5);
        assertThat(box.getArea()).isEqualTo(150);
    }

}