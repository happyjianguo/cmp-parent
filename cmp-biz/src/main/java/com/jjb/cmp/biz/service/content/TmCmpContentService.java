package com.jjb.cmp.biz.service.content;

import java.util.List;

import com.jjb.cmp.dto.TmCmpContentDto;
import com.jjb.cmp.infrastructure.TmCmpContent;
import com.jjb.unicorn.facility.model.Page;

/**
 * @ClassName ImageContentService
 * @Description TODO 影像Service
 * @Author smh
 * Date 2018/12/31 11:56
 * Version 1.0
 */
public interface TmCmpContentService {
    List<TmCmpContent> getTmCmpContentByBatchNo(String batchNo);

    TmCmpContent getTmCmpContent(TmCmpContent tmCmpContent);

    List<TmCmpContent> getTmCmpContents(TmCmpContent tmCmpContent);

    void updateTmCmpContent(TmCmpContent tmCmpContent);

    void saveTmCmpContent(TmCmpContent tmCmpContent);
	Page<TmCmpContentDto> queryImageList(Page<TmCmpContentDto> page);

	/**
	 * 根据条件查询内容清单
	 *
	 * @param contentDto
	 * @return
	 */
	List<TmCmpContentDto> quyContentByParam(TmCmpContentDto contentDto);
}
