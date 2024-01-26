package printer;

public class StaticBlockPrinter{
    private static StaticBlockPrinter printer;
    private StaticBlockPrinter(){}
    //static block을 통해 EagerPrinter의 단점 해소(예외 처리 불가)
    static {
        try{
            printer = new StaticBlockPrinter();
        }catch (Exception e){
            throw new RuntimeException("예외 처리 가능");
        }
    }
    public static StaticBlockPrinter getPrinter(){
        return printer;
    }

}
