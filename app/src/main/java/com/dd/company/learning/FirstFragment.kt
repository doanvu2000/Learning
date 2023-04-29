package com.dd.company.learning

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dd.company.learning.databinding.ActivityMainBinding
import com.dd.company.learning.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    //c2
//    private lateinit var mContext: Context
    var onClickBtnCount: ((count: Int) -> Unit)? = null
    private lateinit var binding: FragmentFirstBinding
    private val contactAdapter by lazy {
        ContactAdapter()
    }

    //    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        mContext = context
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initListener()
    }

    private fun initRecyclerView() {
        binding.rcvContact.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvContact.adapter = contactAdapter
        contactAdapter.setDataList(fakeData())
        contactAdapter.setOnClickItem(object : IOnClickContact {
            override fun clickItem(contact: Contact) {
                showToast("Click item")
            }

            override fun clickGender(contact: Contact) {
                showToast("Click call")
            }
        })
    }

    private fun fakeData(): MutableList<Contact> {
        val list = mutableListOf<Contact>()
        repeat(10) {
            val gender = it % 2 == 0
            list.add(Contact(R.drawable.user, "Duc Diep $it", gender))
        }
        repeat(10) {
            val gender = it % 2 == 0
            list.add(Contact(R.drawable.user, "Truong $it", gender))
        }
        return list
    }

    private var count = 0

    @SuppressLint("SetTextI18n")
    private fun initListener() {
        binding.tvInfo.text = "Hello world!"
        binding.tvInfo.setOnClickListener {
            showToast("Click text view info")
        }
        binding.btnClick.setOnClickListener {
            count++
            onClickBtnCount?.invoke(count)
//            (requireActivity() as? MainActivity)?.setCount(count.toString())
        }
    }

    fun setResult(rs: String) {
        binding.tvInfo.text = rs
    }

    fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}