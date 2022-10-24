package rikkei.academy.repository.blog;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rikkei.academy.model.Blog;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional
public class BlogRepositoryIMPL implements IBlogRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(Long id) {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b where b.id = :id", Blog.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void save(Blog blog) {
        if (blog.getId() == null) {
            em.persist(blog);
        } else {
            em.merge(blog);
        }
    }

    @Override
    public void remove(Long id) {
        Blog blog = findById(id);
        if (blog != null) {
            em.remove(blog);
        }
    }
}