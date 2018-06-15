package com.example.tapan.demolco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tapan.demolco.models.Question;
import com.example.tapan.demolco.models.QuestionList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionListActivity extends AppCompatActivity {

    @BindView(R.id.tv_que_no)
    TextView tvQueNo;
    @BindView(R.id.tv_que)
    TextView tvQue;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.fm_que)
    FrameLayout fmQue;
    @BindView(R.id.tv_ans)
    TextView tvAns;
    @BindView(R.id.card_view_ans)
    CardView cardViewAns;
    @BindView(R.id.fm_ans)
    FrameLayout fmAns;
    @BindView(R.id.btn_answer)
    TextView btnAnswer;
    @BindView(R.id.ll_bottom_prev_next)
    LinearLayout llBottomPrevNext;
    @BindView(R.id.tv_prev)
    TextView tvPrev;
    @BindView(R.id.tv_next)
    TextView tvNext;

    List<Question> questions ;

    private AdView mAdView;

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        ButterKnife.bind(this);

        loadQuestion();


        MobileAds.initialize(this, ""+getString(R.string.app_id));


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardViewAns.setVisibility(View.VISIBLE);
                llBottomPrevNext.setVisibility(View.VISIBLE);
            }
        });


        tvPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Util.isNetworkConnected(QuestionListActivity.this)){
                    setUpViewWithData(questions,currentIndex-1);
                    currentIndex--;
                    setVisibilityPrevNextTextViews();
                }else{
                    Toast.makeText(QuestionListActivity.this, "Please connect to a valid network.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Util.isNetworkConnected(QuestionListActivity.this)){
                    setUpViewWithData(questions,currentIndex+1);
                    currentIndex++;
                    setVisibilityPrevNextTextViews();
                }else{
                    Toast.makeText(QuestionListActivity.this, "Please connect to a valid network.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        setVisibilityPrevNextTextViews();

    }

    private void setVisibilityPrevNextTextViews() {
        if (currentIndex == 0){
            tvPrev.setVisibility(View.GONE);
            tvNext.setVisibility(View.VISIBLE);
        }else if (currentIndex == questions.size()-1){
            tvNext.setVisibility(View.GONE);
            tvPrev.setVisibility(View.VISIBLE);
        }else {
            tvNext.setVisibility(View.VISIBLE);
            tvPrev.setVisibility(View.VISIBLE);
        }
    }

    private void loadQuestion() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<QuestionList> call = apiService.getAllQuestions();

        call.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                questions = new ArrayList<>();
                questions = response.body().getQuestions();


//                List<String> al = new ArrayList<>();
                Set<Question> setRemovedDuplicate = new LinkedHashSet<>();
                setRemovedDuplicate.addAll(questions);
                questions.clear();
                questions.addAll(setRemovedDuplicate);

                setUpViewWithData(questions, currentIndex);
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {

            }
        });

    }

    private void setUpViewWithData(List<Question> questions, int index) {

        int question_no = index +1;

        tvQueNo.setText("Q"+question_no);
        tvQue.setText(questions.get(index).getQuestion());
        tvAns.setText(questions.get(index).getAnswer());

    }

}
