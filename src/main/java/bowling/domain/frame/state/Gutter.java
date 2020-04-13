package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class Gutter implements State {
    private static final String EXPRESSION = "-";

    private final Pins first;
    private final Pins second;

    Gutter(final Pins first, final Pins second) {
        this.first = first;
        this.second = second;
    }

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
        if (first.isGutter() && second.isGutter()) {
            return EXPRESSION + State.DELIMITER + EXPRESSION;
        }

        if (first.isGutter()) {
            return EXPRESSION + State.DELIMITER + second.count();
        }

        if (second.isGutter()) {
            return first.count() + State.DELIMITER + EXPRESSION;
        }
        return EXPRESSION;
    }
}
