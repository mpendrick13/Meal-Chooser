package mpendrick.mealchooser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddRemoveScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.onRestoreInstanceState(savedInstanceState);
        //savedInstanceState.getParcelableArrayList("myar");
        setContentView(R.layout.add_remove);
        
        Button backButton = (Button)findViewById(R.id.button1);
        backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
        
        Button submitButton = (Button)findViewById(R.id.submitAdd);
        final EditText nameBox = (EditText)findViewById(R.id.nameBox);
        final EditText prefBox = (EditText)findViewById(R.id.prefBox);
        final TextView meal_add_success = (TextView)findViewById(R.id.meal_add_success);
        submitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String mealName = nameBox.getText().toString();
				int mealPref = Integer.parseInt(prefBox.getText().toString());
				if(!Meal_Chooser.mealContainer.mealMap.containsKey(mealName)){
					Meal_Chooser.mealContainer.meals.add(new Meal(mealName, mealPref));
					Meal_Chooser.mealContainer.mealMap.put(mealName, new Meal(mealName, mealPref));
					Meal_Chooser.mealContainer.numMeals++;
					meal_add_success.setText("Meal succesfully added. Add Another?");
					meal_add_success.setVisibility(1);
				}
				else{
					meal_add_success.setText("Meal already exists please try a different name");
					meal_add_success.setVisibility(1);
				}
			}
		});
      /*  
        final EditText nameBox = (EditText)findViewById(R.id.nameBox);
        nameBox.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				nameBox.setText("");
			}
		});
        final EditText prefBox = (EditText)findViewById(R.id.prefBox);
        prefBox.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				prefBox.setText("");
			}
		});
		
		*/
    }
}