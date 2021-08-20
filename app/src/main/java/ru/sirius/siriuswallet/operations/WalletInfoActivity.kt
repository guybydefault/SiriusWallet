package ru.sirius.siriuswallet

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.databinding.ActivityWalletInfoBinding
import ru.sirius.siriuswallet.operations.OperationsRecyclerViewAdapter
import ru.sirius.siriuswallet.operations.OperationsViewModel

class WalletInfoActivity : AppCompatActivity() {

    private val binding: ActivityWalletInfoBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityWalletInfoBinding.inflate(
            layoutInflater
        )
    }

    private val recyclerView: RecyclerView by lazy(LazyThreadSafetyMode.NONE) {
        binding.operationListRecyclerView
    }

    private val recyclerViewAdapter: OperationsRecyclerViewAdapter by lazy(LazyThreadSafetyMode.NONE) {
        recyclerView.adapter as OperationsRecyclerViewAdapter
    }

    private val viewModel: OperationsViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getContainer().operationsViewModel
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
            adapter = OperationsRecyclerViewAdapter(context)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        viewModel.operations.observe(this) { recyclerViewAdapter.dataset = it }
    }

    private fun onBackClick() {
        Toast.makeText(this, "Back arrow click", Toast.LENGTH_LONG).show()
    }

    private fun onSettingsClick() {
        Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()
    }

    fun onAddOperationBtnClick() {
        val intent = Intent(this, EnterOperationSumActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle())
    }
}