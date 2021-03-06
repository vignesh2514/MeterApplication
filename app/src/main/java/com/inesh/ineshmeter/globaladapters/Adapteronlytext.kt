package com.inesh.ineshmeter.globaladapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.listclass.ListTextOnly
import kotlinx.android.synthetic.main.list_only_text.view.*


class Adapteronlytext  (private val partItemList: List<ListTextOnly>, private val clickListener: (ListTextOnly) -> Unit) :
    RecyclerView.Adapter< RecyclerView.ViewHolder>() {

    private val selectedItems: SparseBooleanArray? = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // LayoutInflater: takes ID from layout defined in XML.
        // Instantiates the layout XML into corresponding View objects.
        // Use context from main app -> also supplies theme layout values!
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.list_only_text, parent, false)

        return PartViewHolder(view)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PartViewHolder).bind(partItemList[position], clickListener, selectedItems, partItemList)
    }


    override fun getItemCount() = partItemList.size

    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            part: ListTextOnly,
            clickListener: (ListTextOnly) -> Unit,
            selectedItems: SparseBooleanArray?,
            partItemList: List<ListTextOnly>
        ) {


            itemView.tv_list_text.text = part.itemName
            itemView.tv_list_text_id.text = part.id.toString()
            itemView.tv_list_subtext.text=part.itemSubName
if (itemView.tv_list_subtext.text.contains("Active"))
{
    itemView.layout_back_text.setBackgroundResource(R.drawable.button_fill)
    itemView.tv_list_text.setTextColor(Color.WHITE)
                    itemView.tv_list_subtext.setTextColor(Color.WHITE)
}
            else
{
    itemView.layout_back_text.setBackgroundResource(R.drawable.editext_round_white)
                       itemView.tv_list_text.setTextColor(Color.BLACK)
                    itemView.tv_list_subtext.setTextColor(Color.BLACK)
}


//            if (selectedItems != null && selectedItems.size() > 0) {
//
//                if (!selectedItems.get(adapterPosition)) {
//
//                    itemView.layout_back_text.setBackgroundResource(R.drawable.button_fill)
//                    itemView.tv_list_text.setTextColor(Color.WHITE)
//                    itemView.tv_list_subtext.setTextColor(Color.WHITE)
//
//                } else {
//                    itemView.tv_list_text.setTextColor(Color.BLACK)
//                    itemView.tv_list_subtext.setTextColor(Color.BLACK)
//                    itemView.layout_back_text.setBackgroundResource(R.drawable.editext_round_white)
//                }
//            } else {
//                for (i in 0 until partItemList.size) {
//                    selectedItems!!.put(i, false)
//                }
//            }


            itemView.setOnClickListener {

//                if (selectedItems != null) {
//                    for (i in 0 until selectedItems.size()) {
//                        if (i == adapterPosition) {
//                            if (selectedItems.get(i)) {
//                                selectedItems.put(i, false)
//                            } else {
//                                selectedItems.put(i, true)
//                                Log.d("pos", i.toString())
//                                Log.d("posValue", partItemList[i].itemName)
//                            }
//                        } else {
//                            selectedItems.put(i, false)
//                        }
//                    }
//                }
                clickListener(part)
            }

        }


    }


}