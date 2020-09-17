package kr.co.tjoeun.daily10min_200824.datas

import org.json.JSONObject

class Proof {
    var id = 0
    var content = ""

//    게시글에 첨부된 이미지들
    val imgList = ArrayList<String>()


//    이 게시글을 쓴 사람의 정보
    lateinit var writer : User

//    댓글 달린 갯수 / 좋아요 찍힌 갯수
    var replyCount = 0
    var likeCount = 0

//    내가 좋아요를 찍었는지 여부
    var isMyLikeProof = false

    companion object {

        fun getProofFromJson(json: JSONObject) : Proof {

            val proof = Proof()
            proof.id = json.getInt("id")
            proof.content = json.getString("content")

            val imgJsonArr = json.getJSONArray("images")
            for (i in     0 until imgJsonArr.length()) {
                val imgObj = imgJsonArr.getJSONObject(i)
                proof.imgList.add(imgObj.getString("img_url"))

            }

 //            인증글의 작성자 정보 파싱
            val userObj = json.getJSONObject("user")

            proof.writer = User.getUserFromJson(userObj)


//            댓글 / 좋아요 갯수 파싱

            proof.replyCount = json.getInt("reply_count")
            proof.likeCount = json.getInt("like_count")

//            내 좋아요 여부 파싱

            proof.isMyLikeProof = json.getBoolean("my_like")

            return proof
        }


    }
}