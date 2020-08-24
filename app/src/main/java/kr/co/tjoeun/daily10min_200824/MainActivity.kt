package kr.co.tjoeun.daily10min_200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

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
//            이 받아낸 id/pw 를
        }

    }

    override fun setValues() {

    }
}
