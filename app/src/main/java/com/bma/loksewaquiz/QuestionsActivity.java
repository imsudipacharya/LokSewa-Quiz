package com.bma.loksewaquiz;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class QuestionsActivity extends AppCompatActivity {
    public static final String FILE_NAME = "QUIZZER";
    public static final String KEY_NAME = "QUEST";

    private Button sharebutton, nextbutton;
    private TextView correctOption;
    private FloatingActionButton bookmarkbutton;
    private TextView questions, noindicator;
    private LinearLayout optionContainer;
    private int count = 0;
    private List<QuestionModel> questionModelList;
    private int postion = 0;
    private int score = 1;

    private int setNo;

    private Dialog loadingDialog;

    String fromsets;
    FirebaseDatabase database;
    DatabaseReference reference;

    private List<QuestionModel> bookmarkslist;

    private SharedPreferences.Editor editor;

    private int matchedQuestionPosition;

    private String message;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    Bitmap bitmap;
    ConstraintLayout relativelayout;
private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Intent intent = getIntent();
        fromsets = intent.getExtras().getString("fromset");
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(fromsets);

        String interstitals = intent.getExtras().getString("interstital");
        AudienceNetworkAds.initialize(this);
        mInterstitialAd = new InterstitialAd(this, interstitals);
        mInterstitialAd.loadAd();

        optionContainer = findViewById(R.id.linearLayout2);
        sharebutton = findViewById(R.id.sharebutton);
        nextbutton = findViewById(R.id.nextbutton);
        questions = findViewById(R.id.questions);
        noindicator = findViewById(R.id.noOfQuestion);
        relativelayout = findViewById(R.id.relativelayout);
        TextView textView = findViewById(R.id.datetime);

       String currentDateTimeString = "Date: "+java.text.DateFormat.getDateTimeInstance().format(new Date());
      textView.setText(currentDateTimeString);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                message = dataSnapshot.getValue(String.class);
                // latest.getText();

                questionModelList = new ArrayList<>();
                request = new JsonArrayRequest(message, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        JSONObject jsonObject = null;
Random random = new Random();
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                Collections.shuffle(questionModelList,random);
                                jsonObject = response.getJSONObject(i);
                                QuestionModel anime = new QuestionModel();
                                anime.setQuestion(jsonObject.getString("question"));
                                anime.setOptionA(jsonObject.getString("optionA"));
                                anime.setOptionB(jsonObject.getString("optionB"));
                                anime.setOptionC(jsonObject.getString("optionC"));
                                anime.setOptionD(jsonObject.getString("optionD"));
                                anime.setCorrectAns(jsonObject.getString("correctAns"));

                                questionModelList.add(anime);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        if (questionModelList.size() > 0) {
                            for (int i = 0; i < 4; i++) {
                                optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public void onClick(View v) {
                                        checkAnswer((TextView) v);
                                    }
                                });
                            }

                            playAnim(questions, 0, questionModelList.get(postion).getQuestion());
                            nextbutton.setOnClickListener(new View.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void onClick(View v) {
                                    String currentDateTimeString = "Date: "+java.text.DateFormat.getDateTimeInstance().format(new Date());
                                    textView.setText(currentDateTimeString);

                                    nextbutton.setEnabled(false);
                                    nextbutton.setAlpha(0.10f);
                                    enableOption(true);
                                    postion++;
                                    if (postion == questionModelList.size()) {
                                        if (mInterstitialAd.isAdLoaded()){
                                            mInterstitialAd.show();
                                        }
                                            Intent scoreIntent = new Intent(QuestionsActivity.this, ScoreActivity.class);
                                            scoreIntent.putExtra("score", score);
                                            scoreIntent.putExtra("total", questionModelList.size());
                                            startActivity(scoreIntent);
                                            finish();
                                            return;
                                    }
                                    count = 0;
                                    playAnim(questions, 0, questionModelList.get(postion).getQuestion());
                                }
                            });

                            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(100, 100);
                            final int width = relativelayout.getChildAt(0).getWidth();
                            final int height = relativelayout.getChildAt(0).getHeight();
                            sharebutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    bitmap = Bitmap.createBitmap(relativelayout.getWidth(), relativelayout.getHeight(), Bitmap.Config.ARGB_8888);
                                    Canvas canvas = new Canvas(bitmap);
                                    relativelayout.draw(canvas);
                                    shareBitmap(bitmap);
                                }
                            });

                        } else {
                            finish();
                            Toast.makeText(QuestionsActivity.this, "No Questions", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(QuestionsActivity.this, "Problems in Fetching Data", Toast.LENGTH_SHORT).show();
                        setContentView(R.layout.workinprogress);
                    }
                });


                requestQueue = Volley.newRequestQueue(QuestionsActivity.this);
                requestQueue.add(request);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(QuestionsActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

            }
        });


    }




    private void playAnim(View view, int value, final String data) {
        view.animate().alpha(value).scaleX(value)
                .scaleY(value)
                .setDuration(500)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        if (value == 0 && count < 4) {
                            String option = "";
                            if (count == 0) {
                                option = questionModelList.get(postion).getOptionA();
                            } else if (count == 1) {
                                option = questionModelList.get(postion).getOptionB();
                            } else if (count == 2) {
                                option = questionModelList.get(postion).getOptionC();
                            } else if (count == 3) {
                                option = questionModelList.get(postion).getOptionD();
                            }
                            playAnim(optionContainer.getChildAt(count), 0, option);
                            count++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (value == 0) {
                            try {
                                noindicator.setText(postion + 1 + "/" + questionModelList.size());
                                ((TextView) view).setText(data);

                            } catch (ClassCastException exception) {
                                ((Button) view).setText(data);
                            }
                            view.setTag(data);
                            playAnim(view, 1, data);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!testConnection()){
            setContentView(R.layout.nointernet);
        }
        }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(TextView selectedOption) {
        enableOption(false);
        nextbutton.setEnabled(true);
        nextbutton.setAlpha(1);
        if (selectedOption.getText().toString().equals(questionModelList.get(postion).getCorrectAns())) {
            //correct
            score++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#16d904")));
        } else {
            //incorrect
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#e80212")));
            correctOption = (TextView) optionContainer.findViewWithTag(questionModelList.get(postion).getCorrectAns());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#16d904")));

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++) {
            optionContainer.getChildAt(i).setEnabled(enable);
            if (enable) {
                optionContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF6200EE")));
            }
        }
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
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "For Answer Please Download Application From Google Play Store: https://play.google.com/store/apps/details?id=com.bma.loksewaquiz");
        startActivity(Intent.createChooser(intent, "Share This Question"));
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isAdLoaded()){
            mInterstitialAd.show();
        }
            Intent intent = new Intent(QuestionsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
    }
}