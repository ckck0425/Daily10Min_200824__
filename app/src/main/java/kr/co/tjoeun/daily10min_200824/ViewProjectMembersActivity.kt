package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_project_members.*
import kr.co.tjoeun.daily10min_200824.BaseActivity
import kr.co.tjoeun.daily10min_200824.R
import kr.co.tjoeun.daily10min_200824.datas.Project
import kr.co.tjoeun.daily10min_200824.datas.User
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil
import kr.co.tjoeun.daily10minutes_20200824.adapters.ProjectMemberAdapter
import org.json.JSONObject

class ViewProjectMembersActivity : BaseActivity() {

//    프로젝트 상세화면에서 넘겨준, 프로젝트 데이터를 담기 위한 멤버변수

    lateinit var mProject : Project
    val mProjectMembers = ArrayList<User>()
    lateinit var mAdapter : ProjectMemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_members)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project



        mAdapter = ProjectMemberAdapter(mContext, R.layout.user_list_item, mProjectMembers)
        membersListView.adapter = mAdapter

        getProjectMembersFromServer()
    }

//    서버에서 프로젝트의 참여 멤버 불러오는 기능

    fun getProjectMembersFromServer() {

        ServerUtil.getRequestProjectMemberById(mContext, mProject.id, object : ServerUtil.JsonResponseHandler {

            override fun onResponse(json: JSONObject) {

                val data = json.getJSONObject("data")
                val projectObj = data.getJSONObject("project")
                val ongoingUsersArr = projectObj.getJSONArray("ongoing_users")

                for (i in 0 until ongoingUsersArr.length()) {
                    val memberObj = ongoingUsersArr.getJSONObject(i)

//                    memberObj => User 형태로 변환 => ArrayList에 추가

                    val user = User.getUserFromJson(memberObj)

                    mProjectMembers.add(user)
                }



//                프로젝트 멤버들이 추가되었으니 => 리스트뷰에 새로 반영 처리.
//                서버 통신중 UI에 영향 => runOnUiThread로 처리하자.

                runOnUiThread {
                    mAdapter.notifyDataSetChanged()
            }
        })

    }

}