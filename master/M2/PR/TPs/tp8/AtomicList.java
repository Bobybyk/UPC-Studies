import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicList implements Set {
    private Node head;

    public AtomicList() {
        this.head = new Node(Integer.MIN_VALUE);
        this.head.next = new AtomicMarkableReference(new Node(Integer.MAX_VALUE), false);
    }

    @Override
    public boolean add(Integer item) {
        int key = item.hashCode();
        while (true) {
            Window window = find(head, key);
            Node pred = window.pred; Node curr = window.curr;
            if (curr.key == key) {
                return false;
            } else {
                Node node = new Node(item);
                node.next = new AtomicMarkableRef(curr, false);
                if (pred.next.compareAndSet(curr, node, false, false)) {
                    return true;
                }
            }
        }
    }

    @Override
    public boolean contains(Integer item) {
        boolean[] marked;
        int key = item.hashCode();
        Node curr = this.head;
        while (curr.key < key) {
            curr = curr.next;
        }
        Node succ = curr.next.get(marked);
        return (curr.key == key && !marked[0])
        }

    @Override
    public boolean remove(Integer item) {
        Boolean snip; int key = item.hashCode();
        while (true) {
            Window window = find(head, key);
            Node pred = window.pred; Node curr = window.curr;
            if (curr.key != key) {
                return false;
            } else {
                Node succ = curr.next.getReference();
                snip = curr.next.attemptMark(succ, true);
                if (!snip) continue;
                pred.next.compareAndSet(curr, succ, false, false);
                return true;
            }
        }
    }
    
}
