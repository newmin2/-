import context.LaptopContext;

public class Main {
    public static void main(String[] args) {
        LaptopContext laptop = new LaptopContext();
        laptop.currentStatePrint();

        // Off -> On
        laptop.powerButtonPush();
        laptop.currentStatePrint();
        laptop.typeButtonPush();

        // On -> Saving
        laptop.setSavingState();
        laptop.currentStatePrint();
//        laptop.typeButtonPush(); //-> 노트북이 절전 모드인 상태입니다.

        // Saving -> On
        laptop.powerButtonPush();
        laptop.currentStatePrint();
        laptop.typeButtonPush();

        // On -> Off
        laptop.powerButtonPush();
        laptop.currentStatePrint();
//        laptop.typeButtonPush(); //-> 노트북이 전원 OFF인 상태 입니다.

        // Off-> Saving
//        laptop.setSavingState(); //-> 켜져 있는 상태에서만 절전모드를 사용할 수 있습니다.

    }
}
