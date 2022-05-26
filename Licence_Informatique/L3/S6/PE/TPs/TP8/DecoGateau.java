import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class DecoGateau {

    private static int cakeSize;
    private static int[] stripA;
    private static int[] stripB;

    private static void parsing(String path) throws FileNotFoundException {

		File f = new File(path);

        int count = 0;

        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNextLine()) {
                String[] strLine = sc.nextLine().split(" ");
                int[] intLine = new int[strLine.length];
                for (int i = 0 ; i < intLine.length ; i++) {
                    intLine[i] = Integer.parseInt(strLine[i]);
                }

                if (count == 0) {
                    cakeSize = intLine[count];
                }

                else if (count == 1) {
                    stripA = intLine;
                }
                else if (count == 2) {
                    stripB = intLine;
                }
                count++;
            }
        }
    }

    private static void writeOutput(long[] resSurface) {
        try {
            System.out.println("Debut ecriture resultat");
            File outputfile = new File("res.out");
            outputfile.createNewFile();

            FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(((Long)resSurface[0] + " ").toString());
            bw.write(((Long)resSurface[1] + " ").toString());
            bw.write(((Long)resSurface[2] + " ").toString());

            bw.close();
            System.out.println("Fin ecriture resultat");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long[] findSurface() {
        long[] surface = new long[3];

        for (int i = 0 ; i < cakeSize ; i++) {
            for (int j = 0 ; j < cakeSize ; j++) {
                switch((i+j)%3) {
                    case 0: if (stripA[i] >= 1 && stripA[i] <= 10000 && stripB[j] >= 1 && stripB[j] <= 10000) surface[0] += stripA[i]*stripB[j] ; break;
                    case 1: if (stripA[i] >= 1 && stripA[i] <= 10000 && stripB[j] >= 1 && stripB[j] <= 10000) surface[1] += stripA[i]*stripB[j] ; break;
                    case 2: if (stripA[i] >= 1 && stripA[i] <= 10000 && stripB[j] >= 1 && stripB[j] <= 10000) surface[2] += stripA[i]*stripB[j] ; break;
                }
            }
        }

        return surface;
    }

    public static void main(String[] args) {
        //if (args.length < 1) {
            try {
                parsing(args[0]);
                long[] surface = findSurface();
                System.out.println(Arrays.toString(surface));
                writeOutput(surface);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        //}
    }
}