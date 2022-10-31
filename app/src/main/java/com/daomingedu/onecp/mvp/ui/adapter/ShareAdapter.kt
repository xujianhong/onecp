package com.daomingedu.onecp.mvp.ui.adapter

import android.app.Activity
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
import com.chad.library.adapter.base.BaseViewHolder
import com.daomingedu.art.mvp.model.entity.ShareBean
import com.daomingedu.onecp.R
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.JHCImageConfig
import com.daomingedu.onecp.app.loadImage
import com.daomingedu.onecp.mvp.ui.widget.RoundImageView

class ShareAdapter(datas: MutableList<ShareBean>,val activity:Activity) :
    BaseQuickAdapter<ShareBean,BaseViewHolder>(R.layout.item_share_list,datas){
    override fun convert(helper: BaseViewHolder, item: ShareBean) {
        val headView = helper.getView<RoundImageView>(R.id.riv_share_head)

        headView.loadImage(
            JHCImageConfig.Builder()
                .imageView(headView)
                .url(Constant.IMAGE_PREFIX+item.shareUserImg)
                .isCircle(true)
                .errorPic(R.mipmap.avatar_default)
                .placeholder(R.mipmap.avatar_default)
                .build())

        helper.setText(R.id.tv_share_name, item.nickName)
        helper.setText(R.id.tv_share_musicname, item.remark)
        helper.setText(R.id.tv_playing, "浏览量:" + item.viewCount)
        helper.setText(R.id.tv_like, "点赞:" + item.likeCount)
        helper.setText(R.id.tv_comment, "评论:" + item.commentCount)
        helper.setText(R.id.tv_share_time, item.shareTime)

        /*val tv_new_comment = helper.getView<TextView>(R.id.tv_new_comment)
        if (item.commentCount > 0) {
            tv_new_comment.visibility = View.VISIBLE
            tv_new_comment.text = "${item.commentCount}"
        } else {
            tv_new_comment.visibility = View.GONE
        }*/

        if (item.shareType == Constant.TYPE_PHOTO) {
            helper.getView<View>(R.id.iv_video).visibility = View.GONE
            helper.getView<View>(R.id.iv_video_play).visibility = View.GONE
            helper.getView<View>(R.id.rv_images).visibility = View.VISIBLE
            val images = helper.getView<RecyclerView>(R.id.rv_images)
            val smallUrlList = item.smallUrls.split(",")
            val num = if (smallUrlList.size > 2) 3 else if (smallUrlList.size > 1) 2 else 1
            images.layoutManager = GridLayoutManager(mContext, num)
            images.setHasFixedSize(true)
            //            images.addItemDecoration(new DividerGridItemDecoration(context));
            val adapter = ImageItemAdapter(smallUrlList)
            images.adapter = adapter
            adapter.onItemClickListener = OnItemClickListener { adapter, view, position ->
                val urlList = item.urls.split(",")
                val urls = mutableListOf<String>()
                urlList.forEach {
                    urls.add(Constant.IMAGE_PREFIX+it)
                }
//                mContext.startActivity<ImageDetailsAct>("position" to position,
//                    "images" to urls as Serializable)
            }
            helper.addOnClickListener(R.id.rv_images)
        } else if (item.shareType == Constant.TYPE_VIDEO) {
            //            ((NineGridView) helper.getView(R.id.ngl_images)).setAdapter(null);

            helper.getView<View>(R.id.iv_video).visibility = View.VISIBLE
            helper.getView<View>(R.id.iv_video_play).visibility = View.VISIBLE
            helper.getView<View>(R.id.rv_images).visibility = View.GONE
            if (!TextUtils.isEmpty(item.previewImage)) {
                Glide.with(mContext)
                    .load(Constant.IMAGE_PREFIX+item.previewImage)
                    .into(helper.getView<View>(R.id.iv_video) as ImageView)
            }
        } else if (item.shareType == Constant.TYPE_RECORD) {

            //            ((NineGridView) helper.getView(R.id.ngl_images)).setAdapter(null);

            helper.getView<View>(R.id.iv_video).visibility = View.VISIBLE
            helper.getView<View>(R.id.iv_video_play).visibility = View.GONE
            (helper.getView<View>(R.id.iv_video) as ImageView).setImageResource(R.mipmap.share_recording_bg)
            helper.getView<View>(R.id.rv_images).visibility = View.GONE

        }
        if (item.isMy == Constant.ISMY_TRUE) {
            helper.getView<View>(R.id.ib_share_delete).visibility = View.VISIBLE
            helper.getView<View>(R.id.tv_shield).visibility = View.INVISIBLE
        } else if (item.isMy == Constant.ISMY_FALSE) {
            helper.getView<View>(R.id.ib_share_delete).visibility = View.INVISIBLE
            helper.getView<View>(R.id.tv_shield).visibility = View.VISIBLE
        }
        helper.addOnClickListener(R.id.ib_share_delete)
        helper.addOnClickListener(R.id.tv_shield)
    }

}