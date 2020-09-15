package kr.co.tjoeun.daily10min_200824.datas

import org.json.JSONObject

class User {

    var id = 0
    var email = ""
    var nickname = ""

    val profileImageArrayList = ArrayList<String>()

    companion object {



        fun getUserFromJson(json: JSONObject) : User {


//            기본값세팅된 User 만들고 => json변수를 이용해 내용물 채우고 => 결과로 return

            val user = User()


//            json으로 user변수의 항목들을 채우자.

            user.id = json.getInt("id")
            user.email = json.getString("email")
            user.nickName = json.getString("nick_name")


//            사용자의 프사 목록도 파싱해야함.

            return user
        }
    }

}