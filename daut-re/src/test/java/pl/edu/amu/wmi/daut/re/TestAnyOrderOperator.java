package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.NaiveAutomatonSpecification;
import pl.edu.amu.wmi.daut.base.State;
import pl.edu.amu.wmi.daut.base.CharTransitionLabel;
import junit.framework.TestCase;

    /**
    * Test klasy AnyOrderOperator.
    */

public class TestAnyOrderOperator extends TestCase {

    /**
    * Test metody createAutomatonFromTwoAutomaton
    **/

    public final void testCreateAutomatonFromTwoAutomata() {

        AutomatonSpecification automaton1 = new NaiveAutomatonSpecification();

        State q0 = automaton1.addState();
        State q1 = automaton1.addState();
        automaton1.addTransition(q0, q1, new CharTransitionLabel('ala'));

        automaton1.markAsInitial(q0);
        automaton1.markAsFinal(q1);



        AutomatonSpecification automaton2 = new NaiveAutomatonSpecification();

        State q2 = automaton2.addState();
        State q3 = automaton2.addState();
        automaton2.addTransition(q2, q3, new CharTransitionLabel('kot'));

        automaton2.markAsInitial(q2);
        automaton2.markAsFinal(q3);

        AnyOrderOperator operator = new AnyOrderOperator();
        AutomatonSpecification result =
        new NaiveAutomatonSpecification(
                operator.createAutomatonFromTwoAutomata(automaton1, automaton2));

        assertTrue(result.accepts("alakot"));
        assertTrue(result.accepts("kotala"));
        assertFalse(result.accepts("kot"));
        assertFalse(result.accepts("ala"));
        assertFalse(result.accepts(""));
    }
}