package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Spare implements State {
    private static final String EXPRESSION = "/";

    @Override
    public State roll(final Pins knockOverPins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.TRUE;
    }

    @Override
    public String toResult() {
        return EXPRESSION;
    }
}
