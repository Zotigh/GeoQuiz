package edu.cnm.deepdive.geoquiz;

import android.drm.DrmStore.RightsStatus;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

  private Button mTrueButton;
  private Button mFalseButton;
  private Button  mNextButton;
  private TextView mQuestionTextView;

  private Question[] mQuestionBank = new Question[] {
      new Question(R.string.question_australia, true),
      new Question(R.string.question_oceans, true),
      new Question(R.string.question_mideast, false),
      new Question(R.string.question_africa, false),
      new Question(R.string.question_americas, true),
      new Question(R.string.question_asia, true),
  };

  private int mCurrentIndex = 0;
  //Stopped on pg 41 conti... on pg 42

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz);

    mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

    mTrueButton = (Button) findViewById(R.id.true_button);
    mTrueButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        checkAnswer(true);
      }
    });
    mFalseButton = (Button) findViewById(R.id.false_button);
    mFalseButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        checkAnswer(false);
      }
    });

    mNextButton = (Button) findViewById(R.id.next_buttoon);
    mNextButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
      }
    });
    updateQuestion();
  }
  private void updateQuestion() {
    int question = mQuestionBank[mCurrentIndex].getTextResId();
    mQuestionTextView.setText(question);
  }

  private void checkAnswer(boolean underPressureTrue) {
    boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

    int messageResId = 0;

    if (underPressureTrue == answerIsTrue) {
      messageResId = R.string.correct_toast;
    } else {
      messageResId = R.string.incorrect_toast;
    }
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
  }
}

