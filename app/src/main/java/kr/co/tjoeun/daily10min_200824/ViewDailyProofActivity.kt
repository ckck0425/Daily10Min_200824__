package kr.co.tjoeun.daily10min_200824

import android.os.Bundle


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

//                날짜가 선택되면 실행해줄 코드

                Log.d("선택된 년", year.toString())
                Log.d("선택된 월", month.toString())
                Log.d("선택된 일", dayOfMonth.toString())


//                실생활 : 1~12월
//                JAVA / Kotlin : 0~11월

            }, 2020, Calendar.JUNE, 9)


            datePickerDialog.show()

        }

    }

    override fun setValues() {

    }
}