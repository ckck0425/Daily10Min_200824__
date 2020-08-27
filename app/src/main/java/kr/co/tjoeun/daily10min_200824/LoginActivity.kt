package kr.co.tjoeun.daily10min_200824

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kr.co.tjoeun.daily10min_200824.utils.ContextUtil
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        
        autoLoginCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->
            Log.d("자동로그인체크여부", isChecked.toString())
            ContextUtil.setAutoLoginCheck(mContext, isChecked)
        }


        signUpBtn.setOnClickListener {
            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)
        }

        loginBtn.setOnClickListener{
            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            이 받아낸 id/pw 를 어떻게 서버에 로그인 확인으로 요청하는지 ?

            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

//                    실제로 응답이 돌아왔을때 실행시켜줄 내용
                    val codeNum = json.getInt("code")

                    if (codeNum == 200) {

//                        서버가 알려주는 로그인한 사용자의 토큰값을 받아서
//                        ContextUtil의 기능으로 토큰을 저장하자.

                        val data = json.getJSONObject("data")

                        val token = data.getString("token")

                        ContextUtil.setLoginUserToken(mContext, token)

//                        서버개발자가 -> 로그인 성공일때는 code를 200으로 줌
//                        로그인 성공시에 대한 코드
                        Log.d("로그인시도", "성공상황")

//                        토스트도 일종의 UI 영향 코드 => runOnUiThread 안에서 실행
//                        runOnUiThread {
//                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show()
//                        }
                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

//                        메인으로 이동 후에는 로그인화면은 종료하자
                        finish()
                    }

                    else {
//                        로그인 실패에 대한 코드
//                        왜 실패했는지 서버가 알려주는 사유 토스트로 출력

                        Log.e("로그인시도","실패상황")

//                        서버가 message 이름으로 담아주는 실패 사유 추출
                        val message = json.getString("message")

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }
                }

            })


        }

    }

    override fun setValues() {

        autoLoginCheckBox.isChecked = ContextUtil.getAutoLoginCheck(mContext)

    }
}
