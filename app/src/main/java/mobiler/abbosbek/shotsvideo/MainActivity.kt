package mobiler.abbosbek.shotsvideo

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mobiler.abbosbek.shotsvideo.adapter.VideoAdapter
import mobiler.abbosbek.shotsvideo.model.VideoModel

class MainActivity : AppCompatActivity() {

    lateinit var videPager2: ViewPager2
    lateinit var adapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)

        videPager2 = findViewById(R.id.viewPager)

        val mDataBase = Firebase.database.getReference("videos")

        val options = FirebaseRecyclerOptions.Builder<VideoModel>()
            .setQuery(mDataBase,VideoModel::class.java)
            .build()

        adapter = VideoAdapter(options)
        videPager2.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}

