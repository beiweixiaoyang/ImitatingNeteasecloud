package com.example.imitatingneteasecloud.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imitatingneteasecloud.R;
import com.example.imitatingneteasecloud.activitys.AlbumListActivity;
import com.example.imitatingneteasecloud.models.AlbumModel;
import com.example.imitatingneteasecloud.views.MyImageView;

import java.util.List;

/**
 * 自定义Adapter继承自RecyclerView.Adapter
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private Context mContext;
    private List<AlbumModel> albumModels;

    public GridAdapter(Context mContext,List<AlbumModel> list) {
        this.mContext = mContext;
        this.albumModels=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //返回一个ViewHolder
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.grid_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlbumModel albumModel=albumModels.get(position);
        //通过AlbumModel中的数据设置封面资源
        Glide.with(mContext)
                .load(albumModel.getPoster())
                .into(holder.iv_icon);
        holder.tv_name.setText(albumModel.getName());
        holder.tv_playNum.setText(albumModel.getPlayNum());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, AlbumListActivity.class)
                .putExtra(AlbumListActivity.ALBUM_ID,albumModel.getAlbumId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        MyImageView iv_icon;
        TextView tv_playNum,tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon=itemView.findViewById(R.id.iv_icon);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_playNum=itemView.findViewById(R.id.tv_playNum);
        }
    }
}
