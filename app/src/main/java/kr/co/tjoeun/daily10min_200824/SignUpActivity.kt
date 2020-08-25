package kr.co.tjoeun.daily10min_200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setValues() {

    }

    override fun setupEvents() {

        emailCheckBtn.setOnClickListener {

//            입력된 이메일 확인 > 해당 이메일
            val inputEmail = signUpEmailEdt.text.toString()
            ServerUtil.getRequestEmailCheck(inputEmail, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
                    val code = json.getInt("code")

                    runOnUiThread {
                        if (code == 200) {
                        emailCheckResultTxt.text = "사용해도 좋은 이메일입니다."
                    }
                    else {
                        emailCheckResultTxt.text = "중복된 이메일입니다."
                    } }



                }
            })

        }

    }
}
