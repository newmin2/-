package printer;

public class BillPughPrinter {
    private BillPughPrinter(){}
    //내부클래스가 static일때, 싱글톤 클래스가 초기화할때 메모리에 로드되지 않음
    private static class BillPughHolder{
        // final로 다시 값이 할당되지 않게 방지
        private static final BillPughPrinter printer = new BillPughPrinter();
    }
    public static BillPughPrinter getPrinter(){
        return BillPughHolder.printer;
    }

}
