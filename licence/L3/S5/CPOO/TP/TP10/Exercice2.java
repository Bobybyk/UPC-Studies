import java.time.Duration;
import java.time.Instant;

public class Exercice2 {

	public static boolean isPrime(long n) {
		if(n == 0) return false;
		for (int i = 2; i < Math.ceil(Math.sqrt(n)); i++) {
			if(n%i == 0) return false;
		}
		return true;
	}
	
	public static boolean isPrime(long n, int t) {
		return true;
	}
	
	public static void main(String[] argse) {
		Instant start = Instant.now();
		System.out.println(isPrime(9181531581341931811L));
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println(timeElapsed);

	}
}

