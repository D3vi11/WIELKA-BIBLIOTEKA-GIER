package com.example.greatgamelibrary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greatgamelibrary.R

class GameInfoAdapter(val list: ArrayList<String>): RecyclerView.Adapter<GameInfoAdapter.GameInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_info,parent,false)
        return GameInfoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GameInfoViewHolder, position: Int) {
        val currentItem = list[position]
        holder.gameInfo.text = currentItem
    }

    class GameInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gameInfo : TextView = itemView.findViewById(R.id.GameInfoText)
    }
}