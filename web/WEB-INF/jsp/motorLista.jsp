<%@page import="ejemplo03.dominio.Motor"%>
<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="ejemplo03.dominio.Motor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Motor> motores = (List<Motor>) request.getAttribute("motores");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Motor</title>
        <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet">
        <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.css" rel="stylesheet">
        <script type="text/javascript"  src="<%=request.getContextPath()%>/js/jquery-1.9.0.js"></script>
        <script type="text/javascript"  src="<%=request.getContextPath()%>/js/bootstrap.js" ></script>
    </head>
    <body style="background:#FDFDFD">
        <div class="row-fluid">
            <div class="span12">&nbsp;</div>
        </div>
        <div class="row-fluid">
            <div class="offset1  span10">
                <div class="row-fluid">
                    <div class="span12">
                        <a id="btnNuevo" class="btn btn-primary" href="<%=request.getContextPath()%>/motor/newForInsert.html">Nuevo Motor</a>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12">
                        <table class="table table-bordered table-hover table-condensed">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Combustible</th>
                                    <th>Potencia</th>
                                    <th>Borrar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Motor motor : motores) {
                                %>
                                <tr>
                                    <td><a href="<%=request.getContextPath()%>/motor/readForUpdate.html?idMotor=<%=motor.getIdMotor()%>" title="Editar" ><%=motor.getIdMotor()%></a></td>
                                    <td><%=HtmlUtils.htmlEscape(motor.getCombustible())%></td>
                                    <td><%=HtmlUtils.htmlEscape(motor.getPotencia())%></td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/motor/readForDelete.html?idMotor=<%=motor.getIdMotor()%>" title="Borrar" ><i class="icon-trash"></i></a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="span1"></div>
        </div>
    </body>
</html>