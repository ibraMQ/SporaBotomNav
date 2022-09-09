package com.example.botomexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var data = listOf<Contact>()
    private val PAIR = 1
    private val ODD = 0
    private lateinit var context: Context

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context=parent.context

        val viewHolder : RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        viewHolder = when(viewType){
            PAIR -> {
                val pairView : View = inflater.inflate(R.layout.list_item_contact_left,parent,false)
                ViewHolderPair(pairView)
            }
            else -> {
                val oddView : View = inflater.inflate(R.layout.list_item_contact,parent,false)
                ViewHolderOdd(oddView)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contact = data[position]

        when(holder.itemViewType){
            PAIR ->{
                val vhp = holder as ViewHolderPair
                configureViewHolderPair(vhp,contact,position)
            }
            else ->{
                val vho = holder as ViewHolderOdd
                configureViewHolderOdd(vho,contact,position)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0){
            PAIR
        }else{
            ODD
        }
    }

    private fun configureViewHolderOdd(holder: ViewHolderOdd, contact: Contact, position: Int) {
        holder.tvName.text = contact.name
        holder.tvNumber.text = contact.number
        holder.tvNumber.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,contact.company,Toast.LENGTH_SHORT).show()
        })
    }

    private fun configureViewHolderPair(holder: ViewHolderPair, contact: Contact, position: Int) {
        holder.tvName.text = contact.name
        holder.tvNumber.text = contact.number
        holder.tvNumber.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,contact.company,Toast.LENGTH_SHORT).show()
        })
    }
}

class ViewHolderOdd (itemView: View) : RecyclerView.ViewHolder(itemView){
    val tvName : TextView = itemView.findViewById(R.id.tvName)
    val tvNumber : TextView = itemView.findViewById(R.id.tvPhone)
    val btnDel: ImageButton = itemView.findViewById(R.id.btnDel)
}

class ViewHolderPair (itemView: View) : RecyclerView.ViewHolder(itemView){
    val tvName : TextView = itemView.findViewById(R.id.tvName2)
    val tvNumber : TextView = itemView.findViewById(R.id.tvPhone2)
    val btnDel: ImageButton = itemView.findViewById(R.id.btnDel2)
}