package ru.sirius.siriuswallet

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.databinding.ActivityWalletInfoBinding
import ru.sirius.siriuswallet.operations.OperationsRecyclerViewAdapter
import ru.sirius.siriuswallet.operations.OperationsViewModel
import ru.sirius.siriuswallet.utils.formatForDisplay
import ru.sirius.siriuswallet.view.transition.EnterOperationSumActivity
import android.view.animation.TranslateAnimation



class WalletInfoActivity : AppCompatActivity() {

    var isUp = true

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
        setupBalanceInfo()
        setupOperationList()
        setupErrorToasts()
        setContentView(binding.root)

        binding.operationListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (isUp && dy > 0) {
                    slideDown(binding.addOperationBtn)
                    isUp = !isUp
                } else if (!isUp && dy < 0) {
                    slideUp(binding.addOperationBtn)
                    isUp = !isUp
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.reloadViewModel()
    }

    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(0.0f, 0.0f, 200.0f, 0.0f)
        animate.duration = 200
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    fun slideDown(view: View) {
        val animate = TranslateAnimation(0.0f, 0.0f, 0.0f, 200.0f)
        animate.duration = 200
        animate.fillAfter = true
        view.startAnimation(animate)
    }


    private fun setupListeners() {
        binding.addOperationBtn.setOnClickListener { onAddOperationBtnClick() }
    }

    private fun setupOperationList() {
        recyclerView.apply {
            adapter = OperationsRecyclerViewAdapter(context)
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        viewModel.operations.observe(this) {
            if (it.isEmpty()) {
                binding.operationListRecyclerView.visibility = View.GONE
                binding.noRecords.visibility = View.VISIBLE
            } else {
                binding.operationListRecyclerView.visibility = View.VISIBLE
                binding.noRecords.visibility = View.GONE
            }
            recyclerViewAdapter.dataset = it
        }
    }

    private fun setupBalanceInfo() {
        viewModel.income.observe(this) {
            binding.walletIncomeExpense.income.text = resources.getString(R.string.wallet_balance_format, it.formatForDisplay())
        }
        viewModel.outcome.observe(this) {
            binding.walletIncomeExpense.outcome.text = resources.getString(R.string.wallet_balance_format, it.formatForDisplay())
        }
        viewModel.totalBalance.observe(this) {
            binding.walletBalance.text = resources.getString(R.string.wallet_balance_format, it.formatForDisplay())
        }
    }

    private fun setupErrorToasts() {
        viewModel.err.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }



    fun onAddOperationBtnClick() {
        val intent = Intent(this, EnterOperationSumActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle())
    }
}