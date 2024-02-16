package state;

import context.LaptopContext;

public class OffState implements PowerState{
    private OffState(){

    }
    private static class SingleInstanceHolder{
        private static final OffState instance = new OffState();
    }
    public static OffState getInstance(){
        return SingleInstanceHolder.instance;
    }
    @Override
    public void powerButtonPush(LaptopContext context) {
        System.out.println("노트북 전원 ON");
        context.changeState(OnState.getInstance());
    }

    @Override
    public void typeButtonPush() {
        throw new IllegalStateException(toString());
    }

    @Override
    public String toString(){
        return "노트북이 전원 OFF인 상태 입니다.";
    }
}
