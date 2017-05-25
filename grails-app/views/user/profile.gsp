

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Profile</title>
</head>

<body>

<div class="row">
    <div class="col-md-4">
        <div class="row" style="padding: 10px 0;border: 1px solid #000  ; border-radius: 7px;border-width: 3px; margin: 0">
            <g:render template="/templates/userPanel" model="[userDetails: userDetails]"/>
            <g:hiddenField name="id" value="${userDetails.id}"/>
        </div>
        <br>

        <div class="row">
            <div class="panel panel-default panel-primary panelTrendingTopics">
                <div class="panel-heading">
                    Topics
                </div>

                <div style="overflow-y:scroll; height: 300px;">
                    <g:each in="${userTopics}">
                        <g:render template="/templates/topicPanel" model="[topic:it]"/>
                    </g:each>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="panel panel-default panel-primary panelTrendingTopics">
                <div class="panel-heading">
                    Subscriptions
                </div>

                <div id="subscribedTopics" style="overflow-y:scroll; height: 300px;">
                    <g:render template="/topic/list"/>
                </div>

            </div>
        </div>
    </div>

    <div class="col-md-7 col-md-offset-1">
        <div class="row">
            <div class="panel panel-default panel-primary panelTrendingTopics">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-md-3">
                            Posts
                        </div>

                        <div class="col-md-6 col-md-offset-3">
                            <g:form name="globalSearchForm" class="navbar-form form-inline" method="get"
                                    controller="resource" action="search">
                                <div class="form-group">
                                    <label class="sr-only" for="globalSearchBox">Search</label>

                                    <div class="input-group">
                                        <div class="input-group-addon globalSearchBtn"><span class="glyphicon glyphicon-search"
                                                                                             style="font-size: large"></span>
                                        </div>
                                        <g:textField name="q" type="text" class="form-control globalSearchBox"
                                                     id="globalSearchBox" placeholder="Search"/>
                                        <g:hiddenField name="global" value="${true}"/>
                                        <div class="input-group-addon globalSearchCancelBtn"><span
                                                class="glyphicon glyphicon-remove globalSearchCancelBtn"
                                                style="font-size: large"></span></div>
                                    </div>
                                </div>
                                <g:hiddenField name="visibility" id="visibility" class="visibility"
                                               value="PUBLIC"/>
                            </g:form>
                        </div>
                    </div>
                    <g:hiddenField name="topicId" id="hiddenTopicID" class="topicPostSearchHiddenTopicID" value=""/>
                </div>

                <div class="postPanelBody">
                    <g:each in="${createdResources}">
                        <g:render template="/templates/postPanel" model="[post: it]"/>
                    </g:each>
                    <g:paginate class="pagination" total="${totalPosts}" controller="user" action="profile"
                                max="${resourceSearchCO.max}" offset="${resourceSearchCO.offset}"
                                params="[id: userDetails.id]"/>
                </div>
            </div>
        </div>
    </div>
</div>
<g:javascript>

</g:javascript>
</body>
</html>