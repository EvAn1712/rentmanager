<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                Reservations
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/rents/create">Ajouter</a>
            </h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body no-padding">
                            <table class="table table-striped">
                                <tr>
                                    <th style="width: 10px">#</th>
                                    <th>Voiture</th>
                                    <th>Client</th>
                                    <th>Debut</th>
                                    <th>Fin</th>
                                    <th>Action</th>
                                </tr>

                                <c:forEach items="${reservations}" var="reservations">
                                    <tr>
                                        <td>${reservations.ID}</td>
                                        <td>${reservations.vehicleName}</td>
                                        <td>${reservations.clientName}</td>
                                        <td>${reservations.debut}</td>
                                        <td>${reservations.fin}</td>
                                        <td>
                                            <a class="btn btn-primary " href="${pageContext.request.contextPath}/users/details?id=${reservations.client_id}">
                                                <i class="fa fa-play"></i>
                                            </a>
                                            <a class="btn btn-success " href="${pageContext.request.contextPath}/rents/modifier?id=${reservations.ID}">
                                                <i class="fa fa-edit"></i>
                                            </a>
                                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/rents/delete?id=${reservations.ID}">
                                                <i class="fa fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>


                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
