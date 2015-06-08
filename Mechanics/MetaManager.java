package Mechanics;


import Hunters.*;


public class MetaManager {
	static int NUM_GAMES = 1000;
	public MetaManager() {
		
	}
	
	public static void main(String[] args){
		
		
		
		Hunter[] hunters = {new ParanoidHunter(), new TriggerHappyHunter(), new HuddleHunter(), new RandomHunter(), new Badger(), 
							new Idomeneus(), new MonsterHunter(), new PacingHunter(), new ScaredyCat(), new NascarHunter(), 
							new EatsShootsAndLeaves(), new Pacifist(), new Laomedon(), new FullCoverageHunter(), new NealHunter(), 
							new WalkingIdiot(), new ElmerFudd(), new Stay() }; 
		int[] scores = new int[hunters.length];
		int[] stats = new int[4];
		int totalGames = 0;
		
		//There's gotta be a better way to do this...
		for(int a = 0; a < hunters.length - 3; a++){
			for(int b = a + 1; b < hunters.length - 2; b++){
				for(int c = b + 1; c < hunters.length - 1; c++){
					for(int d = c + 1; d < hunters.length; d++){
						
						//Finally, real code
						for(int i = 0; i < NUM_GAMES; i++){
							totalGames++;
							Hunter[] thisRound = {hunters[a], hunters[b], hunters[c], hunters[d]};
							int[][] newScores = HuntWumpuses.play(thisRound);
							scores[a] += newScores[0][0];
							scores[b] += newScores[0][1];
							scores[c] += newScores[0][2];
							scores[d] += newScores[0][3];
					
							stats[0] += newScores[1][0];
							stats[1] += newScores[1][1];
							stats[2] += newScores[1][2];
							stats[3] += newScores[1][3];
						}
						
					}
				}
			}
		}
		
		
		//Sort hunters
		sort(scores, hunters);
		
		//How many games is each hunter in?
		int gamesPerHunter = choose(hunters.length - 1, 3) * NUM_GAMES;
		
		
		for(int j = 0; j < hunters.length; j++){
			double normalized = Math.floor((double)scores[j]/(double)gamesPerHunter * 100) / 100;
			System.out.println((j+1) + ". " + hunters[j].name + " : " + scores[j] + " (" + normalized + ")");
			
		}
		System.out.println("Total rounds: " + totalGames);
		System.out.println("Humans killed by arrows: " +  stats[0]);
		System.out.println("Humans dead by starvation: " +  stats[1]);
		System.out.println("Humans killed by wumpus: " +  stats[2]);
		System.out.println("Wumpus victories: " +  stats[3]);
		
		
	}
	
	public static int choose(int total, int choose){
	    if(total < choose)
	        return 0;
	    if(choose == 0 || choose == total)
	        return 1;
	    return choose(total-1,choose-1)+choose(total-1,choose);
	}
	
	//array is small so I guess bubblesort is fine
	public static void sort(int[] toSort, Object[] toMatch){
		int c,d, n = toSort.length;
		
		for (c = 0; c < ( n - 1 ); c++) {
		      for (d = 0; d < n - c - 1; d++) {
		        if (toSort[d] < toSort[d+1]) 
		        {
		          swap(toSort, d, d + 1);
		          swap(toMatch, d, d + 1);
		        }
		      }
		    }
	}
	
	private static void swap(int[] a, int x, int y){
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	//God this is getting sloppy
	private static void swap(Object[] a, int x, int y){
		Object temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

}
