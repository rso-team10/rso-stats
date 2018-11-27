package si.fri.rso.team10;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequestScoped
public class UploadService extends AbstractService<UploadDate>{

    @PersistenceContext
    private EntityManager em;

    public List<UploadDate> getUploadDates() {
        return em.createQuery("select uploadDate from UploadDate uploadDate", UploadDate.class).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
