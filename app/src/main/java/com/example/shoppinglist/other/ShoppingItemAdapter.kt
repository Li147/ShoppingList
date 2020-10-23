package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    // tell RecyclerViewAdapter which layout we want within RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val ourShoppingItem = items[position]

        holder.itemView.tvName.text = ourShoppingItem.name
        holder.itemView.tvAmount.text = "${ourShoppingItem.amount}"

        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(ourShoppingItem)
        }

        holder.itemView.ivPlus.setOnClickListener {
            ourShoppingItem.amount++
            viewModel.upsert(ourShoppingItem)
        }

        holder.itemView.ivMinus.setOnClickListener {
            if(ourShoppingItem.amount > 0){
                ourShoppingItem.amount++
                viewModel.upsert(ourShoppingItem)
            }
        }
    }

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}