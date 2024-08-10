package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

class BackwardArrayIteratorTest {

    @Test
    void whenMultiCallHasNextThenTrue() {
        ArrayIterator.BackwardArrayIterator iterator = new ArrayIterator.BackwardArrayIterator(
                new int[] {1, 2, 3, 4}
        );
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(4);
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenMultiCallHasNextThenNext() {
        ArrayIterator.BackwardArrayIterator iterator = new ArrayIterator.BackwardArrayIterator(
                new int[] {1, 2, 3}
        );
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.next()).isEqualTo(3);

    }

    @Test
    void whenReadSequence() {
        ArrayIterator.BackwardArrayIterator iterator = new ArrayIterator.BackwardArrayIterator(
                new int[] {1, 2, 3}
        );
        assertThat(iterator.next()).isEqualTo(3);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenNextFromEmpty() {
        ArrayIterator.BackwardArrayIterator iterator = new ArrayIterator.BackwardArrayIterator(
                new int[] {}
        );
        assertThatThrownBy(iterator::next)
                .isInstanceOf(NoSuchElementException.class);
    }
}