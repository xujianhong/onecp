package com.daomingedu.onecp.mvp.ui.widget.selectimagevideo;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.daomingedu.onecp.R;
import com.daomingedu.onecp.mvp.ui.widget.selectimagevideo.util.FileFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 选择文件夹适配器
 * Created by jianhongxu on 2017/6/19.
 */

public class SelectFolderAdapter extends RecyclerView.Adapter<SelectFolderAdapter.SelectViewHolder> {

    Context context;
    private List<FileFolder> mFolder = new ArrayList<>();

    public SelectFolderAdapter(Context context) {
        this.context = context;
    }

    public List<FileFolder> getData() {
        return mFolder;
    }


    public void setData(List<FileFolder> mFloder) {
        this.mFolder = mFloder;
    }


    @Override
    public SelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new SelectViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_folder, parent, false));
    }

    @Override
    public void onBindViewHolder(final SelectViewHolder holder, int position) {
        FileFolder folder = mFolder.get(position);
        holder.tv_select_folder.setText(folder.getFileParentName());
        Glide.with(context)
                .load(new File(folder.getFilePath()))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.iv_select_folder.setImageResource(R.mipmap.no_image);
                        return true;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.iv_select_folder);
        holder.cb_select_folder.setChecked(folder.isCheck());
    }


    @Override
    public int getItemCount() {
        return mFolder == null ? 0 : mFolder.size();
    }


    public class SelectViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_select_folder;
        TextView tv_select_folder;
        CheckBox cb_select_folder;

        public SelectViewHolder(View itemView) {
            super(itemView);
            iv_select_folder = (ImageView) itemView.findViewById(R.id.iv_select_folder);
            tv_select_folder = (TextView) itemView.findViewById(R.id.tv_select_folder);
            cb_select_folder = (CheckBox) itemView.findViewById(R.id.cb_select_folder);
        }
    }

}
