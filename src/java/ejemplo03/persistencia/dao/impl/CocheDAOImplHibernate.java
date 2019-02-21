package ejemplo03.persistencia.dao.impl;

import ejemplo03.persistencia.dao.CocheDAO;
import ejemplo03.dominio.Coche;
import com.fpmislata.persistencia.dao.impl.GenericDAOImplHibernate;

public class CocheDAOImplHibernate extends GenericDAOImplHibernate<Coche,Integer> implements  CocheDAO {

}
