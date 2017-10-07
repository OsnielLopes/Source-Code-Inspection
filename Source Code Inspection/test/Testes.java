/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.calebe.ticketmachine.core.*;
import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import java.util.Iterator;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;


/**
 *
 * @author osniellopesteixeira
 */
public class Testes extends TestCase{
    
    public Testes() throws PapelMoedaInvalidaException, SaldoInsuficienteException {
        teste1();
        teste2();
        teste3();
    }
    
    public static void teste1() throws PapelMoedaInvalidaException, SaldoInsuficienteException{
        TicketMachine tm = new TicketMachine(2);
        tm.inserir(5);
        tm.imprimir();
        assertEquals(3,tm.getSaldo());
        assertEquals(2,tm.getValorTotal());
    }
    
    public static void teste2() throws PapelMoedaInvalidaException, SaldoInsuficienteException{
        TicketMachine tm = new TicketMachine(10);
        tm.inserir(20);
        tm.imprimir();
        assertEquals(10,tm.getValorTotal());
    }
    
    public static void teste3() throws PapelMoedaInvalidaException, SaldoInsuficienteException{
        TicketMachine tm = new TicketMachine(10);
        tm.inserir(20);
        tm.imprimir();
        Troco troco = tm.getTroco();
        Iterator<PapelMoeda> it = troco.getIterator();
        int trocoA = it.next().getQuantidade();
        assertEquals(0,0);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
}
