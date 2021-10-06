package com.jiwoo.replicationtest.config;

import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReplicationRoutingDataSource.class);

    private CircularList<String> datasourceNameList;

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);

        datasourceNameList = new CircularList<>(
                targetDataSources.keySet()
                                 .stream()
                                 .map(Object::toString)
                                 .filter(string -> string.contains("slave"))
                                 .collect(Collectors.toList()));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if(isReadOnly) {
            String slaveName = datasourceNameList.getOne();
            LOGGER.info("Select Slave DB : {}", slaveName);
            return slaveName;
        } else {
            LOGGER.info("Select Master DB : {}", "master");
            return "master";
        }
    }
}
