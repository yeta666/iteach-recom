package com.recom.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by rayd on 2017/4/13.
 */
/*动态数据源*/
public class DynamicDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey(){
        return DatabaseContextHolder.getDatabaseType();
    }
}