import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicList implements Set {
    private Node head;

    public AtomicList() {
        this.head = new Node(Integer.MIN_VALUE);
        this.head.next = new AtomicMarkableReference<Node>(new Node(Integer.MAX_VALUE),false);
        this.head.next.set(new Node(Integer.MAX_VALUE), false);
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
                node.next = new AtomicMarkableReference<Node>(curr, false);
                if (pred.next.compareAndSet(curr, node, false, false)) {
                    return true;
                }
            }
        }
    }

    @Override
    public boolean contains(Integer item) {
        boolean[] marked = {false};
        int key = item.hashCode();
        Node curr = this.head;

        while (curr.key < key) {
            marked[0] = curr.next.isMarked();
            curr = curr.next.getReference();
        }

        return (curr.key == key && !marked[0]);
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

    public Window find(Node head, int key) {
        Node pred = null, curr = null, succ = null, t = null;
        boolean[] marked = {false};
        boolean snip;
        retry:
        while (true) {
            pred = head;
            curr = pred.next.getReference();

            while (true) {

                if (curr.next == null) {
                    return new Window(pred, curr);
                }

                succ = curr.next.get(marked);
                while (marked[0]) {
                    snip = pred.next.compareAndSet(curr, succ, false, false);
                    if (!snip) continue retry;
                    curr = succ;
                    succ = curr.next.get(marked);
                }
                if (curr.key >= key)
                    return new Window(pred, curr);
                pred = curr;
                curr = succ;
            }
        }
    }

    public Node getNode() {
        return head;
    }
    
}
