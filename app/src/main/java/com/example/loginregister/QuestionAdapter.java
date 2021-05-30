package com.example.loginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    Context context;
    List<Questions> questionsList;
    RecyclerView rvQuestions;
    final View.OnClickListener onClickListener =new MyOnClickListener();
    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView correct_text;
        //TextView zorlukSeviyesi;
        TextView question;
        TextView ChoiceA;
        TextView ChoiceB;
        TextView ChoiceC;
        TextView ChoiceD;


        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.item_id);
            correct_text = itemView.findViewById(R.id.itemcorrect_text);
            //zorlukSeviyesi=itemView.findViewById(R.id.itemZorlukSeviyesi);
            question=itemView.findViewById(R.id.itemquestion);
            ChoiceA=itemView.findViewById(R.id.itemChoiceA);
            ChoiceB=itemView.findViewById(R.id.itemChoiceB);
            ChoiceC=itemView.findViewById(R.id.itemChoiceC);
            ChoiceD=itemView.findViewById(R.id.itemChoiceD);

        }
    }
    public QuestionAdapter(Context context, List<Questions> questionsList,RecyclerView rvQuestions){
        this.context=context;
        this.questionsList=questionsList;
        this.rvQuestions=rvQuestions;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        ViewGroup viewGroup = null;
        View view =inflater.inflate(R.layout.single_item,viewGroup,false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull QuestionAdapter.ViewHolder viewHolder, int position) {
     Questions question =questionsList.get(position);
        viewHolder.id.setText(""+question.getId());
        viewHolder.correct_text.setText(question.getCorrectText());
        viewHolder.question.setText(question.getQuestion());
        viewHolder.ChoiceA.setText(question.getChoiceA());
        viewHolder.ChoiceB.setText(question.getChoiceB());
        viewHolder.ChoiceC.setText(question.getChoiceC());
        viewHolder.ChoiceD.setText(question.getChoiceD());
       // viewHolder.zorlukSeviyesi.setText(question.getDifficulty());

    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = rvQuestions.getChildLayoutPosition(v);
            String item = questionsList.get(itemPosition).getQuestion();
            Toast.makeText(context,item, Toast.LENGTH_SHORT).show();

        }
    }
}
