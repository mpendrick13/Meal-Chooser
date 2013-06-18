package mpendrick.mealchooser;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Meal_Chooser extends Activity {
    public static MealContainer mealContainer;
    public static final String DATA_NAME = "MealChooserDataFile";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedPreferences data = getSharedPreferences(DATA_NAME, 0);
        mealContainer = new MealContainer();
        mealContainer.numMeals = data.getInt("numMealsD", 0);
        mealContainer.mealsOld = data.getInt("mealsOldD", 0);
        
        for(int i = 0; i < mealContainer.numMeals; i++){
        	String mealName = data.getString("nameD" + i, "blah");
        	int mealPref = data.getInt("prefD" + i, 1);
        	mealContainer.meals.add(new Meal(mealName, mealPref));
        	mealContainer.meals.get(i).age = data.getInt("ageD", 0);
        	mealContainer.mealMap.put(mealName, new Meal(mealName, mealPref));
        }
        //set behavior for button to send to meal choose screen
        Button chooseButton = (Button)findViewById(R.id.chooseButton);
        chooseButton.setOnClickListener(new View.OnClickListener(){
        	
        	public void onClick(View view){
        		Intent intent = new Intent(Meal_Chooser.this, MealChooseScreen.class);
        		startActivity(intent);
        	}
        });
        Button removeButton = (Button)findViewById(R.id.removeButton);
        removeButton.setOnClickListener(new View.OnClickListener(){
        	
        	public void onClick(View view){
        		Intent intent = new Intent(Meal_Chooser.this, RemoveScreen.class);
        		startActivity(intent);
        	}
        });
        //set behvrior to end program if click is selected
        Button exitButton = (Button)findViewById(R.id.quitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
        
        Button addRemoveButton = (Button)findViewById(R.id.add_removeButton);
        addRemoveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Meal_Chooser.this, AddRemoveScreen.class);
				startActivity(intent);
			}
		});
             
    }
    public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
    	for(int i = 0; i < mealContainer.numMeals; i++){
    		outState.putInt("pref" + Integer.toString(i), mealContainer.meals.get(i).preference);
    		outState.putString("name" + Integer.toString(i), mealContainer.meals.get(i).name);
    		outState.putInt("age" + Integer.toString(i), mealContainer.meals.get(i).age);
    	}
    	outState.putInt("mealsOld", mealContainer.mealsOld);
    	outState.putInt("numMeals", mealContainer.numMeals);
    }
    
    public void onRestoreInstanceState(Bundle savedInstanceState){
    	super.onRestoreInstanceState(savedInstanceState);
    	mealContainer.numMeals = savedInstanceState.getInt("numMeals");
    	mealContainer.mealsOld = savedInstanceState.getInt("mealsOld");
    	for(int i = 0; i < mealContainer.numMeals; i++){
    		String mealName = savedInstanceState.getString("name" + Integer.toString(i));
    		int mealPref = savedInstanceState.getInt("pref" + Integer.toString(i));
    		mealContainer.meals.add(new Meal(mealName, mealPref));
    		mealContainer.meals.get(i).age = savedInstanceState.getInt("age" + Integer.toString(i));
    		mealContainer.mealMap.put(mealName, new Meal(mealName, mealPref));
    	}
    }
    public void onStop(){
    	super.onStop();
    	
    	SharedPreferences data = getSharedPreferences(DATA_NAME, 0);
    	SharedPreferences.Editor editor = data.edit();
    	editor.putInt("numMealsD", mealContainer.numMeals);
    	editor.putInt("mealsOldD",mealContainer.mealsOld);
    	for(int i = 0; i < mealContainer.numMeals; i++){
    		editor.putString("nameD" + i, mealContainer.meals.get(i).name);
    		editor.putInt("prefD" + i, mealContainer.meals.get(i).preference);
    		editor.putInt("ageD" + i, mealContainer.meals.get(i).age);
    	}
    	editor.commit();
    }
}