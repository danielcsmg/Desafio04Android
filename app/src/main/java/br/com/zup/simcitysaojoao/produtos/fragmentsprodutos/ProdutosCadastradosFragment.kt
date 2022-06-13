package br.com.zup.simcitysaojoao.produtos.fragmentsprodutos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.simcitysaojoao.R
import br.com.zup.simcitysaojoao.consts.BUNDLE
import br.com.zup.simcitysaojoao.consts.PRODUTO
import br.com.zup.simcitysaojoao.consts.PRODUTO_LISTA
import br.com.zup.simcitysaojoao.databinding.FragmentProdutosCadastradosBinding
import br.com.zup.simcitysaojoao.detalheproduto.DetalheProdutoActivity
import br.com.zup.simcitysaojoao.models.Produto
import br.com.zup.simcitysaojoao.produtos.ProdutosActivity
import br.com.zup.simcitysaojoao.produtos.adapter.ProdutoAdapter

class ProdutosCadastradosFragment : Fragment() {
    private lateinit var binding: FragmentProdutosCadastradosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProdutosCadastradosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listaProdutos = arguments?.getParcelableArrayList<Produto>(PRODUTO_LISTA)
        val recyclerView = binding.rvListaProdutos
        criarRecyclerView(listaProdutos, recyclerView)
    }

    fun acessarDetalheProduto(produto: Produto, context: Context) {
        val bundle = bundleOf(PRODUTO to produto)
        val intent = Intent(context, DetalheProdutoActivity::class.java).apply {
            putExtra(BUNDLE, bundle)
        }
        startActivity(intent)
    }

    private fun criarRecyclerView(listaProdutos:  ArrayList<Produto>?, recyclerView: RecyclerView){
        listaProdutos?.let {
            val adapter = ProdutoAdapter(requireContext(), this::acessarDetalheProduto, it)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }
}