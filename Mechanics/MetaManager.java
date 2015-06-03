package Mechanics;
import Hunters.*;


public class MetaManager {
	static int NUM_GAMES = 1;
	public MetaManager() {
		
	}
	
	public static void main(String[] args){
		
		
		Hunter[] hunters = {new ParanoidHunter(), new TriggerHappyHunter(), new HuddleHunter(), new RandomHunter()};
		int[] scores = {0,0,0,0,0,0};
		for(int i = 0; i < NUM_GAMES; i++){
			int[] newScores = HuntWumpuses.play(hunters);
			for(int j = 0; j < scores.length; j++){
				scores[j] += newScores[j];
			}
		}
		
		for(int j = 0; j < hunters.length; j++){
			System.out.println(hunters[j].name + " : " + scores[j]);
			
		}
		System.out.println("Humans killed by arrows:" +  scores[4]);
		System.out.println("Wumpus victories:" +  scores[5]);
		
		
	}

}
