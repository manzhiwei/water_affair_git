package com.welltech.controller.history;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.welltech.framework.aop.pagination.bean.MyPage;
import com.welltech.service.history.AlarmService;
import com.welltech.service.index.UiElementService;

/**
 * 报警查询
 * @author wangxin
 *
 */
@Controller
public class AlarmController {
	
	@Autowired
	private UiElementService uiElementService;
	
	@Autowired
	private AlarmService alarmService;

	@RequestMapping( value = {"alarmData"})
	public String alarmData(Model model, MyPage myPage, Integer[] pointIds,
    		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startTime,
    		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime,String typeCode){
		if(myPage == null){
    		myPage = new MyPage();
    	}
		model.addAttribute("points", uiElementService.findAllPointDtos());
		model.addAttribute("datas", alarmService.listWtAlarmRecords(myPage,pointIds,startTime,endTime,typeCode));
    	model.addAttribute("pointIds", pointIds);
    	model.addAttribute("myPage", myPage);
    	model.addAttribute("startTime", startTime);
    	model.addAttribute("endTime", endTime);
    	model.addAttribute("typeCode", typeCode);
		return "history/alarmData";
	}
	
	@RequestMapping( value = {"removeAlarm"})
	@ResponseBody
	public String removeAlarm(Integer id){
		alarmService.deleteById(id);
		return "1";
	}
}
