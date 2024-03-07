import java.util.concurrent.atomic.AtomicMarkableReference;

public class Node {
    public Integer item;
    public int key;
    public AtomicMarkableReference<Node> next;
}