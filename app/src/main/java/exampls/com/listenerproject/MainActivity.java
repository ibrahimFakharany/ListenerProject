package exampls.com.listenerproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Worker.Listener{
    public  String Key = "key";
    EditText editText = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.edit_text);
        editText.setText("");

        if(savedInstanceState != null){
            editText.setText(savedInstanceState.getString(Key));
        }

        Button btn = (Button) findViewById(R.id.click);
        final Worker worker = new Worker(this);
        worker.setListen(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worker.go();
            }
        });
    }


    @Override
    public void onClick(String text) {
        Log.e("mainActivity", "there is on Click in main activity");
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Key, editText.getText().toString());
    }
}
