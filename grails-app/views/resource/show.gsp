<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main">
    <title>Post</title>
</head>

<body>
<div class="row">
    <div class="col-md-7">
        <div class="panel panel-default panelTrendingTopics">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-3">
                        <ls:userImage userId="${post.userID}" class="img img-responsive img-thumbnail" height="75px"
                                      width="75px"/>
                    </div>

                    <div class="col-md-9">
                        <div class="row">
                            <div class="col-md-6">
                                <span class="text-primary">${post.userFirstName} ${post.userLastName}</span>
                            </div>

                            <div class="col-md-4 col-md-offset-1 topicName">
                                <g:link controller="topic" action="show" params="[id: post.topicID]"
                                        title="${post.topicName}" data-toggle="tooltip" data-placement="right">
                                    <span class="text-primary" style="width:inherit;"><ins>${post.topicName}</ins>
                                    </span>
                                </g:link>

                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <span class="text-muted">@${post.userName}</span>
                            </div>

                            <div class="col-md-4 col-md-offset-1">
                                <span class="text-muted"><g:formatDate format="dd-MMM-yyyy hh:mm"
                                                                       date="${post.postDate}"/></span>

                            </div>
                        </div>
                        <g:if test="${session.user}">
                            <div class="row">
                                <div class="col-md-10 col-md-offset-7">
                                    <div id="rateYo"></div>
                                    <input type="hidden" id="default-hidden-resource-rating"
                                           value="${post?.resourceRating}"/>
                                    <input type="hidden" id="hidden-user-id" value="${session.user?.id}"/>
                                    <input type="hidden" id="hidden-resource-id" value="${post?.resourceID}"/>

                                    <g:each in="${1..post?.resourceRating}" var="rating">
                                        <g:link controller="resourceRating" action="save"
                                                class="glyphicon glyphicon-star"

                                                params="[id: post.resourceID, score: rating]"/>
                                    </g:each>

                                    <g:each in="${(post?.resourceRating+1)..<6}" var="rating">
                                        <g:link controller="resourceRating" action="save"
                                                class="glyphicon glyphicon-star-empty"

                                                params="[id: post.resourceID, score: rating]"/>
                                    </g:each>


                                </div>
                            </div>

                        </g:if>
                    </div>
                </div>
            </div>

            <div class="panel-body">
                <div class="row">
                    <p class="text-justify">${post.description}</p>
                </div>

                <div class="row">

                    <div class="col-sm-8 pull-right">
                        <ul class="list-inline text-right">
                            <li><ls:canDeleteResource resourceID="${post.resourceID}"/></li>
                            <li><ls:editResourceDetails resourceId="${post.resourceID}"/></li>
                            <li><ls:resourceType resourceID="${post.resourceID}" url="${post.url}"
                                                 filePath="${post.filePath}"/></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="col-md-5">
        <g:render template="/templates/trendingTopics" model="[trendingTopics: trendingTopics]"/>
    </div>
</div>
</body>
</html>
