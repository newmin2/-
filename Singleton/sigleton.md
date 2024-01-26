# 싱글톤 패턴(Singleton Pattern)
> - 단 하나의 **유일한** 객체를 만들기 위한 디자인 패턴
> - **리소스를 많이 차지하는 역할**을 하는 클래스일때 적합 
> - _EX)데이터베이스 커넥션 풀, 스레드풀_

<hr>

## 싱글톤 패턴 구현 기법

### 1. Eager Intialization
프로젝트에선 EagerPrinter 클래스에 해당한다.
> - 한번만 만들어두는 직관적이고 심플한 기법
> - static final &rarr; thread-Safe  
> - !! static 멤버는 당장 객체를 사용하지 않더라도 메모리에 적재하기 떄문에 만일 리소스가 큰 객체일 경우, 공간 자원낭비가 발생함  
> - !! 예외 처리를 할 수 없음  
    &rarr; _2. Static Block Intialization_

    ! 만일 싱글톤을 적용한 객체가 그리 크지 않은 객체라면 이 기법으로 적용해도 무리는 없다.
    
### 2. Static Block Intialization
프로젝트에선 StaticBlockPrinter 클래스에 해당한다.
> - static block으로 예외 처리가 가능  
>   -   static block은 클래스가 로딩되고 **클래스 변수가 준비된 후에** 실행되는 블록
> - !! 그러나 *1.Eager Intialization* 과 마찬가지로 static에 의한 리소스 낭비  
    &rarr; _초기화를 필요할때 하자! 3. Lazy Initializatio0n_

### 3. Lazy Initialization
프로젝트에선 LazyPrinter 클래스에 해당한다.
> - 메소드를 호출했을 때 null의 유무로 초기화하거나 반환 &rarr; 미사용 고정 메모리 차지의 문제점 해결
> - !! 그러나 **Thread-Safe X** &larr; if문 분기가 끝나지 않았을 때 발생가능

### 4. Thread Safe Intialization
프로젝트에선 ThreadSafePrinter 클래스에 해당한다.
> - `synchronzied`를 통해 쓰레드들을 하나씩 접근하게 설정
> - !! 그러나 매번 동기화 처리화하는 작업에 overhead 발생으로 **성능 하락 우려**  
    &rarr; _그렇다면 최초 초기화할때만 적용하자! 5. Double-Checked Locking_

### 5. Double-Checked Locking
프로젝트에선 DoubleCheckedPrinter에 해당한다.
> - `volatile`키워드 사용
>   - **여러 개의 쓰레드**를 사용할 땐, 쓰레드들이 **변수를 캐시메모리에서** 가져옴
>   - 그러나 각 쓰레드마다 **할당되어있는 캐시메모리의 변수값이 불일치**할 가능성 존재
>   - **`volatile`은 메인메모리에서** 값을 읽어오도록 만듦
> - 최초 초기화할때만 적용하도록 메소드가 아닌 클래스 자체에 `synchronized`를 통해 동기화함
> - _그러나 JVM의 버전에 따라 결과가 다르고, 이해도가 필요하기에 사용하기를 지양한다고 한다.._

### 6. Bill Pugh Solution(LazyHolder)
프로젝트에서 BillPughPrinter에 해당한다.
> - **static 내부 클래스(holder pattern)** 를 이용하여 바로 클래스가 메모리에 로드되지 않고, getPrinter()가 호출되어야 로드됨  
  &rarr; Lazy Loading, Thread-safe, 메모리 누수 방지
> - _다만 클라이언트가 임의로 싱글톤은 해체할 수 있다고 한다..(Reflection, 직렬화/역직렬화)_

### 7. Enum을 이용한 싱글톤
프로젝트에선 EnumPrinter에 해당한다.
> - enum 자체가 멤버를 만들때 private으로 만들고 한번만 초기화함 &rarr; Thread-safe
> - 변수, 메서드 선언하여 사용이 가능하기 때문에 싱글톤 클래스처럼 응용 가능
> - Reflection을 통한 공격에도 안전
> - _!! 한 번 짜고 수정하려면 처음부터 다시 짜야함_
> - enum은 클래스 상속이 불가능하다

<hr> 

#### 싱글톤의 문제점
1. 하나의 싱글톤 클래스를 여러 모듈이 공유 &rarr; 강한 의존성, 높은 결합도
2. 하나의 인스턴스가 여러 책임을 가짐(SRP 위배), 높은 결합도(OCP 위배), 추상화가 아닌 구현된 클래스에 의존(DIP 위배)
3. 단위 테스트는 서로 독립적이어야 하는데, 싱글톤 인스턴스는 자원을 공유하기에 매번 인스턴스의 상태를 초기화해야한다.

         스프링에선 IoC컨테이너가 관리하기 때문에, 평범한 객체도 하나의 인스턴스인 싱글톤으로 존재하기에 싱글톤 단점이 없다.


<hr>

참고한 책 : JAVA 객체 지향 디자인 패턴(정인상, 채홍석 저)  
참고한 블로그 : https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EC%8B%B1%EA%B8%80%ED%86%A4Singleton-%ED%8C%A8%ED%84%B4-%EA%BC%BC%EA%BC%BC%ED%95%98%EA%B2%8C-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90#eager_initialization