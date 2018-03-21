package org.kingqueen.kiinmis.model.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HourlogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public HourlogExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andHourlogidIsNull() {
            addCriterion("hourlogid is null");
            return (Criteria) this;
        }

        public Criteria andHourlogidIsNotNull() {
            addCriterion("hourlogid is not null");
            return (Criteria) this;
        }

        public Criteria andHourlogidEqualTo(Integer value) {
            addCriterion("hourlogid =", value, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourlogidNotEqualTo(Integer value) {
            addCriterion("hourlogid <>", value, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourlogidGreaterThan(Integer value) {
            addCriterion("hourlogid >", value, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourlogidGreaterThanOrEqualTo(Integer value) {
            addCriterion("hourlogid >=", value, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourlogidLessThan(Integer value) {
            addCriterion("hourlogid <", value, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourlogidLessThanOrEqualTo(Integer value) {
            addCriterion("hourlogid <=", value, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourlogidIn(List<Integer> values) {
            addCriterion("hourlogid in", values, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourlogidNotIn(List<Integer> values) {
            addCriterion("hourlogid not in", values, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourlogidBetween(Integer value1, Integer value2) {
            addCriterion("hourlogid between", value1, value2, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourlogidNotBetween(Integer value1, Integer value2) {
            addCriterion("hourlogid not between", value1, value2, "hourlogid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidIsNull() {
            addCriterion("hourrecordid is null");
            return (Criteria) this;
        }

        public Criteria andHourrecordidIsNotNull() {
            addCriterion("hourrecordid is not null");
            return (Criteria) this;
        }

        public Criteria andHourrecordidEqualTo(Integer value) {
            addCriterion("hourrecordid =", value, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidNotEqualTo(Integer value) {
            addCriterion("hourrecordid <>", value, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidGreaterThan(Integer value) {
            addCriterion("hourrecordid >", value, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidGreaterThanOrEqualTo(Integer value) {
            addCriterion("hourrecordid >=", value, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidLessThan(Integer value) {
            addCriterion("hourrecordid <", value, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidLessThanOrEqualTo(Integer value) {
            addCriterion("hourrecordid <=", value, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidIn(List<Integer> values) {
            addCriterion("hourrecordid in", values, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidNotIn(List<Integer> values) {
            addCriterion("hourrecordid not in", values, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidBetween(Integer value1, Integer value2) {
            addCriterion("hourrecordid between", value1, value2, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andHourrecordidNotBetween(Integer value1, Integer value2) {
            addCriterion("hourrecordid not between", value1, value2, "hourrecordid");
            return (Criteria) this;
        }

        public Criteria andOperationtimeIsNull() {
            addCriterion("operationtime is null");
            return (Criteria) this;
        }

        public Criteria andOperationtimeIsNotNull() {
            addCriterion("operationtime is not null");
            return (Criteria) this;
        }

        public Criteria andOperationtimeEqualTo(Date value) {
            addCriterion("operationtime =", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotEqualTo(Date value) {
            addCriterion("operationtime <>", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeGreaterThan(Date value) {
            addCriterion("operationtime >", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operationtime >=", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeLessThan(Date value) {
            addCriterion("operationtime <", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeLessThanOrEqualTo(Date value) {
            addCriterion("operationtime <=", value, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeIn(List<Date> values) {
            addCriterion("operationtime in", values, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotIn(List<Date> values) {
            addCriterion("operationtime not in", values, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeBetween(Date value1, Date value2) {
            addCriterion("operationtime between", value1, value2, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtimeNotBetween(Date value1, Date value2) {
            addCriterion("operationtime not between", value1, value2, "operationtime");
            return (Criteria) this;
        }

        public Criteria andOperationtypeIsNull() {
            addCriterion("operationtype is null");
            return (Criteria) this;
        }

        public Criteria andOperationtypeIsNotNull() {
            addCriterion("operationtype is not null");
            return (Criteria) this;
        }

        public Criteria andOperationtypeEqualTo(String value) {
            addCriterion("operationtype =", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeNotEqualTo(String value) {
            addCriterion("operationtype <>", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeGreaterThan(String value) {
            addCriterion("operationtype >", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeGreaterThanOrEqualTo(String value) {
            addCriterion("operationtype >=", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeLessThan(String value) {
            addCriterion("operationtype <", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeLessThanOrEqualTo(String value) {
            addCriterion("operationtype <=", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeLike(String value) {
            addCriterion("operationtype like", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeNotLike(String value) {
            addCriterion("operationtype not like", value, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeIn(List<String> values) {
            addCriterion("operationtype in", values, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeNotIn(List<String> values) {
            addCriterion("operationtype not in", values, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeBetween(String value1, String value2) {
            addCriterion("operationtype between", value1, value2, "operationtype");
            return (Criteria) this;
        }

        public Criteria andOperationtypeNotBetween(String value1, String value2) {
            addCriterion("operationtype not between", value1, value2, "operationtype");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("quantity not between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andDonateIsNull() {
            addCriterion("donate is null");
            return (Criteria) this;
        }

        public Criteria andDonateIsNotNull() {
            addCriterion("donate is not null");
            return (Criteria) this;
        }

        public Criteria andDonateEqualTo(Integer value) {
            addCriterion("donate =", value, "donate");
            return (Criteria) this;
        }

        public Criteria andDonateNotEqualTo(Integer value) {
            addCriterion("donate <>", value, "donate");
            return (Criteria) this;
        }

        public Criteria andDonateGreaterThan(Integer value) {
            addCriterion("donate >", value, "donate");
            return (Criteria) this;
        }

        public Criteria andDonateGreaterThanOrEqualTo(Integer value) {
            addCriterion("donate >=", value, "donate");
            return (Criteria) this;
        }

        public Criteria andDonateLessThan(Integer value) {
            addCriterion("donate <", value, "donate");
            return (Criteria) this;
        }

        public Criteria andDonateLessThanOrEqualTo(Integer value) {
            addCriterion("donate <=", value, "donate");
            return (Criteria) this;
        }

        public Criteria andDonateIn(List<Integer> values) {
            addCriterion("donate in", values, "donate");
            return (Criteria) this;
        }

        public Criteria andDonateNotIn(List<Integer> values) {
            addCriterion("donate not in", values, "donate");
            return (Criteria) this;
        }

        public Criteria andDonateBetween(Integer value1, Integer value2) {
            addCriterion("donate between", value1, value2, "donate");
            return (Criteria) this;
        }

        public Criteria andDonateNotBetween(Integer value1, Integer value2) {
            addCriterion("donate not between", value1, value2, "donate");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userid like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userid not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberIsNull() {
            addCriterion("PaymentInformationNumber is null");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberIsNotNull() {
            addCriterion("PaymentInformationNumber is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberEqualTo(String value) {
            addCriterion("PaymentInformationNumber =", value, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberNotEqualTo(String value) {
            addCriterion("PaymentInformationNumber <>", value, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberGreaterThan(String value) {
            addCriterion("PaymentInformationNumber >", value, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberGreaterThanOrEqualTo(String value) {
            addCriterion("PaymentInformationNumber >=", value, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberLessThan(String value) {
            addCriterion("PaymentInformationNumber <", value, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberLessThanOrEqualTo(String value) {
            addCriterion("PaymentInformationNumber <=", value, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberLike(String value) {
            addCriterion("PaymentInformationNumber like", value, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberNotLike(String value) {
            addCriterion("PaymentInformationNumber not like", value, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberIn(List<String> values) {
            addCriterion("PaymentInformationNumber in", values, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberNotIn(List<String> values) {
            addCriterion("PaymentInformationNumber not in", values, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberBetween(String value1, String value2) {
            addCriterion("PaymentInformationNumber between", value1, value2, "paymentinformationnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentinformationnumberNotBetween(String value1, String value2) {
            addCriterion("PaymentInformationNumber not between", value1, value2, "paymentinformationnumber");
            return (Criteria) this;
        }
    }

    /**
     */
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