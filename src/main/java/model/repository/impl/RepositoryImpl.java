package model.repository.impl;

import model.repository.Repository;

import java.util.List;

public class RepositoryImpl<T,ID> implements Repository<T,ID> {
    @Override
    public void save(T t) {
        var session = getSessionFactory().getCurrentSession();
        session.save(t);
    }

    @Override
    public void update(T t) {
        var session = getSessionFactory().getCurrentSession();
        session.update(t);
    }

    @Override
    public void delete(T t) {
        var session =  getSessionFactory().getCurrentSession();
        session.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        try (var session =  getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            try {
//                session.delete();
            } catch (Exception e) {
                e.printStackTrace();
                transaction.rollback();
            }
        }
    }

    @Override
    public T findById(Class<T> tClass, ID id) {
        try(var session =  getSessionFactory().openSession()){
            return session.find(tClass,id);
        }
    }

    @Override
    public List<T> findAll(Class<T> tClass) {
        try(var session =  getSessionFactory().openSession()){
            var criteriaBuilder = session.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(tClass);
            var root = criteriaQuery.from(tClass);
            return session.createQuery(criteriaQuery.select(root)).list();
        }
    }
}
