package com.jjb.cmp.app.tags;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jjb.unicorn.web.tags.UnicornFunctionRouterTag;

@Component
public class CmpTag extends UnicornFunctionRouterTag {
//	@Autowired
//	private CacheContext cacheContext;
//	@Autowired
//	private AccessDictService accessDictService;

	/**
	 * 根据方法类型，获取表数据的列表
	 * 
	 * @param arguments
	 * 
	 * arguments[0] 方法名 arguments[1] 表对应的实体类（需包含包名）。
	 * 例子：com.jjb.acl.infrastructure.TmAclDict arguments[2]
	 * 查询对象，json对象。为空需要传入 {}
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> tableList(List arguments) {
		if (!argsSizeLess(arguments, 2)) {
			return null;
		}

		String method = arguments.get(1).toString();
		String filters = arguments.get(2).toString();
//		if(StringUtils.isNotEmpty(method) && method.equals("getDitDicGroupType")){//
//			TmDitDic tmDitDic = new TmDitDic();
//			if(StringUtils.isNotEmpty(filters)){
//				tmDitDic.setDicType(filters);
//			}
//			return (List<T>) commonService.selectGroupType(tmDitDic);
//		}
		List list = new ArrayList();

		return list;
	}

	/**
	 * 根据方法类型，获取表数据的map
	 * 
	 * @param arguments
	 * 
	 * arguments[0] 方法名 arguments[1] 表对应的实体类（需包含包名）。
	 * 例子：com.jjb.acl.infrastructure.TmAclDict arguments[2]
	 * 查询对象，json对象。为空需要传入 {} arguments[3] keyField arguments[4]
	 * valueField
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public LinkedHashMap<Object, Object> tableMap(List arguments) {
		if (!argsSizeLess(arguments, 3)) {
			return null;
		}
		String method = arguments.get(1).toString();
		String filters = arguments.get(2).toString();
//		if (StringUtils.isNotEmpty(method)) {
//			if (method.equals("dictType")) {// 根据字段类型获取字典数据--用于配置字段
//				List<TmAclDict> list = accessDictService.getByType(filters);
//
//				LinkedHashMap<Object, Object> dictMap = new LinkedHashMap<Object, Object>();
//				if (CollectionUtils.sizeGtZero(list)) {
//					for (TmAclDict dict : list) {
//						dictMap.put(dict.getCode(), dict.getCodeName());
//					}
//				}
//				return dictMap;
//			}
//		}

		return new LinkedHashMap<Object, Object>();
	}

	/**
	 * 根据表实体，获取表数据的map
	 * 
	 * @param arguments
	 * 
	 * arguments[0] 方法名 arguments[1] 表对应的实体类（需包含包名）。
	 * 例子：com.jjb.acl.infrastructure.TmAclDict arguments[2]
	 * 查询对象，json对象。为空需要传入 {} arguments[3] keyField arguments[4]
	 * valueField
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, String>> tableMapList(List arguments) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (!argsSizeLess(arguments, 3)) {
			return null;
		}
		String argType = arguments.get(1).toString();
		String filters = arguments.get(2).toString();
//		if(StringUtils.isNotEmpty(filters)){
//			LinkedHashMap<Object, Object> map = cacheContext.getSimpleProductCardFaceLinkedMap(filters);
//			for(Map.Entry<Object, Object> entry:map.entrySet()){
//				Map m = new HashMap<String, String>();
//				m.put("key", entry.getKey());		
//				m.put("value", entry.getValue());
//				list.add(m);
//			}
//		}
		return list;
	}

	/**
	 * 根据表实体，获取表数据的map
	 * 
	 * @param arguments
	 * 
	 * arguments[0] 方法名 arguments[1] 表对应的实体类（需包含包名）。
	 * 例子：com.jjb.acl.infrastructure.TmAclDict arguments[2]
	 * 查询对象，json对象。为空需要传入 {} arguments[3] keyField arguments[4]
	 * valueField
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getCardFaceCode(List arguments) {
		if (!argsSizeLess(arguments, 3)) {
			return null;
		}
		String argType = arguments.get(1).toString();
		String filters = arguments.get(2).toString();
		return "1101";
	}

}
