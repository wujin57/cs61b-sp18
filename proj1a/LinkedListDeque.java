public class LinkedListDeque<T>{
    public  class IntNode {
        public T item;
        public IntNode next;
        public IntNode prev;
        public IntNode(T i) {
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
    public LinkedListDeque(LinkedListDeque other) {
        this();
        for (IntNode p = other.sentinel.next; p != other.sentinel; p = p.next) {
            this.addLast(p.item);
        }
    }

    public LinkedListDeque(T item) {
        this();
        addFirst(item);
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

    public int size(){
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

    public T get(int index) {
        if (index >= size()) return null;
        IntNode p = sentinel.next;
        for (int i = 0; i < index; i++){
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
        return getRecursiveHelper(index - 1,node.next);
    }


}