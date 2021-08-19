package ru.sirius.siriuswallet

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.databinding.ActivityWalletInfoBinding

class WalletInfoActivity : AppCompatActivity() {

    private val binding: ActivityWalletInfoBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityWalletInfoBinding.inflate(
            layoutInflater
        )
    }

    private val recyclerView: RecyclerView by lazy(LazyThreadSafetyMode.NONE) {
        binding.operationListRecyclerView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListeners()
        setupOperationList()
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.backArrowBtn.setOnClickListener { onBackClick() }
        binding.settingsBtn.setOnClickListener { onSettingsClick() }
        binding.addOperationBtn.setOnClickListener { onAddOperationBtnClick() }
    }

    private fun setupOperationList() {
        recyclerView.apply {
            adapter = OperationsRecyclerViewAdapter()
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(OperationViewHolderDivider(context))
        }
    }

    private fun onBackClick() {
        Toast.makeText(this, "Back arrow click", Toast.LENGTH_LONG).show()
    }

    private fun onSettingsClick() {
        Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()
    }

    private fun onAddOperationBtnClick() {
        val intent = Intent(this, SelectOperationCategoryActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle())
    }


}