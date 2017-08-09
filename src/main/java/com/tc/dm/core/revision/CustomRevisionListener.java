package com.tc.dm.core.revision;

import com.tc.dm.core.entities.CustomRevisionEntity;
import org.hibernate.envers.RevisionListener;

public class CustomRevisionListener implements RevisionListener {

    public static String user = null;

    @Override
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;

        customRevisionEntity.setUserName(user);
    }
}
