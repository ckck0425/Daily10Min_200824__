package kr.co.tjoeun.daily10min_200824


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import kr.co.tjoeun.daily10min_200824.datas.Project
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil
import org.json.JSONObject

class ViewProjectDetailActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {


    }

    override fun setValues() {


        mProject = intent.getSerializableExtra("project") as Project
        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)
        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

    }


    override fun onResume() {
        super.onResume()
        getProjectDetailFromServer()

    }

    fun getProjectDetailFromServer() {

        ServerUtil.getRequestProjectDetailById(mContext, mProject.id, object : ServerUtil.JsonResponseHandler {

            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val projectObj = data.getJSONObject("project")

                mProject = Project.getProjectFromJson(projectObj)

                runOnUiThread {
                    proofMethodTxt.text = mProject.proofMethod
                    onGoingMemberCountTxt.text = "(현재 참여 인원 : ${mProject.onGoingMemberCount}명)"

//                    myLastStatus => 마지막으로 변경된 프로젝트 신청 상태
//                    null : 신청한적이 없다.
//                    ONGOING : 신청해서 진행중
//                    FAIL : 중도포기 or 3일 연속  인증글 X (자동 포기)
//                    COMPLETE : 66(프로젝트마다 다름)일 짜리 모두 수행 완료.
//                    신청하기 언제? 그 외의 모든 상황.
//                    중도 포기 언제? 상태가 ONGOING일때.

                    if (mProject.myLastStatus == "ONGOING") {
                        giveUpBtn.isEnabled = true
                        applyBtn.isEnabled = false
                    }

                    else {
                        giveUpBtn.isEnabled = false
                        applyBtn.isEnabled = true
                    }

                }

            }
        })
    }

}