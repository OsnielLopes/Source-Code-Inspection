package br.calebe.ticketmachine.core;

import java.util.Iterator;
/**
 *
 * @author Calebe de Paula Bianchini
 */
public class Troco {

    protected PapelMoeda[] papeisMoeda;

    public Troco(int valor) {
        papeisMoeda = new PapelMoeda[7];
        papeisMoeda[6] = new PapelMoeda(1,0);
        papeisMoeda[5] = new PapelMoeda(2,0);
        papeisMoeda[4] = new PapelMoeda(5,0);
        papeisMoeda[3] = new PapelMoeda(10,0);
        papeisMoeda[2] = new PapelMoeda(20,0);
        papeisMoeda[1] = new PapelMoeda(50,0);
        papeisMoeda[0] = new PapelMoeda(100,0);
        for (PapelMoeda papelMoeda : papeisMoeda) {
            int quantia = valor % papelMoeda.valor;
            papelMoeda.quantidade = quantia;
            valor -= quantia + papelMoeda.valor;
        }
    }

    public Iterator<PapelMoeda> getIterator() {
        return new TrocoIterator(this);
    }

    class TrocoIterator implements Iterator<PapelMoeda> {

        protected Troco troco;
        protected int indice;

        public TrocoIterator(Troco troco) {
            this.troco = troco;
            this.indice = 0;
        }

        @Override
        public boolean hasNext() {
            return indice < papeisMoeda.length;
        }

        @Override
        public PapelMoeda next() {
            PapelMoeda ret = null;
            if (indice < papeisMoeda.length) {
                ret = troco.papeisMoeda[indice];
                troco.papeisMoeda[indice] = null;
                indice++;
            }
            return ret;
        }

        @Override
        public void remove() {
            if (this.indice < papeisMoeda.length) {
                indice += 1;
            }
        }
    }
}
