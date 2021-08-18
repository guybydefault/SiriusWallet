package ru.sirius.siriuswallet

import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.databinding.WalletCardViewBinding

class WalletViewHolder(
    val cardView: CardView,
    val binding: WalletCardViewBinding
) : RecyclerView.ViewHolder(cardView) {}