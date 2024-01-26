package printer;

public class ThreadSafePrinter {
    private static ThreadSafePrinter printer;
    private ThreadSafePrinter(){}
    //매번 동기화 처리하기 때문에 overhead 발생
    public static synchronized ThreadSafePrinter getPrinter(){
        if(printer==null){
            printer = new ThreadSafePrinter();
        }
        return printer;
    }
}
