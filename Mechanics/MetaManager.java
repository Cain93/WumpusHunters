package Mechanics;


import Hunters.*;


public class MetaManager {
	static int NUM_GAMES = 1000;
	public MetaManager() {
		
	}
	
	public static void main(String[] args){
		
		
		Hunter[] hunters = {/*new ParanoidHunter(), new TriggerHappyHunter(), new HuddleHunter(), new RandomHunter(),*/ new Badger(), new Idomeneus(), new MonsterHunter(), new PacingHunter(), new ScaredyCat()};
		int[] scores = new int[hunters.length + 2];
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
							int[] newScores = HuntWumpuses.play(thisRound);
							scores[a] += newScores[0];
							scores[b] += newScores[1];
							scores[c] += newScores[2];
							scores[d] += newScores[3];
							scores[scores.length - 2] += newScores[4];
							scores[scores.length - 1] += newScores[5];
						}
						
					}
				}
			}
		}
		
		
		//How many games is each hunter in?
		int gamesPerHunter = choose(hunters.length - 1, 3) * NUM_GAMES;
		
		
		for(int j = 0; j < hunters.length; j++){
			double normalized = Math.floor((double)scores[j]/(double)gamesPerHunter * 100) / 100;
			System.out.println(hunters[j].name + " : " + scores[j] + " (" + normalized + ")");
			
		}
		System.out.println("Total rounds: " + totalGames);
		System.out.println("Humans killed by arrows: " +  scores[scores.length - 2]);
		System.out.println("Wumpus victories: " +  scores[scores.length - 1]);
		
		
	}
	
	public static int choose(int total, int choose){
	    if(total < choose)
	        return 0;
	    if(choose == 0 || choose == total)
	        return 1;
	    return choose(total-1,choose-1)+choose(total-1,choose);
	}

}
