package com.example.plantapp.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plantapp.Model.Local.PlantListEntity
import com.example.plantapp.databinding.CardItemBinding

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.ProductViewHolder>() {

    private var productList = listOf<PlantListEntity>()
    private val selectedProduct = MutableLiveData<PlantListEntity>()

    fun update(list: List<PlantListEntity>) {
        productList = list
        notifyDataSetChanged()
    }

    fun selectedProduct(): LiveData<PlantListEntity> = selectedProduct

    inner class ProductViewHolder(private val cardItemBinding: CardItemBinding) :
        RecyclerView.ViewHolder(
            cardItemBinding.root
        ), View.OnClickListener {

        fun bind(product: PlantListEntity) {
            Glide.with(cardItemBinding.imageItem).load(product.image).centerCrop()
                .into(cardItemBinding.imageItem)
            cardItemBinding.nameItemList.text = product.name
            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            selectedProduct.value = productList[adapterPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(CardItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }
}