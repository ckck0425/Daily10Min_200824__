package kr.co.tjoeun.daily10min_200824

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :AppCompatActivity()
{
    var mContext = this

    abstract fun setValues()
    abstract fun setupEvents()
}