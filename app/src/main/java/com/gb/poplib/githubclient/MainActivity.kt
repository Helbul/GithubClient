package com.gb.poplib.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gb.poplib.githubclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private var vb: ActivityMainBinding? = null
    var presenter = MainPresenter(this)
    val counters = mutableListOf(0, 0, 0,)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener { presenter.counterClick(COUNTER_1) }
        vb?.btnCounter2?.setOnClickListener { presenter.counterClick(COUNTER_2) }
        vb?.btnCounter3?.setOnClickListener { presenter.counterClick(COUNTER_3) }
    }


    override fun setButtonText(counter: Int, text: String) {
        when(counter) {
            COUNTER_1-> vb?.btnCounter1?.text = text
            COUNTER_2 -> vb?.btnCounter2?.text = text
            COUNTER_3 -> vb?.btnCounter3?.text = text
        }
    }

    companion object {
        const val COUNTER_1 = 0
        const val COUNTER_2 = 1
        const val COUNTER_3 = 2
    }
}