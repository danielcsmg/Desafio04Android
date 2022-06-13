package br.com.zup.simcitysaojoao.produtos.fragmentsprodutos

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import br.com.zup.simcitysaojoao.R
import br.com.zup.simcitysaojoao.consts.BUNDLE
import br.com.zup.simcitysaojoao.consts.MENSAGEM_CADASTRO_SUCESSO
import br.com.zup.simcitysaojoao.consts.PRODUTO_LISTA
import br.com.zup.simcitysaojoao.databinding.FragmentCadastroProdutosBinding
import br.com.zup.simcitysaojoao.models.Produto
import br.com.zup.simcitysaojoao.produtos.ProdutosActivity
import br.com.zup.simcitysaojoao.total.TotalProdutosActivity

class CadastroProdutosFragment : Fragment() {
    private var listaProdutos = arrayListOf<Produto>()
    private lateinit var binding: FragmentCadastroProdutosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCadastroProdutosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clicarBotao(binding.btnCadastrarProduto, this::cadastrarProduto)
        clicarBotao(binding.btnVerProdutos, this::acessarListaProduto)
        clicarBotao(binding.btnValorTotal, this::mostrarTotal)
    }

    private fun clicarBotao(
        button: Button,
        funcao: () -> Unit
    ) {
        button.setOnClickListener {
            funcao()
        }
    }

    private fun acessarListaProduto() {
        val bundle = bundleOf(PRODUTO_LISTA to listaProdutos)
        NavHostFragment.findNavController(this).navigate(
            R.id.action_cadastroProdutosFragment_to_produtosCadastradosFragment,
            bundle
        )
        limparInformacoes()
    }

    private fun cadastrarProduto() {
        val produto = verificarProdutos()
        if (produto != null) {
            listaProdutos.add(produto)
            toastCadastroSucesso()
            limparInformacoes()
        }
    }

    private fun verificarProdutos(): Produto? {
        val nomeProduto = binding.etNomeProduto.text.toString()
        val qtdProduto = binding.etQtdProduto.text.toString()
        val valorUnitario = binding.etValorUnitario.text.toString()
        val receita = binding.etReceita.text.toString()

        return if (
            nomeProduto.isNotBlank()
            && qtdProduto.isNotBlank()
            && valorUnitario.isNotBlank()
            && receita.isNotBlank()
        ) {
            Produto(nomeProduto, qtdProduto.toInt(), valorUnitario.toDouble(), receita)
        } else {
            mensagemErro(binding.etNomeProduto, nomeProduto, "Insira um produto!")
            mensagemErro(binding.etQtdProduto, qtdProduto, "Insira a quantidade de produtos!")
            mensagemErro(binding.etValorUnitario, valorUnitario, "Insira o valor do produto!")
            mensagemErro(binding.etReceita, receita, "Insira a receita do produto!")
            null
        }
    }

    private fun mostrarTotal() {
        val bundle = bundleOf(PRODUTO_LISTA to listaProdutos)
        val intent = Intent(context, TotalProdutosActivity::class.java).apply {
            putExtra(BUNDLE, bundle)
        }

        startActivity(intent)
    }

    private fun limparInformacoes() {
        binding.etNomeProduto.text.clear()
        binding.etQtdProduto.text.clear()
        binding.etValorUnitario.text.clear()
        binding.etReceita.text.clear()
    }

    private fun mensagemErro(editText: EditText, input: String?, msg: String) {
        editText.error = if (input.isNullOrBlank()) msg else null
    }

    private fun toastCadastroSucesso(){
        Toast.makeText(context, MENSAGEM_CADASTRO_SUCESSO, Toast.LENGTH_SHORT).show()
    }
}