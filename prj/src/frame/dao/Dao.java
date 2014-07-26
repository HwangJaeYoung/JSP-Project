package frame.dao;

import java.util.ArrayList;

public interface Dao
{
	public abstract void insert(Object obj) throws Exception; 
	
	public abstract void update(Object obj) throws Exception;
	
	public abstract void delete(Object obj) throws Exception;
	
	public abstract Object select(Object obj) throws Exception; // 세부사항 선택
	
	public abstract ArrayList<Object> select() throws Exception; // 전체사항 선택
}