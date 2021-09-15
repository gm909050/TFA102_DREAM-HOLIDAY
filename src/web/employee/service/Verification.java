package web.employee.service;

public class Verification {
	
	
	public StringBuffer genAuthCode(char array[]) {
		//設定值 char 48~57 = 0~9
		//設定值 char 65~90 = A~Z
		//設定值 char 97~123 = a~z
		char x = 48;
		char q = 48;
		char r = 48;
		char y = 65;
		char z = 97;
			//陣列0~9 給值0~9
			for(int i = 0 ;i<10;i++) {
				array[i] = x;
				x++;
			}
			//陣列10~35 給值A~Z
			for(int i = 10;i<36;i++) {
				array[i] = y;
				y++;
			}
			//陣列ˇ36~61 給值a~z
			for(int i = 36;i<62;i++) {
				array[i] = z;
				z++;
			}
			//陣列62~71 給值0~9
			for(int i = 62 ;i<72;i++) {
				array[i] = q;
				q++;
			}
			//陣列0~9 給值0~9
			for(int i = 72 ;i<81;i++) {
				array[i] = r;
				r++;
			}
			StringBuffer sb = new StringBuffer();
			//陣列帶入迴圈8次
			for(int i = 0 ; i<8;i++) {
				int a = (int)(Math.random()*81+1);
				sb.append((char)array[a]);
				
			}
			return sb;
	}
	
}
