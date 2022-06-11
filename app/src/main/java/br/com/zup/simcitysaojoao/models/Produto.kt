package br.com.zup.simcitysaojoao.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Produto(
    private val nomeProduto: String,
    private val qtdProduto: Int,
    private val valorUnitario: Double,
    private val receita: String
): Parcelable {
    fun getNomeProduto() = nomeProduto
    fun getQtdProduto() = qtdProduto
    fun getValorUnitario() = valorUnitario
    fun getReceita() = receita
}