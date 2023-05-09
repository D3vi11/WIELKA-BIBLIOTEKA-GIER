package com.example.greatgamelibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameInfoAdapter(val gameInfo: GameInfo): RecyclerView.Adapter<GameInfoAdapter.GameInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.game_info,parent,false)
        return GameInfoViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: GameInfoViewHolder, position: Int) {
        //holder.gameInfo.text = gameInfo.title
       // holder.gameInfo.text = gameInfo.data
       // holder.gameInfo.text = gameInfo.rating
       // holder.gameInfo.text = gameInfo.userRating
    }

    class GameInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gameInfo : TextView = itemView.findViewById(R.id.GameInfoText)
    }
}