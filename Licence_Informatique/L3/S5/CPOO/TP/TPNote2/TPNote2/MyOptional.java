public class MyOptionnal<E>  {
	private E element;

	public MyOptionnal() {}

	public boolean isPresent() {
		return element != null;
	}

	public E getValeur() {
		return element;
	}

	public void setValeur(E valeur) {
		this.element = valeur;
	}

	public void testMyOptional(int n) {
		class CustomThread<E> extends Thread {
			MyOptionnal<E> input;
			MyOptionnal<E> output;
		}
		for (int i = 0 ; i < n ; i++) {
			CustomThread<Integer> c = new CustomThread();
			c.currentThread().start();

			MyOptionnal<Integer> mo = new MyOptionnal<Integer>();
			c.output = mo;
			c.input = mo;

			if (c.input.isPresent()) {
				c.input.setValeur(new Integer(c.input.getValeur()));
			} else {
				c.output.setValeur(new Integer(2));
			}
			if (c.output.isPresent()) {
				c.output.setValeur(c.input.getValeur());
			} else {
				System.out.println("Fin : " + c.input.getValeur());
			}
		}
	}
}