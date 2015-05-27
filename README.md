### 2. 서버가 시작하는 시점에 부모 ApplicationContext와 자식 ApplicationContext가 초기화되는 과정에 대해 구체적으로 설명해라.

# 2번
- 먼저 컨텍스트 부터 정리하자. 하나의 application이 실행되기 위해서는 그 밑바탕에 깔려져야 하는 많은 정보들과 환경들이 필요하다. application 실행을 위해 밑바탕에 깔려져 있는 정보들과 환경들을 Context라 한다.
- 내부에서 쓰이는 빈들을 생성하기 위해 빈 팩토리를 만든다.
- 빈 팩토리들은 등록된 bean들 중에 BeanFactoryPostProcessor인 것을 찾아 생성하고 실행한다.
- 생성 순서는 XML을 따르며 beanFactory에 등록한다.
- 등록된 빈들 중에 특별한 일을 하는 애들을 따로 관리한다.(messageSource, applicationEventMulticaster, 등등 별도 관리)
- 빈들의 의존관계를 정리하여 의존성 정보를 등록하고 주입한다.
- 빈 타입 중에 ApplicationListener인 녀석을 찾아 생성 후 리스너로 등록한다.
- 초기화를 완료하고 초기화 완료 메세지를 보낸다.


### 3. 서버 시작 후 http://localhost:8080으로 접근해서 질문 목록이 보이기까지 흐름에 대해 최대한 구체적으로 설명하라. 

# 3번
- http://localhost:8080으로 접근하면 요청이 발생한다. 여기에서부터 시작하자.
- 요청(Request)은 DispatcherServlet을 통해 컨트롤러에게 요청을 전달한다.
- 위의 과정에서 HandlerMapper가 이전에 미리 읽어서 저장해둔 정보를 가지고 어떤 컨트롤러가 처리할지 결정해 준다.
- 컨트롤러는 필요한 로직을 처리하고 처리 결과를 어떻게 보여줄지 ViewResolver를 통해 처리한다.
- 처리된 결과는 View를 통해 보여주며 클라이언트에게 응답을 생성한다.



### 9. UserService와 QnaService 중 multi thread에서 문제가 발생할 가능성이 있는 소스는 무엇이며, 그 이유는 무엇인가?

# 9번
- userService이다.
- 기본이 싱글톤인데 그 부분이 문제가 된다. 프로토타입으로 바꾸면 된다.