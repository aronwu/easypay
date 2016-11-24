package com.wuswoo.easypay.service.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultQueryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int limitEnd = -1;

    public ResultQueryExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd=limitEnd;
    }

    public int getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNull() {
            addCriterion("trade_id is null");
            return (Criteria) this;
        }

        public Criteria andTradeIdIsNotNull() {
            addCriterion("trade_id is not null");
            return (Criteria) this;
        }

        public Criteria andTradeIdEqualTo(Long value) {
            addCriterion("trade_id =", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotEqualTo(Long value) {
            addCriterion("trade_id <>", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThan(Long value) {
            addCriterion("trade_id >", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("trade_id >=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThan(Long value) {
            addCriterion("trade_id <", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdLessThanOrEqualTo(Long value) {
            addCriterion("trade_id <=", value, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdIn(List<Long> values) {
            addCriterion("trade_id in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotIn(List<Long> values) {
            addCriterion("trade_id not in", values, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdBetween(Long value1, Long value2) {
            addCriterion("trade_id between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeIdNotBetween(Long value1, Long value2) {
            addCriterion("trade_id not between", value1, value2, "tradeId");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNull() {
            addCriterion("trade_type is null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNotNull() {
            addCriterion("trade_type is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeEqualTo(Byte value) {
            addCriterion("trade_type =", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotEqualTo(Byte value) {
            addCriterion("trade_type <>", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThan(Byte value) {
            addCriterion("trade_type >", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("trade_type >=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThan(Byte value) {
            addCriterion("trade_type <", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThanOrEqualTo(Byte value) {
            addCriterion("trade_type <=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIn(List<Byte> values) {
            addCriterion("trade_type in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotIn(List<Byte> values) {
            addCriterion("trade_type not in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeBetween(Byte value1, Byte value2) {
            addCriterion("trade_type between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("trade_type not between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andPlatformIdIsNull() {
            addCriterion("platform_id is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIdIsNotNull() {
            addCriterion("platform_id is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformIdEqualTo(Integer value) {
            addCriterion("platform_id =", value, "platformId");
            return (Criteria) this;
        }

        public Criteria andPlatformIdNotEqualTo(Integer value) {
            addCriterion("platform_id <>", value, "platformId");
            return (Criteria) this;
        }

        public Criteria andPlatformIdGreaterThan(Integer value) {
            addCriterion("platform_id >", value, "platformId");
            return (Criteria) this;
        }

        public Criteria andPlatformIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_id >=", value, "platformId");
            return (Criteria) this;
        }

        public Criteria andPlatformIdLessThan(Integer value) {
            addCriterion("platform_id <", value, "platformId");
            return (Criteria) this;
        }

        public Criteria andPlatformIdLessThanOrEqualTo(Integer value) {
            addCriterion("platform_id <=", value, "platformId");
            return (Criteria) this;
        }

        public Criteria andPlatformIdIn(List<Integer> values) {
            addCriterion("platform_id in", values, "platformId");
            return (Criteria) this;
        }

        public Criteria andPlatformIdNotIn(List<Integer> values) {
            addCriterion("platform_id not in", values, "platformId");
            return (Criteria) this;
        }

        public Criteria andPlatformIdBetween(Integer value1, Integer value2) {
            addCriterion("platform_id between", value1, value2, "platformId");
            return (Criteria) this;
        }

        public Criteria andPlatformIdNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_id not between", value1, value2, "platformId");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeIsNull() {
            addCriterion("next_query_time is null");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeIsNotNull() {
            addCriterion("next_query_time is not null");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeEqualTo(Integer value) {
            addCriterion("next_query_time =", value, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeNotEqualTo(Integer value) {
            addCriterion("next_query_time <>", value, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeGreaterThan(Integer value) {
            addCriterion("next_query_time >", value, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("next_query_time >=", value, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeLessThan(Integer value) {
            addCriterion("next_query_time <", value, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeLessThanOrEqualTo(Integer value) {
            addCriterion("next_query_time <=", value, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeIn(List<Integer> values) {
            addCriterion("next_query_time in", values, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeNotIn(List<Integer> values) {
            addCriterion("next_query_time not in", values, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeBetween(Integer value1, Integer value2) {
            addCriterion("next_query_time between", value1, value2, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andNextQueryTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("next_query_time not between", value1, value2, "nextQueryTime");
            return (Criteria) this;
        }

        public Criteria andQueryCountIsNull() {
            addCriterion("query_count is null");
            return (Criteria) this;
        }

        public Criteria andQueryCountIsNotNull() {
            addCriterion("query_count is not null");
            return (Criteria) this;
        }

        public Criteria andQueryCountEqualTo(Byte value) {
            addCriterion("query_count =", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountNotEqualTo(Byte value) {
            addCriterion("query_count <>", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountGreaterThan(Byte value) {
            addCriterion("query_count >", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountGreaterThanOrEqualTo(Byte value) {
            addCriterion("query_count >=", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountLessThan(Byte value) {
            addCriterion("query_count <", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountLessThanOrEqualTo(Byte value) {
            addCriterion("query_count <=", value, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountIn(List<Byte> values) {
            addCriterion("query_count in", values, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountNotIn(List<Byte> values) {
            addCriterion("query_count not in", values, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountBetween(Byte value1, Byte value2) {
            addCriterion("query_count between", value1, value2, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryCountNotBetween(Byte value1, Byte value2) {
            addCriterion("query_count not between", value1, value2, "queryCount");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIsNull() {
            addCriterion("query_status is null");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIsNotNull() {
            addCriterion("query_status is not null");
            return (Criteria) this;
        }

        public Criteria andQueryStatusEqualTo(Byte value) {
            addCriterion("query_status =", value, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNotEqualTo(Byte value) {
            addCriterion("query_status <>", value, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andQueryStatusGreaterThan(Byte value) {
            addCriterion("query_status >", value, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andQueryStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("query_status >=", value, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andQueryStatusLessThan(Byte value) {
            addCriterion("query_status <", value, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andQueryStatusLessThanOrEqualTo(Byte value) {
            addCriterion("query_status <=", value, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andQueryStatusIn(List<Byte> values) {
            addCriterion("query_status in", values, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNotIn(List<Byte> values) {
            addCriterion("query_status not in", values, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andQueryStatusBetween(Byte value1, Byte value2) {
            addCriterion("query_status between", value1, value2, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andQueryStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("query_status not between", value1, value2, "queryStatus");
            return (Criteria) this;
        }

        public Criteria andReturnContentIsNull() {
            addCriterion("return_content is null");
            return (Criteria) this;
        }

        public Criteria andReturnContentIsNotNull() {
            addCriterion("return_content is not null");
            return (Criteria) this;
        }

        public Criteria andReturnContentEqualTo(String value) {
            addCriterion("return_content =", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotEqualTo(String value) {
            addCriterion("return_content <>", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentGreaterThan(String value) {
            addCriterion("return_content >", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentGreaterThanOrEqualTo(String value) {
            addCriterion("return_content >=", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLessThan(String value) {
            addCriterion("return_content <", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLessThanOrEqualTo(String value) {
            addCriterion("return_content <=", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLike(String value) {
            addCriterion("return_content like", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotLike(String value) {
            addCriterion("return_content not like", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentIn(List<String> values) {
            addCriterion("return_content in", values, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotIn(List<String> values) {
            addCriterion("return_content not in", values, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentBetween(String value1, String value2) {
            addCriterion("return_content between", value1, value2, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotBetween(String value1, String value2) {
            addCriterion("return_content not between", value1, value2, "returnContent");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}