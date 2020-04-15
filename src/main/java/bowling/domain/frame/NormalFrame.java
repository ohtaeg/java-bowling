package bowling.domain.frame;

import bowling.domain.frame.state.Ready;
import bowling.domain.frame.state.State;
import bowling.domain.pin.Pins;

import java.util.Objects;
import java.util.Optional;

public class NormalFrame implements Frame {
    private final FrameNumber frameNumber;
    private final Frame nextFrame;
    //private States states;
    private State state;

    public NormalFrame(final FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.state = new Ready();
        //this.states = new States(Collections.emptyList());
        this.nextFrame = next();
    }

    private Frame next() {
        final FrameNumber nextFrameNumber = frameNumber.increase();
        if (nextFrameNumber.isFinal()) {
            return new FinalFrame(nextFrameNumber);
        }
        return new NormalFrame(nextFrameNumber);
    }

    @Override
    public Frame bowl(final Pins pins) {
        state = state.roll(pins);
        //states.add(state);
        if (!isEnd()) {
            return this;
        }
        return nextFrame;
    }

    @Override
    public boolean isEnd() {
        return state.isTurnOver();
    }

    @Override
    public Optional<Frame> getNext() {
        return Optional.of(nextFrame);
    }

    @Override
    public FrameNumber getFrameNumber() {
        return frameNumber;
    }

    @Override
    public String getState() {
        return state.toResult();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final NormalFrame normalFrame = (NormalFrame) o;
        return Objects.equals(frameNumber, normalFrame.frameNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
