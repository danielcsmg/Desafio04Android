package br.com.zup.simcitysaojoao.produtos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.simcitysaojoao.R
import br.com.zup.simcitysaojoao.databinding.CardProdutoBinding
import br.com.zup.simcitysaojoao.models.Produto

class ProdutoAdapter(
    val context: Context,
    private var clickAlbum: (produto: Produto, context: Context) -> Unit,
    private var listaProdutos: ArrayList<Produto>
) : RecyclerView.Adapter<ProdutoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardProdutoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.exibirInformacoes(produto)
        holder.binding.cvProduto.setOnClickListener{
            clickAlbum(produto, context)
        }
    }

    override fun getItemCount(): Int = listaProdutos.size


    class ViewHolder(val binding: CardProdutoBinding,val context: Context): RecyclerView.ViewHolder(binding.root) {
        fun exibirInformacoes(produto: Produto) {
            binding.tvTituloProduto.text = context.getString(
                R.string.produto_titulo_card,
                produto.getQtdProduto().toString(),
                produto.getNomeProduto()
            )
        }
    }
}