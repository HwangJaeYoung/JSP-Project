package frame.biz;

import java.util.ArrayList;

public interface Biz
{
	public void register(Object obj);
	
	public void modify(Object obj);
	
	public void remove(Object obj);
	
	public Object select(Object obj); // 세부사항 선택
	
	public ArrayList<Object> select(); // 전체사항 선택
}