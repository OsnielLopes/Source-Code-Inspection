package br.calebe.ticketmachine.core;

import br.calebe.ticketmachine.core.Troco.TrocoIterator;
import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import java.util.Iterator;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class TicketMachine {

    protected int valor;
    protected int valorTotal;
    protected int saldo;
    protected int[] papelMoeda = {1,2, 5, 10, 20, 50, 100};

    public TicketMachine(int valor) {
        this.valor = valor;
        this.saldo = 0;
        this.valorTotal = 0;
    }
    // Alterei o indice o vetor papelMoeda, para o laço verificar todas as cédulas possiveis -----""-----
    // Após encontrar o valor da cédula, ele insere no saldo.
    public void inserir(int quantia) throws PapelMoedaInvalidaException {
        boolean achou;
        achou = false;
        for (int i = 0; i < papelMoeda.length && !achou; i++) {
            if (papelMoeda[i] == quantia) {
                achou = true;
                this.saldo += quantia;
            }
        }
        if (!achou) {
            throw new PapelMoedaInvalidaException();
        }
    }

    public int getSaldo() {
        return saldo;
    }
    
    //Retorna o total de venda no dia
    public int getValorTotal() {
        return valorTotal;
    }

    public Troco getTroco() {
        return new Troco(saldo);
    }

    public String imprimir() throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException();
        }
        String result = "*****************\n";
        result += "*** R$ " + saldo + ",00 ****\n";
        result += "*****************\n";
        saldo -= valor;
        //Após toda compra de ticket o software incrementa um valor no valorTotal
        valorTotal += valor;
        return result;
    }
}
