package state;

import context.LaptopContext;

public class SavingState implements PowerState{
    private SavingState(){

    }
    private static class SingleInstanceHolder{
        private static final SavingState instance = new SavingState();
    }
    public static SavingState getInstance(){
        return SingleInstanceHolder.instance;
    }
    @Override
    public void powerButtonPush(LaptopContext context) {
        System.out.println("노트북 전원 On");
        context.changeState(OnState.getInstance());
    }

    @Override
    public void typeButtonPush() {
        throw new IllegalStateException(toString());
    }
    @Override
    public String toString(){
        return "노트북이 절전 모드인 상태입니다.";
    }
}
