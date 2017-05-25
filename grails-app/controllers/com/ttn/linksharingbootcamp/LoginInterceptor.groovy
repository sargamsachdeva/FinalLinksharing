package com.ttn.linksharingbootcamp


class LoginInterceptor {


    LoginInterceptor() {
        matchAll().excludes(controller: "login")
    }

    boolean before() {
        if (!session.user) {
            log.info("redirecting")
            redirect(controller: 'login', action: 'index')
            return false
        }
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }

}
