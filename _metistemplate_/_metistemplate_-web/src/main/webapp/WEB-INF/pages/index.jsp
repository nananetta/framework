<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    	<div class="row">
    		<div class="col-sm-6">
    			<div class="col-sm-12">
    				<div class="col-sm-2"></div>
    				<div class="col-sm-9">
    					<div class="panel-group" id="mainMenuLeft" role="tablist" aria-multiselectable="true">

    					<!--
    					*** Left Column: Row 1
    					 -->
    						<div class="panel panel-default">
    							<div class="panel-heading" role="tab" id="headingMenu">
    								<h4 class="panel-title">
    									<a data-toggle="collapse" data-parent="#mainMenuLeft" href="#mainMenu" aria-expanded="true" aria-controls="#mainMenu">
                                   			<strong>Section A</strong>
                                		</a>
    								</h4>
    							</div>
    							<div id="mainMenu" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingMenu">
                             		<div class="panel-body" style="padding-top: 0px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;">
                          				<div class="panel-body">
                                    		<ul class="nav nav-pills nav-stacked">                                             				
                                        		<li role="presentation"><a href="${context}/page1.html">Page 1</a></li>
                                    		</ul>
                                    	</div>
                                    </div>
                             	</div> 
    						</div>

    					<!--
    					*** Left Column: Row 2
    					 -->
    						<div class="panel panel-default">
    							<div class="panel-heading" role="tab" id="headingUmeMasterMenu">
    								<h4 class="panel-title">
    									<a data-toggle="collapse" data-parent="#mainMenuLeft" href="#umeMasterMenu" aria-expanded="true" aria-controls="#umeMasterMenu">
                                   			<strong>Manage User</strong>
                                		</a>
    								</h4>
    							</div>
    							<div id="umeMasterMenu" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingUmeMasterMenu">
                             		<div class="panel-body" style="padding-top: 0px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;">
                          				<div class="panel-body">
                                    		<ul class="nav nav-pills nav-stacked">   
                                    			<li role="presentation"><a href="${context}/umeMaster/umeUserSearch.html">Search User</a></li>  
                                    			<li role="presentation"><a href="${context}/umeMaster/umeUserEntry.html">Create User</a></li>
                                    		</ul>
                                    	</div>
                                    </div>
                             	</div>                             	
    						</div>    						
    					</div>
    				</div>
    			</div>
    		</div>
    		<div class="col-sm-6">
    			<div class="col-sm-12">
    				<div class="col-sm-1"></div>
    				<div class="col-sm-9">
    					<div class="panel-group" id="mainMenuRight" role="tablist" aria-multiselectable="true">
    					
    					<!--
    					*** Right Column: Row 1
    					 -->
    						<div class="panel panel-default">
    							<div class="panel-heading" role="tab" id="headingMenu">
    								<h4 class="panel-title">
    									<a data-toggle="collapse" data-parent="#mainMenuRight" href="#module1Menu" aria-expanded="true" aria-controls="#module1Menu">
                                   			<strong>Buying-Selling</strong>
                                		</a>
    								</h4>
    							</div>
    							<div id="module1Menu" class="panel-collapse in collapse" role="tabpanel" aria-labelledby="headingMenu">
                             		<div class="panel-body" style="padding-top: 0px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;">
                          				<div class="panel-body">
                                    		<ul class="nav nav-pills nav-stacked"> 
                                    			<li role="presentation"><a href="${context}/module/moduleSearch.html">Module Search</a></li>                                   						
                                    		</ul>
                                    	</div>
                                    </div>
                             	</div> 
    						</div>
    						
    					<!--
    					*** Right Column: Row 2
    					 -->
    						<div class="panel panel-default">
    							<div class="panel-heading" role="tab" id="headingReportMenu">
    								<h4 class="panel-title">
    									<a data-toggle="collapse" data-parent="#mainMenuRight" href="#reportMenu" aria-expanded="true" aria-controls="#reportMenu">
                                   			<strong>Report</strong>
                                		</a>
    								</h4>
    							</div>
    							<div id="reportMenu" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingReportMenu">
                             		<div class="panel-body" style="padding-top: 0px;padding-bottom: 0px;padding-right: 0px;padding-left: 0px;">
                          				<div class="panel-body">
                                    		<ul class="nav nav-pills nav-stacked"> 
                                    			<li role="presentation"><a href="${context}/report/report1.html">Report 1</a></li>                                   						
                                    			<li role="presentation"><a href="${context}/report/report2.html">Report 2</a></li>                                    			                                   			
                                    		</ul>
                                    	</div>
                                    </div>
                             	</div> 
    						</div>
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>
     <div class="row">
     	<div class="col-sm-6">&nbsp;</div>
     </div>
    </tiles:putAttribute>
</tiles:insertDefinition>