package bowling.domain.pin;

import bowling.exception.BowlCountOverThanPinsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pins {
    public static final Pins GUTTER_PINS = new Pins(Collections.emptyList());
    public static final int ZERO = 0;
    public static final int MAX_COUNT = 10;

    private final List<Pin> pins;

    private Pins(final List<Pin> pins) {
        this.pins = Collections.unmodifiableList(pins);
    }

    public static Pins of() {
        return new Pins(create());
    }

    public Pins knockOver(final BowlCount bowlCount) {
        checkKnockOver(bowlCount);
        return pins.stream()
                   .filter(Pin::isStanding)
                   .limit(bowlCount.count())
                   .map(Pin::knockOver)
                   .collect(Collectors.collectingAndThen(Collectors.toList(), Pins::new));
    }

    public long getKnockOverCount() {
        return pins.stream()
                   .filter(Pin::isKnockOver)
                   .count();
    }

    public long getStandingCount() {
        return pins.stream()
                   .filter(Pin::isStanding)
                   .count();
    }

    public boolean isStrike() {
        return getKnockOverCount() == MAX_COUNT;
    }

    public boolean isSpare() {
        return getKnockOverCount() == MAX_COUNT;
    }

    public boolean isGutter() {
        return getKnockOverCount() == ZERO;
    }

    public Pins add(final Pins knockOverPins) {
        checkOverCount(knockOverPins);
        List<Pin> all = new ArrayList<>();
        all.addAll(pins);
        all.addAll(knockOverPins.pins);
        return new Pins(all);
    }

    private void checkOverCount(final Pins knockOverPins) {
        int bowlCount = knockOverPins.count();
        if (pins.size() + knockOverPins.count() > MAX_COUNT) {
            throw new BowlCountOverThanPinsException(bowlCount);
        }
    }

    public int count() {
        return pins.size();
    }

    private static List<Pin> create() {
        return IntStream.range(ZERO, MAX_COUNT)
                        .mapToObj(count -> Pin.of())
                        .collect(Collectors.toList());
    }

    private void checkKnockOver(final BowlCount bowlCount) {
        if (bowlCount.isGreaterThan(getStandingCount())) {
            throw new BowlCountOverThanPinsException(bowlCount.count());
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pins pins1 = (Pins) o;
        return Objects.equals(pins, pins1.pins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
