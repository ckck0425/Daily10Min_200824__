package kr.co.tjoeun.daily10min_200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kr.co.tjoeun.daily10min_200824.datas.Project
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mProjectList = ArrayList<Project>()

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

            }

        })

    }
}
