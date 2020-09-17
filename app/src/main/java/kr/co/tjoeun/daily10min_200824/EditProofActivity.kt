package kr.co.tjoeun.daily10min_200824

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_proof.*
import kr.co.tjoeun.daily10min_200824.datas.Project
import kr.co.tjoeun.daily10min_200824.utils.ServerUtil
import org.json.JSONObject

class EditProofActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_proof)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        postProofBtn.setOnClickListener {

//            어떤 내용 ? 어떤 프로젝트?
            val inputContent = proofContentEdt.text.toString()

            ServerUtil.postRequestWriteProof(mContext, mProject.id, inputContent, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

                    val code = json.getInt("code")

                    if (code == 200) {
                        finish()
                    }
                    else {
//                        인증글 등록 실패시 실패 사유 토스트 출력
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
        mProject = intent.getSerializableExtra("project") as Project
    }

