package br.nom.marcio.belo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

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
        this.salarioAnual = this.salarioAnual.multiply( taxa.divide( new BigDecimal("100")
                , 2, BigDecimal.ROUND_HALF_UP).add( BigDecimal.ONE) );
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
        return this.getSalarioAnual().divide( new BigDecimal("12"), BigDecimal.ROUND_HALF_UP );
    }
}
