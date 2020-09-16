package kr.co.tjoeun.daily10min_200824

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_view_daily_proof.*
import kr.co.tjoeun.daily10min_200824.adapters.ProofAdapter
import kr.co.tjoeun.daily10min_200824.datas.Project
import kr.co.tjoeun.daily10min_200824.datas.Proof
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class ViewDailyProofActivity : BaseActivity() {

    lateinit var mProject : Project

    val mProofList = ArrayList<Proof>()

    lateinit var mProofAdapter : ProofAdapter

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

                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val selectedDateStr = sdf.format(selectedDate.time)
                selectedDateTxt.text = selectedDateStr


//                실생활 : 1~12월
//                JAVA / Kotlin : 0~11월

//                해당 날짜의 인증글 목록 불러오기
                getProofListByDate(selectedDateStr)

            }, 2020, Calendar.JUNE, 9)


            datePickerDialog.show()

        }

    }

    override fun setValues() {


        mProofAdapter = ProofAdapter(mContext, R.layout.proof_list_item, mProofList)
        proofListView.adapter = mProofAdapter

        mProject = intent.getSerializableExtra("project") as Project

//        이 화면이 실행되면 오늘날짜를 => 2020년 9월 5일 양식으로 selectedDateTxt 에 출력

        val todayCal = Calendar.getInstance() // 기본값이 현재 일시 -> 오늘 날짜가 담겨있다
        val sdf = SimpleDateFormat("yyyy년 M월 d일")
        val todayStr = sdf.format(todayCal.time)
        selectedDateTxt.text = todayStr

    }

    fun getProofListByDate(date:String){

        ServerUtil.getRequestProjectProofByIdAndDate(mContext, mProject.id, date, object : ServerUtil.JsonResponseHandler {

            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")
                val projectObj = dataObj.getJSONObject("project")
                val proofsJsonArr = projectObj.getJSONArray("proofs")


//                날짜가 달라지면 => 기존에 불러온 내용은 전부 지우고 새로 채워주자.
                mProofList.clear()

                for (i in    0 until proofsJsonArr.length()) {
                    val proof = Proof.getProofFromJson(proofsJsonArr.getJSONObject(i))
                    mProofList.add(proof)

                }

                runOnUiThread {
                    mProofAdapter.notifyDataSetChanged()

                }

            }

        })


    }



}