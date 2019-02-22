<%@page import="org.springframework.web.util.HtmlUtils"%>
<%@page import="ejemplo03.dominio.Coche"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Coche> coches = (List<Coche>) request.getAttribute("coches");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Coche</title>
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
                        <a id="btnNuevo" class="btn btn-primary" href="<%=request.getContextPath()%>/coche/newForInsert.html">Nuevo Coche</a>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span12">
                        <table class="table table-bordered table-hover table-condensed">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Marca</th>
                                    <th>Modelo</th>
                                    <th>Precio</th>
                                    <th>Puertas</th>
                                    <th>Borrar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Coche coche : coches) {
                                %>
                                <tr>
                                    <td><a href="<%=request.getContextPath()%>/coche/readForUpdate.html?idCoche=<%=coche.getIdCoche()%>" title="Editar" ><%=coche.getIdCoche()%></a></td>
                                    <td><%=HtmlUtils.htmlEscape(coche.getMarca())%></td>
                                    <td><%=HtmlUtils.htmlEscape(coche.getModelo())%></td>
                                    <td><%=HtmlUtils.htmlEscape(Integer.toString(coche.getPrecio()))%></td>
                                    <td><%=HtmlUtils.htmlEscape(coche.getPuertas())%></td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/coche/readForDelete.html?idCoche=<%=coche.getIdCoche()%>" title="Borrar" ><i class="icon-trash"></i></a>
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