package frame.dao;

import java.util.ArrayList;

public interface Dao
{
	public abstract void insert(Object obj) throws Exception; 
	
	public abstract void update(Object obj) throws Exception;
	
	public abstract void delete(Object obj) throws Exception;
	
	public abstract Object select(Object obj) throws Exception; // ���λ��� ����
	
	public abstract ArrayList<Object> select() throws Exception; // ��ü���� ����
}