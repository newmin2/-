package state;

import context.LaptopContext;

public class OnState implements PowerState{
    private OnState(){}
    private static class SingleInstanceHolder{
        private static final OnState instance = new OnState();
    }
    public static OnState getInstance(){
        return SingleInstanceHolder.instance;
    }
    @Override
    public void powerButtonPush(LaptopContext context) {
        System.out.println("노트북 전원 OFF");
        context.changeState(OffState.getInstance());
    }

    @Override
    public void typeButtonPush() {
        System.out.println("키 입력");
    }

    @Override
    public String toString(){
        return "노트북 전원 ON인 상태입니다.";
    }
}
