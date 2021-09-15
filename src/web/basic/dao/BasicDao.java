package web.basic.dao;

import java.util.List;

public interface BasicDao<K, V> {
	int insert(K vo);
	int deleteByKey(V id);
	int updateByKey(K vo);
	K selectByKey(V id);
	List<K> selectAll();
}