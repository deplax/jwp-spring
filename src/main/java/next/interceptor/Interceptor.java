package next.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {
	private static final Logger log = LoggerFactory.getLogger(Interceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("t", System.currentTimeMillis());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long s = (long) request.getAttribute("t");
		long c = System.currentTimeMillis();
		super.afterCompletion(request, response, handler, ex);
		
		log.debug("{} | time : {} millisecond",request.getRequestURI(), c-s);
	}
}

// 1. preHandle - controller 이벤트 호출전
// 2. postHandle - controller 호출 후 view 페이지 출력전
// 3. afterCompletion - controller + view 페이지 모두 출력 후
// 리턴값이 true가 아닐경우 해당 컨트롤러로 이어주지 않는다.