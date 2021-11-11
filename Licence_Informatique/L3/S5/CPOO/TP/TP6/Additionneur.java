import java.util.function.UnaryOperator;

public class Additionneur implements UnaryOperator<Integer> {
    private int x;

    public Additionneur(int x) {
        this.x = x;
    }

    @Override
    public Integer apply(Integer integer) {
        return integer + x;
    }
}