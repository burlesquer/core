package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//InitializingBean : 객체를 생성한 이후에 의존 관계를 주입하기 때문에 생성자 DI를 제외한 DI는 객체를 생성할 때 의존관계가 주입되지 않는다. 고로, InitializingBean 사용

public class NetworkClient { //implements InitializingBean, DisposableBean

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }


//    //의존 관계 주입이 완료되면 실행
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    //빈이 종료될 때 호출
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }
//
//    //의존 관계 주입이 완료되면 실행
//    public void init() throws Exception {
//        System.out.println("NetworkClient.init");
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    //빈이 종료될 때 호출
//    public void close() throws Exception {
//        System.out.println("NetworkClient.close");
//        disconnect();
//    }

    //의존 관계 주입이 완료되면 실행
    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    //빈이 종료될 때 호출
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }

}
