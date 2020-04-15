package bowling.domain.frame.state;

import bowling.domain.pin.Pins;

public class FirstBowl implements State {

    private final Pins first;

    FirstBowl(Pins first) {
        this.first = first;
    }

    @Override
    public State roll(final Pins second) {
        Pins knockOverPins = first.add(second);

        if (knockOverPins.isSpare()) {
            return new Spare();
        }

        if (second.isGutter()) {
            return new SecondGutter();
        }

        return new Miss(second);
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.FALSE;
    }

    @Override
    public String toResult() {
        return String.valueOf(first.count());
    }
}
