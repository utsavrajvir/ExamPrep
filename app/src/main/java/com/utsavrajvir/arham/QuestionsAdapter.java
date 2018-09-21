package com.utsavrajvir.arham;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends ArrayAdapter {


    List list = new ArrayList();
    public static int q_no=0;


    public QuestionsAdapter(@NonNull Context context, int resource) {

        super(context, resource);


    }


    @Override
    public void add(@Nullable Object object) {
        list.add(object);
    }

    @Override
    public int getCount() {
         return list.size();
    }


    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        QuestionsAdapter.QuestionHolder questionHolder;
        row = convertView;
final int pos = position;

        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.display_question_row,parent,false);
            questionHolder = new QuestionsAdapter.QuestionHolder();

            questionHolder.question = (TextView) row.findViewById(R.id.question);
            questionHolder.question_no = (TextView) row.findViewById(R.id.question_no);
            questionHolder.optionA = (RadioButton) row.findViewById(R.id.optionA);
            questionHolder.optionB = (RadioButton) row.findViewById(R.id.optionB);
            questionHolder.optionC = (RadioButton) row.findViewById(R.id.optionC);
            questionHolder.optionD = (RadioButton) row.findViewById(R.id.optionD);

            questionHolder.optionA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked)
                    {
                        Questions questions = (Questions) getItem(pos);
                        //questions.
                        questions.setStud_answer("1");

                    }

                }
            });

            questionHolder.optionB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked)
                    {
                        Questions questions = (Questions) getItem(pos);
                        //questions.
                        questions.setStud_answer("2");

                    }

                }
            });

            questionHolder.optionC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked)
                    {
                        Questions questions = (Questions) getItem(pos);
                        //questions.
                        questions.setStud_answer("3");

                    }

                }
            });

            questionHolder.optionD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked)
                    {
                        Questions questions = (Questions) getItem(pos);
                        //questions.
                        questions.setStud_answer("4");

                    }

                }
            });



            row.setTag(questionHolder);
        }
        else
        {
            questionHolder = (QuestionsAdapter.QuestionHolder)row.getTag();
        }

        Questions questions = (Questions) this.getItem(position);
        questionHolder.question.setText(questions.getQuestion());
        questionHolder.question_no.setText("Q."+ questions.getQ_no());
        questionHolder.optionA.setText(questions.getOptionA());
        questionHolder.optionB.setText(questions.getOptionB());
        questionHolder.optionC.setText(questions.getOptionC());
        questionHolder.optionD.setText(questions.getOptionD());


        return row;
    }


    static  class QuestionHolder
    {
        //TextView M_Cid,M_Name,M_Time;

        TextView question,question_no;

        RadioButton optionA,optionB,optionC,optionD;
    }

}
