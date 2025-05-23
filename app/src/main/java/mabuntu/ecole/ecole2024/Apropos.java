package mabuntu.ecole.ecole2024;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import mabuntu.tombolia.pressing.R;


public class Apropos extends AppCompatActivity {

    private Context context;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apropos);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init_view();
        init_data();
        init_event();
    }

    private void init_view(){ }
    private void init_data(){ }

    private void init_event(){ }


}

