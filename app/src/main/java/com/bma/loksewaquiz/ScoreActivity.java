package com.bma.loksewaquiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScoreActivity extends AppCompatActivity {
private TextView scored,total;
private AppCompatButton done,share;
    Bitmap bitmap;
    LinearLayout relativelayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scored = findViewById(R.id.scored);
        total = findViewById(R.id.total);
        done = findViewById(R.id.done);
        share = findViewById(R.id.share);
        relativelayout = findViewById(R.id.linearLayout3);


        scored.setText(String.valueOf(getIntent().getIntExtra("score",0)));
        String totals = String.valueOf(getIntent().getIntExtra("total", 0));
        total.setText("Out of "+totals);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!testConnection()){
                    setContentView(R.layout.nointernet);
                }
                finish();
            }
        });

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(100, 100);
        final int width = relativelayout.getChildAt(0).getWidth();
        final int height = relativelayout.getChildAt(0).getHeight();
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = Bitmap.createBitmap(relativelayout.getWidth(), relativelayout.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                relativelayout.draw(canvas);
                shareBitmap(bitmap);
            }
        });
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void shareBitmap(@NonNull Bitmap bitmap) {
        //---Save bitmap to external cache directory---//
        //get cache directory
        File cachePath = new File(getExternalCacheDir(), "my_images/");
        cachePath.mkdirs();

        //create png file
        File file = new File(cachePath, "Image_123.png");
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //---Share File---//
        //get file uri
        Uri myImageFileUri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);

        //create a intent
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, myImageFileUri);
        intent.setType("image/png");
        startActivity(Intent.createChooser(intent, "Share Your Score"));
    }


    public boolean testConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


}