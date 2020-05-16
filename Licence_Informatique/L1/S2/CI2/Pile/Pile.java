import java.util.*;

public class Pile {
	
	public static boolean parenthese (String s) {
		Stack<Char> pile = new Stack<Char>();
		char c;
		for (int i = 0 ; i<s.length ; i++) {
			c = s.charAt(i);
			if (c == '(') {
				pile.push(c);
			} else if (c == ')') {
				if (pile.empty()) {
					return false;
				} s.pop();
			}
		} return true;
	}

	public static void main (String[] args) {

		System.out.println(parenthese("(()"));

	}

}