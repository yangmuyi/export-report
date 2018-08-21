package com.krm.report.util;

import java.util.ArrayList;
import java.util.List;

import com.krm.report.vo.AdditionalVo;
import com.krm.report.vo.ConditionVo;
/**
 * 集合转换成字符串工具类
 * @author Li Gaoling
 * @date 2018-07-12
 */
public class ListToStringUtil {

	/**
	 * 表单参数ConditionVo集合转换成字符串数组
	 * @param list
	 * @return
	 */
	public static String[] conditionToString(List<ConditionVo> list){
		String[] result = new String[6];
		StringBuffer widgetType = new StringBuffer();
		StringBuffer systemCode = new StringBuffer();
		StringBuffer modelId = new StringBuffer();
		StringBuffer colCode = new StringBuffer();
		StringBuffer operator = new StringBuffer();
		StringBuffer value = new StringBuffer();
		for (ConditionVo conditionVo : list) {
			widgetType.append(conditionVo.getWidgetType()).append(",");
			systemCode.append(conditionVo.getSystemCode()).append(",");
			modelId.append(conditionVo.getModelId()).append(",");
			colCode.append(conditionVo.getColCode()).append(",");
			operator.append(conditionVo.getOperator()).append(",");
			value.append(conditionVo.getValue()).append(",");
		}
		if(widgetType.indexOf(",")!=-1){
			result[0] = widgetType.substring(0, widgetType.lastIndexOf(","));
		}
		if(systemCode.indexOf(",")!=-1){
			result[1] = systemCode.substring(0, systemCode.lastIndexOf(","));
		}
		if(modelId.indexOf(",")!=-1){
			result[2] = modelId.substring(0, modelId.lastIndexOf(","));
		}
		if(colCode.indexOf(",")!=-1){
			result[3] = colCode.substring(0, colCode.lastIndexOf(","));
		}
		if(operator.indexOf(",")!=-1){
			result[4] = operator.substring(0, operator.lastIndexOf(","));
		}
		if(value.indexOf(",")!=-1){
			result[5] = value.substring(0, value.lastIndexOf(","));
		}
		return result;
	}
	
	/**
	 * 表单参数AdditionalVo集合转换成字符串数组
	 * @param list
	 * @return
	 */
	public static String[] additionalToString(List<AdditionalVo> list){
		String[] result = new String[2];
		StringBuffer conditionValue = new StringBuffer();
		StringBuffer conditionPosition = new StringBuffer();
		for (AdditionalVo additionalVo : list) {
			conditionValue.append(additionalVo.getConditionValue()).append(",");
			conditionPosition.append(additionalVo.getConditionPosition()).append(",");
		}
		if(conditionValue.indexOf(",")!=-1){
			result[0] = conditionValue.substring(0, conditionValue.lastIndexOf(","));
		}
		if(conditionPosition.indexOf(",")!=-1){
			result[1] = conditionPosition.substring(0, conditionPosition.lastIndexOf(","));
		}
		return result;
	}
	
}
