package mpendrick.mealchooser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MealChooseScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_meal);
        TextView chosenMeal = (TextView)findViewById(R.id.chosen_meal);
        Button backButton = (Button)findViewById(R.id.button1);
        backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
       //chosenMeal.setText(Meal_Chooser.mealContainer.numMeals);
        //chosenMeal.setText("7");
        //String cMeal = Integer.toString(Meal_Chooser.mealContainer.numMeals);
        if(Meal_Chooser.mealContainer.numMeals > 0){
        	//algorithm to randomly select a meal based on the pref and timesince meal
        	//pry should be a function in mealcontainer
        	int sIndex = Meal_Chooser.mealContainer.getMealIndex();
	        String cMeal = Meal_Chooser.mealContainer.meals.get(sIndex).name;
	        chosenMeal.setText(cMeal);
        }
        else
        	chosenMeal.setText("You don't have any meals, go to add/remove meals and add meals");
    }

}