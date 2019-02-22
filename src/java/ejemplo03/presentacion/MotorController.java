/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo03.presentacion;

import com.fpmislata.persistencia.dao.BussinessException;
import com.fpmislata.persistencia.dao.BussinessMessage;
import ejemplo03.dominio.Coche;
import ejemplo03.dominio.Coche;
import ejemplo03.dominio.Motor;
import ejemplo03.persistencia.dao.CocheDAO;
import ejemplo03.persistencia.dao.CocheDAO;
import ejemplo03.persistencia.dao.MotorDAO;
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
public class MotorController {

    @Autowired
    private MotorDAO motorDAO;
    
    @RequestMapping({"/index3.html"})
    public ModelAndView listarMotores(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            List<Motor> motores=motorDAO.findAll();
            model.put("motores",motores);
            viewName = "motorLista";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index3.html");
            viewName = "error";
        }
        return new ModelAndView(viewName, model);
    }
    
    @RequestMapping({"/motor/newForInsert"})
    public ModelAndView newForInsert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;

        try {
            Motor motor = motorDAO.create();
            model.put("formOperation", FormOperation.Insert);
            model.put("motor", motor);
            viewName = "motor";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index2.html");
            viewName = "error";
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/motor/readForUpdate"})
    public ModelAndView readForUpdate(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            int id_motor;
            try {
                id_motor = Integer.parseInt(request.getParameter("idMotor"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            Motor motor = motorDAO.get(id_motor);
            if (motor == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el motor con id=" + id_motor));
            }
            model.put("formOperation", FormOperation.Update);
            model.put("motor", motor);
            viewName = "motor";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index3.html");
            viewName = "error";
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/motor/readForDelete"})
    public ModelAndView readForDelete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            int id_motor;
            try {
                id_motor = Integer.parseInt(request.getParameter("idMotor"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            Motor motor = motorDAO.get(id_motor);
            if (motor == null) {
                throw new BussinessException(new BussinessMessage(null, "No existe el motor con id=" + id_motor));
            }
            model.put("formOperation", FormOperation.Delete);
            model.put("motor", motor);
            viewName = "motor";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("backURL", request.getContextPath() + "/index3.html");
            viewName = "error";
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/motor/insert.html"})
    public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        Motor motor = null;
        try {
            motor = motorDAO.create();
            motor.setCombustible(request.getParameter("combustible"));
            motor.setPotencia(request.getParameter("potencia"));

            motorDAO.saveOrUpdate(motor);
            viewName = "redirect:/index3.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            if (motor!=null) {
                motor.setIdMotor(0);
            }
            model.put("motor", motor);
            model.put("formOperation", FormOperation.Insert);
            viewName = "motor";
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/motor/update.html"})
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        Motor motor = null;
        try {
            int id_motor;
            try {
                id_motor = Integer.parseInt(request.getParameter("idMotor"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            motor = motorDAO.get(id_motor);
            if (motor == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el motor."));
            }
            motor.setCombustible(request.getParameter("combustible"));
            motor.setPotencia(request.getParameter("potencia"));
            motorDAO.saveOrUpdate(motor);
            viewName = "redirect:/index3.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("motor", motor);
            model.put("formOperation", FormOperation.Update);
            viewName = "motor";
        }
        return new ModelAndView(viewName, model);
    }

    @RequestMapping({"/motor/delete.html"})
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName;
        Motor motor=null;
        try {
            int id_motor;
            try {
                id_motor = Integer.parseInt(request.getParameter("idMotor"));
            } catch (NumberFormatException nfe) {
                throw new BussinessException(new BussinessMessage(null,"Se debe escribir un Id válido"));
            }
            motor = motorDAO.get(id_motor);
            if (motor == null) {
                throw new BussinessException(new BussinessMessage(null, "Ya no existe el motor a borrar"));
            }
            motorDAO.delete(id_motor);
            viewName = "redirect:/index3.html";
        } catch (BussinessException ex) {
            model.put("bussinessMessages", ex.getBussinessMessages());
            model.put("motor", motor);
            model.put("formOperation", FormOperation.Delete);
            viewName = "motor";
        }
        return new ModelAndView(viewName, model);
    }
}