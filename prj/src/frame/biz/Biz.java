package frame.biz;

import java.util.ArrayList;

public interface Biz
{
	public void register(Object obj);
	
	public void modify(Object obj);
	
	public void remove(Object obj);
	
	public Object select(Object obj); // ���λ��� ����
	
	public ArrayList<Object> select(); // ��ü���� ����
}