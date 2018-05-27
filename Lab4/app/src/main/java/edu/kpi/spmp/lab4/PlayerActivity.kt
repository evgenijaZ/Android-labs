package edu.kpi.spmp.lab4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.MediaController
import android.widget.VideoView


class PlayerActivity : AppCompatActivity() {

    private lateinit var videoPlayer: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        videoPlayer = findViewById<View>(R.id.video_player) as VideoView
        val myVideoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.omelette)
        videoPlayer.setVideoURI(myVideoUri)
        val mediaController = MediaController(this)
        videoPlayer.setMediaController(mediaController)
        mediaController.setMediaPlayer(videoPlayer)
    }

    fun play(view: View) {
        videoPlayer.start()
    }

    fun pause(view: View) {
        videoPlayer.pause()
    }

    fun stop(view: View) {
        videoPlayer.stopPlayback()
        videoPlayer.resume()
    }

    fun playByUrl(view: View) {
        val intent = Intent(this, OnlinePlayerActivity::class.java)
        startActivity(intent)
    }
}
