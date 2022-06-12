package br.com.zup.simcitysaojoao.total

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.os.bundleOf
import br.com.zup.simcitysaojoao.R
import br.com.zup.simcitysaojoao.consts.*
import br.com.zup.simcitysaojoao.databinding.ActivityTotalProdutosBinding
import br.com.zup.simcitysaojoao.models.Produto
import br.com.zup.simcitysaojoao.produtos.ProdutosActivity

class TotalProdutosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTotalProdutosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTotalProdutosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configurarAppBar()

        val bundle = intent.getBundleExtra(BUNDLE)
        val listaProdutos = bundle?.getParcelableArrayList<Produto>(PRODUTO_LISTA)

        listaProdutos?.let {
           verValorTotal(listaProdutos)
        }

        binding.btnNovoCadastro.setOnClickListener {
            onBackPressed()
        }

        binding.btnVerProdutos.setOnClickListener {
            irParaVerProdutos(listaProdutos)
        }
    }

    private fun configurarAppBar() {
        supportActionBar?.title = VALOR_TOTAL
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun formatarTotal(total: Double) = "%.2f".format(total)

    private fun irParaVerProdutos(listaProdutos: ArrayList<Produto>?){
        val bundleLista = bundleOf(PRODUTO_LISTA to listaProdutos, "IR_PARA_FRAGMENT_LISTA" to true)
        val intent = Intent(this, ProdutosActivity::class.java).apply {
            putExtra(BUNDLE, bundleLista)
        }
        startActivity(intent)
    }

    private fun verValorTotal(listaProdutos: ArrayList<Produto>?){
        var total = 0.0
        listaProdutos?.forEach { produto ->
            total += produto.getQtdProduto() * produto.getValorUnitario()
        }
        binding.tvValorTotal.text = getString(R.string.valor_total, formatarTotal(total))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}