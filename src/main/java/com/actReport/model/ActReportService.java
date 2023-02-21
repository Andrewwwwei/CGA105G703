package com.actReport.model;

import java.util.List;
import java.time.LocalDateTime;

public class ActReportService {

	private ActReportDAO_interface dao;

	public ActReportService() {
		dao = new ActReportJDBCDAO();
	}

	public ActReportVO addActReport(Integer activityId,Integer reportUserId,Integer empNo,
			String reportContent,Integer reportStatus,LocalDateTime reportTime,
			Integer reportMatter,LocalDateTime reportFinishTime) {

		ActReportVO actReportVO = new ActReportVO();

		actReportVO.setActivityId(activityId);
		actReportVO.setReportUserId(reportUserId);
		actReportVO.setEmpNo(empNo);
		actReportVO.setReportContent(reportContent);
		actReportVO.setReportStatus(reportStatus);
		actReportVO.setReportTime(reportTime);
		actReportVO.setReportMatter(reportMatter);
		actReportVO.setReportFinishTime(reportFinishTime);
	
	
		
		dao.insert(actReportVO);

		return actReportVO;
	}
	public void addActReport(ActReportVO actReportVO) {
		dao.insert(actReportVO);
	}

	public void updateActReport(Integer activityReportId,Integer activityId,Integer reportUserId,Integer empNo,
			String reportContent,Integer reportStatus,LocalDateTime reportTime,
			Integer reportMatter,LocalDateTime reportFinishTime) {		
		ActReportVO actReportVO = new ActReportVO();
		
		actReportVO.setActivityReportId(activityReportId);
		actReportVO.setActivityId(activityId);
		actReportVO.setReportUserId(reportUserId);
		actReportVO.setEmpNo(empNo);
		actReportVO.setReportContent(reportContent);
		actReportVO.setReportStatus(reportStatus);
		actReportVO.setReportTime(reportTime);
		actReportVO.setReportMatter(reportMatter);
		actReportVO.setReportFinishTime(reportFinishTime);
		dao.update(actReportVO);
	}
	public void updateActReport(ActReportVO actReportVO) {
		dao.update(actReportVO);
	}

	public void deletActReport(Integer activityReportId) {
		dao.delete(activityReportId);
	}

	public ActReportVO getOneActReport(Integer activityReportId) {
		return dao.findByPrimaryKey(activityReportId);
	}

	public List<ActReportVO> getAll() {
		return dao.getAll();
	}
}
