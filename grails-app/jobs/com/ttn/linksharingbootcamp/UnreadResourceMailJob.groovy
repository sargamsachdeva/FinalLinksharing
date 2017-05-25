package com.ttn.linksharingbootcamp

class  UnreadResourceMailJob{

    def linkSharingMailService
    static triggers = {
        cron name: 'myTrigger', cronExpression: "0 0/2 * 1/1 * ? *"

    }

    def execute() {
        // execute job

        log.info("running UnreadResourceMailJob at ${new Date()}!")
       linkSharingMailService.sendUnreadItemsMail()
    }
}
