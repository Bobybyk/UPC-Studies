package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static List<String> linesGR = new LinkedList<String>();
    private static int verticesNbrGR = -1;
    private static int edgesNbrGR = -1;
    private static LinkedList<Integer[]> edgesGR = new LinkedList<Integer[]>();

    public static void parseGR() {
        File f = new File("src/data/graph.gr");
        System.out.println(f.toPath());
        try {
            try (Scanner sc = new Scanner(f)) {
                while (sc.hasNextLine()) {
                    linesGR.add(sc.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        int i = 0;

        while(i < linesGR.size()) {
            if (i == 0) {
                verticesNbrGR = Integer.parseInt(linesGR.get(i).split(" ")[0]);
                edgesNbrGR = Integer.parseInt(linesGR.get(i).split(" ")[1]);
            } else {
                edgesGR.add(new Integer[] {Integer.parseInt(linesGR.get(i).split(" ")[0]), Integer.parseInt(linesGR.get(i).split(" ")[1])});
            }
            i++;
        }
    }

    public static int getVerticesNbrGR() {
        return verticesNbrGR;
    }
    public static int getEdgesNbrGR() {
        return edgesNbrGR;
    }
    public static LinkedList<Integer[]> getEdgesGR() {
        return edgesGR;
    }

}
