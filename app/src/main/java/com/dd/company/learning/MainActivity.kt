package com.dd.company.learning

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.dd.company.learning.databinding.ActivityMainBinding

//Activity, Fragment lifecycle

class MainActivity : AppCompatActivity() {
    //c1
//    private var binding: ActivityMainBinding? = null
    //c2
    private lateinit var binding: ActivityMainBinding

    //c3
//    private val binding by lazy {
//        ActivityMainBinding.inflate(layoutInflater)
//    }
    private lateinit var firstFragment: FirstFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firstFragment = FirstFragment()
        binding.btnSum.setOnClickListener {
            calcSum()
        }
        //Ctrl + Alt + M : Extract method
        showFragment()
        binding.btnShowFragment.setOnClickListener {
            //show Fragment
            showFragment()
        }
        firstFragment.onClickBtnCount = {
            setCount(it.toString())
        }
    }

    private fun showFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, firstFragment)
            .commit()
    }

    @SuppressLint("SetTextI18n")
    private fun calcSum() {
        try {
            val numberOne = binding.edtNumberOne.text.toString().toFloat()
            val numberTwo = binding.edtNumberTwo.text.toString().toFloat()
            binding.tvResult.text = "${numberOne + numberTwo}"
            firstFragment.setResult("${numberOne + numberTwo}")
        } catch (ex: Exception) {
            binding.tvResult.text = ex.message
        }
    }

    fun setCount(rs: String) {
        binding.tvResult.text = rs
    }
}