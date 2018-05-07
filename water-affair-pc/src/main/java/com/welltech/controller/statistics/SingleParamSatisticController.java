package com.welltech.controller.statistics;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.welltech.dao.sysSetting.PortManageDao;
import com.welltech.entity.WtParam;
import com.welltech.entity.WtStation;
import com.welltech.service.index.UiElementService;
import com.welltech.service.realtime.RealtimeService;

@Controller
public class SingleParamSatisticController {

	@Autowired
	private UiElementService uiElementService;
	
	@Autowired
	private RealtimeService realtimeService;
	
	@Autowired
	private PortManageDao portManageDao;
	
	@RequestMapping("/singleParamStatistic")
	public String multipleParamStatistic(Model model) {
		model.addAttribute("points", uiElementService.findAllPointDtos());
		return "statistics/singleParamStatistic";
	}
	
	@RequestMapping(value = {"/singleParamStatisticData"})
    @ResponseBody
    public Map<String, Object> realtimeChartData(String[] stationId, String[] params,
    		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date startTime,
    		@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endTime){
    	Map<String, Object> result = new HashMap<>();
    	if(stationId != null){
    		for(String id : stationId){
    			WtStation station = portManageDao.findStationById(id);
    			if(StringUtils.isNotBlank(station.getGatewaySerial()) 
    					&& params!= null && params.length > 0){
    				result.put(station.getPoint(), realtimeService.findChartWtData(params, startTime, endTime, station.getGatewaySerial()));
    			}
    		}
    	}
    	return result;
    }
	
	@RequestMapping("/singleParamStatisticParam")
	@ResponseBody
	public Collection<WtParam> singleParamStatisticParam(Integer[] stationId) {
		if(stationId != null && stationId.length == 1){
			return uiElementService.getParamsByStationId(stationId[0]).values();
		}
		return uiElementService.getParams().values();
	}
	
	
}
