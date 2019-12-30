package com.app.appName.core;

import org.hibernate.Session;

import java.io.Serializable;

public abstract interface HibernateHandler extends Serializable {  
    public abstract Object doInHibernate(Session paramSession);  
}  