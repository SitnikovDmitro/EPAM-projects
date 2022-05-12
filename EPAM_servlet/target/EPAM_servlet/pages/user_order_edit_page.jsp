<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>Hello, world!</title>
  </head>
  <body>

    <ul class="nav justify-content-center">
      <li class="nav-item">
        <a class="nav-link active" aria-current="page">Create order</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="user_orders_page.jsp">My orders</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="user_edit_page.jsp">Edit me</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Log out</a>
      </li>
    </ul>

    <div class="row m-3">
      <div class="container col-md-6">
        <h2 class="text-center mb-3 mt-3">Order creation</h2>

        <form action="order_create" method="post">
          <div class="mb-3">
            <label for="title" class="form-label">Title</label>

            <c:choose>
              <c:when test="${param.titleInvalid}">
                <input type="text" class="form-control is-invalid" id="title" aria-describedby="emailHelp" maxlength="100" name="title">
                <div class="invalid-feedback">Title can not be empty</div>
              </c:when>
              <c:otherwise>
                <input type="text" class="form-control" id="title" aria-describedby="emailHelp" maxlength="100" name="title">
              </c:otherwise>
            </c:choose>

          </div>


          <div class="mb-3">
            <label for="description" class="form-label" maxlength="400">Description</label>

            <c:choose>
              <c:when test="${param.descriptionInvalid}">
                <textarea class="form-control is-invalid" aria-label="With textarea" id="description" name="description"></textarea>
                <div class="invalid-feedback">Description can not be empty</div>
              </c:when>
              <c:otherwise>
                <textarea class="form-control" aria-label="With textarea" id="description" name="description"></textarea>
              </c:otherwise>
            </c:choose>

          </div>

          <div class="mb-3">
            <label for="cost" class="form-label">Cost</label>

            <c:choose>
              <c:when test="${param.costInvalid}">
                <input type="number" min="0.00" step="0.05" value="0.00" id="cost" class="form-control is-invalid" placeholder="Price" name="price">
                <div class="invalid-feedback">Invalid or too large cost</div>
              </c:when>
              <c:otherwise>
                <input type="number" min="0.00" step="0.05" value="0.00" id="cost" class="form-control" placeholder="Price" name="price">
              </c:otherwise>
            </c:choose>

          </div>

          <button type="submit" class="btn btn-primary">Create order</button>
        </form>
      </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  </body>
</html>