package com.example.greatgamelibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameItemAdapter(val gameItemList: ArrayList<GameItem>): RecyclerView.Adapter<GameItemAdapter.GameItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_item,parent,false)
        return GameItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return gameItemList.size
    }

    override fun onBindViewHolder(holder: GameItemViewHolder, position: Int) {
        val currentItem = gameItemList[position]
        holder.gameImage.setImageResource(currentItem.gameImage)
        holder.gameTitle.text = currentItem.gameTitle
    }

    class GameItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gameImage: ImageView = itemView.findViewById(R.id.gameImage)
        val gameTitle: TextView = itemView.findViewById(R.id.gameTitle)
    }
}