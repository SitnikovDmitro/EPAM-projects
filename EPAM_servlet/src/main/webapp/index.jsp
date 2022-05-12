<%@ page import="entity.Order, localization.SiteText, localization.Lang" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
        getServletConfig().getServletContext().setAttribute("st", SiteText.getInstance());
%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <c:if test="${not empty message}">
      <script>
        $(document).ready(function(){
            $("#myModal").modal('show');
        });
      </script>
    </c:if>

    <title>Hello, world2!</title>
  </head>
  <body>


    <c:if test="${not empty message}">

      <div class="modal" tabindex="-1" id="myModal" data-show="true">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">${st.getText('message', lg)}</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
              <p>${message}</p>
            </div>
          </div>
        </div>
      </div>

      <script>
        $('#myModal').modal();
      </script>

    </c:if>

    
  	<div class="container text-center">
  		<h1 class="m-3">${st.getText('repair_agency', lg)}</h1>
      
  		<a href="pages/login_page.jsp" type="button" class="btn btn-primary">${st.getText('login', lg)}</a>
  		<a href="pages/master_registration_page.jsp" type="button" class="btn btn-primary">${st.getText('master_sign', lg)}</a>
  		<a href="pages/user_registration_page.jsp" type="button" class="btn btn-primary">${st.getText('user_sign', lg)}</a>

        <p></p>

        <div class="btn-group" role="group">
          <a href="${pageContext.request.contextPath}/controller?action=changeLanguage&lang=EN" type="button" class="btn btn-${lg=='RU'?'outline-':''}primary">ENG</a>
          <a href="${pageContext.request.contextPath}/controller?action=changeLanguage&lang=RU" type="button" class="btn btn-${lg!='RU'?'outline-':''}primary">РУС</a>
        </div>

  	</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>