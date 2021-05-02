package com.talib.youtubeclone.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.talib.youtubeclone.R
import kotlin.random.Random


class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.youtubeRV)
        val fab: FloatingActionButton = view.findViewById(R.id.fab)


        val youtubeAdapter = YoutubeAdapter()

        youtubeAdapter.setOnItemClickListener {
            Log.d("dasdasdasd",it.toString())
        }


        recyclerView.adapter = youtubeAdapter

        fab.setOnClickListener {

            val oldList: MutableList<YoutubeModel> = mutableListOf()

            oldList.addAll(youtubeAdapter.differ.currentList)

            val randomBool = Random.nextInt(0,2) == 1


            val video1 = YoutubeModel(
                Random.nextInt(0,100),"https://guardian.ng/wp-content/uploads/2018/04/Drake.-Photo-credit-Youtube.jpg",
                "https://yt3.ggpht.com/ytc/AAUvwnh1mAxc7nv3JTMtQwHoRdhhuIBfALqOYI4Rzy-B6Q=s48-c-k-c0x00ffffff-no-rj-mo",
                "Drake - God's Plan","1,290,005,620 views•Feb 17, 2018",randomBool)

            oldList.add(video1)


            youtubeAdapter.differ.submitList(oldList)
        }

        youtubeAdapter.differ.submitList(getList())



    }


    fun getList() : ArrayList<YoutubeModel>{
        val video1 = YoutubeModel(1,"https://guardian.ng/wp-content/uploads/2018/04/Drake.-Photo-credit-Youtube.jpg",
        "https://yt3.ggpht.com/ytc/AAUvwnh1mAxc7nv3JTMtQwHoRdhhuIBfALqOYI4Rzy-B6Q=s48-c-k-c0x00ffffff-no-rj-mo",
            "Drake - God's Plan","1,290,005,620 views•Feb 17, 2018")

        val post1 = YoutubeModel(2,"https://i-d-images.vice.com/images/articles/meta/2015/09/21/travis-scott-drops-video-for-antidote-1442835991.png?crop=0.9919377258826799xw:1xh;center,center&resize=640:*",
            "https://yt3.ggpht.com/ytc/AAUvwninqPi1-v7NEJBvxm_nHpgmhx2VuzwFcGTHTB8S7g=s48-c-k-c0x00ffffff-no-rj-mo",
            "Travis Scott - Antidote","373,145,953 views•Sep 18, 2015",true)


        val list = arrayListOf(video1,post1)

        return list
    }



}