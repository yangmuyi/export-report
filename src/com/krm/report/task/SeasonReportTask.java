package com.krm.report.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krm.report.common.config.UserConfig;
import com.krm.report.util.HandelReportDateUtil;
import com.krm.report.util.FileUtil;
import com.krm.report.util.HttpClientUtil;
import com.krm.report.util.ListToStringUtil;
import com.krm.report.vo.FormVo;
import com.krm.report.vo.OrganVo;

/**
 * 导出日报pdf任务
 * 
 * @author Li Gaoling
 * @date 2018-07-11
 */
public class SeasonReportTask implements Runnable {

	private static Logger log = LoggerFactory.getLogger(SeasonReportTask.class);
	private String exportDate;
	private OrganVo organVo;
	private List<FormVo> formVoList;

	public SeasonReportTask(String exportDate, OrganVo organVo,
			List<FormVo> formVoList) {
		super();
		this.exportDate = exportDate;
		this.organVo = organVo;
		this.formVoList = formVoList;
	}

	@Override
	public void run() {
		try {
			for (int reportCount = 0; reportCount < formVoList.size(); reportCount++) {
				FormVo formVo = (FormVo) formVoList.get(reportCount);

				String[] condition = ListToStringUtil.conditionToString(formVo
						.getCondition());
				String[] additional = ListToStringUtil
						.additionalToString(formVo.getAdditional());

				// 登录后发送的Post请求都必须带上ticket
				Map<String, String> map = new HashMap<String, String>();
				map.put("ticket", UserLoginTimerTask.ticket);
				map.put("sortCol", formVo.getSortCol());
				map.put("sortOrder", formVo.getSortOrder());
				map.put("reportId", formVo.getReportId());
				map.put("chartChange", formVo.getChartChange());
				map.put("chartType", formVo.getChartType());
				map.put("chartSortType", formVo.getChartSortType());
				map.put("sortField", formVo.getSortField());
				map.put("queryType", formVo.getQueryType());
				map.put("widgetType", condition[0]);
				map.put("systemCode", condition[1]);
				map.put("modelId", condition[2]);
				map.put("colCode", condition[3]);
				map.put("operator", condition[4]);
				String value = condition[5].replace("${data_date}",
						exportDate.replace("-", "")).replace("${organ_id}",
						organVo.getId());
				map.put("value", value);

				if (additional[0] != null) {
					String conditionValue = additional[0].replace(
							"${organ_name}", organVo.getName()).replace(
							"${export_date}",
							HandelReportDateUtil.getSeason(exportDate));
					map.put("conditionValue", conditionValue);
				}
				if (additional[1] != null) {
					map.put("conditionPosition", additional[1]);
				}
				// 模拟导出pdf
				try {
					log.info("***Is Exporting Report:" + value + ","
							+ formVo.getReportId() + "***");
					HttpClientUtil.post(UserConfig.getExportUrl(), map,
							"generalFile");
				} catch (Exception e) {
					String filepath = "/conf/seasonReportExportFailedReport.txt";
					StringBuffer content = new StringBuffer();
					content.append("exportDate:" + exportDate + "|")
							.append("organVo:" + organVo.getId())
							.append("formVo:" + formVo.getReportId());
					try {
						FileUtil.writeFileContent(filepath, content.toString());
					} catch (IOException e1) {
					}
				}
			}
			// 记录这家机构导出成功
			String filepath = "/conf/seasonReportExportSuccessOrgan.txt";
			StringBuffer content = new StringBuffer();
			content.append("exportDate:" + exportDate + "|").append(
					"organVo:" + organVo.getId());
			try {
				FileUtil.writeFileContent(filepath, content.toString());
			} catch (IOException e1) {
				// e1.printStackTrace();
				// log.error("***record failed***");
				// log.error(e1.getMessage());
			}
		} catch (Exception e) {
		}
	}
}
