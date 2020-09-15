package kr.co.tjoeun.daily10min_200824.datas

import org.json.JSONObject
import java.io.Serializable

class Project : Serializable {

    var id = 0
    var title = ""
    var imageUrl = ""
    var description = ""

    companion object {

        fun getProjectFromJson(json: JSONObject) : Project {
            val project = Project()

//            빈 프로젝트에 => json의 데이터를 이용해서 => project 변수의 값을 채워주자.
            project.id = json.getInt("id")
            project.title = json.getString("title")
            project.imageUrl = json.getString("img_url")
            project.description = json.getString("description")
            return project
        }
    }





}