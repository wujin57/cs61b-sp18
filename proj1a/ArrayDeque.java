public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }
    private  int plusOne(int index) {
        return (index + 1) % items.length;
    }
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int currentFirst = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[currentFirst];
            currentFirst = plusOne(currentFirst);
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public T removeLast() {
        if (size == 0){
            return null;
        }
        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        size--;
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void printDeque() {
        int index = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[index] + " ");
            index = plusOne(index);
        }
        System.out.println();
    }
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int actualIndex = (nextFirst + 1 + index) % items.length;
        return items[actualIndex];
    }

    public int size() {
        return size;
    }


}
