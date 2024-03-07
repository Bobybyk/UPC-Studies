public class Window {
    public Node pred, curr;

    public Window(Node pred, Node curr) {
        this.pred = pred;
        this.curr = curr;
    }

    public Window find(Node head, int key) {
        Node pred = null, curr = null, succ = null,t=null;
        boolean[] marked = {false}; boolean snip;
        retry: while (true) {
            pred = head;
            curr = pred.next.getReference();
            while (true) {
            succ = curr.next.get(marked);
                while (marked[0]) {

                }
                if (curr.key >= key)
                return new Window(pred, curr);
                pred = curr;
                curr = succ;
            }
        }
    }
}
