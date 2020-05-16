import java.Util.*;

public class BlockA {
	static int arg0;
	static int arg1;
	static int ric;

	public BlockA (int arg0, int arg1, int ric) {
		this.arg0 = arg0;
		this.arg1 = arg1;
		this.ric = ric;
	}

	public static void main (String[] args) {
		int[] mem = mem int [100000];
		Stack<BlockA>p = new Stack<BlockA>();
		mem[0] = 5;
		mem[1] = 3;
		int ic = 0;
		while (true) {
			switch(ic++) {
				case 0: p.push(new BlockA(7, 11, 1)); ic = 100; break;
				case 1: p.pop(); break;
				case 2: System.out.println("a = " + mem[0] + " b" + mem[1]); break;
				case 3: System.out.exit(0)

				case 100: p.push(new BlockA(mem[0] + mem[1], 12, 101)); ic = 200; break;
				case 101: p.pop(); break;
				case 102: if (p.peek().arg0<2*mem[0] && mem[1] < p.peek().arg1) { ic -= 3; } break;
				case 103: ic = peek().ric; break;
				case 200: if (p.peek().arg0<p.peek().arg1) { ic += }
			}
		}
	}
}