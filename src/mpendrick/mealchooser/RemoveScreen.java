package mpendrick.mealchooser;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class RemoveScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove);
        
        final Spinner removeSpinner = (Spinner)findViewById(R.id.removeSpinner);
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        removeSpinner.setAdapter(spinnerAdapter);
        for(int i = 0; i < Meal_Chooser.mealContainer.numMeals; i++){
        	String mealName = Meal_Chooser.mealContainer.meals.get(i).name;
        	spinnerAdapter.add(mealName);
        	spinnerAdapter.notifyDataSetChanged();
        }
        
        final TextView test = (TextView)findViewById(R.id.test);
       
        
        Button removeItemButton = (Button)findViewById(R.id.removeItemButton);
        removeItemButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String removeMeal = removeSpinner.getSelectedItem().toString();
				Meal removedMeal = Meal_Chooser.mealContainer.mealMap.get(removeMeal);
				int removeIndex = 0;
				for(int i = 0; i < Meal_Chooser.mealContainer.numMeals; i++){
					if(Meal_Chooser.mealContainer.meals.get(i).name.equals(removeMeal)){
						removeIndex = i;
						break;
					}
				}

				Meal_Chooser.mealContainer.meals.remove(removeIndex);
				Meal_Chooser.mealContainer.mealMap.remove(removeMeal);
				Meal_Chooser.mealContainer.numMeals--;
				spinnerAdapter.remove(removeMeal);
			}
		});
        
        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
        
    }
    
}