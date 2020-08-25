package kr.co.tjoeun.daily10min_200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil

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

            ServerUtil.postRequestLogin(inputId, inputPw)


        }

    }

    override fun setValues() {

    }
}
