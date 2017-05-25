package com.ttn.linksharingbootcamp

import grails.gsp.PageRenderer
import grails.transaction.Transactional
import vo.PostVO
import vo.conversion.DomainToVO

@Transactional
class LinkSharingMailService {

    def mailService
    PageRenderer groovyPageRenderer

    def sendUnreadItemsMail() {

        List<User> userList = User.findAll()

        userList.each { User user ->

            List<PostVO> postVOList=DomainToVO.getUnreadreadingItems(user)
            if (postVOList) {

                def contents = groovyPageRenderer.render(template: '/mailTemplates/unreadPanel', model: [unreadResources: postVOList])

                runAsync {
                    mailService.sendMail {
                        to user.email
                        from "csi.online2016@gmail.com"
                        subject 'linksharing: invitation'
                        html contents
                    }

                }


            }

        }

    }

}
