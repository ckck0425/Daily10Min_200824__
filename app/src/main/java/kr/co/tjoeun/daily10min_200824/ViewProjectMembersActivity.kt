package kr.co.tjoeun.daily10minutes_20200824

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import kr.co.tjoeun.daily10min_200824.BaseActivity
import kr.co.tjoeun.daily10min_200824.R
import kr.co.tjoeun.daily10min_200824.datas.Project
import kr.co.tjoeun.daily10min_200824.datas.User

class ViewProjectMembersActivity : BaseActivity() {

//    프로젝트 상세화면에서 넘겨준, 프로젝트 데이터를 담기 위한 멤버변수

    lateinit var mProject : Project
    val mProjectMembers = ArrayList<User>()

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
        getProjectMembersFromServer()
    }

//    서버에서 프로젝트의 참여 멤버 불러오는 기능

    fun getProjectMembersFromServer() {

    }

}