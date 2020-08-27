package kr.co.tjoeun.daily10min_200824.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kr.co.tjoeun.daily10min_200824.R
import kr.co.tjoeun.daily10min_200824.datas.Project

class ProjectAdapter(
    val mContext: Context,
     resId: Int,
    val mList : List<Project>) : ArrayAdapter<Project>(mContext, resId, mList){

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.project_list_item, null)
        }

        val row = tempRow!!

        val projectImg = row.findViewById<ImageView>(R.id.projectiImg)
        val projectTitleTxt = row.findViewById<TextView>(R.id.projectTitleTxt)
        val projectDes = row.findViewById<TextView>(R.id.projectDes)

        val data = mList[position]

        projectTitleTxt.text = data.title
        projectDes.text = data.description

        Glide.with(mContext).load(data.imageUrl).into(projectImg)


        return row

    }
}