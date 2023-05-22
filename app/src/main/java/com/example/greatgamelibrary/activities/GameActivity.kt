package com.example.greatgamelibrary.activities

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greatgamelibrary.R
import com.example.greatgamelibrary.adapters.GameInfoAdapter
import com.example.greatgamelibrary.data.GameInfo
import com.example.greatgamelibrary.database.FirebaseDB
import com.example.greatgamelibrary.interfaces.ActivityInterface

class GameActivity : AppCompatActivity(), ActivityInterface {
    var position = 0
    lateinit var mainMenuButton: Button
    lateinit var rateButton: Button
    lateinit var firebaseDB: FirebaseDB
    lateinit var image: ImageView
    lateinit var recyclerView: RecyclerView
    lateinit var mediaPlayer: MediaPlayer
    lateinit var playButton: Button
    lateinit var musicSeekBar: SeekBar
    lateinit var videoView: VideoView
    lateinit var mediaController: MediaController
    var isLoggedIn: Boolean = false
    var gameInfo: ArrayList<GameInfo> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        mediaController = MediaController(this)
        playButton = findViewById(R.id.playButton)
        musicSeekBar = findViewById(R.id.MusicSeekBar)
        videoView = findViewById(R.id.videoView)
        firebaseDB = FirebaseDB(this@GameActivity)
        mainMenuButton = findViewById(R.id.MainMenuButton)
        rateButton = findViewById(R.id.RateGameButton)
        image = findViewById(R.id.GameImage)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        setRecyclerView()
        position = intent.getIntExtra("position",0)
        isLoggedIn = intent.getBooleanExtra("isLoggedIn",false)
        firebaseDB.getDataFromDB()
        mainMenuButton.setOnClickListener {
            val intent = Intent(this@GameActivity, MainActivity::class.java)
            intent.putExtra("isLoggedIn",isLoggedIn)
            startActivity(intent)
        }
        rateButton.setOnClickListener {
            if(isLoggedIn){
                val intent = Intent(this@GameActivity, RatingActivity::class.java)
                intent.putExtra("isLoggedIn",isLoggedIn)
                intent.putExtra("position",position)
                startActivity(intent)
            }else{
                val intent = Intent(this@GameActivity, LoginActivity::class.java)
                intent.putExtra("isLoggedIn",isLoggedIn)
                startActivity(intent)
            }
        }
        playButton.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }else{
                mediaPlayer.start()
            }
        }
        musicSeekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser) mediaPlayer.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }

    override fun onUpdate() {
        setAllData()
        if(firebaseDB.gameAudio.isNotEmpty()&&firebaseDB.gameAudio.size==firebaseDB.gameDataList.size){
            mediaPlayer = MediaPlayer.create(this,firebaseDB.gameAudio[position].audio)
            initializeSeekBar()
        }
        if(firebaseDB.gameVideo.isNotEmpty()&&firebaseDB.gameVideo.size==firebaseDB.gameDataList.size){
            videoView.setVideoURI(firebaseDB.gameVideo[position].video)
            videoView.requestFocus()
        }
    }
    fun setAllData(){
        gameInfo = firebaseDB.gameDataList
        var gameImage = firebaseDB.gameImage
        if(gameInfo.size == gameImage.size){
            var adapter = GameInfoAdapter(gameInfo[position].getList())
            image.setImageBitmap(gameImage[position].image)
            recyclerView.adapter =adapter
        }
        //Toast.makeText(this@GameActivity,gameInfo.toString(),Toast.LENGTH_SHORT).show()

    }
    fun setRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }

    fun initializeSeekBar(){
        musicSeekBar.max = mediaPlayer.duration
        val handler = Handler()
        handler.postDelayed(object: Runnable{
            override fun run() {
                try {
                    musicSeekBar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                }catch (e: Exception){
                    musicSeekBar.progress=0
                }
            }
        },0)
    }
}