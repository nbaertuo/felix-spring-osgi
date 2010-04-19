package org.ertuo.douche.dao.opration;


import java.util.List;

import org.ertuo.douche.dao.domain.WebProxyDo;

/**
 * This interface defines type-safe methods for all queries of Greeting entities.
 */
public interface GreetingQueries
{
    /**
     * Return the latest greetings, ordered by descending date.
     *
     * @param max the maximum number of greetings to return
     * @return the greetings
     */
    List<WebProxyDo> latest(int max);
}
