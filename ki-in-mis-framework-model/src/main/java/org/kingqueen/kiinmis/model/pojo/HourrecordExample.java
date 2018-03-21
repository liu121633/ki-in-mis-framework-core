package org.kingqueen.kiinmis.model.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HourrecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HourrecordExample() {
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

        public Criteria andStudentnumberIsNull() {
            addCriterion("StudentNumber is null");
            return (Criteria) this;
        }

        public Criteria andStudentnumberIsNotNull() {
            addCriterion("StudentNumber is not null");
            return (Criteria) this;
        }

        public Criteria andStudentnumberEqualTo(String value) {
            addCriterion("StudentNumber =", value, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberNotEqualTo(String value) {
            addCriterion("StudentNumber <>", value, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberGreaterThan(String value) {
            addCriterion("StudentNumber >", value, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberGreaterThanOrEqualTo(String value) {
            addCriterion("StudentNumber >=", value, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberLessThan(String value) {
            addCriterion("StudentNumber <", value, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberLessThanOrEqualTo(String value) {
            addCriterion("StudentNumber <=", value, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberLike(String value) {
            addCriterion("StudentNumber like", value, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberNotLike(String value) {
            addCriterion("StudentNumber not like", value, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberIn(List<String> values) {
            addCriterion("StudentNumber in", values, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberNotIn(List<String> values) {
            addCriterion("StudentNumber not in", values, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberBetween(String value1, String value2) {
            addCriterion("StudentNumber between", value1, value2, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andStudentnumberNotBetween(String value1, String value2) {
            addCriterion("StudentNumber not between", value1, value2, "studentnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberIsNull() {
            addCriterion("PaymentPeriodNumber is null");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberIsNotNull() {
            addCriterion("PaymentPeriodNumber is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberEqualTo(String value) {
            addCriterion("PaymentPeriodNumber =", value, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberNotEqualTo(String value) {
            addCriterion("PaymentPeriodNumber <>", value, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberGreaterThan(String value) {
            addCriterion("PaymentPeriodNumber >", value, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberGreaterThanOrEqualTo(String value) {
            addCriterion("PaymentPeriodNumber >=", value, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberLessThan(String value) {
            addCriterion("PaymentPeriodNumber <", value, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberLessThanOrEqualTo(String value) {
            addCriterion("PaymentPeriodNumber <=", value, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberLike(String value) {
            addCriterion("PaymentPeriodNumber like", value, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberNotLike(String value) {
            addCriterion("PaymentPeriodNumber not like", value, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberIn(List<String> values) {
            addCriterion("PaymentPeriodNumber in", values, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberNotIn(List<String> values) {
            addCriterion("PaymentPeriodNumber not in", values, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberBetween(String value1, String value2) {
            addCriterion("PaymentPeriodNumber between", value1, value2, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andPaymentperiodnumberNotBetween(String value1, String value2) {
            addCriterion("PaymentPeriodNumber not between", value1, value2, "paymentperiodnumber");
            return (Criteria) this;
        }

        public Criteria andResidueIsNull() {
            addCriterion("residue is null");
            return (Criteria) this;
        }

        public Criteria andResidueIsNotNull() {
            addCriterion("residue is not null");
            return (Criteria) this;
        }

        public Criteria andResidueEqualTo(Integer value) {
            addCriterion("residue =", value, "residue");
            return (Criteria) this;
        }

        public Criteria andResidueNotEqualTo(Integer value) {
            addCriterion("residue <>", value, "residue");
            return (Criteria) this;
        }

        public Criteria andResidueGreaterThan(Integer value) {
            addCriterion("residue >", value, "residue");
            return (Criteria) this;
        }

        public Criteria andResidueGreaterThanOrEqualTo(Integer value) {
            addCriterion("residue >=", value, "residue");
            return (Criteria) this;
        }

        public Criteria andResidueLessThan(Integer value) {
            addCriterion("residue <", value, "residue");
            return (Criteria) this;
        }

        public Criteria andResidueLessThanOrEqualTo(Integer value) {
            addCriterion("residue <=", value, "residue");
            return (Criteria) this;
        }

        public Criteria andResidueIn(List<Integer> values) {
            addCriterion("residue in", values, "residue");
            return (Criteria) this;
        }

        public Criteria andResidueNotIn(List<Integer> values) {
            addCriterion("residue not in", values, "residue");
            return (Criteria) this;
        }

        public Criteria andResidueBetween(Integer value1, Integer value2) {
            addCriterion("residue between", value1, value2, "residue");
            return (Criteria) this;
        }

        public Criteria andResidueNotBetween(Integer value1, Integer value2) {
            addCriterion("residue not between", value1, value2, "residue");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
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