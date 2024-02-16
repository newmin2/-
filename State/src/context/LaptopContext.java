package context;

import state.OffState;
import state.OnState;
import state.PowerState;
import state.SavingState;

public class LaptopContext {
    PowerState powerState;
    public LaptopContext(){
        this.powerState = OffState.getInstance();
    }
    public void changeState(PowerState state){
        this.powerState=state;
    }
    /*
    만약 off상태에서 savingstate를 바꾼다면? 에서 'if문으로 처리하는게 맞나?' 라는 혼란이 있었다.
    하지만 changeState와 setSavingState는 Context가 행위실행하는 그냥 메쏘드이므로
    if문 그냥 바로 사용했다!
    */
    public void setSavingState(){
        if(powerState == OnState.getInstance()){
            System.out.println("노트북 절전 모드");
            changeState(SavingState.getInstance());
        }else{
            throw new IllegalStateException("켜져 있는 상태에서만 절전모드를 사용할 수 있습니다.");
        }
    }
    public void powerButtonPush(){
        powerState.powerButtonPush(this);
    }
    public void typeButtonPush(){
        powerState.typeButtonPush();
    }
    public void currentStatePrint(){
        System.out.println(powerState.toString());
    }
}
