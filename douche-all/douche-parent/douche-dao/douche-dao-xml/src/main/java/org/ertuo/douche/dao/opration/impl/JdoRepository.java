package org.ertuo.douche.dao.opration.impl;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.ertuo.douche.dao.opration.Repository;
import org.springframework.orm.jdo.support.JdoDaoSupport;

import com.google.inject.Provider;

/**
 * This base class implements the full Repository interface for managing persistent JDO entities.
 *
 * @param <T> the persistent entity type
 */
public abstract class JdoRepository<T> extends JdoDaoSupport implements Repository<T>
{
    private final Class<T> clazz;

    protected JdoRepository(Class<T> clazz)
    {
        this.clazz = clazz;
    }

    public T get(Object key)
    {
        PersistenceManager pm = getPersistenceManager();
        try
        {
            return pm.getObjectById(clazz, key);
        }
        catch (JDOObjectNotFoundException e)
        {
            return null;
        }
    }

    public void persist(T entity)
    {
    	getPersistenceManager().makePersistent(entity);
    }

    public void delete(T entity)
    {
    	getPersistenceManager().deletePersistent(entity);
    }

    public void runInTransaction(Runnable block)
    {
        Transaction tx = getPersistenceManager().currentTransaction();
        try
        {
            tx.begin();
            block.run();
            tx.commit();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
        }
    }
}