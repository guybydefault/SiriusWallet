package ru.sirius.siriuswallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.databinding.WalletCardViewBinding

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