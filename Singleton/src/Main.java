import printer.*;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    실제 Thread-safe한지 확인하기 위해 만들었다.
    돌려보면 Lazy Initialization은 Thread-safe하지 않다는 것을 볼 수 있다.
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InterruptedException {
        //Reflection 연습겸 Class 객체 사용해서 map을 만들었다
        Map<Class<?>, Object[]> printerMap = new LinkedHashMap<>();
        printerMap.put(EagerPrinter.class, new EagerPrinter[10]);
        printerMap.put(StaticBlockPrinter.class, new StaticBlockPrinter[10]);
        printerMap.put(LazyPrinter.class, new LazyPrinter[10]);
        printerMap.put(DoubleCheckedPrinter.class, new DoubleCheckedPrinter[10]);
        printerMap.put(BillPughPrinter.class, new BillPughPrinter[10]);
        printerMap.put(ThreadSafePrinter.class, new ThreadSafePrinter[10]);
        printerMap.put(EnumPrinter.class, new EnumPrinter[10]);

        //쓰레드 풀 생성
        ExecutorService printService = Executors.newCachedThreadPool();

        //entryset으로 돌기
        for (Map.Entry<Class<?>, Object[]> e : printerMap.entrySet()) {

            Class<?> clazz = e.getKey();
            Method method = clazz.getMethod("getPrinter");
            Object[] printers = e.getValue();

            System.out.println(clazz.getSimpleName());

            for (int i = 0; i < 10; i++) {
                final int num = i;
                printService.submit(() -> {
                    try {
                        printers[num] = method.invoke(null);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        throw new RuntimeException(ex);
                    }
                });
            }
            //sleep을 하지 않으면 제대로 안 돌아간다..
            Thread.sleep(100);

            for (Object o : e.getValue()) {
                //enum배열은 각 요소의 주소값들을 참조할 수가 없어서 주소값 비교만 했다.
                if(o.equals(EnumPrinter.PRINTER)) {
                    System.out.println(EnumPrinter.getPrinter()==o);
                }else {
                    System.out.println(o);
                }
            }
            System.out.println('\n');
        }
        //쓰레드 풀 끄기
        printService.shutdown();
    }
}
