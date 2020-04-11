package bowling.domain;

import java.util.Objects;

public class Player {
    private final PlayerName playerName;

    public Player(final String name) {
        this.playerName = new PlayerName(name);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
