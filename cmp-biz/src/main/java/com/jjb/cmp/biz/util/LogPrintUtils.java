/**
 * 
 */
package com.jjb.cmp.biz.util;

import com.jjb.unicorn.facility.context.OrganizationContextHolder;
import com.jjb.unicorn.facility.util.StringUtils;

/**
 * @Description: 日志公共类
 * @author JYData-R&D-HN
 * @date 2018年9月23日 下午3:39:15
 * @version V1.0  
 */
public class LogPrintUtils {
	/**
	 * 申请件编号为主的简单日志打印模版-开始日志
	 * @param batchNo
	 * @param tokenId = System.currentTimeMillis();
	 * @return
	 */
	public static String printAppNoLog(String batchNo,Long tokenId,String servId){
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotEmpty(batchNo)){
			sb.append(".[BatchNo-");
			sb.append(batchNo);
			sb.append("],");
		}
		sb.append(printCommonStartLog(tokenId,servId));
		return sb.toString();
	}
	/**
	 * 申请件编号为主的简单日志打印模版-结尾日志，有耗时时间计算
	 * @param batchNo
	 * @param tokenId = System.currentTimeMillis();
	 * @return
	 */
	public static String printAppNoEndLog(String batchNo,Long tokenId,String servId){
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isNotEmpty(batchNo)){
			sb.append(".[BatchNo-");
			sb.append(batchNo);
			sb.append("],");
		}
		sb.append(printCommonEndLog(tokenId,servId));
		return sb.toString();
	}
	/**
	 * 公共日志打印-开始
	 * @param tokenId = System.currentTimeMillis();
	 * @param servId 服务代码，可唯恐
	 * @return
	 */
	public static String printCommonStartLog(Long tokenId,String servId){
		StringBuffer sb = new StringBuffer();
//		sb.append("[org-");
//		sb.append(OrganizationContextHolder.getOrg());
//		sb.append("].");
		if(tokenId!=null){
			sb.append("[PID-");
			sb.append(tokenId+"");
			sb.append("].");
		}
		if(StringUtils.isNotEmpty(servId)){
			sb.append("[服务类型-");
			sb.append(servId+"");
			sb.append("].");
		}
		sb.append("[userId-");
		sb.append(OrganizationContextHolder.getUserNo());
		sb.append("].");
		return sb.toString();
	}
	/**
	 * 公共日志打印-结束
	 * @param tokenId = System.currentTimeMillis();
	 * @return
	 */
	public static String printCommonEndLog(Long tokenId,String servId){
		StringBuffer sb = new StringBuffer();
//		sb.append("[org-");
//		sb.append(OrganizationContextHolder.getOrg());
//		sb.append("].");
		sb.append(printCommonStartLog(tokenId, servId));
		if(tokenId!=null){
			sb.append("[耗时-");
			sb.append(System.currentTimeMillis() - tokenId+"毫秒");
			sb.append("].");
		}
		return sb.toString();
	}
}
