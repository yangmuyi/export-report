package com.krm.report.vo;

import java.util.List;

public class FormVo {
	private String sortCol;
	private String sortOrder;
	private String reportId;
	private String chartChange;
	private String chartType;
	private String chartSortType;
	private String sortField;
	private String queryType;
	private List<ConditionVo> condition;
	private List<AdditionalVo> additional;
	public List<AdditionalVo> getAdditional() {
		return additional;
	}
	public void setAdditional(List<AdditionalVo> additional) {
		this.additional = additional;
	}
	public String getSortCol() {
		return sortCol;
	}
	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getChartChange() {
		return chartChange;
	}
	public void setChartChange(String chartChange) {
		this.chartChange = chartChange;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getChartSortType() {
		return chartSortType;
	}
	public void setChartSortType(String chartSortType) {
		this.chartSortType = chartSortType;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	
	
	public List<ConditionVo> getCondition() {
		return condition;
	}
	public void setCondition(List<ConditionVo> condition) {
		this.condition = condition;
	}
	@Override
	public String toString() {
		
		return "FormVo [sortCol=" + sortCol + ", sortOrder=" + sortOrder
				+ ", reportId=" + reportId + ", chartChange=" + chartChange
				+ ", chartType=" + chartType + ", chartSortType="
				+ chartSortType + ", sortField=" + sortField + ", queryType="
				+ queryType + ", condition=" + condition + ", additional="
				+ additional + "]";
	}

	
	

}
