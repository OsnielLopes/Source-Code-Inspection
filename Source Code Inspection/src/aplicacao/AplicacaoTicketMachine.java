package aplicacao;

import br.calebe.ticketmachine.core.PapelMoeda;
import br.calebe.ticketmachine.core.TicketMachine;
import br.calebe.ticketmachine.core.Troco;
import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import java.awt.GridLayout;
import java.util.Iterator;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.*;
import javax.swing.JPanel;

/**
 *
 * @author Osniel Teixeira e Helder Gomes
 */
public class AplicacaoTicketMachine {

    public static void main(String[] args) {
        TicketMachine tm = new TicketMachine(3);

        while (true) {
            String[] opcoes = {"Inserir", "Emitir", "Troco"};

            JPanel panel = new JPanel();
            GridLayout gd = new GridLayout(2, 1);
            panel.setLayout(gd);
            panel.add(new JLabel("Valor do ticket:  R$" + tm.getValor() + ",00"));
            panel.add(new JLabel("Saldo atual:       R$" + tm.getSaldo() + ",00"));

            int result = showOptionDialog(null, panel, "Ticket Machine",
                    YES_NO_CANCEL_OPTION, PLAIN_MESSAGE, null, opcoes, null);

            switch (result) {
                case YES_OPTION:
                    while (true) {
                        int valor = Integer.parseInt(showInputDialog("Digite o valor que deseja inserir:"));
                        {
                            try {
                                tm.inserir(valor);
                                break;
                            } catch (PapelMoedaInvalidaException ex) {
                                showMessageDialog(null, "Valor inválido, insira uma nota por vez!");
                            }
                        }
                    }
                    break;
                case NO_OPTION:
                    try {
                        showMessageDialog(null, tm.imprimir());
                    } catch (SaldoInsuficienteException ex) {
                        showMessageDialog(null, "Saldo insuficiente!");
                    }
                    break;
                case CANCEL_OPTION:
                    Troco t = tm.getTroco();
                    Iterator<PapelMoeda> it = t.getIterator();
                    String notas = "Seu troco:";
                    while (it.hasNext()) {
                        PapelMoeda pm = it.next();
                        for (int i = 0; i < pm.getQuantidade(); i++) {
                            notas += "\n***********"
                                    + "\n* R$" + pm.getValor() + ",00 *"
                                    + "\n***********\n";
                        }
                    }
                    showMessageDialog(null, notas);
            }
        }
    }
}
