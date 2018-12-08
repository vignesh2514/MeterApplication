package com.inesh.ineshmeter.globaladapters

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inesh.ineshmeter.R
import com.inesh.ineshmeter.listclass.Listmultitext
import kotlinx.android.synthetic.main.list_multi_text.view.*

class Adaptermultitext (private val partItemList: List<Listmultitext>, private val clickListener: (Listmultitext) -> Unit) :
    RecyclerView.Adapter< RecyclerView.ViewHolder>() {

    private val selectedItems: SparseBooleanArray? = SparseBooleanArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // LayoutInflater: takes ID from layout defined in XML.
        // Instantiates the layout XML into corresponding View objects.
        // Use context from main app -> also supplies theme layout values!
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.list_multi_text, parent, false)

        return PartViewHolder(view)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PartViewHolder).bind(partItemList[position], clickListener, selectedItems, partItemList)
    }
    
    override fun getItemCount() = partItemList.size

    class PartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(
            part: Listmultitext,
            clickListener: (Listmultitext) -> Unit,
            selectedItems: SparseBooleanArray?,
            partItemList: List<Listmultitext>
        ) {


            itemView.tv_list_text.text = part.itemName
            itemView.tv_list_text_id.text = part.id.toString()
            itemView.tv_list_subtext.text=part.itemSubName
            itemView.tv_list_subtext2.text=part.itemSubNam
           itemView.tv_list_subtext3.text=part.itemSubNa




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