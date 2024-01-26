package printer;

public class DoubleCheckedPrinter {
    private static volatile DoubleCheckedPrinter printer;
    private DoubleCheckedPrinter(){};
    public static DoubleCheckedPrinter getPrinter(){
        if(printer == null){
            //함수가 아닌 클래스 자체에다가 synchronized. 처음 만들어줄때만 호출한다.
            synchronized (DoubleCheckedPrinter.class){
                if(printer ==null){
                    printer = new DoubleCheckedPrinter();
                }
            }
        }
        return printer;
    }
}
