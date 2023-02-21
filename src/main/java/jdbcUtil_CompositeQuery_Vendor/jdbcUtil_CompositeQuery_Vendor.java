package jdbcUtil_CompositeQuery_Vendor;
import java.util.*;
public class jdbcUtil_CompositeQuery_Vendor {

	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;

		if ("ven_id".equals(columnName) || "ven_taxnum".equals(columnName) || "ven_status".equals(columnName) || "ven_score".equals(columnName)) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("ven_name".equals(columnName) || "ven_location".equals(columnName)) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
			}
		}
		
		return whereCondition.toString();
	}

}

