package pl.edu.amu.wmi.daut.re;

import pl.edu.amu.wmi.daut.base.AutomatonSpecification;
import pl.edu.amu.wmi.daut.base.EpsilonTransitionLabel;
import pl.edu.amu.wmi.daut.base.State;

/**
* Klasa reprezentującą operator '{n}' z wyrażeń regularnych.
*/
public abstract class FixedNumberOfOccurrencesOperator extends UnaryRegexpOperator {

    private int n;

    /**
     * Konstruktor klasy.
     */
    public FixedNumberOfOccurrencesOperator(int a) {
        this.n = a;
    }

    /**
     * Główna metoda klasy.
     */
    public AutomatonSpecification createAutomatonFromOneAutomaton(
            AutomatonSpecification subautomaton) {

        AutomatonSpecification automatbudowany = subautomaton.clone();
        AutomatonSpecification automatpom1 = subautomaton.clone();
        automatbudowany.addTransition(automatbudowany.getInitialState(),
                            automatpom1.getInitialState(),
                            new EpsilonTransitionLabel());

        for (State state : automatbudowany.allStates()) {
            if (automatbudowany.isFinal(state)) {
                automatbudowany.unmarkAsFinalState(state);
            }
        }

        for (int i = 1; i < this.n; i++) {

            for (State state : automatpom1.allStates()) {
                if (automatpom1.isFinal(state)) {
                    AutomatonSpecification automatdod = subautomaton.clone();
                    automatpom1.addTransition(state,
                    automatdod.getInitialState(),
                    new EpsilonTransitionLabel());
                    automatpom1.unmarkAsFinalState(state);
                }
            }
        }
        return automatbudowany;
    }
}
