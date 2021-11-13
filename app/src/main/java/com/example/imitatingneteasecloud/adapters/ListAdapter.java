package com.example.imitatingneteasecloud.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.activitys.PlayMusicActivity;
import com.example.imitatingneteasecloud.models.MusicModel;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mContext;
    private boolean isFirstCalculate;//是否第一次计算recyclerView的高度

    private List<MusicModel> musicModels;
    private View mItemView;//
    private RecyclerView mRecyclerView;

    public ListAdapter(Context mContext,RecyclerView recyclerView,List<MusicModel>list) {
        this.mContext = mContext;
        this.mRecyclerView=recyclerView;
        this.musicModels=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mItemView=LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MusicModel musicModel=musicModels.get(position);
        setRecyclerViewHeight();
        Glide.with(mContext)
                .load(musicModel.getPoster())
                .into(holder.iv_icon);
        holder.tv_author.setText(musicModel.getAuthor());
        holder.tv_name.setText(musicModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, PlayMusicActivity.class)
                .putExtra(PlayMusicActivity.MUSIC_ID,musicModel.getMusicId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_icon;
        TextView tv_name,tv_author;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon=itemView.findViewById(R.id.iv_icon);
            tv_author=itemView.findViewById(R.id.tv_author);
            tv_name=itemView.findViewById(R.id.tv_name);
        }
    }

    /**
     * 手动计算recyclerView的高度（解决在界面中显示不完全的问题）
     * 1.获得item的数量
     * 2.计算单个item的高度
     * 3.count * height
     * 4.将计算所得的高度设置给recyclerView
     */

    private void setRecyclerViewHeight(){
        if(!isFirstCalculate || mRecyclerView ==null){
            isFirstCalculate=true;
            //获取单个item的高度
            RecyclerView.LayoutParams itemViewLayoutParamsParams =
                    (RecyclerView.LayoutParams) mItemView.getLayoutParams();
            int height = itemViewLayoutParamsParams.height;
            int count=getItemCount();
            int recyclerViewHeight=height*count;
            LinearLayout.LayoutParams mRecyclerViewLayoutParams =
                    (LinearLayout.LayoutParams) mRecyclerView.getLayoutParams();
            mRecyclerViewLayoutParams.height=recyclerViewHeight;
            mRecyclerView.setLayoutParams(mRecyclerViewLayoutParams);
        }
    }
}
