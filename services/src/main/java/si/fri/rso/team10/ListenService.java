package si.fri.rso.team10;

import si.fri.rso.team10.dto.TrackCount;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequestScoped
public class ListenService extends AbstractService<ListenInstance> {

    @PersistenceContext
    private EntityManager em;

    public List<TrackCount> getTrackCounts() {
        return em.createQuery("select new si.fri.rso.team10.dto.TrackCount(li.trackId, count(li.id)) from ListenInstance li group by li.trackId", TrackCount.class).getResultList();
    }

    public Long getListenCount(Long trackId) {
        return em.createQuery("select count(li) from ListenInstance li where li.trackId = :id", Long.class).setParameter("id", trackId).getSingleResult();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
