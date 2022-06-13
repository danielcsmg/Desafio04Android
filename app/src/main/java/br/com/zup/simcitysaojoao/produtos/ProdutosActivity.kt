package br.com.zup.simcitysaojoao.produtos

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.zup.simcitysaojoao.R
import br.com.zup.simcitysaojoao.consts.BUNDLE
import br.com.zup.simcitysaojoao.consts.PRODUTOS
import br.com.zup.simcitysaojoao.databinding.ActivityProdutosBinding

class ProdutosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProdutosBinding
    private var irParaFragmentListaProdutos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configurarAppBar()

        val bundle = intent.getBundleExtra(BUNDLE)
        irParaFragmentListaProdutos = controleIrParaProdutosCadastrados(bundle)
        val nav = supportFragmentManager
            .findFragmentById(binding.navHostFragment.id) as NavHostFragment

        navegarParaFragmentProdutosCadastrados(
            irParaFragmentListaProdutos,
            nav,
            bundle
        )
    }

    private fun configurarAppBar() {
        supportActionBar?.title = PRODUTOS
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun navegarParaFragmentProdutosCadastrados(irParaFragment: Boolean, nav: NavHostFragment, bundle: Bundle?) {
        if (irParaFragment) {
            nav.findNavController()
                .navigate(
                    R.id.action_cadastroProdutosFragment_to_produtosCadastradosFragment,
                    bundle
                )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if(irParaFragmentListaProdutos){
                finish()
            }else{
                onBackPressed()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun controleIrParaProdutosCadastrados(bundle: Bundle?): Boolean{
        return bundle?.getBoolean("IR_PARA_FRAGMENT_LISTA") ?: false
    }
}