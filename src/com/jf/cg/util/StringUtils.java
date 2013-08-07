package com.jf.cg.util;


public class StringUtils {

	public static void main(String[] args) {
		System.out.println(formatAndNoPrefix("KB_CARD"));
	}
	/**
	 * format database type into java type, eg format "card_type" into "cardType"
	 * @param string
	 * @return
	 */
	public static String format(String string){
		StringBuilder sb = new StringBuilder();
		char[] cArr = string.trim().toLowerCase().toCharArray();
		for(int i=0;i<cArr.length;i++){
			char c = cArr[i];
			if(c=='_'){
				i++;
                sb.append(Character.toUpperCase(cArr[i]));
			}else{
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
	public static String formatAndNoPrefix(String tableName){
		String noPrefixTableName = tableName.toLowerCase().replaceFirst(PropertiesUtils.getTablePrefix(), "");
		noPrefixTableName = format(noPrefixTableName);
		return noPrefixTableName;
	}
	public static String firstUpper(String string){
		String str = format(string);
		str = str.substring(0, 1).toUpperCase()+str.substring(1);
		return str;
	}
	public static String firstUpperAndNoPrefix(String tableName){
		String noPrefixTableName = tableName.toLowerCase().replaceFirst(PropertiesUtils.getTablePrefix(), "");
		noPrefixTableName = firstUpper(noPrefixTableName);
		return noPrefixTableName;
	}
	public static String firstUpperNoFormat(String string){
		String str = string.substring(0, 1).toUpperCase()+string.substring(1);
		return str;
	}
}
