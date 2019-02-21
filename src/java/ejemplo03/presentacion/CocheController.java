/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo03.presentacion;

import com.fpmislata.persistencia.dao.BussinessException;
import com.fpmislata.persistencia.dao.BussinessMessage;
import ejemplo03.dominio.Coche;
import ejemplo03.dominio.Coche;
import ejemplo03.persistencia.dao.CocheDAO;
import ejemplo03.persistencia.dao.CocheDAO;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Lorenzo González
 */
@Controller
public class CocheController {

    @Autowired
    private CocheDAO cocheDAO;
    
    @RequestMapping({"/index.html"})
    public ModelAndView listarCoches(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            List<Coche> coches=cocheDAO.findAll();
            model.put("coches",coches);
            viewName = "cocheLista";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }
        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/coche/newForInsert"})
    public ModelAndView newForInsert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            Coche coche = cocheDAO.create();
            model.put("formOperation", FormOperation.Insert);
            model.put("coche", coche);
            viewName = "coche";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/coche/readForUpdate"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            int id_coche;
            try {
                id_coche = Integer.parseInt(request.getParameter("idCoche"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            Coche coche = cocheDAO.get(id_coche);
            if (coche == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el coche con id=" + id_coche));
            }
            model.put("formOperation", FormOperation.Update);
            model.put("coche", coche);
            viewName = "coche";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/coche/readForDelete"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            int id_coche;
            try {
                id_coche = Integer.parseInt(request.getParameter("idCoche"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }

            Coche coche = cocheDAO.get(id_coche);
            if (coche == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el coche con id=" + id_coche));
            }
            model.put("formOperation", FormOperation.Delete);
            model.put("coche", coche);
            viewName = "coche";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index.html");
            viewName = "error";
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/coche/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Coche coche = null;
        
        try {
            coche = cocheDAO.create();
            coche.setMarca(request.getParameter("marca"));
            coche.setModelo(request.getParameter("modelo"));
            coche.setPuertas(request.getParameter("puertas"));
            coche.setPrecio(Integer.parseInt(request.getParameter("precio")));

            cocheDAO.saveOrUpdate(coche);
            viewName = "redirect:/index.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            if (coche!=null) {
                coche.setIdCoche(0);
            }
            model.put("coche", coche);
            model.put("formOperation", FormOperation.Insert);
            viewName = "coche";
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/coche/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        Coche coche = null;
        try {
            int id_coche;
            try {
                id_coche = Integer.parseInt(request.getParameter("idCoche"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            coche = cocheDAO.get(id_coche);
            if (coche == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el coche."));
            }
            coche.setPrecio(Integer.parseInt(request.getParameter("precio")));
            coche.setMarca(request.getParameter("marca"));
            coche.setModelo(request.getParameter("modelo"));
            coche.setPuertas(request.getParameter("puertas"));
            cocheDAO.saveOrUpdate(coche);

            viewName = "redirect:/index.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("profesor", coche);
            model.put("formOperation", FormOperation.Update);
            viewName = "profesor";
        }

        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/coche/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        Coche coche=null;
        try {
            int id_coche;
            try {
                id_coche = Integer.parseInt(request.getParameter("idCoche"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            coche = cocheDAO.get(id_coche);
            if (coche == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el coche a borrar"));
            }

            cocheDAO.delete(id_coche);
            viewName = "redirect:/index.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("coche", coche);
            model.put("formOperation", FormOperation.Delete);
            viewName = "coche";
        }

        return new ModelAndView(viewName, model);
    }

}
