package strategy;

public interface AttackStrategy {
    void attack();

    /*
    아래는 concrete strategy
    setter에 parameter 넣어줄때 오히려 가시성이 좋아서 inner로 만들었다.
    */
    class MeleeStrategy implements AttackStrategy {

        @Override
        public void attack() {
            System.out.println("붕붕");
        }
    }

    class RangedStrategy implements AttackStrategy {

        @Override
        public void attack() {
            System.out.println("쇽쇽");
        }
    }
}
