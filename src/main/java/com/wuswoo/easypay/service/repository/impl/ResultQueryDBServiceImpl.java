package com.wuswoo.easypay.service.repository.impl;

import com.wuswoo.easypay.common.util.DateTimeUtil;
import com.wuswoo.easypay.service.mapper.ResultQueryMapper;
import com.wuswoo.easypay.service.model.ResultQuery;
import com.wuswoo.easypay.service.model.ResultQueryExample;
import com.wuswoo.easypay.service.repository.IResultQueryDBService;
import com.wuswoo.easypay.service.util.PayConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wuxinjun on 16/9/14.
 */
@Service("resultQueryDBService")
public class ResultQueryDBServiceImpl implements IResultQueryDBService {
    @Autowired
    private ResultQueryMapper resultQueryMapper;

    @Override
    public ResultQuery saveResultQuery(ResultQuery resultQuery) {
        Date current = new Date();
        if (resultQuery.getId() == null) {
            resultQuery.setCreatedTime(current);
            resultQuery.setUpdatedTime(current);
            resultQueryMapper.insertSelective(resultQuery);
        } else {
            resultQuery.setCreatedTime(null);
            resultQuery.setUpdatedTime(current);
            resultQueryMapper.updateByPrimaryKeySelective(resultQuery);
        }
        return resultQuery;
    }

    @Override
    public ResultQuery getResultQueryById(Long id) {
        return resultQueryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void nextQuery(ResultQuery resultQuery) {
        resultQuery.setNextQueryTime(getNextQueryTime(resultQuery.getQueryCount()));
        resultQuery.setQueryCount((byte) (resultQuery.getQueryCount() + 1));
        if (resultQuery.getQueryCount() > PayConstant.ResultQuery.MAX_QUERY) {
            resultQuery.setQueryStatus(PayConstant.ResultQueryType.FAIL.byteValue());
        }
        saveResultQuery(resultQuery);
    }

    @Override
    public ResultQuery findResultQuerybyPlatformTradeTypeId(Integer platformId, Byte tradeType,
        Long tradeId) {
        ResultQueryExample resultQueryExample = new ResultQueryExample();
        resultQueryExample.createCriteria().andPlatformIdEqualTo(platformId)
            .andTradeTypeEqualTo(tradeType).andTradeIdEqualTo(tradeId);
        List<ResultQuery> resultQueries = resultQueryMapper.selectByExample(resultQueryExample);
        return resultQueries != null && resultQueries.size() > 0 ? resultQueries.get(0) : null;

    }

    @Override
    public ResultQuery createResultQuery(Integer platformId, Byte tradeType, Long tradeId) {
        ResultQuery resultQuery = new ResultQuery();
        resultQuery.setPlatformId(platformId);
        resultQuery.setTradeType(tradeType);
        resultQuery.setTradeId(tradeId);
        resultQuery.setQueryCount((byte) 0);
        resultQuery.setNextQueryTime(getNextQueryTime(resultQuery.getQueryCount()));
        return saveResultQuery(resultQuery);
    }

    @Override
    public List<ResultQuery> getPendingResultQueries(Integer platformId, Byte tradeType,
        Long startTime) {
        int currentTime = DateTimeUtil.currentTime();
        ResultQueryExample resultQueryExample = new ResultQueryExample();
        resultQueryExample.createCriteria().andPlatformIdEqualTo(platformId)
            .andNextQueryTimeLessThan(currentTime).andCreatedTimeGreaterThan(new Date(startTime))
            .andQueryStatusEqualTo(PayConstant.ResultQueryType.PENDING.byteValue())
            .andQueryCountLessThan(PayConstant.ResultQuery.MAX_QUERY)
            .andTradeTypeEqualTo(tradeType);
        return resultQueryMapper.selectByExample(resultQueryExample);

    }

    private int getNextQueryTime(int queryNum) {
        int minutes = 5;
        switch (queryNum) {
            case 0:
                minutes = 1;
                break;
            case 1:
                minutes = 5;
                break;
            case 2:
                minutes = 10;
                break;
            case 3:
                minutes = 30;
                break;
            case 4:
                minutes = 60;
                break;
            case 5:
                minutes = 120;
                break;
            case 6:
                minutes = 240;
                break;
            default:
                minutes = 240;
        }
        return new Long(DateTimeUtil.addForNow(Calendar.MINUTE, minutes).getTime() / 1000)
            .intValue();

    }
}
