package com.course.springdata.persistence.audit;

import com.course.springdata.persistence.entity.Pizza;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This listener does not work with native queries, spring data repository only
 */
public class AuditPizzaListener {

    Logger logger = Logger.getLogger(getClass().getName());

    private Pizza currentValue;

    @PostPersist
    @PostUpdate
    public void onPostPersist(Pizza entity){
        logger.info("POST PERSIST OR UPDATE");
        logger.log(Level.INFO, "OLD VALUE: {0}", currentValue);
        logger.log(Level.INFO, "NEW VALUE: {0}", entity);
    }

    @PreRemove
    public void onPreDelete(Pizza entity){
        logger.log(Level.INFO, entity::toString);
    }

    @PostLoad
    public void postLoad(Pizza pizza){
        logger.log(Level.INFO, "POST LOAD");
        currentValue = SerializationUtils.clone(pizza);
    }
}
