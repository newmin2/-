package printer;

public class LazyPrinter {
    private static LazyPrinter printer;
    private LazyPrinter(){}

    /*
    Thread-Safe X
    쓰레드 A가 아직 객체가 생성되지 않았는데, 다른 쓰레드가 if분기문을 맞닥뜨리는 상황
     */
    public static LazyPrinter getPrinter(){
        if(printer==null){
            printer = new LazyPrinter();
        }
        return printer;
    }

}
