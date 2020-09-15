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
            user.nickname = json.getString("nick_name")


//            사용자의 프사 목록도 파싱해야함.

            //            json 변수 내의 profile_images JSONArray를 추가로 파싱하자.


            val pfImgJsonArr = json.getJSONArray("profile_images")

            for (i in   0 until pfImgJsonArr.length() ) {


//                프사정보 JSONObject 하나하나 추출.

                val pfImgObj = pfImgJsonArr.getJSONObject(i)

//                따낸 JSONObject 안에서 이미지주소 String만 추출 => user의 프사목록에 추가

                val imageUrl = pfImgObj.getString("img_url")
                user.profileImageArrayList.add(imageUrl)
            }

            return user
        }
    }

}