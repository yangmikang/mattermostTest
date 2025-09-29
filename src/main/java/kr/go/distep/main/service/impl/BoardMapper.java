package kr.go.distep.main.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.go.distep.main.service.vo.BoardVo;

@Mapper
public interface BoardMapper {

	public List<BoardVo> list(Map<String, Object> map);

	public int getTotal(Map<String, Object> map);

	public BoardVo detail(BoardVo boardVo);

	public int boardInsert(BoardVo boardVo);

	public int updateBd(BoardVo boardVo);

	public int deleteBd(BoardVo boardVo);

}
