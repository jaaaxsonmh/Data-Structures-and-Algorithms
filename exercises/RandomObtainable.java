package exercises;

import java.util.NoSuchElementException;

public interface RandomObtainable<E> {
    E getRandom() throws NoSuchElementException;

    boolean removeRandom() throws UnsupportedOperationException;
}
