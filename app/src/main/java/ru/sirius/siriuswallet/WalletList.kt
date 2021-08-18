package ru.sirius.siriuswallet

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.databinding.ActivityWalletListBinding

class WalletList : AppCompatActivity() {

    private lateinit var binding: ActivityWalletListBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalletListBinding.inflate(layoutInflater)
        binding.backArrowBtn.setOnClickListener { onBackClick(it) }
        binding.settingsBtn.setOnClickListener { onSettingsClick(it) }
        binding.addOperationBtn.setOnClickListener { onAddOperationBtnClick(it) }
        recyclerView = binding.walletRecyclerView
        recyclerView.apply {
            adapter = WalletRecyclerViewAdapter
            layoutManager = LinearLayoutManager(this@WalletList)
            setHasFixedSize(true)
        }

        setContentView(binding.root)
    }

    fun onBackClick(view: View) {
        Toast.makeText(this, "Back arrow click", Toast.LENGTH_LONG).show()
    }

    fun onSettingsClick(view: View) {
        Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()
    }

    fun onAddOperationBtnClick(view: View) {
        Toast.makeText(this, "Add operation", Toast.LENGTH_LONG).show()
    }


}