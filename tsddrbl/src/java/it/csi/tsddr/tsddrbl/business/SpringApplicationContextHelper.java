/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Spring application context helper.
 */
public class SpringApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext appContext;

    private static Map<String, Object> beanCache = new HashMap<>();

    private static Map<String, Object> restEasyServiceCache = new HashMap<>();

    // Private constructor prevents instantiation from other classes
    private SpringApplicationContextHelper() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }


    /**
     * Gets bean.
     *
     * @param beanName  the bean name
     * @param cacheable the cacheable
     * @return the bean
     */
    public static Object getBean(String beanName, boolean cacheable) {

        if (cacheable && beanCache.containsKey(beanName)) {
            return beanCache.get(beanName);
        }

        Object bean = null;

        if (appContext.containsBean(beanName)) {
            bean = appContext.getBean(beanName);
        } else {
            bean = appContext.getBean(beanName.substring(0, 1).toLowerCase() + beanName.substring(1));
        }

        if (cacheable) {
            beanCache.put(beanName, bean);
        }

        return bean;
    }

    /**
     * Gets bean.
     *
     * @param beanName the bean name
     * @return the bean
     */
    public static Object getBean(String beanName) {
        return getBean(beanName, true);
    }


}