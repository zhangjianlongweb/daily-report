package com.weikun.server.model;

import java.util.ArrayList;
import java.util.List;

public class ProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductExample() {
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

        public Criteria andProductidIsNull() {
            addCriterion("productid is null");
            return (Criteria) this;
        }

        public Criteria andProductidIsNotNull() {
            addCriterion("productid is not null");
            return (Criteria) this;
        }

        public Criteria andProductidEqualTo(Integer value) {
            addCriterion("productid =", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotEqualTo(Integer value) {
            addCriterion("productid <>", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThan(Integer value) {
            addCriterion("productid >", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidGreaterThanOrEqualTo(Integer value) {
            addCriterion("productid >=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThan(Integer value) {
            addCriterion("productid <", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidLessThanOrEqualTo(Integer value) {
            addCriterion("productid <=", value, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidIn(List<Integer> values) {
            addCriterion("productid in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotIn(List<Integer> values) {
            addCriterion("productid not in", values, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidBetween(Integer value1, Integer value2) {
            addCriterion("productid between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProductidNotBetween(Integer value1, Integer value2) {
            addCriterion("productid not between", value1, value2, "productid");
            return (Criteria) this;
        }

        public Criteria andProductnoIsNull() {
            addCriterion("productno is null");
            return (Criteria) this;
        }

        public Criteria andProductnoIsNotNull() {
            addCriterion("productno is not null");
            return (Criteria) this;
        }

        public Criteria andProductnoEqualTo(String value) {
            addCriterion("productno =", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoNotEqualTo(String value) {
            addCriterion("productno <>", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoGreaterThan(String value) {
            addCriterion("productno >", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoGreaterThanOrEqualTo(String value) {
            addCriterion("productno >=", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoLessThan(String value) {
            addCriterion("productno <", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoLessThanOrEqualTo(String value) {
            addCriterion("productno <=", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoLike(String value) {
            addCriterion("productno like", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoNotLike(String value) {
            addCriterion("productno not like", value, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoIn(List<String> values) {
            addCriterion("productno in", values, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoNotIn(List<String> values) {
            addCriterion("productno not in", values, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoBetween(String value1, String value2) {
            addCriterion("productno between", value1, value2, "productno");
            return (Criteria) this;
        }

        public Criteria andProductnoNotBetween(String value1, String value2) {
            addCriterion("productno not between", value1, value2, "productno");
            return (Criteria) this;
        }

        public Criteria andCatidIsNull() {
            addCriterion("catid is null");
            return (Criteria) this;
        }

        public Criteria andCatidIsNotNull() {
            addCriterion("catid is not null");
            return (Criteria) this;
        }

        public Criteria andCatidEqualTo(Integer value) {
            addCriterion("catid =", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidNotEqualTo(Integer value) {
            addCriterion("catid <>", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidGreaterThan(Integer value) {
            addCriterion("catid >", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidGreaterThanOrEqualTo(Integer value) {
            addCriterion("catid >=", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidLessThan(Integer value) {
            addCriterion("catid <", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidLessThanOrEqualTo(Integer value) {
            addCriterion("catid <=", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidIn(List<Integer> values) {
            addCriterion("catid in", values, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidNotIn(List<Integer> values) {
            addCriterion("catid not in", values, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidBetween(Integer value1, Integer value2) {
            addCriterion("catid between", value1, value2, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidNotBetween(Integer value1, Integer value2) {
            addCriterion("catid not between", value1, value2, "catid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDescnIsNull() {
            addCriterion("descn is null");
            return (Criteria) this;
        }

        public Criteria andDescnIsNotNull() {
            addCriterion("descn is not null");
            return (Criteria) this;
        }

        public Criteria andDescnEqualTo(String value) {
            addCriterion("descn =", value, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnNotEqualTo(String value) {
            addCriterion("descn <>", value, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnGreaterThan(String value) {
            addCriterion("descn >", value, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnGreaterThanOrEqualTo(String value) {
            addCriterion("descn >=", value, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnLessThan(String value) {
            addCriterion("descn <", value, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnLessThanOrEqualTo(String value) {
            addCriterion("descn <=", value, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnLike(String value) {
            addCriterion("descn like", value, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnNotLike(String value) {
            addCriterion("descn not like", value, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnIn(List<String> values) {
            addCriterion("descn in", values, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnNotIn(List<String> values) {
            addCriterion("descn not in", values, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnBetween(String value1, String value2) {
            addCriterion("descn between", value1, value2, "descn");
            return (Criteria) this;
        }

        public Criteria andDescnNotBetween(String value1, String value2) {
            addCriterion("descn not between", value1, value2, "descn");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
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