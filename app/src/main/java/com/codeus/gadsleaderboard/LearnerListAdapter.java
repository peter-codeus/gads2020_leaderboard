package com.codeus.gadsleaderboard;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LearnerListAdapter extends RecyclerView.Adapter<LearnerListAdapter.ViewHolder> {

    private List<GadsModel> gadsModels;
    private Context context;
    private FragmentType fragmentType;

    public LearnerListAdapter(Context context, List<GadsModel> learners, FragmentType fragmentType) {
        this.gadsModels = learners;
        this.context = context;
        this.fragmentType = fragmentType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.learner_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(this.fragmentType == FragmentType.SKILL_IQ)
            holder.profileImage.setImageResource(R.drawable.skill_iq_trimmed);
        GadsModel currentModel = this.gadsModels.get(position);
        holder.learnerNameView.setText(currentModel.getName());
        String descAppendText = this.fragmentType == FragmentType.SKILL_IQ ? "skill IQ Score" : "learning hours";
        holder.descriptionView.setText(""+currentModel.getPoints()+" "+descAppendText+", "+currentModel.getCountry());
    }

    @Override
    public int getItemCount() {
        return this.gadsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView learnerNameView;
        TextView descriptionView;
        ImageView profileImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            learnerNameView = itemView.findViewById(R.id.learner_name);
            descriptionView = itemView.findViewById(R.id.description);
            profileImage = itemView.findViewById(R.id.profile_img);
        }
    }
}
