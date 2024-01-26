package printer;

public enum EnumPrinter {
    //근본적으로 enum상수 하나당 자신의 인스턴스를 만들어, public static final 필드로 공개한다.
    PRINTER;
    EnumPrinter(){
    }
    public static EnumPrinter getPrinter(){
        return PRINTER;
    }
}
