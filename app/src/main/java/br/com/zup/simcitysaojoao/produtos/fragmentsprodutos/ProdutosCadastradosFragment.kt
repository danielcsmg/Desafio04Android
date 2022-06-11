package br.com.zup.simcitysaojoao.produtos.fragmentsprodutos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.simcitysaojoao.R
import br.com.zup.simcitysaojoao.consts.PRODUTO_LISTA
import br.com.zup.simcitysaojoao.databinding.FragmentProdutosCadastradosBinding
import br.com.zup.simcitysaojoao.models.Produto
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


        val reciclerView = binding.rvListaProdutos
        listaProdutos?.let {
            val adapter = ProdutoAdapter(requireContext(), it)
            reciclerView.adapter = adapter
            reciclerView.layoutManager = LinearLayoutManager(context)
        }
    }
}