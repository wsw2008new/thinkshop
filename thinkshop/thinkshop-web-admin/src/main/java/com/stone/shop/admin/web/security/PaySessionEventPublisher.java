package com.stone.shop.admin.web.security;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.stone.shop.admin.service.manage.role.IOperatorService;
import com.stone.shop.domain.manage.role.OperatorEntity;

public class PaySessionEventPublisher extends HttpSessionEventPublisher
        implements ApplicationListener<ApplicationEvent> {

    /**
     * HTTP Session 属性 最后一次操作时的IP
     */
    private static final String SESSION_ATTR_LAST_IP = "session.lastIp";
    /**
     * HTTP Session 属性 最后一次操作时间
     */
    private static final String SESSION_ATTR_LAST_TIME = "session.lastTime";


    @Autowired
    private IOperatorService service;

    private static HttpSession session;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        session = event.getSession();
        super.sessionCreated(event);

    }

    @Override
	public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof AuthenticationSuccessEvent) {
            AuthenticationSuccessEvent successEvent = (AuthenticationSuccessEvent) event;
            Authentication auth = successEvent.getAuthentication();
            WebAuthenticationDetails detalis = (WebAuthenticationDetails) auth.getDetails();

            String lastIp = detalis.getRemoteAddress();
            UserInfo user = (UserInfo) auth.getPrincipal();
            OperatorEntity operator = user.getOperator();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
            if (session != null) {
//            	try {
//					Date cdate = sdf.parse(operator.getLastTime());
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
                session.setAttribute(SESSION_ATTR_LAST_IP, operator.getLastIp());
                session.setAttribute(SESSION_ATTR_LAST_TIME, operator.getLastTime());
            }
            operator.setLastIp(lastIp);
            operator.setLastTime(sdf.format(new Date()));
            service.modifyOperator(operator);
        }
    }

}
