package egovframework.bopr.com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class StringUtl {
	
	/**
	 * target String 객체를 regex 구분자로 구분하여 List<String> 형태로 리턴
	 * @param target String
	 * @param regex String
	 * @return List<String>
	 */
	static public List<String> stringToList(String target, String regex)
	{
		List<String> returnList = new ArrayList<String>();
		
		if (StringUtils.hasText(target) && StringUtils.hasText(regex))
		{
			String [] strArray = StringUtils.delimitedListToStringArray(target, regex);
			
			for (int index = 0; index < strArray.length; index++)
			{
				String strPart = strArray[index].trim();
				
				if (!StringUtils.hasText(strPart))
				{
					continue;
				}
				
				returnList.add(strPart);
			}
		}
		else
		{
			returnList.add(target);
		}
		
		return returnList;
	}
	
	/**
	 * strList List<String> 객체의 Element 중에서 removeStr String 객체와 일치하는 Element remove
	 * @param strList List<String>
	 * @param removeStr String
	 * @return List<String>
	 */
	static public List<String> removeStringFromList(List<String> strList, String removeStr)
	{
		if (CollectionUtils.isEmpty(strList) || strList.size() <= 0 || !StringUtils.hasText(removeStr))
		{
			return strList;
		}
		else
		{
			for (int index = strList.size()-1; index >= 0; index--)
			{
				if (removeStr.equals(strList.get(index)))
				{
					strList.remove(index);
				}
			}
		}
		
		return strList;
	}
}
