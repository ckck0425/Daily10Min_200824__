package kr.co.tjoeun.daily10min_200824.utils

import android.content.Context
import android.os.Handler
import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.internal.http2.Http2Reader
import org.json.JSONObject
import java.io.IOException
import java.sql.ClientInfoStatus
import java.sql.Date

class ServerUtil {

//    화면 (액티비티)의 입장에서 서버응답이 돌아왔을때 실행해줄 내용을 담기위한 인터페이스
    interface JsonResponseHandler{
        fun onResponse(json : JSONObject)
    }

    companion object {

//        companion object 안에 만드는 변수 or 함수는 객체를 이용하지 않고
//        클래스 자체의 기능으로 활용 가능 (JAVA의 static 처럼 활용가능)

//        호스트 주소를 모든 기능이 공유하기 위한 변수
//        이 클래스 내부에서만 사용하면 된다. (private 키워드 추가)

        private val BASE_URL = "http://15.164.153.174"

//        로그인 기능 > 로그인을 수행하는 함수 작성

        fun postRequestWriteProof(context: Context, projectId:Int, content:String, handler: JsonResponseHandler?) {

//            안드로이드 앱이 클라이언트로 동작하도록 도와주자.
            val client = OkHttpClient()

//            어느 기능으로 갈건지 주소 완성. => http://호스트주소/user
            val urlStr =  "${BASE_URL}/project_proof"

//            파라미터들을 미리 담아주자. => POST(/PUT/PATCH) - formData 활용
            val formData = FormBody.Builder()
                .add("project_id", projectId.toString())
                .add("content", content)
                .build()

//            목적지의 정보를 Request 형태로 완성하자. (티켓 최종 발권)

            val request = Request.Builder()
                .url(urlStr)
                .post(formData)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

//            미리 만들어둔 client 변수를 활용해서
//            request 변수에 적힌 정보로 서버에 요청 날리기(호출 - call)

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버 연결 자체에 실패.
                }

                override fun onResponse(call: Call, response: Response) {
//                    결과가 성공이던 / 실패던 상관없이, 서버가 뭔가 답변을 해준 경우
//                    응답이 돌아온 경우

//                    서버가 내려준 응답의 본문(body) 을 String 형태로 저장
                    val bodyString = response.body!!.string()

//                    받아낸 String을 => 분석하기 용이한 JSONObject 형태로 변환.
                    val json = JSONObject(bodyString)

                    Log.d("서버응답본문", json.toString())

//                    어떤 처리를 해줄지 가이드북 (인터페이스) 이 존재한다면,
//                    그 가이드북에 적힌 내용을 실제로 실행

                    handler?.onResponse(json)

                }

            })

        }

        fun postRequestLikeProof(context: Context, proofId:Int, handler: JsonResponseHandler?) {


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

//                    서버가 내려준 응답의 본문(body) 을 String 형태로 저장
                    val bodyString = response.body!!.string()

//                    받아낸 String을 => 분석하기 용이한 JSONObject 형태로 변환.
                    val json = JSONObject(bodyString)

                    Log.d("서버응답본문", json.toString())

//                    어떤 처리를 해줄지 가이드북 (인터페이스) 이 존재한다면,
//                    그 가이드북에 적힌 내용을 실제로 실행

                    handler?.onResponse(json)

                }

            })

        }

        fun putRequestSignUp(id : String, pw: String, nickName:String, handler: JsonResponseHandler?) {

//            안드로이드 앱이 클라이언트로 동작하도록 도와주자.
            val client = OkHttpClient()

//            어느 기능으로 갈건지 주소 완성. => http://호스트주소/user
            val urlStr =  "${BASE_URL}/user"

//            파라미터들을 미리 담아주자. => POST(/PUT/PATCH) - formData 활용
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .add("nick_name", nickName)
                .build()

//            목적지의 정보를 Request 형태로 완성하자. (티켓 최종 발권)

            val request = Request.Builder()
                .url(urlStr)
                .put(formData)
                .build()

//            미리 만들어둔 client 변수를 활용해서
//            request 변수에 적힌 정보로 서버에 요청 날리기(호출 - call)

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버 연결 자체에 실패.
                }

                override fun onResponse(call: Call, response: Response) {
//                    결과가 성공이던 / 실패던 상관없이, 서버가 뭔가 답변을 해준 경우
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

        fun getRequestEmailCheck(emailAddress : String, handler : JsonResponseHandler?){

//            서버에 Request 를 날려주는 클라이언트 역할을 돕는 변수
            val client = OkHttpClient()

//            url (호스트주소+기능주소)을 만드는 과정에서 > 필요 파라미터도 가공-첨부
            val urlBuilder = "${BASE_URL}/email_check".toHttpUrlOrNull()!!.newBuilder()

//            url 가공기를 이용해서 필요 데이터 첨부
            urlBuilder.addEncodedQueryParameter("email",emailAddress)

//            가공이 끝난 url을 urlStr으로 완성
            val urlStr = urlBuilder.build().toString()

//            임시 :  어떻게 url 이 가공되었는지 로그로 확인
            Log.d("완성된url", urlStr)

//            요청 정보를 담는 request
            val request = Request.Builder()
                .url(urlStr)
                .get()
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val json = JSONObject(bodyString)

                    Log.d("서버응답본문", json.toString())

                    handler?.onResponse(json)
                }
            })
        }



        fun getRequestProjectList(context: Context, handler: JsonResponseHandler?) {

//            서버에 Request를 날려주는 클라이언트 역할을 돕는 변수
            val client = OkHttpClient()

//            url (호스트주소+기능주소)을 만드는 과정에서 => 필요 파라미터도 가공-첨부
            val urlBuilder = "${BASE_URL}/project".toHttpUrlOrNull()!!.newBuilder()
//            url 가공기를 이용해서 => 필요 데이터 첨부
//            urlBuilder.addEncodedQueryParameter("email", emailAddress)

//            가공이 끝난 url을 => urlStr 으로 완성
            val urlStr = urlBuilder.build().toString()

//            임시 : 어떻게 url이 가공되었는지 로그로 확인
            Log.d("완성된url", urlStr)


//            요청 정보를 담는 request
            val request = Request.Builder()
                .url(urlStr)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()

                    val json = JSONObject(bodyString)

                    Log.d("서버응답본문", json.toString())

                    handler?.onResponse(json)
                }

            })
        }

        fun getRequestProjectDetailById(context: Context, projectId: Int, handler: JsonResponseHandler?) {

//            서버에 Request를 날려주는 클라이언트 역할을 돕는 변수

            val client = OkHttpClient()

//            url (호스트주소+기능주소)을 만드는 과정에서 => 필요 파라미터도 가공-첨부

            val urlBuilder = "${BASE_URL}/project/${projectId}".toHttpUrlOrNull()!!.newBuilder()

//            url 가공기를 이용해서 => 필요 데이터 첨부
//            urlBuilder.addEncodedQueryParameter("email", emailAddress)
//            가공이 끝난 url을 => urlStr 으로 완성

            val urlStr = urlBuilder.build().toString()

//            임시 : 어떻게 url이 가공되었는지 로그로 확인

            Log.d("완성된url", urlStr)


//            요청 정보를 담는 request

            val request = Request.Builder()
                .url(urlStr)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyString = response.body!!.string()
                    val json = JSONObject(bodyString)
                    Log.d("서버응답본문", json.toString())
                    handler?.onResponse(json)
                }
            })
        }


//        프로젝트에 참여중인 사람들을 보기 위한 API
//        상세보기 API + need_all_users 파라미터만 추가

        fun getRequestProjectMemberById(context: Context, projectId: Int, handler: JsonResponseHandler?) {

//            서버에 Request를 날려주는 클라이언트 역할을 돕는 변수
                val client = OkHttpClient()

//            url (호스트주소+기능주소)을 만드는 과정에서 => 필요 파라미터도 가공-첨부
                val urlBuilder = "${BASE_URL}/project/${projectId}".toHttpUrlOrNull()!!.newBuilder()

//            url 가공기를 이용해서 => 필요 데이터 첨부
            urlBuilder.addEncodedQueryParameter("need_user_list", "true")

//            가공이 끝난 url을 => urlStr 으로 완성
                val urlStr = urlBuilder.build().toString()

//            임시 : 어떻게 url이 가공되었는지 로그로 확인
                Log.d("완성된url", urlStr)

//            요청 정보를 담는 request

                val request = Request.Builder()
                    .url(urlStr)
                    .get()
                    .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                    .build()

                client.newCall(request).enqueue(object : Callback {

                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {

                        val bodyString = response.body!!.string()
                        val json = JSONObject(bodyString)

                        Log.d("서버응답본문", json.toString())

                        handler?.onResponse(json)
                    }
                })
            }



//        프로젝트의 해당 날짜에 맞는 인증글을 가져오기 위한 API
//        상세보기 API + proof_date 파라미터만 추가

        fun getRequestProjectProofByIdAndDate(context: Context, projectId: Int, date:String, handler: JsonResponseHandler?) {

//            서버에 Request를 날려주는 클라이언트 역할을 돕는 변수
            val client = OkHttpClient()

//            url (호스트주소+기능주소)을 만드는 과정에서 => 필요 파라미터도 가공-첨부
            val urlBuilder = "${BASE_URL}/project/${projectId}".toHttpUrlOrNull()!!.newBuilder()

//            url 가공기를 이용해서 => 필요 데이터 첨부
            urlBuilder.addEncodedQueryParameter("proof_date", date)

//            가공이 끝난 url을 => urlStr 으로 완성
            val urlStr = urlBuilder.build().toString()

//            임시 : 어떻게 url이 가공되었는지 로그로 확인
            Log.d("완성된url", urlStr)

//            요청 정보를 담는 request
            val request = Request.Builder()
                .url(urlStr)
                .get()
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

            client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val json = JSONObject(bodyString)

                    Log.d("서버응답본문", json.toString())
                    handler?.onResponse(json)

                }
            })
        }




        fun postRequestApplyProject(context: Context, projectId:Int, handler: JsonResponseHandler?) {

//            안드로이드 앱이 클라이언트로 동작하도록 도와주자.

            val client = OkHttpClient()

//            어느 기능으로 갈건지 주소 완성. => http://호스트주소/user

            val urlStr =  "${BASE_URL}/project"

//            파라미터들을 미리 담아주자. => POST(/PUT/PATCH) - formData 활용

            val formData = FormBody.Builder()
                .add("project_id", projectId.toString())
                .build()

//            목적지의 정보를 Request 형태로 완성하자. (티켓 최종 발권)

            val request = Request.Builder()
                .url(urlStr)
                .post(formData)
                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))
                .build()

//            미리 만들어둔 client 변수를 활용해서
//            request 변수에 적힌 정보로 서버에 요청 날리기(호출 - call)

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

//                    서버 연결 자체에 실패.
                }

                override fun onResponse(call: Call, response: Response) {

//                    결과가 성공이던 / 실패던 상관없이, 서버가 뭔가 답변을 해준 경우
//                    응답이 돌아온 경우

                    //                    서버가 내려준 응답의 본문(body) 을 String 형태로 저장



                    val bodyString = response.body!!.string()







//                    받아낸 String을 => 분석하기 용이한 JSONObject 형태로 변환.



                    val json = JSONObject(bodyString)







                    Log.d("서버응답본문", json.toString())







//                    어떤 처리를 해줄지 가이드북 (인터페이스) 이 존재한다면,



//                    그 가이드북에 적힌 내용을 실제로 실행







                    handler?.onResponse(json)







                }







            })







        }











        fun postRequestLikeProof(context: Context, proofId:Int, handler: JsonResponseHandler?) {







//            안드로이드 앱이 클라이언트로 동작하도록 도와주자.



            val client = OkHttpClient()







//            어느 기능으로 갈건지 주소 완성. => http://호스트주소/user



            val urlStr =  "${BASE_URL}/like_proof"







//            파라미터들을 미리 담아주자. => POST(/PUT/PATCH) - formData 활용



            val formData = FormBody.Builder()



                .add("proof_id", proofId.toString())



                .build()







//            목적지의 정보를 Request 형태로 완성하자. (티켓 최종 발권)







            val request = Request.Builder()



                .url(urlStr)



                .post(formData)



                .header("X-Http-Token", ContextUtil.getLoginUserToken(context))



                .build()







//            미리 만들어둔 client 변수를 활용해서



//            request 변수에 적힌 정보로 서버에 요청 날리기(호출 - call)







            client.newCall(request).enqueue(object : Callback {



                override fun onFailure(call: Call, e: IOException) {



//                    서버 연결 자체에 실패.



                }



                override fun onResponse(call: Call, response: Response) {



//                    결과가 성공이던 / 실패던 상관없이, 서버가 뭔가 답변을 해준 경우



//                    응답이 돌아온 경우

//                    서버가 내려준 응답의 본문(body) 을 String 형태로 저장

                    val bodyString = response.body!!.string()
//                    받아낸 String을 => 분석하기 용이한 JSONObject 형태로 변환.

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