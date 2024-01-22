import context.Champion;
import context.Garen;
import context.Vayne;
import strategy.AttackStrategy;
import strategy.MovingStrategy;

public class Battle {
    public static void main(String[] args) {
        Champion garen = new Garen("가렌");

        garen.setMovingStrategy(new MovingStrategy.RunningStrategy());
        garen.setAttackStrategy(new AttackStrategy.MeleeStrategy());

        garen.move();
        garen.attack();

        Champion vayne = new Vayne("베인");

        vayne.setMovingStrategy(new MovingStrategy.TumblingStrategy());
        vayne.setAttackStrategy(new AttackStrategy.RangedStrategy());

        vayne.move();
        vayne.attack();


    }
}
