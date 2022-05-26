import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static int nbrVertex = 0;
	private static int nbrEdges = 0;
	private static int nbrPlayers = 0;
	private static int nbrParty = 0;
	private static int[][] matrix;
	static int[] distanceFromArbitre;     //taille joeuurs
	static int[] distanceToArbitre;  

	// Liste de toutes les lignes
	private static List<int[]> ficLines = new LinkedList<>();

	public static void main(String args[]) {
		parsing(args[0]);
		//printMatrix();
		/*int[] res=dijkstra(matrix, nbrPlayers+1);
		for(int i=0;i<res.length;i++){
			System.out.print(res[i]+" ");
		}
		System.out.println();
        writeOutput(res); */
		
        distanceFromArbitre = dijkstra(matrix, nbrPlayers);
        /*distanceToArbitre = dijkstra(reverseGraph(matrix),nbrPlayers);
        int[][] timz = createTeams();
        int total = 0;
        for(int i=0;i<timz.length;i++)
        	total += poidsEquipe(timz[i]);
        System.out.println(total);*/
        int[] p=dijkstra2(matrix, nbrPlayers+1);
        printArray(p);
	}

	private static void parsing(String path) {
		// ouverture du fichier passé en argument
		File f = new File(path);

		// lecture du fichier et ajout de chaque ligne dans l'ArrayList de type String
		try {
			try (Scanner sc = new Scanner(f)) {
				while (sc.hasNextLine()) {
					String[] strLine = split_on_char(sc.nextLine());
					int[] intLine = new int[strLine.length];
					for (int i = 0 ; i < intLine.length ; i++) {
						intLine[i] = Integer.parseInt(strLine[i]);
					}
					ficLines.add(intLine);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0 ; i <ficLines.size() ; i++) {
			if (i == 0) {
				nbrVertex = ficLines.get(i)[0];
				nbrPlayers = ficLines.get(i)[1];
				nbrParty = ficLines.get(i)[2];
				nbrEdges = ficLines.get(i)[3];
				matrix = new int[nbrVertex][nbrVertex];
				initMatrix(matrix);
				continue;
			}
			matrix[ficLines.get(i)[0]-1][ficLines.get(i)[1]-1] = ficLines.get(i)[2];
		}
	}

	public static int[][] createTeams() {
		int[][] teams = new int[nbrParty][];

		int playersPerTeam = nbrPlayers / nbrParty;

		int dernierJoueur = 0;
		int id = 0;

		//System.out.println("nbr equipes : " + nbrParty);

		for(int i=0;i<teams.length;i++) {
			
			if(i == teams.length -1) {
				int rest = nbrPlayers - playersPerTeam*(nbrParty-1);
				teams[i] = new int[rest];
				for (int j = 0 ; j < rest ; j++) {
					teams[i][j] = id;
					id++;
				}
				continue;
			}
			
			teams[i] = new int[playersPerTeam];
			for (int j = 0 ; j<playersPerTeam ; j++) {
				teams[i][j] = id;
				id ++;
			}
		}

		return teams;
	}

	//retourne le poids total de toutes les communications au sein d'une équipe
	public static int poidsEquipe(int joueursEquipes[]) {
		int total = 0;
		for(int i=0;i<joueursEquipes.length;i++) {
			total += distanceToArbitre[joueursEquipes[i]] + distanceFromArbitre[joueursEquipes[i]];
		}
		return total;
	}

	public static int poidsEquipe2(Integer joueursEquipes[]) {
		int total = 0;
		for(int i=0;i<joueursEquipes.length;i++) {
			total += distanceToArbitre[joueursEquipes[i]] + distanceFromArbitre[joueursEquipes[i]];
		}
		return total;
	}



	// Pour convertir la chaine de caractère en tableau
	public static String[] split_on_char(String line) {
		return line.split(" ");
	}

	public static void printMatrix() {
		for (int i = 0 ; i < matrix.length ; i++) {
			System.out.print("[");
			for (int j = 0 ; j < matrix.length ; j++) {
				if(matrix[i][j]>-1){
					System.out.print(" "+matrix[i][j] + ", ");
				}
				else{
					System.out.print(matrix[i][j] + ", ");
				}
			}
			System.out.println("]");
		}
	}

	public static int[][] reverseGraph(int [][] src) {
			int[][] res = new int[src.length][src.length];
			for(int i=0;i<res.length;i++) {
				for(int j=0;j<res.length;j++) {
					res[i][j] = src[j][i];
				}
			}
			return res;
	}

        // écriture du résultat dans un fichier res.out
        private static void writeOutput(int res[]) {
            try {
                System.out.println("Debut ecriture resultat");
                File outputfile = new File("res.out");
                outputfile.createNewFile();
    
                FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
    
                //bw.write(res);
                
                for(int i=0;i<res.length;i++){
                    bw.write(res[i]+" ");
                }

                bw.close();
                System.out.println("Fin ecriture resultat");
    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    static void printArray(int [] p){
        System.out.print("[");
        for(int i=0;i<p.length-1;i++){
            System.out.print(p[i]+";");
        }
        System.out.println(p[p.length-1]+"]");
    }

	/// ----------------- DEBUT VERSION1 ----------------

	//Renvoie le tableau contenant le plus petit chemin reliant chaque sommet à l'arbitre
	public static int[] dijkstra2(int[][] matrice, int arbitre){
		int [] P=new int[matrice.length-1];
		for(int i=0;i<matrice[1].length-1;i++){
			P[i]=-1;
		}
        int i=0;
        while(i<matrice.length-1){
            if(i!=arbitre-1){
			    P[i]=distance(matrice, i, arbitre);
            }
            else{
                for(int j=i;j<matrice.length-1;j++){
                    P[j]=distance(matrice, j+1,arbitre);
                    i=matrice.length;
                }
            }
            i++;
        }
		return P;
	}

	//Calcule la distance de tous les points par rapport à départ
	
    public static int distance(int[][] matrice,int position,int depart){
		int t=1000000000;
		int tmp=0;
		int stockage_position_x=0;
		int i=0;
		while(i<matrice.length){
			//Si l'arc à la position (i;position) est différent de -1 et a un poids plus petit
			//que le dernier arc plus petit qui a été trouvé, on stocke la valeur de l'arc et la valeur de i
			
            if(matrice[position][i]>-1 && t>=matrice[position][i]){
				t=matrice[position][i];
				stockage_position_x=i;
			}
			//Si on a parcouru l'entièreté de la hauteur de la matrice, on incrémente tmp de la valeur du plus petit arc trouvé
            if(i==matrice.length-1){
				tmp+=t;
				
                //Si l'arc le plus petit trouvé n'est pas dirigé vers le point de départ on continue la recherche du plus petit arc
				//partant du sommet qu'on vient de trouver
				if(stockage_position_x!=nbrPlayers){
					position=stockage_position_x;
                    
                    //On reset t et i pour la recherche du nouveau plus petit arc
					t=1000000000;
					i=-1;
				}
			}
			i++;
		}
		return tmp;
	}


	//Remplit la matrice avec de -1 afin de savoir quels arcs n'existent pas pour Dijkstra
	public static void initMatrix(int[][] matrice){
		for(int i=0;i<matrice.length;i++){
			for(int j=0;j<matrice[i].length;j++){
				matrice[i][j]=-1;
			}
		}
	}


	/// ----------------- FIN VERSION2 ----------------
    
	/// ----------------- DEBUT VERSION1 ----------------
	private static int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
 
        for (int v = 0; v < matrix.length; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed distance array
    private static void printSolution(int dist[])
    {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < matrix.length; i++)
            System.out.println(i + " \t\t " + dist[i]);
    }
 
    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    private static int[] dijkstra(int graph[][], int src)
    {
        int dist[] = new int[matrix.length]; // The output array. dist[i] will hold
        // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[matrix.length];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < matrix.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < matrix.length - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < matrix.length ; v++)
 
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
 
        // print the constructed distance array
        return dist;
    }

		public static int worstTeam() {
        int from = 0;
        int to = 0;
        for (int i = 0; i < distanceToArbitre.length; i++) {
            if (distanceToArbitre[i] > to)
                to = i;
            if (distanceFromArbitre[i] > from)
                from = i;
        }
        int[] t = { to, from };
        return poidsEquipe(t);
    }

	public static int bestWorstTeam(){
		int from = 0;
        int to = Integer.MAX_VALUE;
        for (int i = 0; i < distanceToArbitre.length; i++) {
            if (distanceToArbitre[i] < to)
                to = i;
            if (distanceFromArbitre[i] > from)
                from = i;
        }
        int[] t = { to, from };
        return poidsEquipe(t);
	}

    public Integer[][] makeTeam(int distance) {
		Integer[][] teams = new Integer[nbrParty][];
		int pointerFrom = 0;
		int pointerTo = 0;
		int minPlayersLeft = nbrParty*2;
		int worst = worstTeam();

        for (int i = 0; i < nbrParty; i++) {
			int poids;
			ArrayList<Integer> team = new ArrayList<Integer>();
			team.add(distanceToArbitre[pointerTo]);
			team.add(distanceFromArbitre[pointerFrom]);
			pointerFrom ++;
			pointerTo ++;
			Integer[] newTeam = new Integer[team.size()];

			newTeam = team.toArray(newTeam);
				
			poids = poidsEquipe2(newTeam);

			while(poids<worst){

				if(minPlayersLeft<0)
					break;

            	if(distanceToArbitre[pointerTo]<distanceFromArbitre[pointerFrom]){
					team.add(distanceToArbitre[pointerTo]);
					pointerTo++;
				}else{
					team.add(distanceFromArbitre[pointerFrom]);
					pointerFrom++;
				}
				newTeam = new Integer[team.size()];
				newTeam = team.toArray(newTeam);				
				poids = poidsEquipe2(newTeam);
			}
			teams[i] = newTeam;
        }
		return teams;
    }
}
