<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<tiles:insertDefinition name="loginTemplate">
	<tiles:putAttribute name="body">
		<div class="container">
			<div class="row">
				<div class="col-md-3">&nbsp;</div>
				<c:if test="${not empty param.err}">
					<div>
						<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
					</div>
				</c:if>
				<c:if test="${not empty param.out}">
					<div>You've logged out successfully.</div>
				</c:if>
				<c:if test="${not empty param.time}">
					<div>You've been logged out due to inactivity.</div>
				</c:if>
				<div class="col-md-6 exactCenter">
					<section id="loginForm">
						<form id="frmLogin" action="<c:url value='j_spring_security_check' />" method="POST">
							<div class="row">
								<div class="col-md-12" style="padding-top: 8px;">
									<center><a href="#"><img src="${context}/resources/img/_webtemplate__logo.png" style="vertical-align: text-bottom; width: 40%;"/></a></center>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12"><hr /></div>
							</div>
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-12 form-group editor-field-non-required form-login">
										<label class="col-md-4 control-label text-right" for="Username">ชื่อผู้ใช้ (Username) :</label>
										<div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon"> <span class="glyphicon glyphicon-user"></span>
												</span> <input Autofocus="Autofocus" class="form-control form-login" data-val="true"
													data-val-required="The Username field is required." id="Username" name="username" placeHolder="Username"
													type="text" value="" />
											</div>
											<span class="field-validation-valid" data-valmsg-for="Username" data-valmsg-replace="true"></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-12 form-group editor-field-non-required">
										<label class="col-md-4 control-label text-right" for="Password">รหัสผ่าน (Password) :</label>
										<div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon"> <span class="glyphicon glyphicon-lock"></span>
												</span> <input class="form-control form-login" data-val="true" data-val-required="The Password field is required."
													id="Password" name="password" placeHolder="Password" type="password" />
											</div>
											<span class="field-validation-valid" data-valmsg-for="Password" data-valmsg-replace="true"></span>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="row">
									<div class="col-md-12 form-group" style="margin-bottom: 0px;">
										<div class="col-md-offset-5">
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
											<button type="submit" class="btn btn-login">เข้าสู่ระบบ (Login)</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row"><div>&nbsp;</div></div>
						</form>
					</section>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>