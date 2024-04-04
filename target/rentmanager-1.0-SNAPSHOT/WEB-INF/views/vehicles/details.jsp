<%--
  Created by IntelliJ IDEA.
  User: evan
  Date: 06/03/2024
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

      <div class="row">
        <div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">
              <h3 class="profile-username text-center"> ${vehicle.constructeur} ${vehicle.modele} (${vehicle.nb_place})</h3>

              <ul class="list-group list-group-unbordered">
                <li class="list-group-item">
                  <b>Reservation(s)</b> <a class="pull-right">${nbReservations}</a>
                </li>
                <li class="list-group-item">
                  <b>Voiture(s)</b> <a class="pull-right">${nbClients}</a>
                </li>
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
        <div class="col-md-9">
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#rents" data-toggle="tab">Reservations</a></li>
              <li><a href="#users" data-toggle="tab">Clients</a></li>
            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="rents">
                <div class="box-body no-padding">
                  <table class="table table-striped">
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>Client</th>
                      <th>Date de debut</th>
                      <th>Date de fin</th>
                    </tr>
                    <c:forEach items="${reservations}" var="reservations" varStatus="loop">
                      <tr>
                        <td>${reservations.ID}</td>
                        <td>${reservations.clientName}</td>
                        <td>${reservations.debut}</td>
                        <td>${reservations.fin}</td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
              </div>
              <!-- /.tab-pane -->
              <div class="tab-pane" id="users">
                <!-- /.box-header -->
                <div class="box-body no-padding">
                  <table class="table table-striped">
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>Nom</th>
                      <th>Prenom</th>
                      <th>Email</th>
                      <th>Naissance</th>
                    </tr>
                    <c:forEach items="${client}" var="client" varStatus="loop">
                      <tr>
                        <td>${client.ID}</td>
                        <td>${client.nom}</td>
                        <td>${client.prenom}</td>
                        <td>${client.email}</td>
                        <td>${client.naissance}</td>
                      </tr>
                    </c:forEach>
                  </table>
                </div>
              </div>
              <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
          </div>
          <!-- /.nav-tabs-custom -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>

  <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>