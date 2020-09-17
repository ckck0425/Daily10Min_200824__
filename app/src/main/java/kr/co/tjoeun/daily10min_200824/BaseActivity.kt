package kr.co.tjoeun.daily10min_200824

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity :AppCompatActivity()
{
    var mContext = this

    abstract fun setValues()
    abstract fun setupEvents()

//    알림 아이콘을 변수로 만들어서 => 모든 BaseActivity의 자녀가 사용할 수 있게 처리
    lateinit var notiImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        액션바를 꺼내올 수 있는 상황일때는 => 커스텀 액션바로 세팅
        supportActionBar?.let {
            setCustomActionBar()
        }
    }

    fun setCustomActionBar() {

//        이 화면에 달린 액션바가 절대 null 아니라고 하면서 가져옴.
        val myActionBar = supportActionBar!!

//        액션바를 커스텀으로 사용할 수 있도록 세팅.
        myActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        액션바에 보여줄 커스텀으로 그린 화면 배치.
        myActionBar.setCustomView(R.layout.my_custom_actionbar)

//        커스텀액션바 뒤의 기본 색 제거 => 액션바를 들고 있는 툴바의 좌우 여백을 0으로 설정하자.
        val parentToolBar = myActionBar.customView.parent as Toolbar
        parentToolBar.setContentInsetsAbsolute(0, 0)


//        액션바에 있는 컴포넌트들을 => 코틀린단에서도 사용하게 연결. findViewById
        notiImg = myActionBar.customView.findViewById(R.id.notiImg)
    }
}