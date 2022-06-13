package br.com.zup.simcitysaojoao.detalheproduto

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.simcitysaojoao.R
import br.com.zup.simcitysaojoao.consts.*
import br.com.zup.simcitysaojoao.databinding.ActivityDetalheProdutoBinding
import br.com.zup.simcitysaojoao.models.Produto

class DetalheProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalheProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarAppBar()
        mostrarInformacoesProduto()
        favoritarProduto(binding.ivFavoritar)
    }

    private fun mostrarInformacoesProduto() {
        val bundle = intent.getBundleExtra(BUNDLE)
        val produto = bundle?.getParcelable<Produto>(PRODUTO)
        produto?.let {
            binding.tvNomeProduto.text = it.getNomeProduto()
            binding.tvQtdProduto.text =
                getString(R.string.quantidade_produto, it.getQtdProduto().toString())
            binding.tvValorUnitario.text =
                getString(R.string.valor_unitario, it.getValorUnitario().toString())
            binding.tvReceita.text = getString(R.string.receita, it.getReceita())
        }
    }

    private fun favoritarProduto(coracao: ImageView){
        coracao.setOnClickListener {
            Toast.makeText(this, MENSAGEM_FAVORITO_SUCESSO, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configurarAppBar() {
        supportActionBar?.title = TITULO_DETALHE_PRODUTO
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}