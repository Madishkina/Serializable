import java.io.Serializable;

public class GameProgress implements Serializable {//Интерфейс Serializable не определяет никаких методов, просто служит указателем системе, что объект, реализующий его, может быть сериализован
    private static final long serialVersionUID = 1L;//Константа serialVersionUID - уникальный идентификатор версии сериализованного класса

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}
