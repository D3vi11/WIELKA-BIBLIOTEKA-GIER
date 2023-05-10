package com.example.greatgamelibrary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greatgamelibrary.R


class SearchAdapter(val list: List<String>): RecyclerView.Adapter<SearchAdapter.SearchParametersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchParametersViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_item,parent,false)
        return SearchParametersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchParametersViewHolder, position: Int) {
        holder.text.text = list[position]
    }


    class SearchParametersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val text: TextView = itemView.findViewById(R.id.TextView)
        val textField: EditText = itemView.findViewById(R.id.Text)
    }

}