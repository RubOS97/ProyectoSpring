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
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css%22%3E">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js%22%3E"</script>
        <scrip<t src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js%22%3E"</script>
    </head>
    <body style="background:#FDFDFD">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">CONCESIONARIO</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-item nav-link active" href="<%=request.getContextPath()%>/index.html">Home <span class="sr-only">(current)</span></a>
                    <a class="nav-item nav-link" href="<%=request.getContextPath()%>/index2.html">Coches</a>
                    <a class="nav-item nav-link" href="<%=request.getContextPath()%>/index3.html">Motores</a>
                </div>
            </div>
        </nav>
                
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
                                        <a href="<%=request.getContextPath()%>/coche/readForDelete.html?idCoche=<%=coche.getIdCoche()%>" title="Borrar" >
                                            <span class="fa fa-trash"></span>
                                        </a>
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