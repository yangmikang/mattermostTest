/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kr.go.distep.cmmn.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * @Class Name : ImagePaginationRenderer.java
 * @Description : ImagePaginationRenderer Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class EgovDashboardPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	public EgovDashboardPaginationRenderer() {
		// no-op
	}

	/**
	* PaginationRenderer
	*
	* @see 개발프레임웍크 실행환경 개발팀
	*/
	public void initVariables() {

		firstPageLabel = "<li><a href=\"javascript:void(0);\" onclick=\"{0}({1}, this); return false;\" class=\"first\">처음</a>&#160;</li>";
		previousPageLabel = "<li><a href=\"javascript:void(0);\" onclick=\"{0}({1}, this); return false;\" class=\"prev\">이전</a>&#160;</li>";
		currentPageLabel = "<li class=\"act\"><a href=\"javascript:void(0);\" onclick=\"return false;\">{0}</a>&#160;</li>";
		otherPageLabel = "<li><a href=\"javascript:void(0);\" onclick=\"{0}({1}, this); return false;\">{2}</a>&#160;</li>";
		nextPageLabel = "<li><a href=\"javascript:void(0);\" onclick=\"{0}({1}, this); return false;\" class=\"next\">다음</a>&#160;</li>";
		lastPageLabel = "<li><a href=\"javascript:void(0);\" onclick=\"{0}({1}, this); return false;\" class=\"last\">끝</a>&#160;</li>";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		initVariables();
	}
	
	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		if (paginationInfo.getTotalRecordCount() == 0){
			return "";
		}
		
		return super.renderPagination(paginationInfo, jsFunction);
	}
}
