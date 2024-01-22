package context;

import strategy.AttackStrategy;
import strategy.MovingStrategy;

public class Champion {
    private MovingStrategy movingStrategy;
    private AttackStrategy attackStrategy;

    public Champion(String name) {
        System.out.println("나는 "+name);
    }

    public void move() {
        movingStrategy.move();
    }

    public void attack() {
        attackStrategy.attack();
    }

    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
}
