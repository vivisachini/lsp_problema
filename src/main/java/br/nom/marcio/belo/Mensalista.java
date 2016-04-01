package br.nom.marcio.belo;

import java.math.BigDecimal;

public class Mensalista extends Funcionario
{
    public Mensalista(String nome, BigDecimal salarioAnual)
    {
        super( nome, salarioAnual);
    }

    @Override
    public void reajustar(BigDecimal taxa)
    {
        validarTaxa( taxa);
    }

    private void validarTaxa(BigDecimal taxa)
    {
        if( isTaxaForaIntervalo(taxa) )
        {
            throw new IllegalArgumentException("Taxa deve estar no limite entre 0 e 10");
        }
    }

    private boolean isTaxaForaIntervalo(BigDecimal taxa) {
        return taxa.compareTo( BigDecimal.ZERO) <= 0 || taxa.compareTo( BigDecimal.TEN) > 0;
    }

    @Override
    public BigDecimal pagar() {
        return this.getSalarioAnual().divide( new BigDecimal("12") );
    }
}
