package app.aaman007.com.tablayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    private EditText name,feedback;
    private ImageButton send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        /// Adding Back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /// Setting title
        this.setTitle("Feedback");

        /// Finding Views
        feedback = findViewById(R.id.feedbackEditTextId);
        name = findViewById(R.id.nameEditTextId);
        send = findViewById(R.id.sendButton);

        // OnClick Listener for Send Button
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String userName = name.getText().toString();
                    String userFeedback = feedback.getText().toString();
                    if(userFeedback.equals("") || userName.equals(""))
                        Toast.makeText(Feedback.this,"Invalid name or feedback",Toast.LENGTH_SHORT).show();
                    else{
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/email");
                        intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"aaman.sunny007@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT,"User Feedback");
                        intent.putExtra(Intent.EXTRA_TEXT,"Name : "+userName+"\nFeedback :\n"+userFeedback);
                        startActivity(Intent.createChooser(intent,"Send Using"));
                        name.setText("");
                        feedback.setText("");
                    }
                }catch(Exception e){
                    Toast.makeText(Feedback.this,"Invalid name or feedback",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            this.finish();
        return super.onOptionsItemSelected(item);
    }
}
