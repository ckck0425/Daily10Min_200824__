package kr.co.tjoeun.daily10min_200824.datas

import org.json.JSONObject
import java.io.Serializable

class Project : Serializable {

    var id = 0
    var title = ""
    var imageUrl = ""
    var description = ""
    var proofMethod = ""
    var onGoingMemberCount = 0
    var myLastStatus : String? = null

    companion object {

        fun getProjectFromJson(json: JSONObject) : Project {
            val project = Project()

//            빈 프로젝트에 => json의 데이터를 이용해서 => project 변수의 값을 채워주자.
            project.id = json.getInt("id")
            project.title = json.getString("title")
            project.imageUrl = json.getString("img_url")
            project.description = json.getString("description")

            project.proofMethod = json.getString("proof_method")
            project.onGoingMemberCount = json.getInt("ongoing_users_count")

//            서버에서 주는 데이터가 null 일 수 있거나
//            아예 상황에 따라 첨부되지 않을 수도 있는 항목은
//            isNull로 체크해서, null 이 아닐때만 파싱하는게 앱의 안정성을 높힌다.

            if (!json.isNull("my_last_status")) {
                project.myLastStatus = json.getString("my_last_status")
            }

            return project
        }
    }





}