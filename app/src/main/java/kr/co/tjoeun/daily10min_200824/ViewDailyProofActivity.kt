package kr.co.tjoeun.daily10min_200824

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_view_daily_proof.*
import java.util.*


class ViewDailyProofActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_view_daily_proof)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {


        selectDateBtn.setOnClickListener {


            val datePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

//                일단 Calendar 변수 하나 생성, Calendar 객체(Instance)를 담아두자

                val selectedDate = Calendar.getInstance()

//                selectedDate 에는 몇년 몇월 몇일이 들어있나 ? 기본값 : 현재 일시가가 자동으로 기록



//                날가 선택되면 실행해줄 코드 => 년도/월/일 각각의 항목을 변경 (selectedDate 변수의 값 변경)

                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

//                년/월/일 한꺼번에 세팅

                selectedDate.set(year, month, dayOfMonth)



                Log.d("선택된 년", selectedDate.get(Calendar.YEAR).toString())
                Log.d("선택된 월", selectedDate.get(Calendar.MONTH).toString())
                Log.d("선택된 일", selectedDate.get(Calendar.DAY_OF_MONTH).toString())


//                실생활 : 1~12월
//                JAVA / Kotlin : 0~11월

            }, 2020, Calendar.JUNE, 9)


            datePickerDialog.show()

        }

    }

    override fun setValues() {

    }
}