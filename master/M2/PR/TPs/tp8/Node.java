import java.util.concurrent.atomic.AtomicMarkableReference;

public class Node {
    public Integer item;
    public int key;
    public AtomicMarkableReference<Node> next;

    public Node(Integer item){
        this.item = item;
        this.key = item.hashCode();

    }
}