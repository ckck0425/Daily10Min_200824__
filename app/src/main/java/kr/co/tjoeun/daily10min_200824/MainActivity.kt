package kr.co.tjoeun.daily10min_200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10min_200824.adapters.ProjectAdapter
import kr.co.tjoeun.daily10min_200824.datas.Project
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mProjectList = ArrayList<Project>()

    lateinit var mProjectAdapter : ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        getProjectListFromServer()
        mProjectAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProjectList)
        projectListView.adapter = mProjectAdapter
    }

    fun getProjectListFromServer() {
        ServerUtil.getRequestProjectList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")
                val projectArr = dataObj.getJSONArray("projects")

//                프로젝트가 10개  : 반복 -> 0,1,2, ...,9
                for (i in 0 until projectArr.length()){
                    val projectObj = projectArr.getJSONObject(i)

                    val project = Project()
                    project.id = projectObj.getInt("id")
                    project.title = projectObj.getString("title")
                    project.imageUrl = projectObj.getString("img_url")
                    project.description = projectObj.getString("description")

                    mProjectList.add(project)
                }

//                비동기처리 -> 어댑터 연결이 먼저 끝나고, 프로젝트 목록이 나중에 추가될 수 있따
//                리스트뷰의 입장에서는 -> 내용물 목로기 변경된 상황 => notifyDataSetChanged()로 새로고침
//                새로고침 : UI에 영향을 주겠다 => runOnUiTread{} 안에 작성해야 앱이 작동함
                runOnUiThread {
                mProjectAdapter.notifyDataSetChanged()}

            }

        })

    }
}
