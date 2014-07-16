package com.vms.utils;

import java.sql.Clob;
import java.sql.SQLException;


public class StringUtil {
	public static String processKeyword(String keyword) {
		keyword = keyword.trim();
		keyword = keyword.replace('*', '%');
		return keyword;
	}
	public static String clobToString(Clob clob) {
		String result = "";
		try {
			result = clob.getSubString(1, (int) clob.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: Loi doc Clob!");
			e.printStackTrace();
		}
		return result;
	}

	public static boolean notEmpty(String src) {
		if (src == null)
			return false;
		if (src=="")
			return false;
		return true;
	}
	public static String getUnsignedString(String s) {
		StringBuffer unsignedString = new StringBuffer(1024);	
        for (int i = 0; i < s.length(); i++) {
            unsignedString.append(getUnsignedChar(s.charAt(i)));
        }
        return unsignedString.toString();
		/*StringBuffer unsignedString = new StringBuffer();
		HashMap hash = new HashMap();
		hash.put("ấ" ,"A");
		hash.put("ẩ","A");
		hash.put("ẫ","A");
		hash.put("ậ","A");
		hash.put("â","A");
		hash.put("ắ","A");
		hash.put("ằ","A");
		hash.put("ẳ","A");
		hash.put("ẵ","A");
		hash.put("ặ","A");
		hash.put("ă","A");
		hash.put("á","A");
		hash.put("à","A");
		hash.put("ả","A");
		hash.put("ã","A");
		hash.put("ạ","A");
		
		hash.put("Ấ" ,"A");
		hash.put("Ẩ","A");
		hash.put("Ẫ","A");
		hash.put("Ậ","A");
		hash.put("Â","A");
		hash.put("Ắ","A");
		hash.put("Ằ","A");
		hash.put("Ẳ","A");
		hash.put("Ẵ","A");
		hash.put("Ặ","A");
		hash.put("Ă","A");
		hash.put("Á","A");
		hash.put("À","A");
		hash.put("Ả","A");
		hash.put("Ã","A");
		hash.put("Ạ","A");
		
		hash.put("ế" ,"E");
		hash.put("ề" ,"E");
		hash.put("ể" ,"E");
		hash.put("ễ" ,"E");
		hash.put("ệ" ,"E");
		hash.put("ê" ,"E");
		hash.put("é" ,"E");
		hash.put("è" ,"E");
		hash.put("ẻ" ,"E");
		hash.put("ẽ" ,"E");
		hash.put("ẹ" ,"E");
		
		hash.put("Ế" ,"E");
		hash.put("Ề" ,"E");
		hash.put("Ể" ,"E");
		hash.put("Ễ" ,"E");
		hash.put("Ệ" ,"E");
		hash.put("Ê" ,"E");
		hash.put("É" ,"E");
		hash.put("È" ,"E");
		hash.put("Ẻ" ,"E");
		hash.put("Ẽ" ,"E");
		hash.put("Ẹ" ,"E");
		
		hash.put("í" ,"I");
		hash.put("ì" ,"I");
		hash.put("ỉ" ,"I");
		hash.put("ĩ" ,"I");
		hash.put("ị" ,"I");
		
		hash.put("Í" ,"I");
		hash.put("Ì" ,"I");
		hash.put("Ỉ" ,"I");
		hash.put("Ĩ" ,"I");
		hash.put("Ị" ,"I");
		
		hash.put("ố" ,"O");
		hash.put("ồ" ,"O");
		hash.put("ổ" ,"O");
		hash.put("ỗ" ,"O");
		hash.put("ộ" ,"O");
		hash.put("ớ" ,"O");
		hash.put("ờ" ,"O");
		hash.put("ở" ,"O");
		hash.put("ỡ" ,"O");
		hash.put("ợ" ,"O");
		hash.put("ó" ,"O");
		hash.put("ò" ,"O");
		hash.put("ỏ" ,"O");
		hash.put("õ" ,"O");
		hash.put("ọ" ,"O");
		hash.put("ô" ,"O");
		hash.put("ơ" ,"O");
		
		hash.put("Ố" ,"O");
		hash.put("Ồ" ,"O");
		hash.put("Ổ" ,"O");
		hash.put("Ỗ" ,"O");
		hash.put("Ộ" ,"O");
		hash.put("Ớ" ,"O");
		hash.put("Ờ" ,"O");
		hash.put("Ở" ,"O");
		hash.put("Ỡ" ,"O");
		hash.put("Ợ" ,"O");
		hash.put("Ó" ,"O");
		hash.put("Ò" ,"O");
		hash.put("Ỏ" ,"O");
		hash.put("Õ" ,"O");
		hash.put("Ọ" ,"O");
		hash.put("Ô" ,"O");
		hash.put("Ơ" ,"O");
		
		hash.put("ứ" ,"U");
		hash.put("ừ" ,"U");
		hash.put("ử" ,"U");
		hash.put("ữ" ,"U");
		hash.put("ự" ,"U");
		hash.put("ư" ,"U");
		hash.put("ú" ,"U");
		hash.put("ù" ,"U");
		hash.put("ủ" ,"U");
		hash.put("ũ" ,"U");
		hash.put("ụ" ,"U");
		
		hash.put("Ứ" ,"U");
		hash.put("Ừ" ,"U");
		hash.put("Ử" ,"U");
		hash.put("Ữ" ,"U");
		hash.put("Ự" ,"U");
		hash.put("Ư" ,"U");
		hash.put("Ú" ,"U");
		hash.put("Ù" ,"U");
		hash.put("Ủ" ,"U");
		hash.put("Ũ" ,"U");
		hash.put("Ụ" ,"U");
		
		hash.put("ý" ,"Y");
		hash.put("ỳ" ,"Y");
		hash.put("ỷ" ,"Y");
		hash.put("ỹ" ,"Y");
		hash.put("ỵ" ,"Y");
		
		hash.put("Ý" ,"Y");
		hash.put("Ỳ" ,"Y");
		hash.put("Ỷ" ,"Y");
		hash.put("Ỹ" ,"Y");
		hash.put("Ỵ" ,"Y");
		
		hash.put("đ" ,"D");
		hash.put("Đ" ,"D");
				
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			String sAppend = String.valueOf(c);
			if(hash.containsKey(sAppend)) {
				sAppend = (String) hash.get(sAppend);
			} 
			unsignedString.append(sAppend);
			//System.out.println("kk");
		}
		return unsignedString.toString();*/
	}

	public static char getUnsignedChar(char c) {
		
		if (c=='\u00E1'||c=='\u00E0'||c=='\u1EA3'||c=='\u00E3'||c=='\u1EA1'||
                c=='\u0103'||c=='\u1EAF'||c=='\u1EB1'||c=='\u1EB3'||c=='\u1EB5'||c=='\u1EB7'||
                c=='\u00E2'||c=='\u1EA5'||c=='\u1EA7'||c=='\u1EA9'||c=='\u1EAB'||c=='\u1EAD') {
                return 'a';
            } else if (c=='\u00C1'||c=='\u00C0'||c=='\u1EA2'||c=='\u00C3'||c=='\u1EA0'||
                c=='\u0102'||c=='\u1EAE'||c=='\u1EB0'||c=='\u1EB2'||c=='\u1EB4'||c=='\u1EB6'||
                c=='\u00C2'||c=='\u1EA4'||c=='\u1EA6'||c=='\u1EA8'||c=='\u1EAA'||c=='\u1EAC') {
                return 'A';
            } else if (c=='\u00E9'||c=='\u00E8'||c=='\u1EBB'||c=='\u1EBD'||c=='\u1EB9'||
                c=='\u00EA'||c=='\u1EBF'||c=='\u1EC1'||c=='\u1EC3'||c=='\u1EC5'||c=='\u1EC7') {
                return 'e';
            } else if (c=='\u00C9'||c=='\u00C8'||c=='\u1EBA'||c=='\u1EBC'||c=='\u1EB8'||
                c=='\u00CA'||c=='\u1EBE'||c=='\u1EC0'||c=='\u1EC2'||c=='\u1EC4'||c=='\u1EC6') {
                return 'E';
            } else if (c=='\u00ED'||c=='\u00EC'||c=='\u1EC9'||c=='\u0129'||c=='\u1ECB') {
                return 'i';
            } else if (c=='\u00CD'||c=='\u00CC'||c=='\u1EC8'||c=='\u0128'||c=='\u1ECA') {
                return 'I';
            } else if (c=='\u00F3'||c=='\u00F2'||c=='\u1ECF'||c=='\u00F5'|c=='\u1ECD'||
                c=='\u00F4'||c=='\u1ED1'||c=='\u1ED3'||c=='\u1ED5'||c=='\u1ED7'||c=='\u1ED9'||
                c=='\u01A1'||c=='\u1EDB'||c=='\u1EDD'||c=='\u1EDF'||c=='\u1EE1'||c=='\u1EE3') {
                return 'o';
            } else if (c=='\u00D3'||c=='\u00D2'||c=='\u1ECE'||c=='\u00D5'|c=='\u1ECC'||
                c=='\u00D4'||c=='\u1ED0'||c=='\u1ED2'||c=='\u1ED4'||c=='\u1ED6'||c=='\u1ED8'||
                c=='\u01A0'||c=='\u1EDA'||c=='\u1EDC'||c=='\u1EDE'||c=='\u1EE0'||c=='\u1EE2') {
                return 'O';
            } else if (c=='\u00FA'||c=='\u00F9'||c=='\u1EE7'||c=='\u0169'|c=='\u1EE5'||
                c=='\u01B0'||c=='\u1EE9'||c=='\u1EEB'||c=='\u1EED'||c=='\u1EEF'||c=='\u1EF1') {
                return 'u';
            } else if (c=='\u00DA'||c=='\u00D9'||c=='\u1EE6'||c=='\u0168'|c=='\u1EE4'||
                c=='\u01AF'||c=='\u1EE8'||c=='\u1EEA'||c=='\u1EEC'||c=='\u1EEE'||c=='\u1EF0') {
                return 'U';
            } else if (c=='\u00FD'||c=='\u1EF3'||c=='\u1EF7'||c=='\u1EF9'||c=='\u1EF5') {
                return 'y';
            } else if (c=='\u00DD'||c=='\u1EF2'||c=='\u1EF6'||c=='\u1EF8'||c=='\u1EF4') {
                return 'Y';
            } else if(c=='\u0111'){
                return 'd';
            }else if(c=='\u0110'){
                return 'D';
            }
            return c;
	}
	public static String TrimTagHTML(String htmlString) {
		//return src.replaceAll("\\<.*?\\>", "");
		 // Remove HTML tag from java String
		int i=htmlString.indexOf("<![CDATA[");
		System.out.println(i);
		if(i>=0) {
			
			htmlString = htmlString.substring(i+9, htmlString.length()-3);
		}
        String noHTMLString = htmlString.replaceAll("\\<.*?\\>", "");

        // Remove Carriage return from java String
        noHTMLString = noHTMLString.replaceAll("\r", "<br/>");

        // Remove New line from java string and replace html break
        noHTMLString = noHTMLString.replaceAll("\n", " ");
        noHTMLString = noHTMLString.replaceAll("\'", "&#39;");
        noHTMLString = noHTMLString.replaceAll("\"", "&quot;");
/*        if(i>=0) {
        	noHTMLString = "<![CDATA[" + noHTMLString + "]]>";
        }*/
        return noHTMLString;
	}
	public static String RemoveStopWords(String htmlString) {
		String regex = "\\b(a ha|a lô|à ơi|á|à|á à|ạ|ạ ơi|ai|ai ai|ai nấy|ái|ái chà|ái dà|alô|amen|áng|ào|ắt|ắt hẳn|ắt là|âu là|ầu ơ|ấy|bài|bản|bao giờ|bao lâu|bao nả|bao nhiêu|bay biến|bằng|bằng ấy|bằng không|bằng nấy|bắt đầu từ|bập bà|bập bõm|bập bõm|bất chợt|bất cứ|bất đồ|bất giác|bất kể|bất kì|bất kỳ|bất luận|bất nhược|bất quá|bất|thình lình|bất tử|bây bẩy|bây chừ|bây giờ|bây giờ|bây nhiêu|bấy|bấy giờ|bấy chầy|bấy chừ|bấy giờ|bấy lâu|bấy lâu nay|bấy nay|bấy nhiêu|bèn|béng|bển|bệt|biết bao|biết bao nhiêu|biết chừng nào|biết đâu|biết đâu chừng|biết đâu đấy|biết mấy|bộ|bội phần|bông|bỗng|bỗng chốc|bỗng dưng|bỗng đâu|bỗng không|bỗng nhiên|bỏ mẹ|bớ|bởi|bởi chưng|bởi nhưng|bởi thế|bởi vậy|bởi vì|bức|cả|cả thảy|cái|các|cả thảy|cả thể|càng|căn|căn cắt|cật lực|cật sức|cây|cha |cha chả|chành chạnh|chao ôi|chắc|chắc hẳn|chắc chắn|chăng|chẳng lẽ|chẳng những|chẳng nữa|chẳng phải|chậc|chầm chập|chết nỗi|chết tiệt|chết thật|chí chết|chỉn|chính|chính là|chính thị|chỉ|chỉ do|chỉ là|chỉ tại|chỉ vì|chiếc|cho đến|cho đến khi|cho nên|cho|tới|cho tới khi|choa|chốc chốc|chớ|chớ chi|chợt|chú|chu cha|chú mày|chú mình|chui cha|chùn chùn|chùn chũn|chủn|chung cục|chung qui|chung quy|chung quy lại|chúng mình|chúng ta|chúng tôi|chứ|chứ lị|có chăng là|có dễ|có vẻ|cóc khô|coi bộ|coi mòi|con|còn|cô|cô mình|cổ lai|công nhiên|cơ|cơ chừng|cơ hồ|cơ mà|cơn|cu cậu|của|cùng|cùng cực|cùng nhau|cùng với|cũng|cũng như|cũng vậy|cũng vậy thôi|cứ|cứ việc|cực kì|cực kỳ|cực lực|cuộc|cuốn|dào|dạ|dần dà|dần dần|dầu sao|dẫu|dẫu sao|dễ sợ|dễ thường|do|do vì|do đó|do vậy|dở chừng|dù cho|dù rằng|duy|dữ|dưới|đã|đại để|đại loại|đại nhân|đại phàm|đang|đáng lẽ|đáng lí|đáng lý|đành đạch|đánh đùng|đáo để|nấy|nên chi|nền|nếu|nếu như|ngay|ngay cả|ngay lập tức|ngay lúc|ngay khi|ngay từ|ngay tức khắc|ngày càng|ngày ngày|ngày xưa|ngày xửa|ngăn ngắt|nghe chừng|nghe đâu|nghen|nghiễm nhiên|nghỉm|ngõ hầu|ngoải|ngoài|ngôi|ngọn|ngọt|ngộ nhỡ|ngươi|nhau|nhân dịp|nhân tiện|nhất|nhất đán|nhất định|nhất loạt|nhất luật|nhất mực|nhất nhất|nhất quyết|nhất sinh|nhất tâm|nhất tề|nhất thiết|nhé|nhỉ|nhiên hậu|nhiệt liệt|nhón nhén|nhỡ ra|nhung nhăng|như|như chơi|như không|như quả|như|thể|như tuồng|như vậy|nhưng|nhưng mà|những|những ai|những như|nhược bằng|nó|nóc|nọ|nổi|nớ|nữa|nức nở|oai oái|oái|ô hay|ô hô|ô kê|ô kìa|ồ|ôi chao|ôi thôi|ối dào|ối giời|ối|giời ơi|ôkê|ổng|ơ|ơ hay|ơ kìa|ờ|ớ|ơi|phải|phải chi|phải chăng|phăn phắt|phắt|phè|phỉ phui|pho|phóc|phỏng|phỏng như|phót|phốc|phụt|phương chi|phứt|qua quít|qua quýt|quả|quả đúng|quả là|quả tang|quả thật|quả tình|quả vậy|quá|quá chừng|quá độ|quá đỗi|quá lắm|quá sá|quá thể|quá trời|quá ư|quá xá|quý hồ|quyển|quyết|quyết nhiên|ra|ra phết|ra trò|ráo|ráo trọi|rày|răng|rằng|rằng là|rất|rất chi là|rất đỗi|rất mực|ren rén|rén|rích|riệt|riu ríu|rón rén|rồi|rốt cục|rốt cuộc|rút cục|rứa|sa sả|sạch|sao|sau chót|sau cùng|sau cuối|sau đó|sắp|sất|sẽ|sì|số là|sốt|sột|sở dĩ|suýt|sự|tà tà|tại|tại vì|tấm|tấn|tự vì|tanh|tăm tắp|tắp|tắp lự|tất cả|tất tần tật|tất tật|tất thảy|tênh|tha hồ|thà|thà là|thà rằng|thái quá|than ôi|thanh|thành ra|thành thử|thảo hèn|thảo nào|thậm|thậm chí|thật lực|thật vậy|thật ra|thẩy|thế|thế à|thế là|thế mà|thế nào|thế nên|thế ra|thế thì|thếch|thi thoảng|thì|thình lình|thỉnh thoảng|thoạt|thoạt nhiên|thoắt|thỏm|thọt|thốc|thốc tháo|thộc|thôi|thốt|thuần|thục mạng|thúng thắng|thửa|thực ra|thực vậy|thương ôi|tiện thể|tiếp đó|tiếp theo|tít mù|tỏ ra|tỏ vẻ|tò te|toà|toé khói|toẹt|tọt|tốc tả|tôi|tối ư|tông tốc|tột|tràn cung mây|trên|trển|trệt|trếu tráo|trệu trạo|trong|trỏng|trời đất ơi|trước|trước đây|trước đó|trước kia|trước nay|trước tiên|trừ phi|tù tì|tuần tự|tuốt luốt|tuốt tuồn tuột|tuốt tuột|tuy|tuy nhiên|tuy rằng|tuy thế|tuy vậy|tuyệt nhiên|từng|tức thì|tức tốc|tựu trung|ủa|úi|úi chà|úi dào|ư|ứ hự|ứ ừ|ử|ừ|và|vả chăng|vả lại|vạn|nhất|văng tê|vẫn|vâng|vậy|vậy là|vậy thì|veo|veo veo|vèo|về|vì|vì chưng|vì thế|vì vậy|ví bằng|ví dù|ví phỏng||ví thử|vị tất|vô hình trung|vô kể|vô luận|vô vàn|vốn dĩ|với|với lại|vở|vung tàn tán|vung tán|tàn|vung thiên địa|vụt|vừa mới|xa xả|xăm xăm|xăm xắm|xăm xúi|xềnh xệch|xệp|xiết bao|xoành xoạch|xoẳn|xoét|xoẹt|xon xón|xuất kỳ bất ý|xuể|xuống|ý|ý chừng|ý da)\\b";
		String result = htmlString.replaceAll(regex, "");
		int i=0;
		StringBuffer sb = new StringBuffer(1024);
		
		while(i<result.length()) {
			char c = result.charAt(i);
			sb.append(c);
			if(c==' ' ) {
				while(i+1<result.length() && result.charAt(i+1)==' ') {
					i++;
				}
			}
			i++;
		}
		return sb.toString();
		
	}
	public static void main(String arg[]) {
		String src ="  Toi  a ha   Long    ";
		long l1 = System.currentTimeMillis();		
		String str = RemoveStopWords(src);		
		System.out.println("test1 =" +(System.currentTimeMillis() - l1));
		l1 = System.currentTimeMillis();		
		System.out.println("test2 =" +(System.currentTimeMillis() - l1));
		System.out.println(str);
		System.out.println("Done!");
    }
}