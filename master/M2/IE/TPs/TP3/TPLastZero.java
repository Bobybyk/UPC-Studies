import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TPLastZero {


        public static void main(String[] args) {
                int[] x1 = {0, 1, 0};
                System.out.println(lastZero(x1));
        }

        public static int lastZero(int [] x) {
                for (int i = 0; i < x.length; i++) {
                        if (x[i] == 0) return i;
                }
                return -1;
        }

        @Test
        public void testQuestion2() {
                int[] x1 = {1, 1, 1};
                assertEquals(-1, lastZero(x1));
        }

        @Test
        public void testQuestion3() {
                int[] x1 = {1, 1, 0};
                assertEquals(2, lastZero(x1));
        }

        /* question 4
         * il n'y a pas de scenario de test où 
         */

}