package com.fx.shiro.filter.online;

import com.fx.shiro.Constants;
import com.fx.shiro.session.OnlineSession;
import com.fx.shiro.session.mgt.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by bei2love@gmail.com on 15/9/22.
 */
public class OnlineSessionFilter  extends AccessControlFilter {

    /**
     * 强制退出后重定向的地址
     */
    private String forceLogoutUrl;

    private EnterpriseCacheSessionDAO onlineSessionDAO;

    public String getForceLogoutUrl() {
        return forceLogoutUrl;
    }

    public void setForceLogoutUrl(String forceLogoutUrl) {
        this.forceLogoutUrl = forceLogoutUrl;
    }

    public void setOnlineSessionDAO(EnterpriseCacheSessionDAO onlineSessionDAO) {
        this.onlineSessionDAO = onlineSessionDAO;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null || subject.getSession() == null) {
            return true;
        }
        Session session = onlineSessionDAO.readSession(subject.getSession().getId());
        if (session != null && session instanceof OnlineSession) {
            OnlineSession onlineSession = (OnlineSession) session;
            request.setAttribute(Constants.ONLINE_SESSION, onlineSession);
            //把user id设置进去
//            boolean isGuest = onlineSession.getUserId() == null || onlineSession.getUserId() == 0L;
//            if (isGuest == true) {
//                User user = (User) request.getAttribute(Constants.CURRENT_USER);
//                if (user != null) {
//                    onlineSession.setUserId(user.getId());
//                    onlineSession.setUsername(user.getUsername());
//                    onlineSession.markAttributeChanged();
//                }
//            }

            if (onlineSession.getStatus() == OnlineSession.OnlineStatus.force_logout) {
                return false;
            }
        }
        return true;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject != null) {
            subject.logout();
        }
        saveRequestAndRedirectToLogin(request, response);
        return true;
    }


    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        WebUtils.issueRedirect(request, response, getForceLogoutUrl());
    }

}
