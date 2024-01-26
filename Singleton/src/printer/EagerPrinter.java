package printer;

public class EagerPrinter {
    private static final EagerPrinter printer = new EagerPrinter();
    private EagerPrinter(){}
    public static EagerPrinter getPrinter(){
        return printer;
    }
}
