package binaryTree;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.ListIterator;
/**
 *
 * @author Carlos Auqui
 */

public class GenericArrayList<T> implements Iterable<T> {
    private T[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public GenericArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T data) {
        if (size == array.length) {
            resize();
        }
        array[size++] = data;
    }
    public T get(int index) {
        if(this.isEmpty()){
            throw new UnsupportedOperationException("ista vacia");
        }
        if(index>=0 && index<size){
            return array[index];
        }else{
            throw new ArrayIndexOutOfBoundsException("indice fuera de rango");
        }
    }

    private void resize() {
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public ListIterator<T> listIterator() {
        return new GenericArrayListIterator();
    }

    private class GenericArrayListIterator implements ListIterator<T> {
        private int index = 0;
        private int lastReturnedIndex = -1;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = index;
            return array[index++];
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = --index;
            return array[index];
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (lastReturnedIndex < 0) {
                throw new IllegalStateException();
            }
            System.arraycopy(array, lastReturnedIndex + 1, array, lastReturnedIndex, size - lastReturnedIndex - 1);
            array[--size] = null;
            index = lastReturnedIndex;
            lastReturnedIndex = -1;
        }

        @Override
        public void set(T t) {
            if (lastReturnedIndex < 0) {
                throw new IllegalStateException();
            }
            array[lastReturnedIndex] = t;
        }

        @Override
        public void add(T t) {
            if (size == array.length) {
                resize();
            }
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = t;
            size++;
            index++;
            lastReturnedIndex = -1;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }
        };
    }

}
