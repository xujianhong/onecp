package com.daomingedu.art.mvp.ui.fragment

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.daomingedu.onecp.mvp.model.entity.AboutUsBean
import com.daomingedu.art.mvp.model.entity.CheckCerBean
import com.daomingedu.onecp.mvp.model.entity.UserInfoBean

import com.daomingedu.onecp.R
import com.daomingedu.onecp.app.Constant
import com.daomingedu.onecp.app.JHCImageConfig
import com.daomingedu.onecp.app.loadImage
import com.daomingedu.onecp.app.onClick
import com.daomingedu.onecp.di.component.DaggerMineComponent
import com.daomingedu.onecp.di.module.MineModule
import com.daomingedu.onecp.mvp.contract.MineContract
import com.daomingedu.onecp.mvp.presenter.MinePresenter
import com.daomingedu.onecp.mvp.ui.activity.*
import com.daomingedu.onecp.mvp.ui.widget.LoadingDialog
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.ArmsUtils.getColor
import kotlinx.android.synthetic.main.activity_enroll_test.*
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.fragment_mine.ivAvatar
import kotlinx.android.synthetic.main.fragment_mine.tvCheck

import org.jetbrains.anko.support.v4.startActivity


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 04/25/2019 21:53
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @FragmentScope(請注意命名空間) class NullObjectPresenterByFragment
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class MineFragment : BaseFragment<MinePresenter>(), MineContract.View {
    override fun requestCheckCerSuccess(data: CheckCerBean?) {

    }

    override fun requestAboutUsSuccess(data: AboutUsBean?, type: Int) {
        hideLoading()
        data?.let {
            when (type) {
                0 -> startActivity<CommonWebAct>(
                    Constant.URL_EXTRA to it.userAgreement,
                    Constant.TITLE_EXTRA to "用户协议"
                )
                1 -> startActivity<CommonWebAct>(
                    Constant.URL_EXTRA to it.serviceAgreement,
                    Constant.TITLE_EXTRA to "关于我们"
                )
                2 -> startActivity<CommonWebAct>(
                    Constant.URL_EXTRA to it.privacyAgreement,
                    Constant.TITLE_EXTRA to "隐私政策"
                )
            }
        }
    }

    private val loadingDialog by lazy {
        LoadingDialog(activity!!)
    }

    override fun requestGetCustomerInfoSuccess(data: UserInfoBean?) {
        data?.apply {
            if (photoImg != "")
                ivAvatar.loadImage(
                    JHCImageConfig.Builder()
                        .imageView(ivAvatar)
                        .url(photoImg)
                        .isCircle(true)
                        .errorPic(R.mipmap.avatar_default)
                        .placeholder(R.mipmap.avatar_default)
                        .build()
                )
            /*val avatarBitmap = Utils.decodeImage(imagePath)
            if (avatarBitmap != null) {
                ivAvatar.setImageBitmap(avatarBitmap)
            }else{
                ivAvatar.setImageResource(R.drawable.avatar_default)
            }*/
            tvName.text = name
            tvPhone.text = mobilePhone

            when (photoImgCheck) {
                0 -> {

                    tvCheck.text = "待审核中"
                }
                1 -> {

                    tvCheck.text = "审核通过"
                }
                2 -> {

                    tvCheck.text = "审核未通过,$reason"

                }
            }
        }
    }

    override fun requestLogoutSuccess() {
        startActivity<LoginAct>()
        killMyself()
    }

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerMineComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .mineModule(MineModule(this))
            .build()
            .inject(this)
    }

    override fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }


    override fun initData(savedInstanceState: Bundle?) {
        llUpdate.onClick {
            startActivity<CommonWebAct>(
                Constant.URL_EXTRA to Constant.UPLOAD_ID_PHOTO + mPresenter?.mSessionId,
                Constant.TITLE_EXTRA to "上传证件照"
            )
        }

        llVideo.onClick {
            UploadVideoListAct.startUploadVideoListActivity(
                activity as AppCompatActivity,
                UploadVideoListAct.TYPE_RETURN_NO,
                UploadVideoListAct.IMPORT_YES
            )
        }

        clStudentInfo.onClick {
            startActivity<ModifyStudentInfoAct>()
        }

        llRegisterProtocol.onClick {
            showLoading()
            mPresenter?.aboutUs(0)
        }

        llAboutUs.onClick {
            showLoading()
            mPresenter?.aboutUs(1)
        }

        llRegisterPrivate.onClick {
            showLoading()
            mPresenter?.aboutUs(2)
        }
        llAdvice.onClick {
            startActivity(Intent(mContext, FeedbackActivity::class.java))
        }
        llLoginOut.onClick {
            AlertDialog.Builder(activity!!).setTitle("确认注销登陆?")
                .setNegativeButton("否", null)
                .setPositiveButton("是") { _, _ -> mPresenter?.loginOut() }
                .show()
        }
        llChangePwd.onClick {
            startActivity<ModifyPasswordAct>()
        }
        llDelete.onClick {
            AlertDialog.Builder(activity!!).setTitle("注意")
                .setMessage("清除缓存,将清除拍摄的缓存文件!\n确定清除缓存吗?")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val file = activity!!.getExternalFilesDir("Movies")
                        if (file != null) {
                            if (file.exists()) {
                                val list = file.listFiles()
                                if (list != null) {
                                    for (item in list) {
                                        if (item.name.endsWith(".mp4")) {
                                            item.delete()
                                        }
                                    }
                                }
                            }
                        }
                    }
                })
                .show()
        }

        val packageManager: PackageManager = activity!!.packageManager
        val packageInfo = packageManager.getPackageInfo(activity!!.packageName, 0)


    }


    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     *fun setData(data:Any) {
     *   if(data is Message){
     *       when (data.what) {
     *           0-> {
     *               //根据what 做你想做的事情
     *           }
     *           else -> {
     *           }
     *       }
     *   }
     *}
     *
     *
     *
     *
     *
     * // call setData(Object):
     * val data = Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    override fun setData(data: Any?) {

    }

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.dismiss()
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        activity?.finish()
    }

    override fun onResume() {
        super.onResume()
        getStudentInfo()
    }

    fun getStudentInfo() {
        mPresenter?.getStudentInfo()

    }
}
