package kr.co.tjoeun.daily10min_200824.utils

import android.os.Handler
import android.util.Log
import okhttp3.*
import okhttp3.internal.http2.Http2Reader
import org.json.JSONObject
import java.io.IOException
import java.sql.ClientInfoStatus

class ServerUtil {

//    화면 (액티비티)의 입장에서 서버응답이 돌아왔을때 실행해줄 내용을 담기위한 인터페이스
    interface JsonResponseHandler{
        fun onResponse(json : JSONObject)
    }

    companion object {

//        companion object 안에 만드는 변수 or 함수는 객체를 이용하지 않고
//        클래스 자체의 기능으로 활용 가능 (JAVA의 static 처럼 활용가능)

//        호스트 주소를 모든 기능이 공유하기 위한 변수
        val BASE_URL = "http://15.164.153.174"

//        로그인 기능 > 로그인을 수행하는 함수 작성
        fun postRequestLogin(id : String, pw:String, handler:JsonResponseHandler?){

//          안드로이드 앱이 클라이언트로 동작하도록 도와주기
            val client = OkHttpClient()

//           어느 기능으로 갈건지 주소 완성 > http://호스트주소/user
            val urlStr = "${BASE_URL}/user"

//           파라미터들을 미리 담아주자 > POST/PUT/PATCH - formData 활용
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()

//            목적지의 정보를 Request 형태로 완성하기 (티켓 최종 발권)
            val request = Request.Builder()
                .url(urlStr)
                .post(formData)
                .build()

//            미리 만들어둔 client 변수 활용하여
//            request 변수에 적힌 정보로 서버에 요청 날리기 (호출 - call)
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버 연결자체에 실패
                }

                override fun onResponse(call: Call, response: Response) {
//                    결과가 성공이던 실패던 상관없이, 서버가 뭔가 답변을 해준경우
//                    응답이 돌아온 경우

//                    서버가 내려준 응답의 본문(body) 을 string 형태로 저장
                    val bodyString = response.body!!.string()

//                    받아낸 string > 분석하기 용이한 JSONObject 형태로 변환
                    val json = JSONObject(bodyString)

                    Log.d("서버응답본문", json.toString())

//                    어떤 처리를 해줄지 가이드북 (인터페이스) 이 존재한다면,
//                    그 가이드북에 적힌 내용을 실제로 실행

                    handler?.onResponse(json)

                }

            })

        }
    }
}