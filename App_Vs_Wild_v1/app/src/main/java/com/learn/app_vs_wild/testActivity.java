package com.learn.app_vs_wild;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class testActivity extends AppCompatActivity {

    TextToSpeech toSpeech;
    int result;
    EditText editText;
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        editText = findViewById(R.id.edittexttest);
        toSpeech = new TextToSpeech(testActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = toSpeech.setLanguage(Locale.FRANCE);
                } else {
                    Toast.makeText(getApplicationContext(), "no mec", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void la(View view) {

        switch (view.getId()) {

            case R.id.start:
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Toast.makeText(getApplicationContext(),"probléme",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    text=editText.getText().toString();
                    toSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
                }
                break;
            case R.id.stop:
                if(toSpeech!=null)
                {
                    toSpeech.stop();
                }
                break;
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        if (toSpeech!=null)
        {
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }
}
