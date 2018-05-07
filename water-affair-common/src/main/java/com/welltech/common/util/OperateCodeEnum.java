/**
 * 
 */
package com.welltech.common.util;

/**
 * Created by Zhujia at 2017年8月29日 上午11:02:08
 */
public enum OperateCodeEnum {

	OPERATE_01("1", "实时刷新","{0}测点实时数据刷新"),
	OPERATE_02("2", "读取参数","{0}测点读取参数"),
	OPERATE_03("3", "设置参数","{0}测点设置参数，{1}从{1}修改为{2}"),
	OPERATE_04("4", "新建用户","新增{0}用户，并配以{1}角色"),
	OPERATE_05("5", "修改用户","修改用户信息，{0}信息修改为{1}"),
	OPERATE_06("6", "注销用户","{0}用户被注销"),
	OPERATE_07("7", "新建角色","新增{0}角色"),
	OPERATE_08("8", "修改角色","{0}角色功能被修改");
	
	private String code = null;
    private String title = null;
    private String content = null;

    private OperateCodeEnum(String _code, String _title, String _content) {
        this.code = _code;
        this.title = _title;
        this.content = _content;
    }

    public static OperateCodeEnum getEnumByKey(String key) {
        for (OperateCodeEnum e : OperateCodeEnum.values()) {
            if (e.getCode().equals(key)) {
                return e;
            }
        }
        return null;
    }

    /** 获取code */
    public String getCode() {
        return code;
    }

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

}
