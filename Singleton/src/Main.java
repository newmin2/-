import printer.*;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    실제 Thread-safe한지 확인하기 위해 만들었다.
    돌려보면 Lazy Initialization은 Thread-safe하지 않다는 것을 볼 수 있다.
 */
public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InterruptedException, ExecutionException {
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
            /*
            sleep을 하지 않으면 제대로 안 돌아간다..
            -> shutdown()은 non-blocking 이었다... Future의 get()이 blocking 역할을 해줌.
            -> Thread.sleep은 메인쓰레드 진행을 멈춰서 자식쓰레드들이 다 돌았기 때문에 에러가 안난거라고 예상
            -> 근데 get()을 붙히면 blocking을 해줘서 LazyPrinter가 계속 thread-safe처럼 나온다...
            -> 때문에 그냥 thread.sleep을 써서 LazyPrinter가 thread-safe하지 않은 모습을 봐야겠다.
            */
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
