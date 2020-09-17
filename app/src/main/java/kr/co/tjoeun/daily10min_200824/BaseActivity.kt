package kr.co.tjoeun.daily10min_200824

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :AppCompatActivity()
{
    var mContext = this

    abstract fun setValues()
    abstract fun setupEvents()

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
    }
}