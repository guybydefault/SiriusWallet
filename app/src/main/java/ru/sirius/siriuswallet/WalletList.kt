package ru.sirius.siriuswallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.databinding.ActivityWalletListBinding
import ru.sirius.siriuswallet.databinding.WalletCardViewBinding

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

    class WalletViewHolder(
        val cardView: CardView,
        val binding: WalletCardViewBinding
    ) : RecyclerView.ViewHolder(cardView) {}

    object WalletRecyclerViewAdapter : RecyclerView.Adapter<WalletViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
            val cardView =
                WalletCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return WalletViewHolder(cardView.root, cardView)
        }

        override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
            holder.binding.walletName.text = "Wallet #${position}"
        }

        override fun getItemCount(): Int {
            return 20
        }

    }
}