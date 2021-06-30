public class LinkedListDeque<T> {
    private static class ItemList<T> {
        public T item;
        public ItemList pre;
        public ItemList next;

        public ItemList(T item) {
            this.item = item;
            this.pre = null;
            this.next = null;
        }
    }

    private ItemList dummy;
    private int size;

    /*create an empty list */
    public LinkedListDeque() {
        dummy = new ItemList(0);
        size = 0;
    }
    /*connect two itemList*/
    private void connect(ItemList<T> a, ItemList<T> b) {
        a.next = b;
        b.pre = a;
    }

    public LinkedListDeque(T item) {
        dummy = new ItemList(0);
        size = 1;
        ItemList<T> node = new ItemList(item);
        connect(dummy, node);
        connect(node, dummy);
    }
    /*Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        ItemList<T> node = new ItemList(item);
        ItemList next = dummy.next;
        size += 1;

        //add first to empty list
        if (next == null) {
            connect(dummy, node);
            connect(node, dummy);
            return;
        }

        //add first to non-empty list
        connect(dummy, node);
        connect(node, next);

    }

    /*Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        ItemList<T> node = new ItemList(item);
        ItemList pre = dummy.pre;
        //add last to empty list
        if (pre == null) {
            connect(dummy, node);
            connect(node, dummy);
            return;
        }
        //add last to non-empty list
        connect(pre, node);
        connect(node, dummy);
        size += 1;
    }

    /*Returns true if deque is empty, false otherwise*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /*Returns the number of items in the deque*/
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last, separated by a space.*/
    public void printDeque() {
        ItemList<T> p = (ItemList<T>) dummy.next;
        if (p == null) return;
        while (p != dummy) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /*Removes and returns the item at the front of the deque. If no such item exists, returns null*/
    public T removeFirst() {
        //empty list
        if (dummy.next == null) return null;

        //non-empty list
        ItemList<T> node = (ItemList<T>)dummy.next.next;
        ItemList<T> first = (ItemList<T>)dummy.next;
        if (size == 1) {
            dummy.next = null;
            dummy.pre = null;
        } else {
            connect(dummy, node);

        }

        size--;
        return (T) first.item;
    }

    /*Removes and returns the item at the back of the deque. If no such item exists, returns null*/
    public T removeLast() {
        //empty list
        if (dummy.pre == null) return null;

        //non-empty list
        ItemList<T> node = (ItemList<T>)dummy.pre.pre;
        ItemList<T> last = (ItemList<T>)dummy.pre;
        if (size == 1) {
            dummy.next = null;
            dummy.pre = null;
        } else{
            connect(node, dummy);
        }
        size--;

        return (T) last.item;
    }
    /*
    helper function for getRecursive function
     */
    private T helperGet(ItemList<T> node, int index) {
        if (node == dummy) {
            return null;
        }
        if (index == 0) {
            return (T)node.item;
        }

        return (T) helperGet(node.next, index-1);
    }
    /*
    same as get, but uses recursion
     */
    public T getRecursive(int index) {
        return (T) helperGet(dummy.next, index);
    }

    /**Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index) {
        if (index < 0) return null;
        ItemList<T> p = dummy.next;
        while (p != dummy) {
            if (index == 0) {
                return (T)p.item;
            }
            index--;
            p = p.next;
        }
        return null;
    }
}
