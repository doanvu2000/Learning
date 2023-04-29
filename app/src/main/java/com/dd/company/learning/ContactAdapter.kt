package com.dd.company.learning

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dd.company.learning.databinding.LayoutItemContactBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    private var dataList: MutableList<Contact> = mutableListOf()

    private var iOnClickContact: IOnClickContact? = null

    fun setOnClickItem(listener: IOnClickContact) {
        iOnClickContact = listener
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: Contact) {
            //execute fill data
            val binding = LayoutItemContactBinding.bind(itemView)
            binding.imgAvatar.setImageResource(contact.idImg)
            binding.tvNameContact.text = contact.name
            val imgGender = if (contact.gender) {
                R.drawable.phone_call
            } else {
                R.drawable.phone_call_red
            }
            binding.imgGender.setImageResource(imgGender)
            itemView.setOnClickListener {
                iOnClickContact?.clickItem(contact)
            }
            binding.imgGender.setOnClickListener {
                iOnClickContact?.clickGender(contact)
            }
        }
    }

    //create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Alt + J
//        val infalter = LayoutInflater.from(parent.context)
//        val binding: LayoutItemContactBinding = LayoutItemContactBinding.inflate(infalter, parent, false)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_contact, parent, false)//required false
//        return MyViewHolder(binding.root)
        return MyViewHolder(view)
    }

    //fill data
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDataList(newList: MutableList<Contact>) {
        dataList.clear()
        dataList = newList
        notifyDataSetChanged()//load lai view
    }
}