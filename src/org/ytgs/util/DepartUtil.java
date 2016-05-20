package org.ytgs.util;

import org.ytgs.system.model.SysDept;

public class DepartUtil {
	public static String trimZero(String str) {
		String s = "";
		if ("0".equals(str.substring(str.length() - 1))) {
			s = trimZero(str.substring(0, str.length() - 1));
		} else {
			s = str;
		}
		return s;
	}
/**
 * 获取权限税务机机构范围，根据sysDept 返回字符串 格式为：13706%
 * @param userDeparts
 * @return
 */
	public static String deptStr(SysDept userDeparts ) {
		if("Y".equals(userDeparts.getDeptstandalone())){
			return deptStrBydeptId(userDeparts.getId());
		}else{
			return deptStrBydeptId(userDeparts.getParentId());
		}
	}
	public static String deptStrBydeptId(String deptId) {
		String deptStr = "";
		String deptTrimZero = "";
		if (deptId.lastIndexOf("%") == -1) {
			if ("0000".equals(deptId.substring(7,11))) {
				if ("0000".equals(deptId.substring(7,11))) {
					deptTrimZero = deptId.substring(0,7);//区县级
				}
				if ("000000".equals(deptId.substring(5,11))){
					deptTrimZero = deptId.substring(0,5);//地市级
				}
				if ("00000000".equals(deptId.substring(3,11))) {
					deptTrimZero = deptId.substring(0,3);//省级
				}
				deptStr = deptTrimZero + "%";
			}else {
				deptTrimZero = trimZero(deptId.substring(0));
				deptStr = deptTrimZero + "%";
			}			
		} else {
			deptStr = deptId;
		}
		return deptStr;
	}
	
	public static String swjgDmStr(String swjgDm) {
		String deptStr = "";
		String deptTrimZero = "";
		if ("00".equals(swjgDm.substring(9,11))) {
			deptTrimZero = swjgDm.substring(0,9);//乡镇级
		}
		if ("0000".equals(swjgDm.substring(7,11))) {
			deptTrimZero = swjgDm.substring(0,7);//区县级
		}
		if ("000000".equals(swjgDm.substring(5,11))){
			deptTrimZero = swjgDm.substring(0,5);//地市级
		}
		if ("00000000".equals(swjgDm.substring(3,11))) {
			deptTrimZero = swjgDm.substring(0,3);//省级
		}
		deptStr = deptTrimZero + "%";
			
		return deptStr;
	}
}
