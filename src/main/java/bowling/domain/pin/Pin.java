package bowling.domain.pin;

import java.util.Objects;

public class Pin {
    private final PinState pinState;

    private Pin(final PinState pinState) {
        this.pinState = pinState;
    }

    public static Pin of() {
        return new Pin(PinState.STANDING);
    }

    public Pin knockOver() {
        return new Pin(PinState.KNOCK_OVER);
    }

    public boolean isKnockOver() {
        return pinState.equals(PinState.KNOCK_OVER);
    }

    public boolean isStanding() {
        return pinState.equals(PinState.STANDING);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pin pin = (Pin) o;
        return pinState == pin.pinState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pinState);
    }
}
