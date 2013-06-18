package mpendrick.mealchooser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MealContainer extends Object{

	public int numMeals;
	public int mealsOld;
	ArrayList<Meal> meals;
	Map<String, Meal> mealMap; 

	public MealContainer(){
		this.numMeals = 0;
		this.mealsOld = 0;
		meals  = new ArrayList<Meal>();
		mealMap = new HashMap<String, Meal>();
	}

	public int getMealIndex(){
		int scoreCount = 0;
		int runningScore = 0;
		int randy;
		int winnerIndex;
		int[] scores = new int[numMeals];
		for(int i = 0; i < numMeals; i++){
			scores[i] = (int)(meals.get(i).preference * (.9) + meals.get(i).age * (.1));
			scoreCount+=scores[i];
		}
		randy = (int)(Math.random() * scoreCount) + 1;
		int i = 0;
		while(true){
			if(scores[i] + runningScore >= randy){
				winnerIndex = i;
				break;
			}
			else{
				runningScore+=scores[i];
				i++;
			}
		}	
		return winnerIndex;
	}

}