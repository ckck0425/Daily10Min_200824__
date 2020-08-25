package kr.co.tjoeun.daily10min_200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener{
            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            이 받아낸 id/pw 를 어떻게 서버에 로그인 확인으로 요청하는지 ?

            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

//                    실제로 응답이 돌아왔을때 실행시켜줄 내용
                    val codeNum = json.getInt("code")

                    if (codeNum == 200) {
//                        서버개발자가 -> 로그인 성공일때는 code를 200으로 줌
//                        로그인 성공시에 대한 코드
                        Log.d("로그인시도", "성공상황")

//                        토스트도 일종의 UI 영향 코드 => runOnUiThread 안에서 실행
                        runOnUiThread {
                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()
                        }
                    }

                    else {
//                        로그인 실패에 대한 코드
                        Log.e("로그인시도","실패상황")

                        runOnUiThread {
                            Toast.makeText(mContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })


        }

    }

    override fun setValues() {

    }
}
