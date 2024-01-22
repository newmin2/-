package strategy;

import context.Champion;

public interface MovingStrategy {
    void move();
    /*
    아래는 concrete strategy
    setter에 parameter 넣어줄때 오히려 가시성이 좋아서 inner로 만들었다.
    */
    class RunningStrategy implements MovingStrategy {

        @Override
        public void move() {
            System.out.println("뚜벅뚜벅");
        }
    }
    class TumblingStrategy implements MovingStrategy {

        @Override
        public void move() {
            System.out.println("데굴데굴");
        }
    }
}
