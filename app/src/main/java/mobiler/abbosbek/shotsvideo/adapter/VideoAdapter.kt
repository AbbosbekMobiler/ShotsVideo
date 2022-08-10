package mobiler.abbosbek.shotsvideo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import mobiler.abbosbek.shotsvideo.R
import mobiler.abbosbek.shotsvideo.model.VideoModel

class VideoAdapter (options:FirebaseRecyclerOptions<VideoModel?>)
    : FirebaseRecyclerAdapter<VideoModel?,VideoAdapter.MyViewHolder>(options)
{
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        lateinit var videoView: VideoView
        lateinit var title:TextView
        lateinit var desc : TextView
        lateinit var pBar : ProgressBar
        lateinit var fav : ImageView
        var isFav = false

        fun setData(obj: VideoModel){
            videoView.setVideoPath(obj.url)
            title.text = obj.title
            desc.text = obj.desc
            videoView.setOnPreparedListener{
                mediaPlayer ->
                pBar.visibility = View.GONE
                mediaPlayer.start()
            }
            videoView.setOnCompletionListener {
                mediaPlayer->
                mediaPlayer.start()
            }
            fav.setOnClickListener {
                if (!isFav){
                    fav.setImageResource(R.drawable.ic_full_favorite)
                    isFav = true
                }else{
                    fav.setImageResource(R.drawable.ic_favorite)
                    isFav = false
                }
            }
        }

        init {
            videoView = view.findViewById(R.id.videoView)
            title = view.findViewById(R.id.textVideoTitle)
            desc = view.findViewById(R.id.textVideoDesc)
            fav = view.findViewById(R.id.favorites)
            pBar = view.findViewById(R.id.videoProgressBar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.single_video_row,parent,false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: VideoModel) {
        holder.setData(model)
    }
}