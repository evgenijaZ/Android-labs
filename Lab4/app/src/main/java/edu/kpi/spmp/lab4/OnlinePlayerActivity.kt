package edu.kpi.spmp.lab4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_online_player.*


class OnlinePlayerActivity : AppCompatActivity() {
    lateinit var videoPlayer: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online_player)
        videoPlayer = findViewById(R.id.online_video_player)

        val mediaController = MediaController(this)
        videoPlayer.setMediaController(mediaController)
        mediaController.setMediaPlayer(videoPlayer)
    }

    fun setPath(view: View) {
        val url = url_edit_text.text.toString()
        if (!url.contains(Regex("\\.(gif|jpe?g|png|swf|mp4)$"))) {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        } else {
            videoPlayer.setVideoPath(url)
        }
    }

    fun clear(view: View) {
        url_edit_text.text.clear()
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

}
