package com.wuswoo.easypay.service.repository;

import com.wuswoo.easypay.service.model.ResultQuery;

import java.util.List;

/**
 * Created by wuxinjun on 16/9/13.
 */
public interface IResultQueryDBService {
    public ResultQuery saveResultQuery(ResultQuery resultQuery);

    public ResultQuery getResultQueryById(Long id);

    public void nextQuery(ResultQuery resultQuery);

    public ResultQuery findResultQuerybyPlatformTradeTypeId(Integer platformId, Byte tradeType, Long tradeId);

    public ResultQuery createResultQuery(Integer platformId, Byte tradeType, Long tradeId);

    public List<ResultQuery> getPendingResultQueries(Integer platformId, Byte tradeType, Long startTime);
}
