public class LinkedListDeque<T> {
    private  class IntNode {
        private T item;
        private IntNode next;
        private IntNode prev;
        private IntNode(T i) {
            item = i;
            next = null;
            prev = null;
        }
        public IntNode(T i, IntNode b, IntNode n) {
            item = i;
            next = n;
            prev = b;
        }
    }
    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }


    public void addFirst(T item) {
        sentinel.next = new IntNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;

    }

    public void addLast(T item) {
        sentinel.prev = new IntNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode p = sentinel.next;
        for (int i = 0; i < size(); i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        IntNode first = sentinel.next;
        T item = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return  item;
    }
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        IntNode last = sentinel.prev;
        T item = last.item;
        sentinel.prev = last.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;
    }
    public T get(int index) {
        if (index >= size()) {return null;}
        IntNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }
    private T getRecursiveHelper(int index, IntNode node) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(index - 1, node.next);
    }


}