package com.mcs.android.testio.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.testio.R;
import com.mcs.android.testio.TestActivity;
import com.mcs.android.testio.models.Question;
import com.mcs.android.testio.models.Response;

import java.util.ArrayList;
import java.util.List;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder> {

    private TestActivity activity;
    private ArrayList<Question> questionArrayList;

    public QuestionRecyclerViewAdapter(TestActivity activity, ArrayList<Question> questionArrayList) {
        this.activity = activity;
        this.questionArrayList = questionArrayList;
    }

    @NonNull
    @Override
    public QuestionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionRecyclerViewAdapter.ViewHolder holder, int position) {
        Question currentQuestion = questionArrayList.get(position);
        List<String> options = currentQuestion.getOptions();

        holder.tvQuestionTitle.setText(currentQuestion.getTitle());
        holder.rbOption1.setText(options.get(0));
        holder.rbOption2.setText(options.get(1));
        holder.rbOption3.setText(options.get(2));
        holder.rbOption4.setText(options.get(3));


        holder.rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                Response currentResponse = activity.responseArrayList.get(position);
                switch (checkedId) {
                    case R.id.rbOption1:
                        currentResponse.setAnswer(options.get(0));
                        break;
                    case R.id.rbOption2:
                        currentResponse.setAnswer(options.get(1));
                        break;
                    case R.id.rbOption3:
                        currentResponse.setAnswer(options.get(2));
                        break;
                    case R.id.rbOption4:
                        currentResponse.setAnswer(options.get(3));
                        break;
                }
                activity.responseArrayList.set(position, currentResponse);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvQuestionTitle;
        private RadioGroup rgOptions;
        private RadioButton rbOption1, rbOption2, rbOption3, rbOption4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvQuestionTitle = (TextView) itemView.findViewById(R.id.tvQuestionTitle);
            rgOptions = (RadioGroup) itemView.findViewById(R.id.rgOptions);
            rbOption1 = (RadioButton) itemView.findViewById(R.id.rbOption1);
            rbOption2 = (RadioButton) itemView.findViewById(R.id.rbOption2);
            rbOption3 = (RadioButton) itemView.findViewById(R.id.rbOption3);
            rbOption4 = (RadioButton) itemView.findViewById(R.id.rbOption4);

        }
    }
}
