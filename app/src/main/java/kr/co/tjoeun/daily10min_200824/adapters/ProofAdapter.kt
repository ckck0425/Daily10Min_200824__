package kr.co.tjoeun.daily10min_200824.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.tjoeun.daily10min_200824.R
import kr.co.tjoeun.daily10min_200824.datas.Proof

class ProofAdapter {
    val mContext: Context,
    resId: Int,
    val mList: List<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

        val inf = LayoutInflater.from(mContext)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var tempRow = convertView
            if (tempRow == null) {
                tempRow = inf.inflate(R.layout.proof_list_item, null)

            }

            val row = tempRow!!
            return row

        }
}